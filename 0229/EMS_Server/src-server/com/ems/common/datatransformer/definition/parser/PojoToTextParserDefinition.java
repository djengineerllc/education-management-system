package com.ems.common.datatransformer.definition.parser;

import com.ems.common.datatransformer.definition.DataFieldDefinition;

/**
 * @author Chiknin
 */
public class PojoToTextParserDefinition extends RepeatDataFieldParserDefinition<DataFieldDefinition> {
	
	public static final String PARSER_TYPE = "PojoToText";
	
	public String getParserType() {
		return PARSER_TYPE;
	}
}