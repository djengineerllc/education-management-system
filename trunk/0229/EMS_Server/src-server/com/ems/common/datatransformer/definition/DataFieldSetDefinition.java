package com.ems.common.datatransformer.definition;

import java.util.List;

public interface DataFieldSetDefinition {
	public String getName();
	public String getRef();
	public List<DataFieldDefinition> getItems();
}