package com.ems.client.action.biz.basicInfo.professManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.code.Code;
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
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadProfess(JsonArray params) {
		ProfessVO professVO_qry = BeanUtils.toBeanFromJsonFirst(params, ProfessVO.class);
		List<ProfessBO> professes = basicInfoBS.findProfessByVO(professVO_qry);
		return new ExtPagingVO(professes);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessBO professBO = BeanUtils.toBeanFromMap(formParameters, ProfessBO.class);
		ExtFormVO result = new ExtFormVO();
		List<ProfessBO> professes = basicInfoBS.getAll(ProfessBO.class, null);
		for(ProfessBO profess_ :professes ){
			if (profess_.getProfessName().equals(professBO.getProfessName())) {
				result.addError("professName", String.format("专业中文名[%s]已重复", professBO.getProfessName()));
				return result;
			}
			if (profess_.getProfessNameEn().equals(professBO.getProfessNameEn())) {
				result.addError("professNameEn", String.format("专业英文名[%s]已重复", professBO.getProfessNameEn()));
				return result;
			}
		}
		professBO.setCreateTime(new Date());
		basicInfoBS.save(professBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		ProfessBO profess = null;
		if(id != null){
			profess = basicInfoBS.findById(ProfessBO.class, id);
		}
		return new ExtFormVO(profess);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessBO professBO = BeanUtils.toBeanFromMap(formParameters, ProfessBO.class);
		ExtFormVO result = new ExtFormVO();
		professBO.setUpdateTime(new Date());
		basicInfoBS.update(professBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			basicInfoBS.delete(basicInfoBS.findById(ProfessBO.class, id));
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
