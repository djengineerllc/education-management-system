package com.ems.client.action.biz.applyonline.apply;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.common.model.info.applyonline.ApplyInfo;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;

import conf.hibernate.ApplyOnlineInfo;

public class ApplyAction {
	
	private Logger log = Logger.getLogger(this.getClass()); 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@DirectFormPostMethod
	public ExtFormVO saveApplyOnlineInfo(Map<String, String> formParameters,
			Map<String, FileItem> fileFields){
		ExtFormVO extFormVO = new ExtFormVO();
		try{
			String projectName = formParameters.get("projectName");
			ApplyOnlineInfo applyOnlineInfo = BeanUtils.toBeanFromMap(formParameters, ApplyOnlineInfo.class);
			log.debug("formParameters--stuBirthday=="+formParameters.get("stuBirthday"));
			log.debug("applyOnlineInfo--StuBirthday==="+applyOnlineInfo.getStuBirthday());
		}catch(Exception e){
			extFormVO.setSuccess(false);
			extFormVO.getErrors().put("SERVER_INVALID",e.getMessage());
		}
		
		return extFormVO;
	}

}
