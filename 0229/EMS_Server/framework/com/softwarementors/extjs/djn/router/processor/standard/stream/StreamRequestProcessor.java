package com.softwarementors.extjs.djn.router.processor.standard.stream;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.GlobalConfiguration;
import com.softwarementors.extjs.djn.router.dispatcher.Dispatcher;
import com.softwarementors.extjs.djn.router.processor.standard.StandardRequestProcessorBase;

/**
 * @author chiknin
 */
public class StreamRequestProcessor extends StandardRequestProcessorBase {
	
	public static final String PATHINFO_STREAM_PREFIX = "/stream/"; 

	private static Logger logger = Logger.getLogger(StreamRequestProcessor.class);

	public StreamRequestProcessor(Registry registry, Dispatcher dispatcher,
			GlobalConfiguration globalConfiguration) {
		super(registry, dispatcher, globalConfiguration);
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		String method = request.getParameter("method");
		
		try {
			Object[] parameters = new Object[] { request, response };
			dispatchStandardMethod(action, method, parameters);
		} catch (Throwable t) {
			logger.error(t.getMessage(), t);
			
			response.setContentType("text/html; charset=utf-8");
			if (t instanceof RuntimeException) {
				throw (RuntimeException) t;
			} else {
				throw new RuntimeException(t.getMessage(), t);
			}
			
//			Throwable reportedException = RequestProcessorUtils.getExceptionToReport(t);
//			String message = RequestProcessorUtils.getExceptionMessage(reportedException);
//			String where = RequestProcessorUtils.getExceptionWhere(reportedException, getDebug());
//			response.setContentType("text/html; charset=utf-8");
//			response.reset();
//			PrintWriter pw = response.getWriter();
//			pw.write(
//				"<SCRIPT LANGUAGE=\"JavaScript\">" +
//					"alert(\"System Error\\nerror message:" + t.getMessage().replaceAll("'|\"", "\\"") + "'))" +
//				"</SCRIPT>"
//			);
//			pw.flush();
//			pw.close();
		}
	}
}