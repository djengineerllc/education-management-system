package com.ems.common.datatransformer.definition.support;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chiknin
 */
public class DefaultDefinitionRegistry<T> implements IDefinitionRegistry<T> {
	private Map<String, T> definitions = new HashMap<String, T>();
	
	public void register(String name, T definition) {
		definitions.put(name, definition);
	}
	
	public boolean containsName(String name) {
		return definitions.containsKey(name);
	}
	
	public void clear() {
		definitions.clear();
	}
	
	public T remove(String name) {
		return definitions.remove(name);
	}

	public T get(String name) {
		return definitions.get(name);
	}
}