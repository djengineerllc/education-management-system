package com.ems.client.action.biz.applyonline.query;

import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.exception.EMSException;
import com.ems.common.model.info.applyonline.QueryApplyInfo;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCurdAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import conf.hibernate.ApplyOnlineInfo;

public class QueryAction extends DirectCurdAction {
	
	private Logger log = Logger.getLogger(this.getClass()); 
	

	@DirectMethod
	public ExtPagingVO getStuInfo(JsonArray params) throws EMSException {
		ExtPagingVO extPagingVO = new ExtPagingVO();
		QueryApplyInfo queryApplyInfo = BeanUtils.toBeanFromJsonFirst(params, QueryApplyInfo.class);
		log.debug("queryApplyInfo==="+ToStringBuilder.reflectionToString(queryApplyInfo));
		IApplyOnlineService applyOnlineService = this.getBean("applyOnlineService",IApplyOnlineService.class);
		List<ApplyOnlineInfo> result = applyOnlineService.findApplyInfo(queryApplyInfo);
		if(result != null && result.size() > 0){
			for(ApplyOnlineInfo applyOnlineInfo:result){
				extPagingVO.addItem(applyOnlineInfo);
			}
		}
		return extPagingVO;
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) 
	throws EMSException{
		
		return null;
	}
	
	@DirectMethod
	public ExtFormVO read(Integer id) throws EMSException {
		ExtFormVO extFormVO = new ExtFormVO();
		log.debug("getFormData userId = " + id);
		IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
		ApplyOnlineInfo applyOnlineInfo = applyOnlineService.findApplyOnlineInfoById(id);
		extFormVO.setData(applyOnlineInfo);
		return extFormVO;
	}
	
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) throws EMSException{
		ExtFormVO result = new ExtFormVO();
		String applyId = formParameters.get("id");
		IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
		ApplyOnlineInfo applyOnlineInfo = applyOnlineService.findApplyOnlineInfoById(new Integer(applyId));
		//applyOnlineInfo = BeanUtils.toBeanFromMap(formParameters,ApplyOnlineInfo.class);
		applyOnlineInfo.setBmNo(formParameters.get("bmNo"));
		applyOnlineInfo.setProjectName(formParameters.get("projectName"));
		applyOnlineInfo.setZyName(formParameters.get("zyName"));
		applyOnlineInfo.setMobile(formParameters.get("mobile"));
		applyOnlineInfo.setEmail(formParameters.get("email"));
		applyOnlineService.updateApplyOnlineInfo(applyOnlineInfo);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids)  throws EMSException {
		IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
		for (Integer id : ids) {
			applyOnlineService.deleteApplyOnlineInfo(id);
		}
		return new ExtFormVO();
	}
}
