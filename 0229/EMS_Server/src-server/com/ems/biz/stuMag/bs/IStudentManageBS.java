package com.ems.biz.stuMag.bs;

import java.util.List;

import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.RegistrationDisVO;
import com.ems.common.model.vo.RegistrationInfoVO;

import conf.hibernate.StudentBO;

public interface IStudentManageBS {
	
	List<StudentBO> findByStudentBO(StudentBO studentBO) throws EMSException;
	
	List<RegistrationDisVO> findRegistDisVO(RegistrationInfoVO registrationInfoVO) throws EMSException;
	
	void deleteByIds(Integer[] ids) throws EMSException;
	
	void update(Object obj) throws EMSException;
	
	StudentBO findById(Integer id) throws EMSException;
	
	void create(Object obj) throws EMSException;
	
	StudentBO findByStuNo(String stuNo) throws EMSException;
}
