package com.ems.client.action.biz.certificate.transcript;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.biz.scoremgr.bs.IScoreMgrBS;
import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.biz.stuMag.bs.IStudentManageBS;
import com.ems.client.action.login.LoginAction;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.model.vo.ScoreVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.DateUtil;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.StudentBO;

@ActionScope(scope=Scope.APPLICATION)
public class TranscriptAction extends DirectCrudAction {
	
	private IStudentManageBS studentManageBS = this.getBean("studentManageBS", IStudentManageBS.class);
	private IScoreMgrBS scoreMgrBS = this.getBean("scoreMgrBS", IScoreMgrBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		StudentBO paramBO = BeanUtils.toBeanFromJsonFirst(params, StudentBO.class);
		
		List<StudentBO> stuBOList = studentManageBS.findByStudentBO(paramBO);
		
		return new ExtPagingVO(stuBOList);
	}
	
	@DirectMethod
	public void printCert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer termId = BeanUtils.toInteger(request.getParameter("termId"));
		String stuNo = request.getParameter("stuNo");
		
		Map<String, Object> rootVO = new HashMap<String, Object>();
		rootVO.put("currUser", this.getAction(LoginAction.class).getLoginInfo());
		
		StudentBO stuQueryVO = new StudentBO();
		stuQueryVO.setStuNo(stuNo);
		StudentBO stuBO = studentManageBS.findByStudentBO(stuQueryVO).get(0);
		rootVO.put("stuInfo", stuBO);
		
		ScoreQueryVO scoQueryVO = new ScoreQueryVO();
		scoQueryVO.setTermId(termId);
		scoQueryVO.setStuNos(Arrays.asList(new String[] {stuNo}));
		List<ScoreVO> stuScoreList = scoreMgrBS.findScoreVOBy(scoQueryVO);
		rootVO.put("stuScoreList", stuScoreList);
		
		Date sysDate = DateUtil.currData();
		rootVO.put("sysDate", sysDate);
		rootVO.put("sysDateZH", DateUtil.formatZh(DateFormat.LONG, sysDate));
		rootVO.put("sysDateEN", DateUtil.formatEn(DateFormat.LONG, sysDate));
		
		String data = (String) DataTransformerHelper.transform("DT_print_certificate_transcript", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
}