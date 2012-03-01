package com.ems.common.datatransformer.exception;

/**
 * @author Chiknin
 */
public class GotoException extends RuntimeException {
	
	private String gotoIndex;
	
	public GotoException() {
	}
	
	public GotoException(String gotoIndex) {
		this.gotoIndex = gotoIndex;
	}
	
	public String getGotoIndex() {
		return gotoIndex;
	}

	public void setGotoIndex(String gotoIndex) {
		this.gotoIndex = gotoIndex;
	}
}