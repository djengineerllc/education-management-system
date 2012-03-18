package com.ems.client.action.biz.basicInfo.gradeManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.client.action.biz.samples.common.vo.GradeVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.Grade;

@ActionScope(scope=Scope.APPLICATION)
public class GradeAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadGrade(JsonArray params) {
		try{
			List<GradeVO> gradeVOList = new ArrayList<GradeVO>();
			GradeVO gradeVO = null;
			List<Grade> gradeList = basicInfoService.getAll(Grade.class);
			for (Grade grade_ : gradeList) {
				gradeVO = new GradeVO();
				gradeVO.setId(grade_.getId());
				gradeVO.setGradeName(grade_.getGradeName());
				gradeVOList.add(gradeVO);
			}
			return new ExtPagingVO(gradeVOList);
		}catch(Exception e){
			logger.error("loadGrade--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeVO gradeVO = BeanUtils.toBeanFromMap(formParameters, GradeVO.class);
		ExtFormVO result = new ExtFormVO();
		List<Grade> gradeList = basicInfoService.getAll(Grade.class);
		for(Grade grade_:gradeList){
			if (grade_.getGradeName().equalsIgnoreCase(gradeVO.getGradeName())) {
				result.addError("gradeName", String.format("年级[%s]已重复", gradeVO.getGradeName()));
				return result;
			}
		}
		Grade grade = new Grade();
		grade.setGradeName(gradeVO.getGradeName());
		grade.setCreateTime(new Date());
		basicInfoService.save(grade);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer gradeId) {
		System.out.println("getFormData gradeId = " + gradeId);
		Grade grade = null;
		GradeVO gradeVO = null;
		if(gradeId != null){
			grade = this.basicInfoService.findById(Grade.class, gradeId);
			gradeVO = new GradeVO();
			gradeVO.setId(grade.getId());
			gradeVO.setGradeName(grade.getGradeName());
		}
		return new ExtFormVO(gradeVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeVO gradeVO = BeanUtils.toBeanFromMap(formParameters, GradeVO.class);
		ExtFormVO result = new ExtFormVO();
		Grade grade = this.basicInfoService.findById(Grade.class, gradeVO.getId());
		grade.setGradeName(gradeVO.getGradeName());
		grade.setUpdateTime(new Date());
		this.basicInfoService.update(grade);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		Grade grade = null;
		for (Integer id : ids) {
			grade = this.basicInfoService.findById(Grade.class, id);
			this.basicInfoService.delete(grade);
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map rootVO = new HashMap();
		rootVO.put("emailTitle", "电子邮件");
		byte[] data = (byte[]) DataTransformerHelper.transform("DT_test_excel_export", rootVO);
		this.download(response, data, "用户表格.xls");
	}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.downloadFile(response, "conf/templates/excel/用户表格.xls", "用户表格-导入模板.xls");
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}
}
