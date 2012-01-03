package com.ems.biz.auth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ems.biz.auth.service.ILoginService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;

import conf.hibernate.MenuBO;
import conf.hibernate.RoleBO;
import conf.hibernate.UserBO;

public class LoginServiceImpl implements ILoginService{

	private ICommonDAO commonDAO;
	
	public UserBO login(String loginName, String password) throws EMSException {
		List<UserBO> userList = commonDAO.findByHql("from " + UserBO.class.getName() + " bo where bo.loginName = '"+loginName+"'");
		if(userList != null && userList.size() == 1){
			UserBO userBO = userList.get(0);
			if(null != userBO && userBO.getPassword().equals(password)){
				return userBO;
			}
		}
		throw new EMSException("用户名或密码错误，请重新登陆！");
	}
	
	public List<MenuBO> changeToRole(String roleCd) throws EMSException {
		return getMenuList(roleCd, null);
	}

	public List<MenuBO> getMenuList(String roleCd, Integer menuId) throws EMSException {
		StringBuilder hql = new StringBuilder();
		hql.append("select m from " + MenuBO.class.getName() + " as m, ");
		hql.append(RoleBO.class.getName() + " as r where m in elements(r.menuList)");
		if(menuId == null || menuId < 0){
			hql.append(" and m.parentId is null");
		}else{
			hql.append(" and m.parentId = " + menuId);
		}
		hql.append(" and r.roleCd = '" + roleCd + "'");
		return commonDAO.findByHql(hql.toString());
	}
	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	

}
