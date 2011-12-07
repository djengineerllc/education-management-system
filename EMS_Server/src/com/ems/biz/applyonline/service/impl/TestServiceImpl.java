package com.ems.biz.applyonline.service.impl;

import com.ems.biz.applyonline.service.ITestService;
import com.ems.common.dao.ICommonDAO;
import conf.hibernate.TestDemo;

public class TestServiceImpl implements ITestService {
	
	private ICommonDAO commonDAO;

	public TestDemo findTestDemo() throws Exception {
		return commonDAO.findById(TestDemo.class, "test001");
	}


	public void updateTestDemo(TestDemo testDemo) throws Exception {
		this.commonDAO.update(testDemo);
		
	}
	public void saveTestDemo(TestDemo testDemo) throws Exception {
		this.commonDAO.save(testDemo);
	}
	
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}


	



	
	

}
