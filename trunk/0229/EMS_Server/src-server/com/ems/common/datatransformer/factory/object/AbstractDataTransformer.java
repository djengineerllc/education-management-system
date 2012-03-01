package com.ems.common.datatransformer.factory.object;

import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.ems.common.datatransformer.api.IDataTransformer;
import com.ems.common.datatransformer.api.IHandler;
import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.api.IValidator;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.HandlerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailException;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.factory.IDataTransformerFactory;
import com.ems.common.datatransformer.objectfactory.IObjectFactory;

/**
 * @author Chiknin
 */
public abstract class AbstractDataTransformer<S, D> implements IDataTransformer<S, D> {
	
	protected IDataTransformerFactory dataTransformerFactory;
	
	public D transform(String dtName, S source) {
		if (source == null) {
			return null;
		}
		
		DataTransformerDefinition dtd = dataTransformerFactory.getDataTransformerDefinition(dtName);
		
		return transformImpl(dtName, source, dtd, dtd.getParser());
	}
	
	protected abstract D transformImpl(String dtName, S source, DataTransformerDefinition dtd, ParserDefinition pd);
	
	protected Object getObject(String objectFactory, String className) {
		IObjectFactory factory = dataTransformerFactory.getObjectFactorys().get(objectFactory);
		Assert.notNull(factory, "not found objectFactory '" + objectFactory + "'");
		
		return factory.getObject(className);
	}
	
	protected boolean validate(Object data, Definition definition, ValidatorDefinition vd, String failMode, ValidateFailExceptions validateFailExceptions) {
		IValidator validator = dataTransformerFactory.getValidator(vd);
		if (validator != null) {
			boolean validatePassed = validator.validate(data, definition, vd);
			if (!validatePassed) {
				if (ParserDefinition.FAILMODE_EXCEPTION.equalsIgnoreCase(failMode)) {
					throw new ValidateFailException(data, definition, vd);
				} 
				else if (ParserDefinition.FAILMODE_RECORD.equalsIgnoreCase(failMode)) {
					validateFailExceptions.addException(new ValidateFailException(data, definition, vd));
				}
				
				return false;
			}
		}
		
		return true;
	}
	
	protected Object render(Object data, Definition definition, List<RendererDefinition> rds, String phase) {
		Object result = data;
		if (rds != null && rds.size() > 0) {
			for (RendererDefinition rd : rds) {
				if (phase.equals(rd.getPhase())) {
					IRenderer renderer = dataTransformerFactory.getRenderer(rd);
					if (renderer != null) {
						result = renderer.render(result, definition, rd);
					}
				}
			}
		}
		
		return result;
	}
	
	protected void handle(Object data, Object dataHost, Definition definition, List<HandlerDefinition> hds, ValidateFailExceptions validateFailExceptions) {
		this.handle(data, dataHost, definition, hds, null, validateFailExceptions);
	}
	protected void handle(Object data, Object dataHost, Definition definition, List<HandlerDefinition> hds, Map<String, Object> attributes, ValidateFailExceptions validateFailExceptions) {
		if (hds != null && hds.size() > 0) {
			for (HandlerDefinition hd : hds) {
				IHandler handler = dataTransformerFactory.getHandler(hd);
				handler.execute(
						new IHandler.HandlerContext(
								data, 
								dataHost, 
								definition, 
								hd, 
								attributes,
								validateFailExceptions
						)
				);
			}
		}
	}
	
	public void setDataTransformerFactory(IDataTransformerFactory dataTransformerFactory) {
		this.dataTransformerFactory = dataTransformerFactory;
	}
}