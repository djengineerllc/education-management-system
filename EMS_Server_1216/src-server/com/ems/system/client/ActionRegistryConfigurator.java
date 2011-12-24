package com.ems.system.client;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;

import com.softwarementors.extjs.djn.api.Registry;
import com.softwarementors.extjs.djn.config.ApiConfiguration;
import com.softwarementors.extjs.djn.scanner.Scanner;
import com.softwarementors.extjs.djn.servlet.ServletRegistryConfigurator;

/**
 * @author Chiknin
 */
public class ActionRegistryConfigurator implements ServletRegistryConfigurator {
	
	private String webRootPath = "";
	private String classesPath = "";
	private String actionRootPath = "";
	
	private String actionPrefix = "";
	
	public void configure(Registry registry, ServletConfig config) {
		webRootPath = config.getServletContext().getRealPath("/");
		classesPath = webRootPath + "/WEB-INF/classes";
		actionRootPath = classesPath + "/com/ems/client/action";
		actionPrefix = "com.ems.client.action.";
		
		try {
			List<ApiConfiguration> apiConfigurations = this.createApiConfigurations();
			
			Scanner scanner = new Scanner(registry);
			scanner.scanAndRegisterApiConfigurations(apiConfigurations);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<ApiConfiguration> createApiConfigurations() throws Exception {
		List<String> classNameList = new ArrayList<String>();
		this.fetchActionClassNameList(classNameList, new File(actionRootPath));
		
		List<ApiConfiguration> apiCfgs = new ArrayList<ApiConfiguration>();
		for (String className : classNameList) {
			apiCfgs.add(this.createApiConfiguration(className));
		}
		
		return apiCfgs;
	}
	
	private void fetchActionClassNameList(List<String> classNameList, File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				this.fetchActionClassNameList(classNameList, subFile);
			}
		} else {
			String filePath = file.getPath();
			if (filePath.endsWith(".class") && filePath.indexOf("$") == -1) {
				String className = filePath.substring(filePath.indexOf(classesPath) + classesPath.length() + 1, filePath.lastIndexOf("."));
				className = className.replace(File.separatorChar, '.');
				
				classNameList.add(className);
			}
		}
	}
	
	private ApiConfiguration createApiConfiguration(String className) throws Exception {
		String fullActionName = className.substring(className.indexOf(actionPrefix) + actionPrefix.length());
		
		String api = fullActionName;
		String apiFile = String.format("apis/ems/%ss.js", fullActionName.replace('.', File.separatorChar)) ;
		String fullGeneratedApiFile = webRootPath + apiFile;
		String apiNamespace = String.format("ems.%ss", fullActionName);
		String actionsNamespace = "";//String.format("ems.%ss.ns", fullActionName);
		
		List<Class<?>> classes = new ArrayList<Class<?>>(1);
		classes.add(Class.forName(className));
		
		return 
			new ApiConfiguration(
				api, 
				apiFile,
				fullGeneratedApiFile, 
				apiNamespace, 
				actionsNamespace, 
				classes
			);
	}
}