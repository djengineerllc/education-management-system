package com.ems.common.datatransformer.definition.parser;

import com.ems.common.datatransformer.definition.DataFieldDefinition;

/**
 * @author Chiknin
 */
public class TextToPojoParserDefinition extends RepeatDataFieldParserDefinition<DataFieldDefinition> {
	
	public static final String PARSER_TYPE = "TextToPojo";
	
	public static final String LENGTH_BY_CHAR = "char";
	public static final String LENGTH_BY_BYTE = "byte";
	
	private String lengthBy = LENGTH_BY_CHAR;
	private String lengthByByteCharset; // = System.getProperty("file.encoding");

	public String getParserType() {
		return PARSER_TYPE;
	}

	public String getLengthBy() {
		return lengthBy;
	}

	public void setLengthBy(String lengthBy) {
		this.lengthBy = lengthBy;
	}

	public String getLengthByByteCharset() {
		return lengthByByteCharset;
	}

	public void setLengthByByteCharset(String lengthByByteCharset) {
		this.lengthByByteCharset = lengthByByteCharset;
	}
}