package com.ems.common.datatransformer.factory.object;

import java.util.Properties;

import org.springframework.util.Assert;

import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.TemplateDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.definition.parser.PojoToTemplateParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.factory.object.template.ITemplateEngine;

/**
 * @author Chiknin
 */
public class PojoToTemplateTransformer extends AbstractDataTransformer<Object, String> {
	public static final String CONSTANT_TPL_PREFIX = "PojoToTemplate.Template.";
	
	public static final String CONSTANT_TPL_ENGINE_OBJECTFACTORY= CONSTANT_TPL_PREFIX + "engine.objectFactory";
	public static final String CONSTANT_TPL_ENGINE_CLASSNAME	= CONSTANT_TPL_PREFIX + "engine.className";
	
	private ITemplateEngine tplEngine;
	
	protected String transformImpl(String dtName, Object source,
			DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		this.initTplEngine();
		
		PojoToTemplateParserDefinition pttpd = (PojoToTemplateParserDefinition) pd;
		String failMode = pttpd.getFailMode();
		TemplateDefinition td = pttpd.getTemplate();
		
		source = this.render(source, td, td.getRenderers(), RendererDefinition.PHASE_BEFORE);
		
		String tpl = td.getFile();
		Properties tplSettings = td.getProps(); // TODO encoding
		String procResult = (String) tplEngine.process(tpl, source, tplSettings);
		
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		boolean validatePassed = this.validate(procResult, td, td.getValidator(), failMode, validateFailExceptions);
		String result = null;
		if (validatePassed) {
			result = (String) this.render(procResult, td, td.getRenderers(), RendererDefinition.PHASE_AFTER);
			this.handle(result, source, dtd, dtd.getHandlers(), null);
		} else {
			this.handle(null, null, dtd, dtd.getHandlers(), validateFailExceptions);
		}
		
		return result;
	}
	
	private void initTplEngine() {
		if (tplEngine == null) {
			synchronized (this) {
				if (tplEngine == null) {
					String objectFactory = dataTransformerFactory.getConstantValue(CONSTANT_TPL_ENGINE_OBJECTFACTORY);
					Assert.hasText(objectFactory, "constant '" + CONSTANT_TPL_ENGINE_OBJECTFACTORY + "' must not be empty");
					
					String tplEngineClassName = dataTransformerFactory.getConstantValue(CONSTANT_TPL_ENGINE_CLASSNAME);
					Assert.hasText(tplEngineClassName, "constant '" + CONSTANT_TPL_ENGINE_CLASSNAME + "' must not be empty");
					
					tplEngine = (ITemplateEngine) this.getObject(objectFactory, tplEngineClassName);
					Properties tplEngineSettings = dataTransformerFactory.getConstantsOfPrefix(CONSTANT_TPL_PREFIX);
					tplEngine.initEngineSettings(tplEngineSettings);
				}
			}
		}
	}
	
	public void setTplEngine(ITemplateEngine tplEngine) {
		this.tplEngine = tplEngine;
	}
}