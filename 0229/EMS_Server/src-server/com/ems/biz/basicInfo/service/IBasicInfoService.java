package com.ems.biz.basicInfo.service;

import java.io.Serializable;
import java.util.List;

import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;

import conf.hibernate.Project;

public interface IBasicInfoService {
	
	public void save(Object obj) throws EMSRollbackableException;
	
	/**
	 * 获取指定类型全部信息
	 * @param <T>
	 * @param clazz
	 * @param orderBy 排序字符串 不用排序则用NULL
	 * @return
	 * @throws EMSException
	 */
	public <T> List<T> getAll(Class<T> clazz,String orderBy) throws EMSException;
	
	public <T>T findById(Class<T> entityClass,Serializable id) throws EMSException;
	
	public List<Project> findProjectByVO(ProjectVO projectVO) throws EMSException;
	
	public void delete(Object obj) throws EMSRollbackableException;
	
	public void update(Object obj) throws EMSRollbackableException;
	
}
