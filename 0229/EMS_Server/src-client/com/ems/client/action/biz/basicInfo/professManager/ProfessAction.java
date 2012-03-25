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
import com.ems.client.action.biz.samples.common.vo.ProfessVO;
import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.Profess;
import conf.hibernate.Project;

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
			List<Profess> professes = basicInfoService.findProfessByVO(professVO_qry);
			ProfessVO professVO = null;
			for(Profess profess : professes){
				professVO = new ProfessVO();
				professVO.setId(profess.getId());
				professVO.setProfessName(profess.getProfessName());
				professVO.setProjectId(profess.getProjectId());
				professVO.setProjectName(basicInfoService.findById(Project.class, profess.getProjectId()).getProjectName());
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
		List<Profess> professes = this.basicInfoService.getAll(Profess.class, null);
		for(Profess profess_ :professes ){
			if (profess_.getProfessName().equals(professVO.getProfessName())) {
				result.addError("professName", String.format("专业[%s]已重复", professVO.getProfessName()));
				return result;
			}
		}
		Profess profess = new Profess();
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
			Profess profess = this.basicInfoService.findById(Profess.class, id);
			professVO = new ProfessVO();
			professVO.setId(profess.getId());
			professVO.setProfessName(profess.getProfessName());
			professVO.setProjectId(profess.getProjectId());
			professVO.setProjectName(this.basicInfoService.findById(Project.class, profess.getProjectId()).getProjectName());
		}
		return new ExtFormVO(professVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessVO professVO = BeanUtils.toBeanFromMap(formParameters, ProfessVO.class);
		ExtFormVO result = new ExtFormVO();
		Profess profess = this.basicInfoService.findById(Profess.class, professVO.getId());
		profess.setProfessName(professVO.getProfessName());
		profess.setProjectId(professVO.getProjectId());
		profess.setUpdateTime(new Date());
		this.basicInfoService.update(profess);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Profess.class, id));
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
