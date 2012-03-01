package com.ems.common.datatransformer.exception;

import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;

/**
 * @author Chiknin
 */
public class ValidateFailException extends RuntimeException {
	
	private Object data;
	private Definition definition;
	private ValidatorDefinition validatorDefinition;

	public ValidateFailException(Object data, Definition definition, ValidatorDefinition validatorDefinition) {
		super(validatorDefinition.getMsg());
		this.data = data;
		this.definition = definition;
		this.validatorDefinition = validatorDefinition;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Definition getDefinition() {
		return definition;
	}
	public void setDefinition(Definition definition) {
		this.definition = definition;
	}
	public ValidatorDefinition getValidatorDefinition() {
		return validatorDefinition;
	}
	public void setValidatorDefinition(ValidatorDefinition validatorDefinition) {
		this.validatorDefinition = validatorDefinition;
	}
}