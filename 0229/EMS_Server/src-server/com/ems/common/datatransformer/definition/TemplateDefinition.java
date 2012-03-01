package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class TemplateDefinition extends Definition {
	
	private String file;
	
	private ValidatorDefinition validator;
	private List<RendererDefinition> renderers = new ArrayList<RendererDefinition>();

	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public ValidatorDefinition getValidator() {
		return validator;
	}
	public void setValidator(ValidatorDefinition validator) {
		this.validator = validator;
	}
	public void addRenderer(RendererDefinition renderer) {
		this.renderers.add(renderer);
	}
	public List<RendererDefinition> getRenderers() {
		return renderers;
	}
	public void setRenderers(List<RendererDefinition> renderers) {
		this.renderers = renderers;
	}
}