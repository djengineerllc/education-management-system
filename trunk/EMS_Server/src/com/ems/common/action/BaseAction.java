package com.ems.common.action;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseAction {
	private static ClassPathXmlApplicationContext ctx;
	
	static{
		ctx = new ClassPathXmlApplicationContext("conf/spring/applicationContext.xml");
	}
	
	protected <T> T getService(Class<T> clazz) {
		return (T) ctx.getBean(clazz.getSimpleName());
	}
	protected <T> T getServiceByName(String serverName) {
		return (T) ctx.getBean(serverName);
	}

}
