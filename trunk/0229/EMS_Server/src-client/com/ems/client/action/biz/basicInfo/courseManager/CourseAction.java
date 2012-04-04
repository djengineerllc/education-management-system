package com.ems.client.action.biz.basicInfo.courseManager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.model.vo.CourseVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.CourseBO;

/**
 * 课程管理
 * @author zhuchaole
 *
 */

@ActionScope(scope=Scope.APPLICATION)
public class CourseAction extends DirectAction {
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadCourse(JsonArray params) {
		CourseVO subjectVO_qry = BeanUtils.toBeanFromJsonFirst(params, CourseVO.class);
		List<CourseBO> courses = basicInfoBS.findCourseByVO(subjectVO_qry);
		return new ExtPagingVO(courses);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		CourseBO courseBO = BeanUtils.toBeanFromMap(formParameters, CourseBO.class);
		ExtFormVO result = new ExtFormVO();
		List<CourseBO> courses = basicInfoBS.getAll(CourseBO.class, " id desc ");
		for(CourseBO course_ : courses){
			if (course_.getCourseName().equals(courseBO.getCourseName())) {
				result.addError("CourseCnName", String.format("课程[%s]已重复", courseBO.getCourseName()));
				return result;
			}
			if(course_.getCourseNo().equals(courseBO.getCourseNo())){
				result.addError("CourseNo", String.format("课程编号[%s]已重复", courseBO.getCourseNo()));
				return result;
			}
		}
		courseBO.setCreateTime(new Date());
		basicInfoBS.save(courseBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		CourseBO subject = null;
		if(id != null){
			subject = basicInfoBS.findById(CourseBO.class, id);
		}
		return new ExtFormVO(subject);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		CourseBO subject = BeanUtils.toBeanFromMap(formParameters, CourseBO.class);
		ExtFormVO result = new ExtFormVO();
		subject.setUpdateTime(new Date());
		basicInfoBS.update(subject);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			basicInfoBS.delete(basicInfoBS.findById(CourseBO.class, id));
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