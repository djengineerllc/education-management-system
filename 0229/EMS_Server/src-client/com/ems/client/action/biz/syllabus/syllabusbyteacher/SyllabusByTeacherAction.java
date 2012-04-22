package com.ems.client.action.biz.syllabus.syllabusbyteacher;

import java.io.IOException;
import java.util.ArrayList;
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
import com.ems.common.model.vo.EducationVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.BeanUtils.KeyNamingCallback;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.CodeTableBO;
import conf.hibernate.SyllabusBO;
import conf.hibernate.UserInfoBO;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusByTeacherAction extends DirectCrudAction {
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	private ISyllabusBS syllabusBS = this.getBean("syllabusBS", ISyllabusBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		EducationVO queryVO = BeanUtils.toBeanFromJsonFirst(params, EducationVO.class);
		
		List<UserInfoBO> teacherBOList = basicInfoBS.findTeacherByEducat(queryVO);
		
		return new ExtPagingVO(teacherBOList);
	}
	
	@DirectMethod
	public void printSyllbusPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer termId = BeanUtils.toInteger(request.getParameter("termId"));
		List<Integer> teacherIds = BeanUtils.toInteger(request.getParameterValues("teacherIds"));
		
		List<SyllabusPlanVO> syllabusPlanVOList = this.getSyllabusPlanVOList(termId, teacherIds);
		
		Map rootVO = new HashMap();
		rootVO.put("items", syllabusPlanVOList);
		rootVO.put("classCount", syllabusPlanVOList.size() / 5 / 2);
		rootVO.put("termId", termId);
		
		String data = (String) DataTransformerHelper.transform("DT_print_syllabusbyteacher", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
	
	private List<SyllabusPlanVO> getSyllabusPlanVOList(Integer termId, List<Integer> teacherIds) throws EMSException {
		List<UserInfoBO> userInfoBOList = basicInfoBS.findUserInfoById(teacherIds);
		List<SyllabusBO> syllabusBOList = syllabusBS.findByTermIdAndTeacherId(termId, teacherIds);
		Map<String, SyllabusBO> syllabusBOMap = BeanUtils.toMap(syllabusBOList, new KeyNamingCallback<SyllabusBO>() {
			public String getKey(SyllabusBO item, List<SyllabusBO> list, int idx) {
				return String.format("%s-%s-%s-%s", item.getClassId().toString(), item.getLesson(), item.getOeInd(), item.getWeek());
			}
		});
		
		List<SyllabusPlanVO> syllabusPlanVOList = new ArrayList<SyllabusPlanVO>();
		SyllabusPlanVO syllabusPlanVO = null;
		SyllabusBO syllabusBO = null;
		for (UserInfoBO userInfoBO : userInfoBOList) {
			for (CodeTableBO lessonBO : Code.getCodes("Lesson")) {
				for (CodeTableBO weekOeIndBO : Code.getCodes("WeekOeInd")) {
					syllabusPlanVO = new SyllabusPlanVO();
					syllabusPlanVO.setTeacher(userInfoBO.getId());
					syllabusPlanVO.setLesson(lessonBO.getCodeValue());
					syllabusPlanVO.setOeInd(weekOeIndBO.getCodeValue());
					
					String baseKey = String.format("%s-%s-%s-", syllabusPlanVO.getClassId().toString(), syllabusPlanVO.getLesson(), syllabusPlanVO.getOeInd());
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S1"));
					if (syllabusBO != null) {
						syllabusPlanVO.setMonCourse(syllabusBO.getCourseNo());
						syllabusPlanVO.setMonClass(syllabusBO.getClassId());
						syllabusPlanVO.setMonRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S2"));
					if (syllabusBO != null) {
						syllabusPlanVO.setTueCourse(syllabusBO.getCourseNo());
						syllabusPlanVO.setTueClass(syllabusBO.getClassId());
						syllabusPlanVO.setTueRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S3"));
					if (syllabusBO != null) {
						syllabusPlanVO.setWebCourse(syllabusBO.getCourseNo());
						syllabusPlanVO.setWebClass(syllabusBO.getClassId());
						syllabusPlanVO.setWebRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S4"));
					if (syllabusBO != null) {
						syllabusPlanVO.setThuCourse(syllabusBO.getCourseNo());
						syllabusPlanVO.setThuClass(syllabusBO.getClassId());
						syllabusPlanVO.setThuRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S5"));
					if (syllabusBO != null) {
						syllabusPlanVO.setFriCourse(syllabusBO.getCourseNo());
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
