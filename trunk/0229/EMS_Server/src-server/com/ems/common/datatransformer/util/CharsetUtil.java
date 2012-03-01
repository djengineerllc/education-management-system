package com.ems.common.datatransformer.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

/**
 * @author chiknin
 */
public class CharsetUtil {
	public static String getString(byte[] bytes, String charset) {
		if (bytes == null) 
			return null;
		
		if (StringUtils.isNotBlank(charset)) {
			try {
				return new String(bytes, charset);
			} catch (UnsupportedEncodingException e) {
				return new String(bytes);
			}
		} else {
			return new String(bytes);
		}
	}
	
	public static String getString(String str, String srcCharest, String descCharset) {
		byte[] bytes = getBytes(str, srcCharest);
		if (bytes != null) {
			return getString(bytes, descCharset);
		}
		
		return str;
	}
	
	public static byte[] getBytes(String str, String charset) {
		if (str == null) 
			return null;
		
		if (StringUtils.isNotBlank(charset)) {
			try {
				return str.getBytes(charset);
			} catch (UnsupportedEncodingException e) {
				return str.getBytes();
			}
		} else {
			return str.getBytes();
		}
	}
}
