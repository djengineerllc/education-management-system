package com.ems.client.action.biz.basicInfo.termManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.common.model.vo.ProjectVO;
import com.ems.common.model.vo.TermVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.TermBO;

@ActionScope(scope=Scope.APPLICATION)
public class TermAction extends DirectAction {

	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadTerm(JsonArray params) {
		TermVO termVO_qry = BeanUtils.toBeanFromJsonFirst(params, TermVO.class);
		List<TermBO> terms = this.basicInfoService.findTermByVO(termVO_qry);
		return new ExtPagingVO(terms);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermBO termBO = BeanUtils.toBeanFromMap(formParameters, TermBO.class);
		ExtFormVO result = new ExtFormVO();
		List<TermBO> terms = this.basicInfoService.getAll(TermBO.class, " id desc ");
		for(TermBO term:terms){
			if(term.getTermName().equals(termBO.getTermName())){
				result.addError("termName", String.format("学期[%s]已重复", termBO.getTermName()));
				return result;
			}
		}
		termBO.setCreateTime(new Date());
		this.basicInfoService.save(termBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		TermVO termVO = null;
		TermBO termBO = null;
		if(id != null){
			termBO = this.basicInfoService.findById(TermBO.class, id);
			termVO = new TermVO();
			termVO.setId(id);
			termVO.setTermName(termBO.getTermName());
			termVO.setIsCurrentTerm("1".equals(termBO.getIsCurrentTerm())?"是":"否");
		}
		return new ExtFormVO(termVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermBO termBO = BeanUtils.toBeanFromMap(formParameters, TermBO.class);
		ExtFormVO result = new ExtFormVO();
		termBO.setUpdateTime(new Date());
		this.basicInfoService.update(termBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(TermBO.class, id));
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
