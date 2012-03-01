package com.ems.common.datatransformer.factory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ems.common.datatransformer.api.IDataTransformer;
import com.ems.common.datatransformer.api.IHandler;
import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.api.IValidator;
import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.DataFieldSetDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.HandlerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;
import com.ems.common.datatransformer.objectfactory.IObjectFactory;

/**
 * @author Chiknin
 */
public interface IDataTransformerFactory extends IObjectFactory {
	
	public void refresh();
	
	public DataTransformerDefinition getDataTransformerDefinition(String name);
	public void addDynamicDataTransformerDefinition(DataTransformerDefinition dtd);
	
	public IDataTransformer<?, ?> getDataTransformer(String name);
	
	public String getConstantValue(String name);
	public String getConstantValue(String name, String defaultValue);
	public <T> T getConstantValue(String name, Class<T> requiredType);
	public <T> T getConstantValue(String name, Class<T> requiredType, T defaultValue);
	public Properties getConstantsOfPrefix(String prefix);
	
	public IHandler getHandler(String name);
	public IHandler getHandler(HandlerDefinition hd);
	
	public IRenderer getRenderer(String name);
	public IRenderer getRenderer(RendererDefinition rd);
	
	public IValidator getValidator(String name);
	public IValidator getValidator(ValidatorDefinition vd);
	
	public List<DataFieldDefinition> getDataFieldSet(String name);
	public List<DataFieldDefinition> getDataFieldSet(DataFieldSetDefinition fsd);
	
	public Map<String, IObjectFactory> getObjectFactorys();
}