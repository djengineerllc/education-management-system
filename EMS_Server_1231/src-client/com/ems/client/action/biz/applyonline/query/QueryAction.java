package com.ems.client.action.biz.applyonline.query;

import java.util.List;
import java.util.Map;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import com.ems.biz.applyonline.service.IApplyOnlineService;
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
	public ExtPagingVO getStuInfo(JsonArray params) {
		try{
			ExtPagingVO extPagingVO = new ExtPagingVO();
			QueryApplyInfo queryApplyInfo = BeanUtils.toBeanFromJsonFirst(params, QueryApplyInfo.class);
			log.debug("queryApplyInfo==="+ToStringBuilder.reflectionToString(queryApplyInfo));
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			List<ApplyOnlineInfo> result = applyOnlineService.findApplyInfo(queryApplyInfo);
			if(result != null && result.size() > 0){
				for(ApplyOnlineInfo applyOnlineInfo:result){
					extPagingVO.addItem(applyOnlineInfo);
				}
			}
			return extPagingVO;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		
		return null;
	}
	
	@DirectMethod
	public ExtFormVO read(Integer id) {
		ExtFormVO extFormVO = new ExtFormVO();
		log.debug("getFormData userId = " + id);
		try{
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			ApplyOnlineInfo applyOnlineInfo = applyOnlineService.findApplyOnlineInfoById(id);
			extFormVO.setData(applyOnlineInfo);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		return extFormVO;
	}
	
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ExtFormVO result = new ExtFormVO();
		String applyId = formParameters.get("id");
		try{
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			ApplyOnlineInfo applyOnlineInfo = applyOnlineService.findApplyOnlineInfoById(new Integer(applyId));
			//applyOnlineInfo = BeanUtils.toBeanFromMap(formParameters,ApplyOnlineInfo.class);
			applyOnlineInfo.setBmNo(formParameters.get("bmNo"));
			applyOnlineInfo.setProjectName(formParameters.get("projectName"));
			applyOnlineInfo.setZyName(formParameters.get("zyName"));
			applyOnlineInfo.setMobile(formParameters.get("mobile"));
			applyOnlineInfo.setEmail(formParameters.get("email"));
			applyOnlineService.updateApplyOnlineInfo(applyOnlineInfo);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		try{
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			for (Integer id : ids) {
				applyOnlineService.deleteApplyOnlineInfo(id);
			}
			return new ExtFormVO();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
}
