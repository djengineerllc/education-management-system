package com.ems.client.action.biz.applyonline.query;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.model.info.applyonline.QueryApplyInfo;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import conf.hibernate.ApplyOnlineInfo;

public class QueryAction extends DirectAction {
	
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

}
