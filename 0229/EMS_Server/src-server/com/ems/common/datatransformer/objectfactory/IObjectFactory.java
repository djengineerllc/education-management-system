package com.ems.common.datatransformer.objectfactory;

/**
 * @author Chiknin
 */
public interface IObjectFactory {
	public static final String OBJECT_FACTORY_NEW		= "new";
	public static final String OBJECT_FACTORY_SPRING	= "spring";
	
	public String getObjectFactoryName();
	public Object getObject(String name);
}