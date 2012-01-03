package com.ems.client.action.login;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.auth.service.ILoginService;
import com.ems.client.action.login.vo.LoginInfoVO;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.RoleBO;
import conf.hibernate.UserBO;

@ActionScope(scope=Scope.SESSION)
public class LoginAction extends DirectAction {
	
	private LoginInfoVO loginInfoVO = null;
	
	@DirectFormPostMethod
	public ExtFormVO login(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
		String userName = formParameters.get("userName");
		String password = formParameters.get("password");
		
		ExtFormVO result = new ExtFormVO();
		
		ILoginService loginService = (ILoginService)this.getBean("loginService");
		try{
			UserBO userBO = loginService.login(userName, password);
			loginInfoVO = new LoginInfoVO();
			loginInfoVO.setUserName(userBO.getUserName());
//			loginInfoVO.setRoles(userBO.getRoleList());
			if(userBO.getRoleList().size() == 1){
				loginInfoVO.setCurrentRole(userBO.getRoleList().get(0));
			}else{
				result.addProp("selectRole", true);
			}
		}catch(Exception e){
			result.addError("userName", e.getMessage());
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
		RoleBO currentRole = null;
		for (RoleBO roleBO : loginInfoVO.getRoles()) {
			if (roleBO.getRoleCd().equals(role)) {
				currentRole = roleBO;
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