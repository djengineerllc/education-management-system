package com.ems.common.datatransformer.renderer;

import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * @author Chiknin
 */
public class ReplaceRenderer implements IRenderer {
	
	public static final String STYLE_MODE = "mode";
	public static final String STYLE_MODE_FIRST = "first";
	public static final String STYLE_MODE_ALL = "all";
	
	public static final String STYLE_REGEX = "regex";
	public static final String STYLE_REPLACEMENT = "replacement";

	public Object render(Object dataObj, Definition definition, RendererDefinition rd) {
		if (dataObj == null) return null;
		
		String mode = rd.getStyleValue(STYLE_MODE);
		String regex = rd.getStyleValue(STYLE_REGEX);
		String replacement = rd.getStyleValue(STYLE_REPLACEMENT);
		
		String data = dataObj.toString();
		if (STYLE_MODE_FIRST.equals(mode)) {
			data = data.replaceFirst(regex, replacement);
		} else {
			data = data.replaceAll(regex, replacement);
		}
		
		return data;
	}

}
