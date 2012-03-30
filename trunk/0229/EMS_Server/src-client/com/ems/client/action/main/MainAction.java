package com.ems.client.action.main;

import java.util.List;

import com.ems.client.action.login.LoginAction;
import com.ems.common.model.vo.LoginInfoVO;
import com.ems.system.acl.bs.IRealmBS;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.MenuItemVO;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class MainAction extends DirectAction {
	
	private IRealmBS realmBS = this.getBean("realmBS", IRealmBS.class);

	@DirectMethod
	public List<MenuItemVO> getMenu(Integer menuId) {
		LoginInfoVO loginInfoVO = this.getAction(LoginAction.class).getLoginInfo();
		
		List<MenuItemVO> menu = realmBS.findMenuList(loginInfoVO.getUserId(), menuId);
		
		return menu;
	}
}