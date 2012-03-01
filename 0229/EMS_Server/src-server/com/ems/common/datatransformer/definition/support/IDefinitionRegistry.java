package com.ems.common.datatransformer.definition.support;


/**
 * @author Chiknin
 */
public interface IDefinitionRegistry<T> {
	public void register(String name, T definition);
	public boolean containsName(String name);
	public void clear();
	public T remove(String name);
	public T get(String name);
}