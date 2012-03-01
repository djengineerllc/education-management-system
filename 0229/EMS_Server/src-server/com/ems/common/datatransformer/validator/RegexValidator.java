package com.ems.common.datatransformer.validator;

import com.ems.common.datatransformer.api.IValidator;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;

/**
 * @author Chiknin
 */
public class RegexValidator implements IValidator {

	public boolean validate(Object data, Definition definition, ValidatorDefinition vd) {
		return true;
	}
}