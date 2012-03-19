package com.ems.client.action.biz.basicInfo.subjectManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.client.action.biz.samples.common.vo.ProjectVO;
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

@ActionScope(scope=Scope.APPLICATION)
public class SubjectAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, SubjectVO> subject = new HashMap<Integer, SubjectVO>();
	static {
		subject.put(1, new SubjectVO(1, "1127","雅思中级词汇2","English Speaking 2",new Double(2),new Double(20)));
		subject.put(2, new SubjectVO(2, "1128","创意训练:服装设计","Creative Thinking &Training and Coaching-Fishion Design ",
				new Double(2.5),new Double(20)));
		subject.put(3, new SubjectVO(3, "1129","创意训练:视觉艺术","Creative Thinking &Training and Coaching-Visual Art ",
				new Double(1.5),new Double(18)));
		subject.put(4, new SubjectVO(4, "1130","雅思集训","",new Double(2),new Double(20)));
	}
	
	@DirectMethod
	public ExtPagingVO loadSubject(JsonArray params) {
		try{
			List<SubjectVO> subjectVOList = new ArrayList<SubjectVO>();
			for (Map.Entry<Integer, SubjectVO> user : subject.entrySet()) {
				subjectVOList.add(user.getValue());
			}
			return new ExtPagingVO(subjectVOList);
		}catch(Exception e){
			logger.error("loadProject--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		SubjectVO subjectVO = BeanUtils.toBeanFromMap(formParameters, SubjectVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (subject.containsKey(subjectVO.getSubjectCnName())) {
			result.addError("SubjectCnName", String.format("课程[%s]已重复", subjectVO.getSubjectCnName()));
			return result;
		}
		
		subjectVO.setId(++idCounter);
		subject.put(subjectVO.getId(), subjectVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer subjectId) {
		System.out.println("getFormData gradeId = " + subjectId);
		SubjectVO subjectVO = subject.get(subjectId);
		return new ExtFormVO(subjectVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		SubjectVO subjectVO = BeanUtils.toBeanFromMap(formParameters, SubjectVO.class);
		ExtFormVO result = new ExtFormVO();
		subject.put(subjectVO.getId(),subjectVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			subject.remove(id);
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
