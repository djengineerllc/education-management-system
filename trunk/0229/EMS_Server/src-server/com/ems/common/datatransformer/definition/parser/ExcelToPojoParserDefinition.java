package com.ems.common.datatransformer.definition.parser;

import java.util.ArrayList;
import java.util.List;

import com.ems.common.datatransformer.definition.SheetDefinition;

/**
 * @author Chiknin
 */
public class ExcelToPojoParserDefinition extends ParserDefinition implements ParserVersion {
	public static final String PARSER_TYPE = "ExcelToPojo";
	
	private String version = VERSION_EXCEL_2003;
	
	private List<SheetDefinition> sheets = new ArrayList<SheetDefinition>();
	
	public String getParserType() {
		return PARSER_TYPE;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public void addSheet(SheetDefinition sheet) {
		this.sheets.add(sheet);
	}
	public List<SheetDefinition> getSheets() {
		return sheets;
	}
	public void setSheets(List<SheetDefinition> sheets) {
		this.sheets = sheets;
	}
}