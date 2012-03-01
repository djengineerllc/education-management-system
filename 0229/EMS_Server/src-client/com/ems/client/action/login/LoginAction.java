package com.ems.client.action.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.client.action.login.vo.LoginInfoVO;
import com.ems.client.action.login.vo.UserRoleVO;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.SESSION)
public class LoginAction extends DirectAction {
	
	private LoginInfoVO loginInfoVO = null;
	
	@DirectFormPostMethod
	public ExtFormVO login(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
		String userName = formParameters.get("userName");
		String password = formParameters.get("password");
		
		ExtFormVO result = new ExtFormVO();
		
		if (!"admin".equalsIgnoreCase(userName) && !"test".equalsIgnoreCase(userName)) {
			result.addError("userName", "用户名不存在");
			return result;
		}
		
		loginInfoVO = new LoginInfoVO();
		loginInfoVO.setUserName(userName);
		
		if ("admin".equalsIgnoreCase(userName)) {
			List<UserRoleVO> roles = new ArrayList<UserRoleVO>();
			roles.add(new UserRoleVO(1, "admin"));
			loginInfoVO.setRoles(roles);
			loginInfoVO.setCurrentRole(roles.get(0));
		}
		
		if ("test".equalsIgnoreCase(userName)) {
			List<UserRoleVO> roles = new ArrayList<UserRoleVO>();
			roles.add(new UserRoleVO(1, "admin"));
			roles.add(new UserRoleVO(1, "student"));
			roles.add(new UserRoleVO(1, "teacher"));
			
			loginInfoVO.setRoles(roles);
			result.addProp("selectRole", true);
		}
		
		result.setData(loginInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public void logout() {
		this.getSession().invalidate();
	}
	
	@DirectMethod
	public ExtFormVO selectRole(String userName, String role) {
		UserRoleVO currentRole = null;
		for (UserRoleVO userRoleVO : loginInfoVO.getRoles()) {
			if (userRoleVO.getName().equals(role)) {
				currentRole = userRoleVO;
				break;
			}
		}
		
		loginInfoVO.setCurrentRole(currentRole);
		
		return ExtFormVO.success(loginInfoVO);
	}

	public LoginInfoVO getLoginInfo() {
		return loginInfoVO;
	}
}