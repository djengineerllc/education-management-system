package com.ems.common.datatransformer.factory.object.template;

import java.util.Properties;

/**
 * @author Chiknin
 */
public interface ITemplateEngine {
	public void initEngineSettings(Properties engineSettings);
	public Object process(String tplPath, Object data, Properties settings);
}