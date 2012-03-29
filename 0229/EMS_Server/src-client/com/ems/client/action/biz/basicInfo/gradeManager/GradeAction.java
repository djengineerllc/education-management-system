package com.ems.client.action.biz.basicInfo.gradeManager;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.model.vo.GradeVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.GradeBO;

@ActionScope(scope=Scope.APPLICATION)
public class GradeAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadGrade(JsonArray params) {
		GradeVO gradeVO_qry = BeanUtils.toBeanFromJsonFirst(params, GradeVO.class);
		List<GradeBO> gradeList = basicInfoService.findGradeByVO(gradeVO_qry);
		return new ExtPagingVO(gradeList);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeBO gradeBO = BeanUtils.toBeanFromMap(formParameters, GradeBO.class);
		ExtFormVO result = new ExtFormVO();
		List<GradeBO> gradeList = basicInfoService.getAll(GradeBO.class,"id desc");
		for(GradeBO grade_:gradeList){
			if (grade_.getGradeName().equalsIgnoreCase(gradeBO.getGradeName())) {
				result.addError("gradeName", String.format("年级[%s]已重复", gradeBO.getGradeName()));
				return result;
			}
		}
		gradeBO.setCreateTime(new Date());
		basicInfoService.save(gradeBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer gradeId) {
		GradeBO grade = null;
		if(gradeId != null){
			grade = this.basicInfoService.findById(GradeBO.class, gradeId);
		}
		return new ExtFormVO(grade);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeBO gradeBO = BeanUtils.toBeanFromMap(formParameters, GradeBO.class);
		ExtFormVO result = new ExtFormVO();
		gradeBO.setUpdateTime(new Date());
		this.basicInfoService.update(gradeBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		GradeBO grade = null;
		for (Integer id : ids) {
			grade = this.basicInfoService.findById(GradeBO.class, id);
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
