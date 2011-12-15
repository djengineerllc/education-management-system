package com.ems.biz.applyonline.service.impl;

import java.util.List;

import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.dao.ICommonDAO;
import conf.hibernate.Project;

public class ApplyOnlineServiceImpl implements IApplyOnlineService{
	private ICommonDAO commonDAO;
	
	public List<Project> findAllProject() throws Exception {
		return this.commonDAO.findAll(Project.class);
	}
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	

}
