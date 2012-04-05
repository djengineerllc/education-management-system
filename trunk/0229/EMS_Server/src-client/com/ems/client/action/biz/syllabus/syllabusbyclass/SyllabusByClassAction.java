package com.ems.client.action.biz.syllabus.syllabusbyclass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.client.action.biz.syllabus.common.vo.SyllabusPlanVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.model.vo.ClassVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ClassBO;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusByClassAction extends DirectCrudAction {

	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
//		 Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
//		 Integer termId = BeanUtils.toInteger(paramMap.get("termId"));
		ClassVO queryVO = BeanUtils.toBeanFromJsonFirst(params, ClassVO.class);
		 
		List<ClassBO> classBOList = basicInfoBS.findClassByVO(queryVO);
		
		return new ExtPagingVO(classBOList);
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
