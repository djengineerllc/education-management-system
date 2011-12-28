package com.ems.system.client;

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
@SuppressWarnings("unchecked")
public class DirectAction {
	
	protected ApplicationContext appCtx;
	
	public DirectAction() {
		this.appCtx = WebApplicationContextUtils.getWebApplicationContext(this.getApplication()); //WebApplicationContextUtils.getRequiredWebApplicationContext(this.getApplication());
	}
	
	public WebContext getWebContext() {
		return WebContextManager.get();
	}
	
	public <T extends DirectAction> T getAction(Class<T> actionClass) {
		String actionName = this.getActionName(actionClass);
		System.out.println(actionName);
		Object action = this.getWebContext().getSessionScopedObject(actionName);
		if (action == null) {
			action = this.getWebContext().getApplicationScopedObject(actionName);
		}
		
		return (T) action;
	}
	
	public String getActionName(Class actionClass) {
		String className = actionClass.getName();
		String actionPrefix = "com.ems.client.action.";
		return String.format("ems.%ss", className.substring(className.indexOf(actionPrefix) + actionPrefix.length())).replace('.', '_') + '_' + actionClass.getSimpleName();
	}
	
	public String getParameter(String name) {
		return this.getRequest().getParameter(name);
	}
	
	public String[] getParameterValues(String name) {
		return this.getRequest().getParameterValues(name);
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