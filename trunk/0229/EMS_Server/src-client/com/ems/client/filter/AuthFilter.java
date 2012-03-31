package com.ems.client.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.ems.client.action.login.LoginAction;
import com.ems.common.model.vo.LoginInfoVO;
import com.ems.system.client.DirectAction;

/**
 * @author chiknin
 * (保留)
 */
public class AuthFilter implements Filter {
	
	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException 
	{
		HttpServletRequest request = (HttpServletRequest) req;
		String action = request.getParameter("action"); //  request.getParameter 与 DirectJNgineServlet request.getReader() 冲突
		if (StringUtils.isEmpty(action)) {
			action = request.getParameter("extAction");
		}
		if (StringUtils.isNotEmpty(action) && action.matches(config.getInitParameter("filterActionRegex"))) {
			HttpSession session = request.getSession(true);
			LoginAction loginAction = (LoginAction) session.getAttribute(DirectAction.getActionName(LoginAction.class));
			if (loginAction != null) {
				LoginInfoVO loginInfoVO = loginAction.getLoginInfo();
				if (loginInfoVO == null) {
					request.getRequestDispatcher(config.getInitParameter("loginPage") + "errorCode=notLogin&errorMsg=访问拒绝，请您重新登录系统").forward(req, resp);
					return;
				}
			}
		}
		
		filterChain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		this.config = null;
	}
}