package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class TextFieldGroupDefinition extends TextFieldDefinition {

	private String countRef;
	private String mappingClass;
	private String itemMappingClass;
	
	private List<TextFieldDefinition> fields = new ArrayList<TextFieldDefinition>();

	public String getCountRef() {
		return countRef;
	}
	public void setCountRef(String countRef) {
		this.countRef = countRef;
	}
	public String getMappingClass() {
		return mappingClass;
	}
	public void setMappingClass(String mappingClass) {
		this.mappingClass = mappingClass;
	}
	public String getItemMappingClass() {
		return itemMappingClass;
	}
	public void setItemMappingClass(String itemMappingClass) {
		this.itemMappingClass = itemMappingClass;
	}
	public void addField(TextFieldDefinition field) {
		this.fields.add(field);
	}
	public List<TextFieldDefinition> getFields() {
		return fields;
	}
	public void setFields(List<TextFieldDefinition> fields) {
		this.fields = fields;
	}
}