package com.ems.client.action.biz.certificate.graduate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.ems.client.action.biz.certificate.common.vo.CertQueryVO;
import com.ems.client.action.biz.certificate.common.vo.StudentInfoVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class GraduateAction extends DirectCrudAction {
	private static Map<Integer, StudentInfoVO> studentInfos = new HashMap<Integer, StudentInfoVO>();
	static {
		studentInfos.put(1, new StudentInfoVO(1, "0001", "小祝", "1", "2001", "2011A", "101", "10101", "1"));
		studentInfos.put(2, new StudentInfoVO(2, "0002", "小萌", "2", "2001", "2011A", "101", "10101", "1"));
	}
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		CertQueryVO queryVO = BeanUtils.toBeanFromJsonFirst(params, CertQueryVO.class);
		System.out.println("-------->" + ToStringBuilder.reflectionToString(queryVO));
		
		List<StudentInfoVO> items = new ArrayList<StudentInfoVO>();
		for (Map.Entry<Integer, StudentInfoVO> user : studentInfos.entrySet()) {
			items.add(user.getValue());
		}
		
		return new ExtPagingVO(items);
	}
	
	@DirectMethod
	public void printCert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String stuNo = request.getParameter("stuNo");
		Object rootVO = studentInfos.get(Integer.valueOf(stuNo));
		
		String data = (String) DataTransformerHelper.transform("DT_print_certificate_graduate", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
}
