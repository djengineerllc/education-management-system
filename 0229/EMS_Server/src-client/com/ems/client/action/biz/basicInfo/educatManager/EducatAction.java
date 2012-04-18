package com.ems.client.action.biz.basicInfo.educatManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.code.Code;
import com.ems.common.model.vo.EducationVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;
import conf.hibernate.EducationBO;

/**
 * 任课管理
 * @author zhuchaole
 *
 */
@ActionScope(scope=Scope.APPLICATION)
public class EducatAction extends DirectAction {
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadEducat(JsonArray params) {
		EducationVO educationVO_qry = BeanUtils.toBeanFromJsonFirst(params, EducationVO.class);
		List<EducationBO> educationBOS = basicInfoBS.findEducationByVO(educationVO_qry);
		List<EducationVO> education = new ArrayList<EducationVO>();
		EducationVO educationVO = null;
		for(EducationBO educationBO : educationBOS){
			educationVO = new EducationVO();
			BeanUtils.copyProperties(educationBO, educationVO);
			educationVO.setTeacherName(Code.getName("Teacher", educationBO.getTeacherId()));
			educationVO.setCourseName(Code.getName("Course", educationBO.getCourseNo()));
			educationVO.setClassName(Code.getName("Class", educationBO.getClassId()));
			educationVO.setTermName(Code.getName("Term", educationBO.getTermId()));
			education.add(educationVO);
		}
		return new ExtPagingVO(education);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		EducationBO educationBO = BeanUtils.toBeanFromMap(formParameters, EducationBO.class);
		ExtFormVO result = new ExtFormVO();
		basicInfoBS.save(educationBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		EducationBO educationBO = null;
		EducationVO educationVO = null;
		if(id != null){
			educationBO = basicInfoBS.findById(EducationBO.class, id);
			educationVO = new EducationVO();
			BeanUtils.copyProperties(educationBO, educationVO);
			educationVO.setTeacherName(Code.getName("Teacher", educationBO.getTeacherId()));
			educationVO.setCourseName(Code.getName("Course", educationBO.getCourseNo()));
			educationVO.setClassName(Code.getName("Class", educationBO.getClassId()));
			educationVO.setTermName(Code.getName("Term", educationBO.getTermId()));
		}
		
		return new ExtFormVO(educationVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		EducationBO educationBO = BeanUtils.toBeanFromMap(formParameters, EducationBO.class);
		ExtFormVO result = new ExtFormVO();
		basicInfoBS.update(educationBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			basicInfoBS.delete(basicInfoBS.findById(EducationBO.class, id));
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
