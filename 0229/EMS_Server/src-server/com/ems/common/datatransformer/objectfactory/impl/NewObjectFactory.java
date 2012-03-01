package com.ems.common.datatransformer.objectfactory.impl;

import com.ems.common.datatransformer.objectfactory.IObjectFactory;
import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 */
public class NewObjectFactory implements IObjectFactory {
	
	public String getObjectFactoryName() {
		return OBJECT_FACTORY_NEW;
	}
	
	public Object getObject(String name) {
		return ObjectUtil.instance(name);
	}
}