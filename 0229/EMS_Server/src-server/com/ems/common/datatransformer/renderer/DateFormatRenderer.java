package com.ems.common.datatransformer.renderer;

import java.util.Date;

import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.util.DateUtil;

/**
 * 
 * @author Chiknin
 *
 */
public class DateFormatRenderer implements IRenderer {
	
	public static final String STYLE_PATTERN = "pattern";
	public static final String STYLE_OLD_PATTERN = "oldPattern";

	public Object render(Object data, Definition definition, RendererDefinition rd) {
		if (data == null) {
			return null;
		}
		
		String pattern = rd.getStyleValue(STYLE_PATTERN);
		
		if (data instanceof Date) {
			return DateUtil.format((Date) data, pattern);
		} else if (data instanceof String) {
			String oldPattern = rd.getStyleValue(STYLE_OLD_PATTERN);
			return DateUtil.format((String) data, oldPattern, pattern);
		}
		
		return data;
	}
}