package com.ems.biz.syllabus.bs;

import java.util.List;

import com.ems.common.exception.EMSException;

import conf.hibernate.SyllabusBO;

public interface ISyllabusBS {
	public List<SyllabusBO> findByTermId(Integer termId) throws EMSException;
	
	public List<SyllabusBO> findByTermIdAndClassId(Integer termId, List<Integer> classId) throws EMSException;
	
	public List<SyllabusBO> findByTermIdAndCourseNo(Integer termId, List<String> courseNo) throws EMSException;
	
	public List<SyllabusBO> findByTermIdAndTeacherId(Integer termId, List<Integer> teacherId) throws EMSException;

	public void submitSyllabus(Integer termId, List<SyllabusBO> boList) throws EMSException;
}
