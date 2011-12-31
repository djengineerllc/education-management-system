package com.ems.system.client.vo;

import java.util.Map;
import java.util.TreeMap;

import com.ems.common.util.BeanUtils;

/**
 * @author Chiknin
 */
public class ExtFormVO extends ExtBaseVO {

	private boolean success = true;
	
	private Map<String, String> errors;
	
	private Map<String, String> data;
	
	public ExtFormVO() {
	}
	
	public ExtFormVO(Map<String, String> data) {
		this.data = data;
	}
	
	public ExtFormVO(Object dataObj) {
		this.setDataFormObject(dataObj);
	}

	public ExtFormVO addField(String name, Object value) {
		if (data == null) {
			data = new TreeMap<String, String>();
		}
		
		data.put(name, (value != null ? value.toString() : ""));
		
		return this;
	}
	public ExtFormVO setDataFormObject(Object dataObj) {
		if (data == null) {
			data = new TreeMap<String, String>();
		}
		
		BeanUtils.copyBeanToMap(dataObj, data);
		
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
	public ExtFormVO setErrorsFormObject(Object dataObj) {
		if (errors == null) {
			success = false;
			errors = new TreeMap<String, String>();
		}
		
		BeanUtils.copyBeanToMap(dataObj, errors);
		
		return this;
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

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}