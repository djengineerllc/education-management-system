package com.ems.common.datatransformer.renderer;

import java.text.DecimalFormat;

import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * 
 * @author Chiknin
 *
 */
public class NumberFormatRenderer implements IRenderer {
	
	public static final String STYLE_PATTERN = "pattern";

	public Object render(Object o, Definition definition, RendererDefinition rd) {
		if (o == null) {
			return null;
		}
		
		Double value = null;
		try {
			value = new Double(o.toString());
		} catch (Exception e) {
		}
		
		if (value == null) {
			return o;
		}
		
		String pattern = rd.getStyleValue(STYLE_PATTERN);
		return new DecimalFormat(pattern).format(value);
	}
}