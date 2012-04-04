package com.ems.client.action.biz.syllabus.syllabusplan;

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
import com.ems.common.model.vo.ClassVO;
import com.ems.common.model.vo.TermVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.BeanUtils.KeyNamingCallback;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.ClassBO;
import conf.hibernate.CodeTableBO;
import conf.hibernate.SyllabusBO;
import conf.hibernate.TermBO;

@ActionScope(scope=Scope.APPLICATION)
public class SyllabusPlanAction extends DirectCrudAction {
	
	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	private ISyllabusBS syllabusBS = this.getBean("syllabusBS", ISyllabusBS.class);
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		List<TermBO> termBOList = basicInfoBS.findTermByVO(new TermVO());
		
		return new ExtPagingVO(termBOList);
	}
	
	@DirectMethod
	public ExtPagingVO loadSyllabusPlanDetail(JsonArray params) {
		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		Integer termId = BeanUtils.toInteger(paramMap.get("id"));
		
		List<ClassBO> classBOList = basicInfoBS.findClassByVO(new ClassVO());
		List<SyllabusBO> syllabusBOList = syllabusBS.findByTermId(termId);
		Map<String, SyllabusBO> syllabusBOMap = BeanUtils.toMap(syllabusBOList, new KeyNamingCallback<SyllabusBO>() {
			public String getKey(SyllabusBO item, List<SyllabusBO> list, int idx) {
				return String.format("%s-%s-%s-%s", item.getLesson(), item.getClassId().toString(), item.getOeInd(), item.getWeek());
			}
		});
		
		
		List<SyllabusPlanVO> syllabusPlanVOList = new ArrayList<SyllabusPlanVO>();
		SyllabusPlanVO syllabusPlanVO = null;
		SyllabusBO syllabusBO = null;
		for (CodeTableBO lessonBO : Code.getCodes("Lesson")) {
			for (ClassBO classBO : classBOList) {
				for (CodeTableBO weekOeIndBO : Code.getCodes("WeekOeInd")) {
					syllabusPlanVO = new SyllabusPlanVO();
					syllabusPlanVO.setLesson(lessonBO.getCodeValue());
					syllabusPlanVO.setClassId(classBO.getId());
					syllabusPlanVO.setOeInd(weekOeIndBO.getCodeValue());
					
					String baseKey = String.format("%s-%s-%s-", syllabusPlanVO.getLesson(), syllabusPlanVO.getClassId().toString(), syllabusPlanVO.getOeInd());
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S1"));
					if (syllabusBO != null) {
						syllabusPlanVO.setMonCourse(syllabusBO.getCourseId());
						syllabusPlanVO.setMonTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setMonRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S2"));
					if (syllabusBO != null) {
						syllabusPlanVO.setTueCourse(syllabusBO.getCourseId());
						syllabusPlanVO.setTueTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setTueRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S3"));
					if (syllabusBO != null) {
						syllabusPlanVO.setWebCourse(syllabusBO.getCourseId());
						syllabusPlanVO.setWebTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setWebRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S4"));
					if (syllabusBO != null) {
						syllabusPlanVO.setThuCourse(syllabusBO.getCourseId());
						syllabusPlanVO.setThuTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setThuRoom(syllabusBO.getRoomId());
					}
					syllabusBO = syllabusBOMap.get(baseKey + Code.getValue("Week", "S5"));
					if (syllabusBO != null) {
						syllabusPlanVO.setFriCourse(syllabusBO.getCourseId());
						syllabusPlanVO.setFriTeacher(syllabusBO.getTeacherId());
						syllabusPlanVO.setFriRoom(syllabusBO.getRoomId());
					}
					
					syllabusPlanVOList.add(syllabusPlanVO);
				}
			}
		}
		
		return new ExtPagingVO(syllabusPlanVOList);
	}
	
	@DirectMethod
	public ExtFormVO submitSyllabusPlanDetail(JsonArray params) {
		JsonObject jsonObj = params.get(0).getAsJsonObject();
		Integer termId = jsonObj.get("termId").getAsInt();
		List<SyllabusPlanVO> syllabusPlanVOList = BeanUtils.toBeanFromJson(jsonObj.get("submitData").getAsJsonArray(), SyllabusPlanVO.class);
		
		List<SyllabusBO> boList = new ArrayList<SyllabusBO>();
		SyllabusBO bo = null;
		for (SyllabusPlanVO vo : syllabusPlanVOList) {
			bo = this.toSyllabusBO(termId, vo, Code.getValue("Week", "S1"), vo.getMonId(), vo.getMonCourse(), vo.getMonTeacher(), vo.getMonRoom());
			if (bo != null) {
				boList.add(bo);
			}
			
			bo = this.toSyllabusBO(termId, vo, Code.getValue("Week", "S2"), vo.getTueId(), vo.getTueCourse(), vo.getTueTeacher(), vo.getTueRoom());
			if (bo != null) {
				boList.add(bo);
			}
			
			bo = this.toSyllabusBO(termId, vo, Code.getValue("Week", "S3"), vo.getWebId(), vo.getWebCourse(), vo.getWebTeacher(), vo.getWebRoom());
			if (bo != null) {
				boList.add(bo);
			}
			
			bo = this.toSyllabusBO(termId, vo, Code.getValue("Week", "S4"), vo.getThuId(), vo.getThuCourse(), vo.getThuTeacher(), vo.getThuRoom());
			if (bo != null) {
				boList.add(bo);
			}
			
			bo = this.toSyllabusBO(termId, vo, Code.getValue("Week", "S5"), vo.getFriId(), vo.getFriCourse(), vo.getFriTeacher(), vo.getFriRoom());
			if (bo != null) {
				boList.add(bo);
			}
		}
		syllabusBS.submitSyllabus(termId, boList);
		
		return ExtFormVO.success();
	}
	private SyllabusBO toSyllabusBO(Integer termId, SyllabusPlanVO vo, String week, Integer id, Integer course, Integer teacher, Integer room) {
		if (course == null && teacher == null && room == null) {
			return null;
		}
		
		SyllabusBO bo = new SyllabusBO();
		bo.setId(id);
		bo.setTermId(termId);
		bo.setClassId(vo.getClassId());
		bo.setLesson(vo.getLesson());
		bo.setOeInd(vo.getOeInd());
		bo.setWeek(week);
		bo.setCourseId(course);
		bo.setTeacherId(teacher);
		bo.setRoomId(room);
		
		return bo;
	}
	
	@DirectMethod
	public void printSyllbusPlan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String teamId = request.getParameter("teamId");
		
		Map rootVO = new HashMap();
		
		List<SyllabusPlanVO> items = this.getTestData();
		rootVO.put("items", items);
		rootVO.put("classCount", 2); // items.size() / 2 / 5);
		
		String data = (String) DataTransformerHelper.transform("DT_print_syllabusplan", rootVO);
		
		this.writeToResponse(response, data.getBytes("UTF-8"));
	}
	
	private List<SyllabusPlanVO> getTestData() {
		List items = new ArrayList();
		
		return items;
		
	}
}
