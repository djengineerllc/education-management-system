package com.ems.client.action.biz.samples.xgridsample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.client.action.biz.samples.common.vo.UserInfoVO;
import com.ems.client.action.biz.samples.xgridsample.vo.XGridSample1ViewQueryVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class XGridSampleAction extends DirectAction {
	
	@DirectMethod
	public ExtPagingVO getXGridSample1ViewData(JsonArray params) {
		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
		
		
		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
		userInfoVOList.add(new UserInfoVO("小萌", "1", "2011-10-10", "xm@163.com"));
		userInfoVOList.add(new UserInfoVO("小乐", "2", "2011-11-11", "xl@163.com"));
		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
		
		return new ExtPagingVO("userName", userInfoVOList);
//		throw new IllegalArgumentException("异常");
	}
	
	@DirectMethod
	public ExtFormVO getFormData(String userId) {
		System.out.println("getFormData userId = " + userId);
		
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserName("Chiknin");
		userInfoVO.setSex("1");
		userInfoVO.setBirthday("2011-11-11");
		userInfoVO.setEmail("chiknin@gmail.com");
		
		userInfoVO.setSexcheckboxgroup_S1("1");
		userInfoVO.setSexcheckboxgroup_S2("6");
		
		return new ExtFormVO(userInfoVO);
	}
	
	@DirectFormPostMethod
	public ExtFormVO submitFormData(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if ("chiknin".equalsIgnoreCase(userInfoVO.getUserName())) {
			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
		}
		
		return result;
	}
}