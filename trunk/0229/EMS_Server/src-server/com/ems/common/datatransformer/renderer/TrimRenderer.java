package com.ems.common.datatransformer.renderer;

import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * @author Chiknin
 */
public class TrimRenderer implements IRenderer {

	public Object render(Object dataObj, Definition definition, RendererDefinition rd) {
		if (dataObj == null) {
			return null;
		}
		
		return ((String) dataObj).trim();
	}
}
