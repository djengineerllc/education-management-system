package com.ems.common.datatransformer.renderer;

import com.ems.common.code.Code;
import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * 
 * @author Chiknin
 *
 */
public class CodeRenderer implements IRenderer {
	
	public static final String STYLE_MODE = "mode";
	public static final String STYLE_MODE_GET_VALUE = "getValue";
	public static final String STYLE_MODE_GET_NAME = "getName";
	public static final String STYLE_MODE_GET_NAME_BY_VALUE	= "getNameByValue";
	public static final String STYLE_MODE_GET_VALUE_BY_NAME	= "getValueByName";
	
	public static final String STYLE_CODE_TYPE	= "codeType";

	public Object render(Object data, Definition definition, RendererDefinition rd) {
		String dataStr = (data == null ? null : data.toString());
		
		String mode = rd.getStyleValue(STYLE_MODE);
		String codeType = rd.getStyleValue(STYLE_CODE_TYPE);
		
		String result = null;
		if (STYLE_MODE_GET_VALUE.equalsIgnoreCase(mode)) {
			result = Code.getValue(codeType, dataStr);
		} else if (STYLE_MODE_GET_NAME_BY_VALUE.equalsIgnoreCase(mode)) {
			result = Code.getNameByValue(codeType, dataStr);
		} else if (STYLE_MODE_GET_VALUE_BY_NAME.equalsIgnoreCase(mode)) {
			result = Code.getValueByName(codeType, dataStr);
		} else { // STYLE_MODE_GET_NAME
			result = Code.getName(codeType, dataStr);
		}
		
		return result;
	}
}