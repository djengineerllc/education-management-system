package com.ems.common.datatransformer.api;

import com.ems.common.datatransformer.factory.IDataTransformerFactory;

/**
 * @author Chiknin
 */
public interface IDataTransformer<S, D> {
	public D transform(String dtName, S source);
	
	public void setDataTransformerFactory(IDataTransformerFactory dataTransformerFactory);
}