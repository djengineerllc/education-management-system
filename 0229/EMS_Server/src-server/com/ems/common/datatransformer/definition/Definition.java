package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Chiknin
 */
public class Definition {
	
	protected String name;
	protected String description;
	
	private List<PropertyDefinition> properties = new ArrayList<PropertyDefinition>();
	
	public void mergeProps(Properties srcProps, boolean overwrite) {
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addProperty(PropertyDefinition property) {
		this.properties.add(property);
	}
	public List<PropertyDefinition> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyDefinition> properties) {
		this.properties = properties;
	}
	private static final Properties EMPTY_PROPERTIES = new Properties();
	public Properties getProps() {
		if (properties == null) {
			return EMPTY_PROPERTIES;
		}
		
		Properties props = new Properties();
		for (PropertyDefinition property : properties) {
			props.put(property.getName(), property.getValue());
		}
		
		return props;
	}
}