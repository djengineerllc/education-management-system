package com.ems.client.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ems.client.action.login.LoginAction;
import com.ems.client.action.login.vo.LoginInfoVO;
import com.ems.common.model.vo.DicVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.google.gson.JsonArray;
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
	public List<DicVO> getDicData(JsonArray params) {
		DicVO queryInfo = BeanUtils.toBeanFromJsonFirst(params, DicVO.class);
		String dicType = queryInfo.getType();
		
		List<DicVO> dicVOList = new ArrayList<DicVO>();
		if ("Grade".equals(dicType)) {
//			dicVOList.add(new DicVO(9, "Grade", "", "", "请选择"));
			dicVOList.add(new DicVO(10, "Grade", "2011", "2011", "2011级"));
			dicVOList.add(new DicVO(11, "Grade", "2012", "2012", "2012级"));
		} else if ("Class".equals(dicType)) {
			if ("2011".equals(queryInfo.getGroup())) {
				dicVOList.add(new DicVO(20, "Class", "2011A", "2011A", "2011A班"));
				dicVOList.add(new DicVO(21, "Class", "2011B", "2011B", "2011B班"));	
			} else {
				dicVOList.add(new DicVO(22, "Class", "2012A", "2012A", "2012A班"));
				dicVOList.add(new DicVO(23, "Class", "2012B", "2012B", "2012B班"));
			}
		} else if ("Sex".equals(dicType)) {
			dicVOList.add(new DicVO(30, "Sex", "S1", "1", "男"));
			dicVOList.add(new DicVO(31, "Sex", "S2", "2", "女"));
		} else if ("Project".equals(dicType)) {
			dicVOList.add(new DicVO(41, "Project", "S101", "101", "项目101"));
			dicVOList.add(new DicVO(42, "Project", "S102", "102", "项目102"));
		} else if ("Professional".equals(dicType)) {
			dicVOList.add(new DicVO(51, "Professional", "S10101", "10101", "专业01"));
			dicVOList.add(new DicVO(52, "Professional", "S10102", "10102", "专业02"));
		} else if ("StudentStatus".equals(dicType)) {
			dicVOList.add(new DicVO(61, "StudentStatus", "S1", "1", "在读"));
		}
		
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