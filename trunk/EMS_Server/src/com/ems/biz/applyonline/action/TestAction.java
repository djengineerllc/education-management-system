package com.ems.biz.applyonline.action;

import com.ems.biz.applyonline.service.ITestService;

import conf.hibernate.TestDemo;



public class TestAction {
	
	private ITestService testService;
	
	
	public void saveTestDemo(TestDemo testDemo) throws Exception {
		this.testService.saveTestDemo(testDemo);
	}
	
	public void queryTestDemo(TestDemo testDemo) throws Exception {
		this.testService.saveTestDemo(testDemo);
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}
	
	
	
	
}
