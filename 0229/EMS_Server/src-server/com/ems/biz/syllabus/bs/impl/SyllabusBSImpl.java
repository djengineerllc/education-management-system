package com.ems.biz.syllabus.bs.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.syllabus.bs.ISyllabusBS;
import com.ems.common.code.Code;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;

import conf.hibernate.SyllabusBO;

@Service("syllabusBS")
public class SyllabusBSImpl implements ISyllabusBS {
	
	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;
	
	public List<SyllabusBO> findByTermId(Integer termId) throws EMSException {
		return commonDAO.findByHql("FROM SyllabusBO bo WHERE bo.termId = ? ORDER BY bo.id ASC", termId);
	}
	
	public List<SyllabusBO> findByTermIdAndClassId(Integer termId, List<Integer> classId) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("termId", termId);
		paramMap.put("classId", classId);
		
		return commonDAO.findListByHql("FROM SyllabusBO bo WHERE bo.termId = :termId AND bo.classId IN (:classId) ORDER BY bo.id ASC", paramMap);
	}
	
	public List<SyllabusBO> findByTermIdAndCourseNo(Integer termId, List<String> courseNo) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("termId", termId);
		paramMap.put("courseNo", courseNo);
		
		return commonDAO.findListByHql("FROM SyllabusBO bo WHERE bo.termId = :termId AND bo.courseNo IN (:courseNo) ORDER BY bo.id ASC", paramMap);
	}
	
	public List<SyllabusBO> findByTermIdAndTeacherId(Integer termId, List<Integer> teacherId) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("termId", termId);
		paramMap.put("teacherId", teacherId);
		
		return commonDAO.findListByHql("FROM SyllabusBO bo WHERE bo.termId = :termId AND bo.teacherId IN (:teacherId) ORDER BY bo.id ASC", paramMap);
	}

	public void submitSyllabus(Integer termId, List<SyllabusBO> boList) throws EMSException {
		commonDAO.executeHql("DELETE FROM SyllabusBO bo WHERE bo.termId = ?", termId);
		
		String syllabusStatus = null;
		if (boList != null && boList.size() > 0) {
			for (SyllabusBO bo : boList) {
				commonDAO.save(bo);
			}
			syllabusStatus = Code.getValue("Indicator", "S1");
		} else {
			syllabusStatus = Code.getValue("Indicator", "S2");
		}
		
		commonDAO.executeHql("UPDATE TermBO bo SET bo.syllabusStatus = ? WHERE bo.id = ?", 
			Arrays.asList(syllabusStatus, termId)
		);
	}
}