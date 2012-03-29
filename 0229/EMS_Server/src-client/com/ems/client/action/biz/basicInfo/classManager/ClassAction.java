package com.ems.client.action.biz.basicInfo.classManager;

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
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.model.vo.ClassVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ClassBO;
import conf.hibernate.GradeBO;

@ActionScope(scope=Scope.APPLICATION)
public class ClassAction extends DirectAction  {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadClass(JsonArray params) {
		ClassVO classVO_qry = BeanUtils.toBeanFromJsonFirst(params, ClassVO.class);
		ClassVO classVO = null;
		List<ClassVO> classVOList = new ArrayList<ClassVO>();
		List<ClassBO> classes = basicInfoService.findClassByVO(classVO_qry);
		for(ClassBO classBO : classes){
			classVO = new ClassVO();
			classVO.setId(classBO.getId());
			classVO.setClassName(classBO.getClassName());
			classVO.setGradeId(classBO.getGradeId());
			classVO.setGradeName(basicInfoService.findById(GradeBO.class, classBO.getGradeId()).getGradeName());
			classVO.setStudentNum(classBO.getStudentNum());
			classVOList.add(classVO);
		}
		return new ExtPagingVO(classVOList);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ClassBO classBO = BeanUtils.toBeanFromMap(formParameters, ClassBO.class);
		
		ExtFormVO result = new ExtFormVO();
		List<ClassBO> classes = basicInfoService.getAll(ClassBO.class, " id desc ");
		for(ClassBO classBO_ : classes){
			if (classBO_.getClassName().equals(classBO.getClassName())
					&& classBO_.getGradeId() == classBO.getGradeId()) {
				result.addError("className", String.format("班级[%s]已重复", classBO.getClassName()));
				return result;
			}
		}
		classBO.setCreateTime(new Date());
		this.basicInfoService.save(classBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		ClassBO classBO = null;
		ClassVO classVO = null;
		if(id != null){
			classBO = this.basicInfoService.findById(ClassBO.class, id);
			classVO = new ClassVO();
			classVO.setId(id);
			classVO.setClassName(classBO.getClassName());
			classVO.setGradeId(classBO.getGradeId());
			classVO.setGradeName(basicInfoService.findById(GradeBO.class, classBO.getGradeId()).getGradeName());
			classVO.setStudentNum(classBO.getStudentNum());
		}
		return new ExtFormVO(classVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ClassBO classBO = BeanUtils.toBeanFromMap(formParameters, ClassBO.class);
		ExtFormVO result = new ExtFormVO();
		classBO.setUpdateTime(new Date());
		this.basicInfoService.update(classBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			ClassBO classBO = basicInfoService.findById(ClassBO.class, id);
			this.basicInfoService.delete(classBO);
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
