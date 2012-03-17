package com.ems.biz.basicInfo.service;

import java.io.Serializable;
import java.util.List;

import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;

public interface IBasicInfoService {
	
	public void save(Object obj) throws EMSRollbackableException;
	
	public <T> List<T> getAll(Class<T> clazz) throws EMSException;
	
	public <T>T findById(Class<T> entityClass,Serializable id) throws EMSException;
	
	public void delete(Object obj) throws EMSRollbackableException;
	
	public void update(Object obj) throws EMSRollbackableException;

}
