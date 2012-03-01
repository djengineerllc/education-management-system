package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class TextFieldSetDefinition extends DataFieldDefinition implements DataFieldSetDefinition {
	
	private String ref;
	
	private List<DataFieldDefinition> fields = new ArrayList<DataFieldDefinition>();
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public void addField(DataFieldDefinition field) {
		this.fields.add(field);
	}
	public List<DataFieldDefinition> getFields() {
		return fields;
	}
	public void setFields(List<DataFieldDefinition> fields) {
		this.fields = fields;
	}
	public List<DataFieldDefinition> getItems() {
		return fields;
	}
}