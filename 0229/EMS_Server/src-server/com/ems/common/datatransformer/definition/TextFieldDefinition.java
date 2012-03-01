package com.ems.common.datatransformer.definition;

/**
 * @author Chiknin
 */
public class TextFieldDefinition extends DataFieldDefinition {
	
	public static final String STARTMODE_RELATIVE = "relative";
	public static final String STARTMODE_ABSOLUTE = "absolute";
	public static final String STARTMODE_CHARACTER = "character";
	
	public static final String ENDMODE_LENGTH = "length";
	public static final String ENDMODE_CHARACTER = "character";
	
	private String startMode = STARTMODE_RELATIVE;
	private String startValue = "0";
	private String endMode = ENDMODE_LENGTH;
	private String endValue;
	
	private boolean trim = true;
	
	public String getStartMode() {
		return startMode;
	}
	public void setStartMode(String startMode) {
		this.startMode = startMode;
	}
	public String getStartValue() {
		return startValue;
	}
	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}
	public String getEndMode() {
		return endMode;
	}
	public void setEndMode(String endMode) {
		this.endMode = endMode;
	}
	public String getEndValue() {
		return endValue;
	}
	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}
	public boolean isTrim() {
		return trim;
	}
	public void setTrim(boolean trim) {
		this.trim = trim;
	}
}