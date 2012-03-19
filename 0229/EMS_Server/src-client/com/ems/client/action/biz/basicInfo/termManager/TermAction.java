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
import com.ems.client.action.biz.samples.common.vo.TermVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.Term;

@ActionScope(scope=Scope.APPLICATION)
public class TermAction extends DirectAction {

	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadTerm(JsonArray params) {
		try{
			List<TermVO> termVOList = new ArrayList<TermVO>();
			TermVO termVO = null;
			List<Term> terms = this.basicInfoService.getAll(Term.class, " id desc ");
			for(Term term:terms){
				termVO = new TermVO();
				termVO.setId(term.getId());
				termVO.setTermName(term.getTermName());
				termVO.setIsCurrentTerm("1".equals(term.getIsCurrentTerm())?"是":"否");
				termVOList.add(termVO);
			}
			return new ExtPagingVO(termVOList);
		}catch(Exception e){
			logger.error("loadTerm--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermVO termVO = BeanUtils.toBeanFromMap(formParameters, TermVO.class);
		ExtFormVO result = new ExtFormVO();
		List<Term> terms = this.basicInfoService.getAll(Term.class, " id desc ");
		for(Term term:terms){
			if(term.getTermName().equals(termVO.getTermName())){
				result.addError("termName", String.format("学期[%s]已重复", termVO.getTermName()));
				return result;
			}
		}
		Term termBO = new Term();
		termBO.setTermName(termVO.getTermName());
		termBO.setIsCurrentTerm(termVO.getIsCurrentTerm());
		termBO.setCreateTime(new Date());
		this.basicInfoService.save(termBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData termId = " + id);
		TermVO termVO = null;
		Term termBO = null;
		if(id != null){
			termBO = this.basicInfoService.findById(Term.class, id);
			termVO = new TermVO();
			termVO.setId(id);
			termVO.setTermName(termBO.getTermName());
			termVO.setIsCurrentTerm("1".equals(termBO.getIsCurrentTerm())?"是":"否");
		}
		return new ExtFormVO(termVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermVO termVO = BeanUtils.toBeanFromMap(formParameters, TermVO.class);
		ExtFormVO result = new ExtFormVO();
		Term term = this.basicInfoService.findById(Term.class, termVO.getId());
		term.setTermName(termVO.getTermName());
		term.setIsCurrentTerm(termVO.getIsCurrentTerm());
		term.setUpdateTime(new Date());
		this.basicInfoService.update(term);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Term.class, id));
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
