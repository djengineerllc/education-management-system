package com.ems.client.action.biz.syllabus.syllabusplan;

import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusPlanAction extends DirectCrudAction {
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		return new ExtPagingVO();
	}
}
