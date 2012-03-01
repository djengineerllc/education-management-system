package com.ems.common.datatransformer.definition.parser;

import com.ems.common.datatransformer.definition.TemplateDefinition;

/**
 * @author Chiknin
 */
public class XmlToPojoParserDefinition extends ParserDefinition {
	public static final String PARSER_TYPE = "XmlToPojo";
	
	private TemplateDefinition template;
	
	public String getParserType() {
		return PARSER_TYPE;
	}
	
	public TemplateDefinition getTemplate() {
		return template;
	}

	public void setTemplate(TemplateDefinition template) {
		this.template = template;
	}
}