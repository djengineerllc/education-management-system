package com.ems.biz.basicInfo.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;

public class BasicInfoService implements IBasicInfoService {
	
	private ICommonDAO commonDAO;

	public void save(Object obj) throws EMSRollbackableException {
		this.commonDAO.save(obj);
	}

	public <T> T findById(Class<T> entityClass, Serializable id)
			throws EMSException {
		return this.commonDAO.findById(entityClass, id);
	}

	public <T> List<T> getAll(Class<T> clazz) throws EMSException {
		return this.commonDAO.findAll(clazz);
	}
	
	public void delete(Object obj) throws EMSRollbackableException {
		this.commonDAO.delete(obj);
	}
	
	public void update(Object obj) throws EMSRollbackableException{
		this.commonDAO.update(obj);
	}

	
	/** GET **/
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

}
