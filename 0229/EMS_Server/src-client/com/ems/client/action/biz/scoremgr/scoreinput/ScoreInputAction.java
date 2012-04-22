package com.ems.client.action.biz.scoremgr.scoreinput;

import java.util.List;

import com.ems.biz.scoremgr.bs.IScoreMgrBS;
import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.biz.stuMag.bs.IStudentManageBS;
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

import conf.hibernate.ScoreBO;
import conf.hibernate.StudentBO;

@ActionScope(scope=Scope.APPLICATION)
public class ScoreInputAction extends DirectCrudAction {
	
	private IScoreMgrBS scoreMgrBS = this.getBean("scoreMgrBS", IScoreMgrBS.class);
	private IStudentManageBS studentManageBS = this.getBean("studentManageBS", IStudentManageBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
//		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		StudentBO paramBO = BeanUtils.toBeanFromJsonFirst(params, StudentBO.class);
		
		List<StudentBO> stuBOList = studentManageBS.findByStudentBO(paramBO);
		
		return new ExtPagingVO(stuBOList);
	}
	
	@DirectMethod
	public ExtPagingVO loadScoreDefaultList(JsonArray params) throws EMSException {
		ScoreQueryVO queryVO = new ScoreQueryVO();
		JsonObject jsonObj = params.get(0).getAsJsonObject();
		queryVO.setTermId(jsonObj.get("termId").getAsInt());
		queryVO.setClassId(jsonObj.get("classId").getAsInt());
		queryVO.setCourseNo(jsonObj.get("courseNo").getAsString());
		queryVO.setStuIds(BeanUtils.toBeanFromJson(jsonObj.get("stuIds").getAsJsonArray(), Integer.class));
		
		List<ScoreVO> items = scoreMgrBS.findScoreVOBy(queryVO);
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public ExtFormVO submitScoreInputDetail(JsonArray params) {
		JsonObject jsonObj = params.get(0).getAsJsonObject();
		Integer termId = jsonObj.get("termId").getAsInt();
		String courseNo = jsonObj.get("courseNo").getAsString();
		List<ScoreBO> scoreBOList = BeanUtils.toBeanFromJson(jsonObj.get("submitData").getAsJsonArray(), ScoreBO.class);
		
		for (ScoreBO scoreBO : scoreBOList) {
			scoreBO.setTermId(termId);
			scoreBO.setCourseNo(courseNo);
		}
		
		scoreMgrBS.submitScore(scoreBOList);
		
		return new ExtFormVO();
	}
}