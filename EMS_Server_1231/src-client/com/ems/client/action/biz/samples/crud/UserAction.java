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
	
	private static Integer idCounter = 10;
	
	private static Map<Integer, UserInfoVO> users = new HashMap<Integer, UserInfoVO>();
	static {
		users.put(1, new UserInfoVO(1, "小萌", "1", "2011-10-10", "xm@163.com"));
		users.put(2, new UserInfoVO(2, "小乐", "2", "2011-11-11", "xl@163.com"));
	}
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
		
		
		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
		for (Map.Entry<Integer, UserInfoVO> user : users.entrySet()) {
			userInfoVOList.add(user.getValue());
		}
//		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
		
		return new ExtPagingVO(userInfoVOList);
//		throw new IllegalArgumentException("xf自定义异常");
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (users.containsKey(userInfoVO.getUserName())) {
			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
			return result;
		}
		
		userInfoVO.setId(++idCounter);
		users.put(userInfoVO.getId(), userInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public ExtFormVO read(Integer id) {
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
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		users.put(userInfoVO.getId(), userInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			users.remove(id);
		}
		
		return new ExtFormVO();
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