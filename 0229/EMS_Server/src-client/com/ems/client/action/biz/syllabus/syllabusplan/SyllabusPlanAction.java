package com.ems.client.action.biz.syllabus.syllabusplan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ems.client.action.biz.syllabus.common.vo.SyllabusPlanVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtFormVO;
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
		item.put("teamId", "1");
		item.put("teamName", "2012年第一学期");
		item.put("syllabusState", "2");
		items.add(item);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public ExtPagingVO loadSyllabusPlanDetail(JsonArray params) {
		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		String teamId = paramMap.get("teamId");
		
		System.out.println(paramMap);
		
		List items = new ArrayList();
		
		SyllabusPlanVO spVO1 = new SyllabusPlanVO();
		spVO1.setId(1);
		spVO1.setLesson("12课");
		spVO1.setClassCode("12CP");
		spVO1.setOeInd("单");
		items.add(spVO1);
		
		SyllabusPlanVO spVO2 = new SyllabusPlanVO();
		spVO2.setId(2);
		spVO2.setLesson("12课");
		spVO2.setClassCode("12CP");
		spVO2.setOeInd("双");
		items.add(spVO2);
		
		SyllabusPlanVO spVO3 = new SyllabusPlanVO();
		spVO3.setId(3);
		spVO3.setLesson("12课");
		spVO3.setClassCode("12SCF");
		spVO3.setOeInd("单");
		items.add(spVO3);
		
		SyllabusPlanVO spVO4 = new SyllabusPlanVO();
		spVO4.setId(4);
		spVO4.setLesson("12课");
		spVO4.setClassCode("12SCF");
		spVO4.setOeInd("双");
		items.add(spVO4);
		
		SyllabusPlanVO spVO21 = new SyllabusPlanVO();
		spVO21.setLesson("34课");
		spVO21.setClassCode("12CP");
		spVO21.setOeInd("单");
		items.add(spVO21);
		
		SyllabusPlanVO spVO22 = new SyllabusPlanVO();
		spVO22.setLesson("34课");
		spVO22.setClassCode("12CP");
		spVO22.setOeInd("双");
		items.add(spVO22);
		
		SyllabusPlanVO spVO23 = new SyllabusPlanVO();
		spVO23.setLesson("34课");
		spVO23.setClassCode("12SCF");
		spVO23.setOeInd("单");
		items.add(spVO23);
		
		SyllabusPlanVO spVO24 = new SyllabusPlanVO();
		spVO24.setLesson("34课");
		spVO24.setClassCode("12SCF");
		spVO24.setOeInd("双");
		items.add(spVO24);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public ExtFormVO submitSyllabusPlanDetail(JsonArray params) {
//		List<SyllabusPlanVO> syllabusPlanVOList = BeanUtils.toBeanFromJson(.get("data"), SyllabusPlanVO.class);
//		
//		for (int i = 0; i < syllabusPlanVOList.size(); i++) {
//			syllabusPlanVOList.get(i).setId(i);
//		}
		
		return ExtFormVO.success();
	}
}
