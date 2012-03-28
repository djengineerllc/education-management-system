package com.ems.client.action.biz.basicInfo.courseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.basicInfo.service.IBasicInfoService;
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
	
	private IBasicInfoService basicInfoService = (IBasicInfoService) super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadCourse(JsonArray params) {
		try{
			List<CourseVO> subjectVOList = new ArrayList<CourseVO>();
			CourseVO subjectVO_qry = BeanUtils.toBeanFromJsonFirst(params, CourseVO.class);
			List<CourseBO> subjectes = basicInfoService.findCourseByVO(subjectVO_qry);
			for(CourseBO subject : subjectes){
				CourseVO subjectVO = new CourseVO();
				subjectVO.setId(subject.getId());
				subjectVO.setCourseNo(subject.getCourseNo());
				subjectVO.setCourseCnName(subject.getCourseName());
				subjectVO.setCourseEnName(subject.getCourseEngName());
				subjectVO.setCourseScore(subject.getCourseScore());
				subjectVO.setCourseTime(subject.getCourseTime());
				subjectVOList.add(subjectVO);
			}
			return new ExtPagingVO(subjectVOList);
		}catch(Exception e){
			logger.error("loadCourse--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		CourseVO subjectVO = BeanUtils.toBeanFromMap(formParameters, CourseVO.class);
		ExtFormVO result = new ExtFormVO();
		List<CourseBO> subjectes = basicInfoService.getAll(CourseBO.class, " id desc ");
		for(CourseBO subject_ : subjectes){
			if (subject_.getCourseName().equals(subjectVO.getCourseCnName())) {
				result.addError("CourseCnName", String.format("课程[%s]已重复", subjectVO.getCourseCnName()));
				return result;
			}
			if(subject_.getCourseNo().equals(subjectVO.getCourseNo())){
				result.addError("CourseNo", String.format("课程编号[%s]已重复", subjectVO.getCourseNo()));
				return result;
			}
		}
		CourseBO subject = new CourseBO();
		subject.setCourseNo(subjectVO.getCourseNo());
		subject.setCourseName(subjectVO.getCourseCnName());
		subject.setCourseEngName(subjectVO.getCourseEnName());
		subject.setCourseScore(subjectVO.getCourseScore());
		subject.setCourseTime(subjectVO.getCourseTime().intValue());
		subject.setCreateTime(new Date());
		this.basicInfoService.save(subject);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData id = " + id);
		CourseVO subjectVO = null;
		CourseBO subject = null;
		if(id != null){
			subject = this.basicInfoService.findById(CourseBO.class, id);
			subjectVO = new CourseVO();
			subjectVO.setId(subject.getId());
			subjectVO.setCourseNo(subject.getCourseNo());
			subjectVO.setCourseCnName(subject.getCourseName());
			subjectVO.setCourseEnName(subject.getCourseEngName());
			subjectVO.setCourseTime(subject.getCourseTime());
			subjectVO.setCourseScore(subject.getCourseScore());
		}
		return new ExtFormVO(subjectVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		CourseVO subjectVO = BeanUtils.toBeanFromMap(formParameters, CourseVO.class);
		ExtFormVO result = new ExtFormVO();
		CourseBO subject = this.basicInfoService.findById(CourseBO.class, subjectVO.getId());
		subject.setCourseNo(subjectVO.getCourseNo());
		subject.setCourseName(subjectVO.getCourseCnName());
		subject.setCourseEngName(subjectVO.getCourseEnName());
		subject.setCourseTime(subjectVO.getCourseTime());
		subject.setCourseScore(subjectVO.getCourseScore());
		subject.setUpdateTime(new Date());
		this.basicInfoService.update(subject);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(CourseBO.class, id));
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
