package com.ems.system;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.softwarementors.extjs.djn.servlet.ssm.WebContext;
import com.softwarementors.extjs.djn.servlet.ssm.WebContextManager;

/**
 * @author Chiknin
 */
public class DirectAction {
	
	protected ApplicationContext appCtx;
	
	public DirectAction() {
		this.appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getApplication());
	}
	
	public WebContext getWebContext() {
		return WebContextManager.get();
	}
	
	public HttpServletRequest getRequest() {
		return this.getWebContext().getRequest();
	}
	
	public HttpServletResponse getResponse() {
		return this.getWebContext().getResponse();
	}
	
	public HttpSession getSession() {
		return this.getWebContext().getSession();
	}
	
	public ServletContext getApplication() {
		return this.getWebContext().getServletContext();
	}
	
	public Object getBean(String beanName) {
		return this.appCtx.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName, Class<T> requireType) {
		return (T) this.appCtx.getBean(beanName, requireType);
	}
	
	public ApplicationContext getAppCtx() {
		return this.appCtx;
	}
}