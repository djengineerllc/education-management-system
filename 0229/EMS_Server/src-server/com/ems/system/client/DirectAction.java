package com.ems.system.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.softwarementors.extjs.djn.servlet.ssm.WebContext;
import com.softwarementors.extjs.djn.servlet.ssm.WebContextManager;

/**
 * @author Chiknin
 */
@SuppressWarnings("unchecked")
public class DirectAction {
	
	protected Logger logger = Logger.getLogger(this.getClass()); 
	
	protected ApplicationContext appCtx;
	
	public DirectAction() {
		this.appCtx = WebApplicationContextUtils.getWebApplicationContext(this.getApplication()); //WebApplicationContextUtils.getRequiredWebApplicationContext(this.getApplication());
	}
	
	public WebContext getWebContext() {
		return WebContextManager.get();
	}
	
	public <T extends DirectAction> T getAction(Class<T> actionClass) {
		String actionName = this.getActionName(actionClass);
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
	public <T> T getBean(String beanName, Class<T> requireType) {
		return this.appCtx.getBean(beanName, requireType);
	}
	public <T> T getBean(Class<T> beanClass) {
		return this.appCtx.getBean(beanClass);
	}
	
	public ApplicationContext getAppCtx() {
		return this.appCtx;
	}
	
	protected void download(HttpServletResponse response, byte[] data, String fileName) throws IOException {
		if (StringUtils.isNotBlank(fileName)) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		
		response.setContentType("application/x-msdownload");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			
			response.setContentLength(data.length);
			sos.write(data);
			sos.flush();
		} finally {
			if (sos != null) {
				sos.close();
			}
		}
	}
	
	protected void downloadFile(HttpServletResponse response, String filePath, String fileName) throws IOException {
		InputStream is = null;
		byte[] data = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath); // .getResourceAsStream("conf/templates/excel/用户表格.xls");
			data = new byte[is.available()]; // TODO 
			is.read(data);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		
		this.download(response, data, fileName);
	}
	
	protected void writeToResponse(HttpServletResponse response, byte[] data) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
			
			response.setContentLength(data.length);
			sos.write(data);
			sos.flush();
		} finally {
			if (sos != null) {
				sos.close();
			}
		}
	}
}