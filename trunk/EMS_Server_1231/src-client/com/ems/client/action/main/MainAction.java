package com.ems.client.action.main;

import java.util.List;

import com.ems.biz.auth.service.ILoginService;
import com.ems.client.action.login.LoginAction;
import com.ems.system.client.DirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.MenuBO;

@ActionScope(scope=Scope.APPLICATION)
public class MainAction extends DirectAction {

	
	
	@DirectMethod
	public List<MenuBO> getMenu(Integer menuId) {
		String roleCd = this.getAction(LoginAction.class).getLoginInfo().getCurrentRole().getRoleCd();
		return this.getLoginService().getMenuList(roleCd, menuId);
	}
	
	private ILoginService getLoginService(){
		return (ILoginService)this.getBean("loginService");
	}
}