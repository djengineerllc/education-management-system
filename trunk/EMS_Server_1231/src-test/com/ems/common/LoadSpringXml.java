package com.ems.common;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoadSpringXml extends TestCase{
	
	private static final ClassPathXmlApplicationContext context;
	
	static{
		context = new ClassPathXmlApplicationContext("/conf/spring/applicationContext.xml");
	}
	
	protected Object getBean(String beanName){
		return context.getBean(beanName);
	}
}
