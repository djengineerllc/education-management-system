package com.ems.client.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ems.client.action.login.LoginAction;
import com.ems.client.action.login.vo.LoginInfoVO;
import com.ems.common.model.vo.DicVO;
import com.ems.system.client.DirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class SystemAction extends DirectAction {
	
	@DirectMethod
	public Date getSystemTime() {
		return new Date();
	}
	
	@DirectMethod
	public List<DicVO> getDicData(String dicType) {
		List<DicVO> dicVOList = new ArrayList<DicVO>();
		dicVOList.add(new DicVO(1, "Sex", "S1", "1", "男"));
		dicVOList.add(new DicVO(2, "Sex", "S2", "2", "女"));
		
		return dicVOList;
	}
	
	@DirectMethod
	public LoginInfoVO getLoginInfo() {
		LoginAction loginAction = this.getAction(LoginAction.class);
		if (loginAction != null) {
			return loginAction.getLoginInfo();
		}
		
		return null;
	}
}