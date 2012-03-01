package com.ems.common.datatransformer.definition;

/**
 * @author Chiknin
 */
public class ValidatorDefinition extends ClassMetadataDefinition {
	
	private String expr;
	private String msg;
	
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}