package com.ems.common.datatransformer.validator;

import com.ems.common.datatransformer.api.IValidator;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;

/**
 * @author Chiknin
 */
public class XsdValidator implements IValidator {

	public boolean validate(Object dataObj, Definition definition, ValidatorDefinition vd) {
		if (dataObj == null) return false;
		
		String xml = (String) dataObj;
		
		return true;
	}
}