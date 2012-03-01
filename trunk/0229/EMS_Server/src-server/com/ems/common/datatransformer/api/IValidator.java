package com.ems.common.datatransformer.api;

import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;

/**
 * @author Chiknin
 */
public interface IValidator {
	public boolean validate(Object data, Definition definition, ValidatorDefinition vd);
}
