package com.ems.common.datatransformer.factory.object;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.springframework.util.Assert;

import com.ems.common.datatransformer.definition.ClassMetadataDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.TemplateDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.definition.parser.XmlToPojoParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.factory.object.template.ITemplateEngine;
import com.ems.common.datatransformer.util.CharsetUtil;

/**
 * @author Chiknin
 */
public class XmlToPojoTransformer extends AbstractDataTransformer<String, Object> {
	public static final String CONSTANT_TPL_PREFIX = "XmlToPojo.Template.";
	
	public static final String CONSTANT_TPL_ENGINE_OBJECTFACTORY= CONSTANT_TPL_PREFIX + "engine.objectFactory";
	public static final String CONSTANT_TPL_ENGINE_CLASSNAME	= CONSTANT_TPL_PREFIX + "engine.className";
	
	public static final String XML_DEFAULT_ENCODING = "UTF-8";
	
	private ITemplateEngine tplEngine;
	
	protected Object transformImpl(String dtName, String source,
			DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		this.initTplEngine();
		
		XmlToPojoParserDefinition xtppd = (XmlToPojoParserDefinition) pd;
		String failMode = xtppd.getFailMode();
		TemplateDefinition td = xtppd.getTemplate();
		
		source = (String) this.render(source, td, td.getRenderers(), RendererDefinition.PHASE_BEFORE);
		
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		boolean validatePassed = this.validate(source, td, td.getValidator(), failMode, validateFailExceptions);
		if (!validatePassed) {
			this.handle(null, null, dtd, dtd.getHandlers(), validateFailExceptions);
			return null;
		}
		
		String defaultEncoding = dataTransformerFactory.getConstantValue(CONSTANT_TPL_PREFIX + ".encoding", XML_DEFAULT_ENCODING); // // TODO encoding
		byte[] sourceBytes = CharsetUtil.getBytes(source, defaultEncoding);
		
		String tpl = td.getFile();
		Properties tplSettings = td.getProps();
		Object result = (Object) tplEngine.process(tpl, new ByteArrayInputStream(sourceBytes), tplSettings);
		
		result = this.render(result, td, td.getRenderers(), RendererDefinition.PHASE_AFTER);
		
		this.handle(result, source, dtd, dtd.getHandlers(), null);
		
		return result;
	}
	
	private void initTplEngine() {
		if (tplEngine == null) {
			synchronized (this) {
				if (tplEngine == null) {
					String objectFactory = dataTransformerFactory.getConstantValue(CONSTANT_TPL_ENGINE_OBJECTFACTORY, ClassMetadataDefinition.OBJECTFACTORY_NEW);
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
}