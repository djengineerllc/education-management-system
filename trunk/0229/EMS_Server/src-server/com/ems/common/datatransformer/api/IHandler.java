package com.ems.common.datatransformer.api;

import java.util.HashMap;
import java.util.Map;

import com.ems.common.datatransformer.definition.Definition;
import com.ems.common.datatransformer.definition.HandlerDefinition;
import com.ems.common.datatransformer.exception.ValidateFailExceptions;

/**
 * @author Chiknin
 */
public interface IHandler {
	
	public void execute(HandlerContext hCtx);
	
	static class HandlerContext implements IContext<Object> {
		
		private Object value;
		private Object valueHost;
		private Definition targetDefinition;
		private HandlerDefinition handlerDefinition;
		private Map<String, Object> attributes = new HashMap<String, Object>();
		
		private ValidateFailExceptions validateFailExceptions;
		
		public HandlerContext() {
		}

		public HandlerContext(Object value, Object valueHost,
				Definition targetDefinition,
				HandlerDefinition handlerDefinition,
				ValidateFailExceptions validateFailExceptions) {
			this(value, valueHost, targetDefinition, handlerDefinition, null, validateFailExceptions);
		}
		
		public HandlerContext(Object value, Object valueHost,
				Definition targetDefinition, HandlerDefinition handlerDefinition) {
			this(value, valueHost, targetDefinition, handlerDefinition, null, null);
		}
		
		public HandlerContext(Object value, Object valueHost,
				Definition targetDefinition,
				HandlerDefinition handlerDefinition,
				Map<String, Object> attributes,
				ValidateFailExceptions validateFailExceptions) {
			this.value = value;
			this.valueHost = valueHost;
			this.targetDefinition = targetDefinition;
			this.handlerDefinition = handlerDefinition;
			if (attributes != null) this.attributes = attributes;
			this.validateFailExceptions = validateFailExceptions;
		}

		public boolean existsException() {
			return validateFailExceptions == null ? false : validateFailExceptions.existsException();
		}
		
		public Object getAttribute(String key) {
			return attributes.get(key);
		}
		public void setAttribute(String key, Object value) {
			attributes.put(key, value);
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public Object getValueHost() {
			return valueHost;
		}
		public void setValueHost(Object valueHost) {
			this.valueHost = valueHost;
		}
		public Definition getTargetDefinition() {
			return targetDefinition;
		}
		public void setTargetDefinition(Definition targetDefinition) {
			this.targetDefinition = targetDefinition;
		}
		public HandlerDefinition getHandlerDefinition() {
			return handlerDefinition;
		}
		public void setHandlerDefinition(HandlerDefinition handlerDefinition) {
			this.handlerDefinition = handlerDefinition;
		}
		public Map<String, Object> getAttributes() {
			return attributes;
		}
		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}
		public ValidateFailExceptions getValidateFailExceptions() {
			return validateFailExceptions;
		}
		public void setValidateFailExceptions(
				ValidateFailExceptions validateFailExceptions) {
			this.validateFailExceptions = validateFailExceptions;
		}
	}
}