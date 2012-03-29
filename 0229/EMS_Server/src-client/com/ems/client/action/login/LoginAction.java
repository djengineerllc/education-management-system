package com.ems.client.action.login;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.common.model.vo.LoginInfoVO;
import com.ems.system.acl.bs.IUserBS;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.SESSION)
public class LoginAction extends DirectAction {
	
	private IUserBS userBS = this.getBean("userBS", IUserBS.class);
	
	private LoginInfoVO loginInfoVO = null;
	
	@DirectFormPostMethod
	public ExtFormVO login(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
		String loginName = formParameters.get("loginName");
		String password = formParameters.get("password");
		
		ExtFormVO result = new ExtFormVO();
		if (result.isEmpty(loginName, "loginName", "用户名不能为空") 
				|| result.isEmpty(password, "password", "密码不能为空")) {
			return result;
		}
		
		loginInfoVO = userBS.findLoginInfoVO(loginName, password);
		if (loginInfoVO == null) {
			result.addError("password", "用户名不存在或密码错误");
			return result;
		}
		
		result.setData(loginInfoVO);
//		result.addProp("selectRole", true);
		
		return result;
	}
	
	@DirectMethod
	public void logout() {
		this.loginInfoVO = null;
		this.getSession().invalidate();
	}
	
	@DirectMethod
	public ExtFormVO selectRole(String userName, String role) {
//		UserRoleVO currentRole = null;
//		for (UserRoleVO userRoleVO : loginInfoVO.getRoles()) {
//			if (userRoleVO.getName().equals(role)) {
//				currentRole = userRoleVO;
//				break;
//			}
//		}
//		
//		loginInfoVO.setCurrentRole(currentRole);
		
		return ExtFormVO.success(loginInfoVO);
	}

	public LoginInfoVO getLoginInfo() {
		return loginInfoVO;
	}
}