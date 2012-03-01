package com.ems.common.datatransformer.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class ValidateFailExceptions extends RuntimeException {
	
	List<ValidateFailException> validateFailExceptions = new ArrayList<ValidateFailException>();

	public ValidateFailExceptions() {
		
	}
	
	public void addException(ValidateFailException validateFailException) {
		this.validateFailExceptions.add(validateFailException);
	}
	public void addException(ValidateFailExceptions validateFailExceptions) {
		this.validateFailExceptions.addAll(validateFailExceptions.getValidateFailExceptions());
	}
	
	public boolean existsException() {
		return validateFailExceptions.size() > 0;
	}

	public List<ValidateFailException> getValidateFailExceptions() {
		return validateFailExceptions;
	}

	public void setValidateFailExceptions(
			List<ValidateFailException> validateFailExceptions) {
		this.validateFailExceptions = validateFailExceptions;
	}
}