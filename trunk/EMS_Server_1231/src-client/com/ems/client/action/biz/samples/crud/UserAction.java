package com.ems.client.action.biz.samples.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.client.action.biz.samples.common.vo.UserInfoVO;
import com.ems.client.action.biz.samples.xgridsample.vo.XGridSample1ViewQueryVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCurdAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class UserAction extends DirectCurdAction {
	
	private static Map<String, UserInfoVO> users = new HashMap<String, UserInfoVO>();
	static {
		users.put("小萌", new UserInfoVO("小萌", "1", "2011-10-10", "xm@163.com"));
		users.put("小乐", new UserInfoVO("小乐", "2", "2011-11-11", "xl@163.com"));
	}
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
		
		
		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
		for (Map.Entry<String, UserInfoVO> user : users.entrySet()) {
			userInfoVOList.add(user.getValue());
		}
//		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
		
		return new ExtPagingVO("userName", userInfoVOList);
//		throw new IllegalArgumentException("异常");
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (users.containsKey(userInfoVO.getUserName())) {
			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
		}
		
		users.put(userInfoVO.getUserName(), userInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public ExtFormVO read(String id) {
		System.out.println("getFormData userId = " + id);
		
		UserInfoVO userInfoVO = users.get(id);
//			new UserInfoVO();
//		userInfoVO.setUserName("Chiknin");
//		userInfoVO.setSex("1");
//		userInfoVO.setBirthday("2011-11-11");
//		userInfoVO.setEmail("chiknin@gmail.com");
//		
//		userInfoVO.setSexcheckboxgroup_S1("1");
//		userInfoVO.setSexcheckboxgroup_S2("6");
		
		return new ExtFormVO(userInfoVO);
	}
	
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		return this.create(formParameters, fileFields);
	}
	
	@DirectMethod
	public ExtFormVO delete(List<String> ids) {
		System.out.println(ids);
		throw new UnsupportedOperationException("未实现");
	}
	
//	@DirectMethod
//	public ExtPagingVO loadUserList(JsonArray params) {
//		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
//		
//		
//		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
//		for (Map.Entry<String, UserInfoVO> user : users.entrySet()) {
//			userInfoVOList.add(user.getValue());
//		}
////		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
//		
//		return new ExtPagingVO("userName", userInfoVOList);
////		throw new IllegalArgumentException("异常");
//	}
//	
//	@DirectMethod
//	public ExtFormVO loadUser(String id) {
//		System.out.println("getFormData userId = " + id);
//		
//		UserInfoVO userInfoVO = users.get(id);
////			new UserInfoVO();
////		userInfoVO.setUserName("Chiknin");
////		userInfoVO.setSex("1");
////		userInfoVO.setBirthday("2011-11-11");
////		userInfoVO.setEmail("chiknin@gmail.com");
////		
////		userInfoVO.setSexcheckboxgroup_S1("1");
////		userInfoVO.setSexcheckboxgroup_S2("6");
//		
//		return new ExtFormVO(userInfoVO);
//	}
//	
//	@DirectFormPostMethod
//	public ExtFormVO submitUser(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
//		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
//		
//		ExtFormVO result = new ExtFormVO();
//		
//		if (users.containsKey(userInfoVO.getUserName())) {
//			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
//		}
//		
//		users.put(userInfoVO.getUserName(), userInfoVO);
//		
//		return result;
//	}
}