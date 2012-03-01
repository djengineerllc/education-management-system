package com.ems.common.code.cache;

/**
 * 
 * @author Chiknin
 * 
 */
public interface ICache {
	public void put(String key, Object value);

	public Object get(String key);

	public void remove(String key);

	public void removeAll();
}