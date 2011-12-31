package com.ems.client.action.biz.samples.formsample;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.ems.client.action.biz.samples.common.vo.UserInfoVO;
import com.ems.client.action.biz.samples.xgridsample.XGridSampleAction;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class FormSampleAction extends DirectAction {
	
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
		
		System.out.println("-------->" + this.getAction(XGridSampleAction.class));
		
		return new ExtFormVO(userInfoVO);
	}
	
	@DirectFormPostMethod
	public ExtFormVO submitFormData(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		System.out.println("submitFormDataSuccess = " + ToStringBuilder.reflectionToString(userInfoVO));
		
		ExtFormVO result = new ExtFormVO();
		
		if ("chiknin".equalsIgnoreCase(userInfoVO.getUserName())) {
			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
		}
		
		return result;
	}
}