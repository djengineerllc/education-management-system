package com.ems.biz.basicInfo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.util.StringUtils;

import conf.hibernate.Project;

public class BasicInfoService implements IBasicInfoService {
	
	private ICommonDAO commonDAO;

	public void save(Object obj) throws EMSRollbackableException {
		this.commonDAO.save(obj);
	}

	public <T> T findById(Class<T> entityClass, Serializable id)
			throws EMSException {
		return this.commonDAO.findById(entityClass, id);
	}
	
	public List<Project> findProjectByVO(ProjectVO projectVO) throws EMSException{
		String hql = "from Project where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(projectVO.getProjectName())){
			hql = hql+" and projectName like ? "; 
			valueParam.add("%"+projectVO.getProjectName()+"%");
		}
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}

	public <T> List<T> getAll(Class<T> clazz,String orderBy) throws EMSException {
		return this.commonDAO.findAll(clazz,orderBy);
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
