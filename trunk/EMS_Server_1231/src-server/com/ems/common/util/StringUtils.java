package com.ems.common.util;

import java.text.DecimalFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract  class StringUtils
{ 
	private static Log log = LogFactory.getLog(StringUtils.class);
	
	
	/**
	 * 获取标准double字符串，非科学计数法表示
	 * @param doubleObj
	 * @return
	 */
	public static String getDoubleStr(Double doubleObj){
		DecimalFormat df1 = new DecimalFormat("#.00");
		String doubleString = df1.format(doubleObj);
		return doubleString;
	}

	/**
	 * 获取系统文件分割符
	 * @return
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}
	
	
	public static boolean isNullBlank(String str)
	{
		return str == null || "".equals(str.trim());
	}
}
