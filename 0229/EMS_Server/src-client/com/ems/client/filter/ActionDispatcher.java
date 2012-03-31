package com.ems.client.filter;

import org.apache.commons.lang.StringUtils;

import com.ems.client.action.login.LoginAction;
import com.ems.system.client.DirectAction;
import com.softwarementors.extjs.djn.api.RegisteredMethod;
import com.softwarementors.extjs.djn.servlet.ssm.SsmDispatcher;
import com.softwarementors.extjs.djn.servlet.ssm.WebContextManager;

/**
 * @author chiknin
 */
public class ActionDispatcher extends SsmDispatcher {
	
	public static final String ACTION_AUTH_REGEX = "ems_system_.*|ems_main_.*|ems_biz_.*";
	
	@Override
	public Object dispatch(RegisteredMethod method, Object[] parameters) {
		
		this.checkAuth(method);
		
		return super.dispatch(method, parameters);
	}
	
	public void checkAuth(RegisteredMethod method) {
		String action = method.getActionName();
		if (StringUtils.isNotEmpty(action) && action.matches(ACTION_AUTH_REGEX)) {
			LoginAction loginAction = (LoginAction) WebContextManager.get().getSessionScopedObject(DirectAction.getActionName(LoginAction.class));
			if (loginAction == null || loginAction.getLoginInfo() == null) {
				throw new RuntimeException("{errorCode: 'AccessDenied', errorMsg: '访问拒绝，请您重新登录系统'}");
			}
		}
	}
}
