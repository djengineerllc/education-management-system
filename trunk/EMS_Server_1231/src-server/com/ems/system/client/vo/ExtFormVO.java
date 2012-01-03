package com.ems.system.client.vo;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Chiknin
 */
@SuppressWarnings("unchecked")
public class ExtFormVO extends ExtBaseVO {

	private boolean success = true;

	private Map<String, String> errors;

	private Object data;
	
	private Map<String, String> props;
	
	public ExtFormVO() {
	}

	public ExtFormVO(Object data) {
		super();
		this.data = data;
	}

	public ExtFormVO addField(String name, Object value) {
		if (data == null) {
			data = new TreeMap<String, String>();
		}

		((Map<String, String>) data).put(name, value != null ? value.toString(): null);

		return this;
	}

	public ExtFormVO addError(String fieldName, String errorMsg) {
		if (errors == null) {
			success = false;
			errors = new TreeMap<String, String>();
		}
		errors.put(fieldName, errorMsg);

		return this;
	}
	
	public ExtFormVO addProp(String fieldName, Object prop) {
		if (props == null) {
			props = new TreeMap<String, String>();
		}
		props.put(fieldName, prop != null ? prop.toString(): null);

		return this;
	}

	public static ExtFormVO success(Object dataObj) {
		return new ExtFormVO(dataObj);
	}
	public static ExtFormVO success() {
		return new ExtFormVO();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, String> getProps() {
		return props;
	}

	public void setProps(Map<String, String> props) {
		this.props = props;
	}
}