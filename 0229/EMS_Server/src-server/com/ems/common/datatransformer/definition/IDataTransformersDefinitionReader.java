package com.ems.common.datatransformer.definition;

import java.util.List;

/**
 * @author Chiknin
 */
public interface IDataTransformersDefinitionReader extends IDefinitionReader<DataTransformersDefinition> {
	public DataTransformersDefinition getDataTransformers();
	public ConstantDefinition getConstant(String name);
	public List<ConstantDefinition> getConstants();
	public HandlerDefinition getHandler(String name);
	public RendererDefinition getRenderer(String name);
	public ValidatorDefinition getValidator(String name);
	public DataFieldSetDefinition getDataFieldSet(String name);
	public DataTransformerDefinition getDataTransformer(String name);
	
	public void addDataTransformerDefinition(DataTransformerDefinition dtd);
}