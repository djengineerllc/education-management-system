package com.ems.client.action.biz.syllabus.syllabusbyclass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.client.action.biz.syllabus.common.vo.SyllabusPlanVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusByClassAction extends DirectCrudAction {
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		
		List items = new ArrayList();
		
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("id", "2011");
		item.put("gradeId", "2011A");
		items.add(item);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public void printSyllbusPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String teamId = request.getParameter("teamId");
		
		Map rootVO = new HashMap();
		
		List<SyllabusPlanVO> items = new ArrayList<SyllabusPlanVO>();
		rootVO.put("items", items);
		
		String data = (String) DataTransformerHelper.transform("DT_print_syllabusbyclass", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
}
