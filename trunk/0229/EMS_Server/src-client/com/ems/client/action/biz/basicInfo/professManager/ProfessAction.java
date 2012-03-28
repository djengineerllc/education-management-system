package com.ems.client.action.biz.basicInfo.professManager;

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
import com.ems.common.model.vo.ProfessVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ProfessBO;
import conf.hibernate.ProjectBO;

/**
 * 专业管理
 * @author zhuchaole
 *
 */

@ActionScope(scope=Scope.APPLICATION)
public class ProfessAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadProfess(JsonArray params) {
		try{
			List<ProfessVO> professVOList = new ArrayList<ProfessVO>();
			ProfessVO professVO_qry = BeanUtils.toBeanFromJsonFirst(params, ProfessVO.class);
			List<ProfessBO> professes = basicInfoService.findProfessByVO(professVO_qry);
			ProfessVO professVO = null;
			for(ProfessBO profess : professes){
				professVO = new ProfessVO();
				professVO.setId(profess.getId());
				professVO.setProfessName(profess.getProfessName());
				professVO.setProjectId(profess.getProjectId());
				professVO.setProjectName(basicInfoService.findById(ProjectBO.class, profess.getProjectId()).getProjectName());
				professVOList.add(professVO);
			}
			return new ExtPagingVO(professVOList);
		}catch(Exception e){
			logger.error("loadProfess--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessVO professVO = BeanUtils.toBeanFromMap(formParameters, ProfessVO.class);
		ExtFormVO result = new ExtFormVO();
		List<ProfessBO> professes = this.basicInfoService.getAll(ProfessBO.class, null);
		for(ProfessBO profess_ :professes ){
			if (profess_.getProfessName().equals(professVO.getProfessName())) {
				result.addError("professName", String.format("专业[%s]已重复", professVO.getProfessName()));
				return result;
			}
		}
		ProfessBO profess = new ProfessBO();
		profess.setProfessName(professVO.getProfessName());
		profess.setProjectId(professVO.getProjectId());
		profess.setCreateTime(new Date());
		this.basicInfoService.save(profess);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData gradeId = " + id);
		ProfessVO professVO = null;
		if(id != null){
			ProfessBO profess = this.basicInfoService.findById(ProfessBO.class, id);
			professVO = new ProfessVO();
			professVO.setId(profess.getId());
			professVO.setProfessName(profess.getProfessName());
			professVO.setProjectId(profess.getProjectId());
			professVO.setProjectName(this.basicInfoService.findById(ProjectBO.class, profess.getProjectId()).getProjectName());
		}
		return new ExtFormVO(professVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessVO professVO = BeanUtils.toBeanFromMap(formParameters, ProfessVO.class);
		ExtFormVO result = new ExtFormVO();
		ProfessBO profess = this.basicInfoService.findById(ProfessBO.class, professVO.getId());
		profess.setProfessName(professVO.getProfessName());
		profess.setProjectId(professVO.getProjectId());
		profess.setUpdateTime(new Date());
		this.basicInfoService.update(profess);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(ProfessBO.class, id));
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}




}
