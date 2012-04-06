package com.ems.client.action.biz.syllabus.syllabusbycourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.biz.syllabus.bs.ISyllabusBS;
import com.ems.client.action.biz.syllabus.common.vo.SyllabusPlanVO;
import com.ems.common.code.Code;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.CourseVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.BeanUtils.KeyNamingCallback;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ClassBO;
import conf.hibernate.CodeTableBO;
import conf.hibernate.CourseBO;
import conf.hibernate.SyllabusBO;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusByCourseAction extends DirectCrudAction {
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	private ISyllabusBS syllabusBS = this.getBean("syllabusBS", ISyllabusBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
//		 Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
//		 Integer termId = BeanUtils.toInteger(paramMap.get("termId"));
		CourseVO courseVO = BeanUtils.toBeanFromJsonFirst(params, CourseVO.class);
		courseVO.setCourseEngName(courseVO.getCourseName());
		
		List<CourseBO> courseBOList = basicInfoBS.findCourseByVO(courseVO);
		
		return new ExtPagingVO(courseBOList);
	}
	
	@DirectMethod
	public void printSyllbusPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer termId = BeanUtils.toInteger(request.getParameter("termId"));
		List<String> courseNos = Arrays.asList(request.getParameterValues("courseNo"));
		
		List<SyllabusPlanVO> syllabusPlanVOList = this.getSyllabusPlanVOList(termId, courseNos);
		
		Map rootVO = new HashMap();
		rootVO.put("items", syllabusPlanVOList);
		rootVO.put("classCount", syllabusPlanVOList.size() / 5 / 2);
		rootVO.put("termId", termId);
		
		String data = (String) DataTransformerHelper.transform("DT_print_syllabusbycourse", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
	
	private List<SyllabusPlanVO> getSyllabusPlanVOList(Integer termId, List<String> courseNos) throws EMSException {
		List<CourseBO> courseBOList = basicInfoBS.findCourseByNo(courseNos);
		List<SyllabusBO> syllabusBOList = syllabusBS.findByTermIdAndCourseNo(termId, courseNos);
		Map<String, SyllabusBO> syllabusBOMap = BeanUtils.toMap(syllabusBOList, new KeyNamingCallback<SyllabusBO>() {
			public String getKey(SyllabusBO item, List<SyllabusBO> list, int idx) {
				return String.format("%s-%s-%s-%s", item.getCourseNo(), item.getLesson(), item.getOeInd(), item.getWeek());
			}
		});
		
		List<SyllabusPlanVO> syllabusPlanVOList = new ArrayList<SyllabusPlanVO>();
		SyllabusPlanVO syllabusPlanVO = null;
		SyllabusBO syllabusBO = null;
		for (CourseBO courseBO : courseBOList) {
			for (CodeTableBO lessonBO : Code.getCodes("Lesson")) {
				for (CodeTableBO weekOeIndBO : Code.getCodes("WeekOeInd")) {
					syllabusPlanVO = new SyllabusPlanVO();
					syllabusPlanVO.setCourse(courseBO.getCourseNo());
					syllabusPlanVO.setLesson(lessonBO.getCodeValue());
					syllabusPlanVO.setOeInd(weekOeIndBO.getCodeValue());
					
					String baseKey = String.format("%s-%s-%s-", syllabusPlanVO.getCourse(), syllabusPlanVO.getLesson(), syllabusPlanVO.getOeInd());
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S1"));
					if (syllabusBO != null) {
						syllabusPlanVO.setMonTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setMonClass(syllabusBO.getClassId());
						syllabusPlanVO.setMonRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S2"));
					if (syllabusBO != null) {
						syllabusPlanVO.setTueTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setTueClass(syllabusBO.getClassId());
						syllabusPlanVO.setTueRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S3"));
					if (syllabusBO != null) {
						syllabusPlanVO.setWebTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setWebClass(syllabusBO.getClassId());
						syllabusPlanVO.setWebRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S4"));
					if (syllabusBO != null) {
						syllabusPlanVO.setThuTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setThuClass(syllabusBO.getClassId());
						syllabusPlanVO.setThuRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S5"));
					if (syllabusBO != null) {
						syllabusPlanVO.setFriTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setFriClass(syllabusBO.getClassId());
						syllabusPlanVO.setFriRoom(syllabusBO.getRoomId());
					}
					
					syllabusPlanVOList.add(syllabusPlanVO);
				}
			}
		}
		
		return syllabusPlanVOList;
	}
}
