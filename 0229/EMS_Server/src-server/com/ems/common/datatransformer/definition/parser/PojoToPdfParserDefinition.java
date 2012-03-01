package com.ems.common.datatransformer.definition.parser;

public class PojoToPdfParserDefinition extends ParserDefinition {
	
	public static final String PARSER_TYPE = "PojoToPdf";
	public String getParserType() {
		return PARSER_TYPE;
	}
	
	private String title;
	private String subject;
	private String creator;
	private String keywords;
	private boolean creationDate = false;
	private boolean producer = false;
}