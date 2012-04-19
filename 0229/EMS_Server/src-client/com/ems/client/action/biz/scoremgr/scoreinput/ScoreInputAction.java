package com.ems.client.action.biz.scoremgr.scoreinput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ems.biz.scoremgr.bs.IScoreMgrBS;
import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.client.action.biz.common.vo.StudentInfoVO;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.ScoreVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class ScoreInputAction extends DirectCrudAction {
	
	private IScoreMgrBS scoreMgrBS = this.getBean("scoreMgrBS", IScoreMgrBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		
		List<StudentInfoVO> items = new ArrayList<StudentInfoVO>();
		StudentInfoVO stu1 = new StudentInfoVO();
		stu1.setId(1);
		stu1.setStuName("A");
		stu1.setClassId(4);
		items.add(stu1);
		
		StudentInfoVO stu2 = new StudentInfoVO();
		stu2.setId(2);
		stu2.setStuName("B");
		stu2.setClassId(5);
		items.add(stu2);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public ExtPagingVO loadScoreDefaultList(JsonArray params) throws EMSException {
		JsonObject jsonObj = params.get(0).getAsJsonObject();
		ScoreQueryVO queryVO = new ScoreQueryVO();
		queryVO.setTermId(jsonObj.get("termId").getAsInt());
		queryVO.setClassId(jsonObj.get("classId").getAsInt());
		queryVO.setCourseNo(jsonObj.get("courseNo").getAsString());
		queryVO.setStuIds(BeanUtils.toBeanFromJson(jsonObj.get("stuIds").getAsJsonArray(), Integer.class));
		
		List<ScoreVO> items = scoreMgrBS.findScoreVOBy(queryVO);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public ExtFormVO submitScoreInputDetail(JsonArray params) {
		return new ExtFormVO();
	}
}