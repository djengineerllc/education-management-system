package com.ems.biz.syllabus.bs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.syllabus.bs.ISyllabusBS;
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
}
