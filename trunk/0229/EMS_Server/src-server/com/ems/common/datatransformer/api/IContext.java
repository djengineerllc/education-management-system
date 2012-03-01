package com.ems.common.datatransformer.api;

/**
 * @author Chiknin
 */
public interface IContext<T> {
	public T getAttribute(String key);
	public void setAttribute(String key, T value);
}