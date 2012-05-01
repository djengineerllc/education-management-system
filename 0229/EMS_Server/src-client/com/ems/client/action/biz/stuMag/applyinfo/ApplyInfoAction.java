package com.ems.client.action.biz.stuMag.applyinfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.stuMag.bs.IApplyInfoBS;
import com.ems.common.model.vo.ApplyInfoVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ApplyInfoBO;

@ActionScope(scope=Scope.APPLICATION)
public class ApplyInfoAction extends DirectAction {
	
	private IApplyInfoBS applyInfoBS = this.getBean("applyInfoBS", IApplyInfoBS.class);

	@DirectMethod
	public ExtPagingVO loadApplyInfos(JsonArray params){
		ApplyInfoVO applyInfoVO = BeanUtils.toBeanFromJsonFirst(params, ApplyInfoVO.class);
		List<ApplyInfoBO> applyInfos = applyInfoBS.findApplyInfoBO(applyInfoVO);
		return new ExtPagingVO(applyInfos);
	}
	

	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ApplyInfoBO applyInfoBO = BeanUtils.toBeanFromMap(formParameters, ApplyInfoBO.class);
		ExtFormVO result = new ExtFormVO();
		applyInfoBS.create(applyInfoBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO read(Integer id) {
		ApplyInfoBO applyInfoBO = null;
		if(id != null){
			applyInfoBO = applyInfoBS.findById(id);
		}
		
		return new ExtFormVO(applyInfoBO);
	}
	
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ApplyInfoBO applyInfoBO = BeanUtils.toBeanFromMap(formParameters, ApplyInfoBO.class);
		ExtFormVO result = new ExtFormVO();
		applyInfoBS.update(applyInfoBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		applyInfoBS.deleteByIds(ids);
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
