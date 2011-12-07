package com.ems.biz.applyonline.service;

import conf.hibernate.TestDemo;

public interface ITestService {
	
	public TestDemo findTestDemo() throws Exception;
	
	public void updateTestDemo(TestDemo testDemo)throws Exception;
	
	public void saveTestDemo(TestDemo testDemo)throws Exception;

}
