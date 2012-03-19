package com.ems.client.action.biz.syllabus.syllabusplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		List items = new ArrayList();
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("teamName", "2012年第一学期");
		item.put("syllabusState", "2");
		items.add(item);
		
		return new ExtPagingVO(items);
	}
}
