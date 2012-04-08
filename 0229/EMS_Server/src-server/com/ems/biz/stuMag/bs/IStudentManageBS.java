package com.ems.biz.stuMag.bs;

import java.util.List;

import com.ems.common.exception.EMSException;

import conf.hibernate.StudentBO;

public interface IStudentManageBS {
	
	List<StudentBO> findByStudentBO(StudentBO studentBO) throws EMSException;
	
	void deleteByIds(Integer[] ids) throws EMSException;
	
	void update(Object obj) throws EMSException;
	
	StudentBO findById(Integer id) throws EMSException;
	
	void create(Object obj) throws EMSException;
}
