package com.ems.system.client.vo;

import java.io.Serializable;
import java.util.Date;

import com.ems.common.util.DateUtil;

/**
 * @author Chiknin
 */
public class ExtBaseVO implements Serializable {
//	private Map<String, Map<String, String>> events = new TreeMap<String, Map<String, String>>();
	
	private String systemTime;
	
	public ExtBaseVO() {
		systemTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public String getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(String systemTime) {
		this.systemTime = systemTime;
	}
}