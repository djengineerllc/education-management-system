package com.ems.common.datatransformer.definition;


/**
 * @author Chiknin
 */
public interface IDefinitionReader<T> {
	public void read(String configPath);
	public T getDefinition();
	public ClassLoader getClassLoader();
}