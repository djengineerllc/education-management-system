package com.ems.common.code.cache.impl;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.DisposableBean;

import com.ems.common.code.cache.ICache;

/**
 * @author Chiknin
 */
public class EhCacheImpl implements ICache, DisposableBean {

	private Cache cache;

	public void destroy() throws Exception {
		cache.dispose();
	}

	public void put(String key, Object value) {
		cache.put(new Element(key, value));
	}

	public Object get(String key) {
		Element el = cache.get(key);
		if (el != null) {
			return el.getValue();
		}

		return null;
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public void removeAll() {
		cache.removeAll();
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}
}