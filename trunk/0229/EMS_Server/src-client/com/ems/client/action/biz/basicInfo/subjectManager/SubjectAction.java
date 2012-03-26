package com.ems.client.action.biz.basicInfo.subjectManager;

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
import com.ems.client.action.biz.samples.common.vo.SubjectVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;
import conf.hibernate.Subject;

/**
 * 课程管理
 * @author zhuchaole
 *
 */

@ActionScope(scope=Scope.APPLICATION)
public class SubjectAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadSubject(JsonArray params) {
		try{
			List<SubjectVO> subjectVOList = new ArrayList<SubjectVO>();
			SubjectVO subjectVO_qry = BeanUtils.toBeanFromJsonFirst(params, SubjectVO.class);
			List<Subject> subjectes = basicInfoService.findSubjectByVO(subjectVO_qry);
			for(Subject subject : subjectes){
				SubjectVO subjectVO = new SubjectVO();
				subjectVO.setId(subject.getId());
				subjectVO.setSubjectNo(subject.getSubjectNo());
				subjectVO.setSubjectCnName(subject.getSubjectName());
				subjectVO.setSubjectEnName(subject.getSubjectEngName());
				subjectVO.setSubjectScore(subject.getSubjectScore());
				subjectVO.setSubjectTime(subject.getSubjectTime());
				subjectVOList.add(subjectVO);
			}
			return new ExtPagingVO(subjectVOList);
		}catch(Exception e){
			logger.error("loadSubject--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		SubjectVO subjectVO = BeanUtils.toBeanFromMap(formParameters, SubjectVO.class);
		ExtFormVO result = new ExtFormVO();
		List<Subject> subjectes = basicInfoService.getAll(Subject.class, " id desc ");
		for(Subject subject_ : subjectes){
			if (subject_.getSubjectName().equals(subjectVO.getSubjectCnName())) {
				result.addError("SubjectCnName", String.format("课程[%s]已重复", subjectVO.getSubjectCnName()));
				return result;
			}
			if(subject_.getSubjectNo().equals(subjectVO.getSubjectNo())){
				result.addError("SubjectNo", String.format("课程编号[%s]已重复", subjectVO.getSubjectNo()));
				return result;
			}
		}
		Subject subject = new Subject();
		subject.setSubjectNo(subjectVO.getSubjectNo());
		subject.setSubjectName(subjectVO.getSubjectCnName());
		subject.setSubjectEngName(subjectVO.getSubjectEnName());
		subject.setSubjectScore(subjectVO.getSubjectScore());
		subject.setSubjectTime(subjectVO.getSubjectTime().intValue());
		subject.setCreateTime(new Date());
		this.basicInfoService.save(subject);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData id = " + id);
		SubjectVO subjectVO = null;
		Subject subject = null;
		if(id != null){
			subject = this.basicInfoService.findById(Subject.class, id);
			subjectVO = new SubjectVO();
			subjectVO.setId(subject.getId());
			subjectVO.setSubjectNo(subject.getSubjectNo());
			subjectVO.setSubjectCnName(subject.getSubjectName());
			subjectVO.setSubjectEnName(subject.getSubjectEngName());
			subjectVO.setSubjectTime(subject.getSubjectTime());
			subjectVO.setSubjectScore(subject.getSubjectScore());
		}
		return new ExtFormVO(subjectVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		SubjectVO subjectVO = BeanUtils.toBeanFromMap(formParameters, SubjectVO.class);
		ExtFormVO result = new ExtFormVO();
		Subject subject = this.basicInfoService.findById(Subject.class, subjectVO.getId());
		subject.setSubjectNo(subjectVO.getSubjectNo());
		subject.setSubjectName(subjectVO.getSubjectCnName());
		subject.setSubjectEngName(subjectVO.getSubjectEnName());
		subject.setSubjectTime(subjectVO.getSubjectTime());
		subject.setSubjectScore(subjectVO.getSubjectScore());
		subject.setUpdateTime(new Date());
		this.basicInfoService.update(subject);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Subject.class, id));
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
