package com.ems.common.datatransformer.factory.object.template.digester;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import com.ems.common.datatransformer.factory.object.template.ITemplateEngine;

/**
 * @author Chiknin
 */
public class DigesterTemplateEngine implements ITemplateEngine {
	
	private String templateLoaderPath;
	
	public void initEngineSettings(Properties engineSettings) {
		templateLoaderPath = engineSettings.getProperty("templateLoaderPath");
	}

	public Object process(String tplPath, Object input, Properties settings) {
		if (StringUtils.hasText(templateLoaderPath)) {
			tplPath = templateLoaderPath + tplPath;
		}
		
		Digester digester = this.createDigester(tplPath, settings);
		digester.setValidating(false);
		digester.setUseContextClassLoader(true);
		
		try {
			InputStream inputStream = (InputStream) input;
			return digester.parse(inputStream);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
	
	private Digester createDigester(String tplPath, Properties settings) {
		Resource tplRes = new ClassPathResource(tplPath);
		Digester digester = null;
		try {
			digester = DigesterLoader.createDigester(tplRes.getURL());
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		return digester;
	}
}