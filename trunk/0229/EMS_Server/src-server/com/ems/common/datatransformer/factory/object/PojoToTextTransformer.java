package com.ems.common.datatransformer.factory.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ems.common.datatransformer.api.IDataTransformer;
import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.RepeatDefinition;
import com.ems.common.datatransformer.definition.TextFieldDefinition;
import com.ems.common.datatransformer.definition.TextFieldGroupDefinition;
import com.ems.common.datatransformer.definition.TextFieldSetDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.definition.parser.PojoToTextParserDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;
import com.ems.common.datatransformer.factory.IDataTransformerFactory;
import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 */
public class PojoToTextTransformer extends AbstractDataTransformer<Object, String> {
	
	public static boolean DEBUG = true;
	
	protected String transformImpl(String dtName, Object source, 
			DataTransformerDefinition dtd, ParserDefinition pd) 
	{
		PojoToTextParserDefinition pttpd = (PojoToTextParserDefinition) pd;
		
		StringBuilder text = new StringBuilder();
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		if (source instanceof Collection<?>) { // OTHER Map, Array
			Collection<?> sourceCollection = (Collection<?>) source;
			for (Object o : sourceCollection) {
				String line = this.lineProcess(o, pttpd, validateFailExceptions);
				text.append(line);
			}
		} else {
			String line = this.lineProcess(source, pttpd, validateFailExceptions);
			text.append(line);
		}
		this.handle(text, source, pd, dtd.getHandlers(), validateFailExceptions);
		
		return text.toString();
	}
	
	private String lineProcess(Object data, PojoToTextParserDefinition pd, ValidateFailExceptions allValidateFailExceptions) {
		if (data == null) {
			return "";
		}
		
		RepeatDefinition<DataFieldDefinition> rd = pd.getRepeat();
		
		StringBuilder line = new StringBuilder();
		ValidateFailExceptions validateFailExceptions = new ValidateFailExceptions();
		for (DataFieldDefinition dfd : rd.getFields()) {
			this.fieldProcess(data, dfd, pd, line, validateFailExceptions);
		}
		line.append(rd.getNewLineChar());
		
		boolean validatePassed = this.validate(line, rd, rd.getValidator(), pd.getFailMode(), validateFailExceptions);
		if (validatePassed) {
			this.handle(line, data, rd, rd.getHandlers(), null);
		} else {
			this.handle(null, null, rd, rd.getHandlers(), validateFailExceptions);
		}
		allValidateFailExceptions.addException(validateFailExceptions);
		
		return line.toString();
	}
	
	private void fieldProcess(Object data, DataFieldDefinition dfd, PojoToTextParserDefinition pd, StringBuilder line, ValidateFailExceptions validateFailExceptions) {
		if (dfd instanceof TextFieldSetDefinition) {
			List<DataFieldDefinition> rdfds = dataTransformerFactory.getDataFieldSet((TextFieldSetDefinition) dfd);
			for (DataFieldDefinition rdfd : rdfds) {
				this.fieldProcess(data, rdfd, pd, line, validateFailExceptions);
			}
		} else if (dfd instanceof TextFieldGroupDefinition) {
			TextFieldGroupDefinition tfgd = (TextFieldGroupDefinition) dfd;
			Object groupValue = ObjectUtil.getProperty(data, tfgd.getName(), Object.class);
			if (groupValue != null) { // TODO    index ...
				if (TextFieldDefinition.STARTMODE_CHARACTER.equalsIgnoreCase(tfgd.getStartMode())) {
					line.append(tfgd.getStartValue());
				}
				if (groupValue instanceof Collection<?>) {
					Collection<?> gvs = (Collection<?>) groupValue;
					for (Object gv : gvs) {
						for (TextFieldDefinition tfd : tfgd.getFields()) {
							this.fieldProcess(gv, tfd, pd, line, validateFailExceptions);
						}	
					}
				} else if (groupValue instanceof Object[]) {
					Object[] gvs = (Object[]) groupValue;
					for (Object gv : gvs) {
						for (TextFieldDefinition tfd : tfgd.getFields()) {
							this.fieldProcess(gv, tfd, pd, line, validateFailExceptions);
						}
					}
				} else {
					for (TextFieldDefinition tfd : tfgd.getFields()) {
						this.fieldProcess(groupValue, tfd, pd, line, validateFailExceptions);
					}
				}
				if (TextFieldDefinition.ENDMODE_CHARACTER.equalsIgnoreCase(tfgd.getEndMode())) {
					line.append(tfgd.getEndValue());
				}
			}
		} else if (dfd instanceof TextFieldDefinition) {
			TextFieldDefinition tfd = (TextFieldDefinition) dfd;
			
			Object value = null;
			if (StringUtils.isNotBlank(tfd.getName())) {
				value = ObjectUtil.getProperty(data, tfd.getName());
			} else if (ObjectUtil.isJavaType(data)) {
				value = data;
			} else {
				value = tfd.getValue();
			}
			if (value == null) {
				value = tfd.getValue();
			}
			
			value = this.render(value, tfd, tfd.getRenderers(), RendererDefinition.PHASE_BEFORE);
			
			boolean validatePassed = this.validate(value, tfd, tfd.getValidator(), pd.getFailMode(), validateFailExceptions);
			if (validatePassed) {
								
				Object result = this.render(value, tfd, tfd.getRenderers(), RendererDefinition.PHASE_AFTER); // TODO pos
				
				this.handle(result, data, tfd, tfd.getHandlers(), null);
				
				result = ObjectUtil.nullToBlankString(result);
				if (TextFieldDefinition.STARTMODE_CHARACTER.equalsIgnoreCase(tfd.getStartMode())) {
					line.append(tfd.getStartValue());
				}
				
				if (DEBUG) {
					System.out.println("----->" + StringUtils.rightPad((StringUtils.isBlank(tfd.getDescription()) ? tfd.getName() : tfd.getDescription()), 25) + " -> |" + result + "|" + tfd.getRenderers().get(0).getStyleValue("length"));
				}
				
				line.append(result);
				if (TextFieldDefinition.ENDMODE_CHARACTER.equalsIgnoreCase(tfd.getEndMode())) {
					line.append(tfd.getEndValue());
				}
			} else {
				this.handle(null, null, tfd, tfd.getHandlers(), validateFailExceptions);
			}
		}
	}
}