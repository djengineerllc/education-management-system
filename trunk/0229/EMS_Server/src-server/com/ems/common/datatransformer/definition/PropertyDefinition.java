package com.ems.common.datatransformer.definition;

/**
 * @author Chiknin
 */
public class PropertyDefinition extends Definition {
	
	private String value;
	private String type;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}