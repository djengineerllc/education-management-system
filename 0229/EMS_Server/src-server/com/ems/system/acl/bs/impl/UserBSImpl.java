package com.ems.system.acl.bs.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.common.code.Code;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.LoginInfoVO;
import com.ems.common.util.MD5;
import com.ems.system.acl.bs.IUserBS;
import com.ems.system.client.vo.MenuItemVO;

import conf.hibernate.MenuInfoBO;
import conf.hibernate.RoleInfoBO;
import conf.hibernate.UserInfoBO;

@Service("userBS")
public class UserBSImpl implements IUserBS {

	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;
	
	public LoginInfoVO findLoginInfoVO(String loginName, String password) throws EMSException {
		
		String md5_pwd = MD5.MD5Encode(password);
		UserInfoBO userInfoBO = (UserInfoBO)commonDAO.firstEntity("FROM UserInfoBO bo WHERE bo.loginName = ? AND bo.password = ?", loginName, md5_pwd);
		
		LoginInfoVO loginInfoVO = null;
		if (userInfoBO != null) {
			loginInfoVO = new LoginInfoVO();
			loginInfoVO.setUserId(userInfoBO.getId());
			loginInfoVO.setUserName(userInfoBO.getUserName());
			loginInfoVO.setLoginName(userInfoBO.getLoginName());
			
			RoleInfoBO roleInfoBO = (RoleInfoBO)commonDAO.firstEntity("SELECT ri FROM UserRoleRelBO urr, RoleInfoBO ri WHERE urr.roleId = ri.id AND urr.userId = ?", userInfoBO.getId());
			loginInfoVO.setRoleId(roleInfoBO.getId());
			loginInfoVO.setRoleName(roleInfoBO.getRoleName());
			
			if (Code.eqValue("Role", "student", roleInfoBO.getRoleCd())) {
				// 学生  年级/班级/专业/项目/...
			}
			
			loginInfoVO.setCurrTerm("4"); // TODO
		}
		
		return loginInfoVO;
	}
	
	public List<MenuItemVO> findMenuList(Integer userId, Integer menuId) throws EMSException {
		List<MenuInfoBO> menuInfoBOList = commonDAO.findByHql("SELECT mi FROM UserRoleRelBO urr, RoleMenuRelBO rmr, MenuInfoBO mi WHERE urr.roleId = rmr.roleId AND rmr.menuId = mi.id AND urr.userId = ? AND mi.parentId = ? ORDER BY mi.parentId, mi.ordinal ASC", userId, menuId);
		
		List<MenuItemVO> menu = new ArrayList<MenuItemVO>();
		MenuItemVO itemVO = null;
		if (menuInfoBOList != null && menuInfoBOList.size() > 0) {
			for (MenuInfoBO menuInfoBO : menuInfoBOList) {
				itemVO = new MenuItemVO();
				itemVO.setId(menuInfoBO.getId().toString());
				itemVO.setText(menuInfoBO.getText());
				itemVO.setLeaf(Code.eqValue("Indicator", "S1", menuInfoBO.getLeaf()));
//				itemVO.setTextKey();
				itemVO.setModuleId(menuInfoBO.getModuleId());
				itemVO.setModuleName(menuInfoBO.getModuleName());
//				itemVO.setModuleNameKey();
//				itemVO.setModuleConfig();
				menu.add(itemVO);
			}
		}
		
		return menu;
	}
}