package com.ems.client.action.biz.applyonline.apply;

import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;

import conf.hibernate.ApplyOnlineInfo;

public class ApplyAction extends DirectAction {
	
	private Logger log = Logger.getLogger(this.getClass()); 
	@DirectFormPostMethod
	public ExtFormVO saveApplyOnlineInfo(Map<String, String> formParameters,
			Map<String, FileItem> fileFields){
		ExtFormVO extFormVO = new ExtFormVO();
		try{
			ApplyOnlineInfo applyOnlineInfo = BeanUtils.toBeanFromMap(formParameters, ApplyOnlineInfo.class);
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			applyOnlineService.saveApplyInfo(applyOnlineInfo);
			log.info("saveApplyOnlineInfo--success--");
		}catch(Exception e){
			extFormVO.setSuccess(false);
		}
		
		return extFormVO;
	}

}
