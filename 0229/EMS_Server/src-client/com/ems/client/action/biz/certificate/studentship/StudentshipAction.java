package com.ems.client.action.biz.certificate.studentship;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.biz.stuMag.bs.IStudentManageBS;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
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
public class StudentshipAction extends DirectCrudAction {
	
	private IStudentManageBS studentManageBS = this.getBean("studentManageBS", IStudentManageBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		StudentBO paramBO = BeanUtils.toBeanFromJsonFirst(params, StudentBO.class);
		
		List<StudentBO> stuBOList = studentManageBS.findByStudentBO(paramBO);
		
		return new ExtPagingVO(stuBOList);
	}
	
	@DirectMethod
	public void printCert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String stuNo = request.getParameter("stuNo");
		
		StudentBO queryInfo = new StudentBO();
		queryInfo.setStuNo(stuNo);
		StudentBO stuBO = studentManageBS.findByStudentBO(queryInfo).get(0);
		
		Map<String, Object> rootVO = new HashMap<String, Object>();
		rootVO.put("stuInfo", stuBO);
		
		Date sysDate = DateUtil.currData();
		rootVO.put("sysDate", sysDate);
		rootVO.put("sysDateZH", DateUtil.formatZh(DateFormat.LONG, sysDate));
		rootVO.put("sysDateEN", DateUtil.formatEn(DateFormat.LONG, sysDate));
		
		String data = (String) DataTransformerHelper.transform("DT_print_certificate_studentship", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
}