package com.ems.common.datatransformer.api;

import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.RendererDefinition;

/**
 * @author Chiknin
 */
public interface IRenderer {
	public Object render(Object data, Definition definition, RendererDefinition rd);
}