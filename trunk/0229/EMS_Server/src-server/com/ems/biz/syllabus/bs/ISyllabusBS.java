package com.ems.biz.syllabus.bs;

import java.util.List;

import com.ems.common.exception.EMSException;

import conf.hibernate.SyllabusBO;

public interface ISyllabusBS {
	public List<SyllabusBO> findByTermId(Integer termId) throws EMSException;
}
