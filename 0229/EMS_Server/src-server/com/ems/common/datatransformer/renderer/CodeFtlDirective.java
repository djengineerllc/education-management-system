package com.ems.common.datatransformer.renderer;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.ems.common.code.Code;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * @author Chiknin
 *
 */
public class CodeFtlDirective implements TemplateDirectiveModel {
	
	public static final String NODE_ATTR_MODE = "mode";
	public static final String NODE_ATTR_MODE_GET_VALUE = "getValue";
	public static final String NODE_ATTR_MODE_GET_NAME = "getName";
	public static final String NODE_ATTR_MODE_GET_NAME_BY_VALUE	= "getNameByValue";
	public static final String NODE_ATTR_MODE_GET_VALUE_BY_NAME	= "getValueByName";
	
	public static final String NODE_ATTR_CODE_TYPE	= "codeType";
	
	public static final String NODE_ATTR_DATA	= "data";

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) 
		throws TemplateException, IOException 
	{
		String mode = (String) params.get(NODE_ATTR_MODE);
		String codeType = ((SimpleScalar) params.get(NODE_ATTR_CODE_TYPE)).getAsString();
		String data = ((SimpleScalar) params.get(NODE_ATTR_DATA)).getAsString();
		
		String result = null;
		if (NODE_ATTR_MODE_GET_VALUE.equalsIgnoreCase(mode)) {
			result = Code.getValue(codeType, data);
		} else if(NODE_ATTR_MODE_GET_NAME.equalsIgnoreCase(mode)) { 
			result = Code.getName(codeType, data);
		} else if (NODE_ATTR_MODE_GET_VALUE_BY_NAME.equalsIgnoreCase(mode)) {
			result = Code.getValueByName(codeType, data);
		} else { // NODE_ATTR_MODE_GET_NAME_BY_VALUE
			result = Code.getNameByValue(codeType, data);
		}
		
		Writer out = env.getOut();
		
		out.write(result);
	}
}