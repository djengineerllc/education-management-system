package com.ems.common.datatransformer.renderer;

import org.apache.commons.lang.StringUtils;

import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * @author Chiknin
 */
public class PadRenderer implements IRenderer {
	public static final String STYLE_LENGTH	= "length";
	public static final String STYLE_ALIGN	= "align";
	public static final String STYLE_ALIGN_LEFT		= "left";
	public static final String STYLE_ALIGN_CENTER	= "center";
	public static final String STYLE_ALIGN_RIGHT	= "right";
	public static final String STYLE_PADCHAR = "padChar";
	
	public Object render(Object data, Definition definition, RendererDefinition rd) {
		String dataStr = data == null ? "" : data.toString();
		
		Integer length = rd.getStyleValue(STYLE_LENGTH, Integer.class);
		if (dataStr.length() < length) {
			String align = rd.getStyleValue(STYLE_ALIGN);
			String padChar = rd.getStyleValue(STYLE_PADCHAR);
			if (STYLE_ALIGN_LEFT.equalsIgnoreCase(align)) {
				dataStr = StringUtils.rightPad(dataStr, length, padChar);
			} else {
				dataStr = StringUtils.leftPad(dataStr, length, padChar);
			}
		}
		
		return dataStr;
	}
}