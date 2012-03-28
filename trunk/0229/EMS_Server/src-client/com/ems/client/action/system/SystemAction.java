package com.ems.client.action.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ems.client.action.login.LoginAction;
import com.ems.client.action.login.vo.LoginInfoVO;
import com.ems.common.code.Code;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.CodeTableBO;

@ActionScope(scope=Scope.APPLICATION)
public class SystemAction extends DirectAction {
	
	@DirectMethod
	public Date getSystemTime() {
		return new Date();
	}
	
	@DirectMethod
	public List<CodeTableBO> getDicData(JsonArray params) {
		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		String dicType = paramMap.get("type");
		String group = paramMap.get("group");
		
		List<CodeTableBO> codes = null;
		if (StringUtils.isNotEmpty(group)) {
			codes = Code.getCodes(dicType, group);
		} else {
			codes = Code.getCodes(dicType);
		}
		
		return codes;
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