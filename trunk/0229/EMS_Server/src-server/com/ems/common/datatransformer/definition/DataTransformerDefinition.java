package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

import com.ems.common.datatransformer.definition.parser.ParserDefinition;

/**
 * @author Chiknin
 */
public class DataTransformerDefinition extends Definition {
	
	private ParserDefinition parser;
	private List<HandlerDefinition> handlers = new ArrayList<HandlerDefinition>();
	
	public void setHandlers(List<HandlerDefinition> handlers) {
		this.handlers = handlers;
	}
	public ParserDefinition getParser() {
		return parser;
	}
	public void setParser(ParserDefinition parser) {
		this.parser = parser;
	}
	public void addHandler(HandlerDefinition handler) {
		handlers.add(handler);
	}
	public List<HandlerDefinition> getHandlers() {
		return handlers;
	}
}