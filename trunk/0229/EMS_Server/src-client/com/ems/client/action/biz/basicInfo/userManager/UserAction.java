package com.ems.client.action.biz.basicInfo.userManager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.model.vo.UserInfoVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.BookBO;
import conf.hibernate.UserInfoBO;

@ActionScope(scope=Scope.APPLICATION)
public class UserAction extends DirectAction {

	
	private IBasicInfoBS basicInfoBS  = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadUser(JsonArray params) {
		UserInfoVO userVO_qry = BeanUtils.toBeanFromJsonFirst(params, UserInfoVO.class);
		
		List<UserInfoVO> userInfo = this.basicInfoBS.findUserByVO(userVO_qry);
		
		return new ExtPagingVO(userInfo);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		ExtFormVO result = new ExtFormVO();
		List<UserInfoBO> userInfoes = this.basicInfoBS.getAll(UserInfoBO.class, " id desc ");
		for(UserInfoBO userInfoBO_:userInfoes){
			if(userInfoBO_.getLoginName().equals(userInfoVO.getLoginName())
					&& userInfoBO_.getUserName().equals(userInfoVO.getUserName())) {
				result.addError("userName", String.format("用户[%s]已重复", userInfoVO.getUserName()));
				return result;
			}
		}
		this.basicInfoBS.createUserInfo(userInfoVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		UserInfoVO userInfoVO = null;
		if(id != null){
			UserInfoVO userInfoVO_qry = new UserInfoVO();
			userInfoVO_qry.setId(id);
			userInfoVO = this.basicInfoBS.findUserByVO(userInfoVO_qry).get(0);
		}
		
		return new ExtFormVO(userInfoVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		ExtFormVO result = new ExtFormVO();
		this.basicInfoBS.updateUserInfo(userInfoVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		UserInfoVO userInfoVO = null;
		for (Integer id : ids) {
			userInfoVO = new UserInfoVO();
			userInfoVO.setId(id);
			this.basicInfoBS.deleteUserInfo(userInfoVO);
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}


}
