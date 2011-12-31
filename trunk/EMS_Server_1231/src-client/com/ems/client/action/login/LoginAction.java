package com.ems.client.action.login;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.client.action.login.vo.LoginInfoVO;
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
		
		if (!"admin".equalsIgnoreCase(userName)) {
			result.addError("userName", "用户名错误");
			return result;
		}
		
		loginInfoVO = new LoginInfoVO();
		loginInfoVO.setUserName(userName);
		
		result.setDataFormObject(loginInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public void logout() {
		this.getSession().invalidate();
	}

	public LoginInfoVO getLoginInfo() {
		return loginInfoVO;
	}
}