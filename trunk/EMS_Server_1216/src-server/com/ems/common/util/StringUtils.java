package com.ems.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

	public static boolean isNumber(String str)
	{
		if (isNullBlank(str))
			return false;
		int index = 0;
		for (int length = str.length(); index < length; index++)
			if (!Character.isDigit(str.charAt(index)))
				return false;

		return true;
	}

	public static int getInt(String intStr, int intDef)
	{
		if (isNullBlank(intStr))
		{
			return intDef;
		} else
		{
			int intRet = intDef;
			Double db = new Double(intStr);
			intRet = db.intValue();
			return intRet;
		}
	}

	public static int getInt(String intStr)
	{
		return getInt(intStr, 0);
	}

	public static double getDouble(String dbStr, double dbDef)
	{
		if (isNullBlank(dbStr))
		{
			return dbDef;
		} else
		{
			double dbRet = dbDef;
			Double db = new Double(dbStr);
			dbRet = db.doubleValue();
			return dbRet;
		}
	}

	public static double getDouble(String dbStr)
	{
		return getDouble(dbStr, 0.0D);
	}

	public static long getLong(String longStr, long longDef)
	{
		if (isNullBlank(longStr))
		{
			return longDef;
		} else
		{
			long longRet = longDef;
			Double db = new Double(longStr);
			longRet = db.longValue();
			return longRet;
		}
	}

	public static long getLong(String longStr)
	{
		return getLong(longStr, 0L);
	}

	public static boolean getBoolean(String booleanStr, boolean booleanDef)
	{
		if (isNullBlank(booleanStr))
			return booleanDef;
		boolean booleanRet = booleanDef;
		if ("true".equalsIgnoreCase(booleanStr))
			booleanRet = true;
		else
		if ("false".equalsIgnoreCase(booleanStr))
			booleanRet = false;
		return booleanRet;
	}

	public static boolean getBoolean(String booleanStr)
	{
		return getBoolean(booleanStr, false);
	}

	public static String htmlEncode(String str)
	{
		String retu = null;
		if (str == null || "".equals(str.trim()))
		{
			retu = str;
		} else
		{
			String html = str;
			html = replace(html, "&", "&amp;");
			html = replace(html, "<", "&lt;");
			html = replace(html, ">", "&gt;");
			html = replace(html, "\r\n", "\n");
			html = replace(html, "\n", "<br>");
			html = replace(html, "\t", "    ");
			html = replace(html, " ", "&nbsp;");
			html = replace(html, "\"", "&quot;");
			retu = html;
			html = null;
		}
		return retu;
	}

	public static String replace(String inString, String oldPattern, String newPattern)
	{
		if (inString == null)
			return null;
		if (oldPattern == null || newPattern == null)
			return inString;
		StringBuffer sbuf = new StringBuffer();
		int pos = 0;
		int index = inString.indexOf(oldPattern);
		int patLen = oldPattern.length();
		for (; index >= 0; index = inString.indexOf(oldPattern, pos))
		{
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
		}

		sbuf.append(inString.substring(pos));
		return sbuf.toString();
	}

	public static String[] split(String sourceStr, String splitStr)
	{
		if (sourceStr == null || splitStr == null)
			return null;
		if (sourceStr.equals("") || splitStr.equals(""))
			return null;
		int int_ArraySize = subStringCount(sourceStr, splitStr);
		if (int_ArraySize == -1)
			return null;
		sourceStr = (new StringBuilder(String.valueOf(sourceStr))).append(splitStr).toString();
		String str_RetArr[] = new String[int_ArraySize + 1];
		int int_pos = sourceStr.indexOf(splitStr);
		int int_ArrayPos = 0;
		for (; int_pos != -1; int_pos = sourceStr.indexOf(splitStr))
		{
			str_RetArr[int_ArrayPos++] = sourceStr.substring(0, int_pos);
			sourceStr = sourceStr.substring(int_pos + splitStr.length());
		}

		return str_RetArr;
	}

	
	/**
	 * 获取固定长度字符串，长度剩余用空格填充，长度超过进行截取
	 * @param target
	 * @param length
	 * @return
	 */
	public static String getFixLengthStr(String target,int length){
		if(isNullString(target)){
			return getBlankStringByLength(length);
		}else{
			byte[] targetBytes = target.getBytes();
			byte[] blankBytes = (getBlankStringByLength(length)).getBytes();
			byte[] transBytes = new byte[blankBytes.length];
			if(targetBytes.length<blankBytes.length){
				for (int i = 0; i < targetBytes.length; i++) {
					transBytes[i]=targetBytes[i];
				}
				for(int i=0;i<blankBytes.length-targetBytes.length;i++){
					transBytes[targetBytes.length+i]=blankBytes[i];
				}
			}else{
				for (int i = 0; i < blankBytes.length; i++) {
					transBytes[i]=targetBytes[i];
				}
			}
			return new String(transBytes);
		}
	}
	
	/**
	 * 获取指定长度空字符串
	 * @param length
	 */
	public static String getBlankStringByLength(int length){
		String targetStr = "";
		for(int i=0;i<length;i++){
			targetStr+=" ";
		}
		return targetStr;
	}
	
	
	/**
	* 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
	* @param s 原文件名
	* @return 重新编码后的文件名
	*/
	public static String toUtf8String(String s) {
	  StringBuffer sb = new StringBuffer();
		 for (int i=0;i<s.length();i++)
		 {
			 char c = s.charAt(i);
		     if (c >= 0 && c <= 255) {
		        sb.append(c);
		     }else {
			    byte[] b;
			    try {
			    	b = Character.toString(c).getBytes("utf-8");
		        }catch (Exception ex) {
		        	log.error(ex);
		        	b = new byte[0];
		        }
				for (int j = 0; j < b.length; j++) {
				    int k = b[j];
				    if (k < 0) k += 256;
				    sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
		     }
		}
		return sb.toString();
	}
	
	
	/**
	 * 打印字符串类型的数组信息
	 */
	public static String printListString(List<String> strList){
		StringBuffer tempBuf = new StringBuffer();
		for (int i = 0; i < strList.size(); i++) {
			tempBuf.append(strList.get(i));
			if(i!=strList.size()-1){
				tempBuf.append(",");
			}
		}
		return tempBuf.toString(); 
	}
	
	/**
	 * 打印字符串类型的数组信息 
	 */
	public static String printListString(Object[] strList){
		StringBuffer tempBuf = new StringBuffer();
		for (int i = 0; i < strList.length; i++) {
			tempBuf.append(strList[i]);
			if(i!=strList.length-1){
				tempBuf.append(",");
			}
		}
		return tempBuf.toString();
	}
	
	
	/**
	 * 获取参数值
	 * @param url 路径
	 * @param paraName 参数名称
	 * @return 参数值
	 */
	public static String getUrlParaValue(String url,String paraName){
		
		int index_0 = url.indexOf("?");
		if(index_0>0){
			//取得参数起始位置
			//url为?paraName=value1&2paraName=value2形式，判断?paraName=
			int index_1 = url.indexOf("?"+paraName+"=",index_0);
			if(index_1<0){
				//url为?1paraName=value1&paraName=value2形式，判断&paraName=
				index_1 = url.indexOf("&"+paraName+"=",index_0);
			}
			if(index_1>0){
				//url为?paraName=value1&2paraName=value2形式,判断后面是否有&
				int index_end_1 = url.indexOf("&",index_1+paraName.length()+2);
				//url为?1paraName=value1&paraName=value2形式
				if(index_end_1<0){
					index_end_1 = url.length();
				}
				if(index_end_1>0){
					String paraValue = url.substring(index_1+paraName.length()+2,index_end_1);
					if(StringUtils.isNullString(paraValue)){
						return "";
					}
					return paraValue.trim();
				}
			}
		}
		return "";
	}
	
	
	
	public static int subStringCount(String sourceStr, String subStr)
	{
		if (sourceStr == null || subStr == null)
			return -1;
		if (sourceStr.equals("") || subStr.equals(""))
			return -1;
		int result = 0;
		for (int int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase()); int_pos != -1; int_pos = sourceStr.toUpperCase().indexOf(subStr.toUpperCase(), int_pos + subStr.length()))
			result++;

		return result;
	}

	public static String arrayToString(String array[], String splitStr)
	{
		if (array == null || array.length == 0)
			return "";
		if (splitStr == null)
			splitStr = "";
		StringBuffer strBuf = new StringBuffer("");
		int Len = array.length;
		for (int i = 0; i < Len - 1; i++)
			strBuf.append(array[i] == null ? "" : array[i]).append(splitStr);

		strBuf.append(array[Len - 1] == null ? "" : array[Len - 1]);
		return strBuf.toString();
	}

	public static String arrayToString(String array[])
	{
		return arrayToString(array, "|");
	}

	public static String getNumFormat(double db, String fmt)
	{
		String retu = "";
		if (fmt == null || "".equals(fmt.trim()))
			return Double.toString(db);
		try
		{
			DecimalFormat decimalformat = new DecimalFormat(fmt);
			retu = decimalformat.format(db);
			decimalformat = null;
		}
		catch (IllegalArgumentException iaex)
		{
			retu = Double.toString(db);
		}
		return retu;
	}

	public static String getNumFormat(double db)
	{
		return getNumFormat(db, "0.00");
	}
	
	
	public static String getStringFromNum(long number, int destLength) {
		String numStr = String.valueOf(number);
		int numStrLen = numStr.length();
		if (numStrLen < destLength) {
			for (int index = 0; index < (destLength - numStrLen); index++) {
				numStr = "0"  + numStr;
			}
			return numStr;
		} else {
			return numStr.toString();
		}
	}
	
	public static String leftPaddingZero(Object number, int destLen) {
		return leftPadding(number, destLen, "0");
	}
	
	public static String rightPaddingZero(Object number, int destLen) {
		return rightPadding(number, destLen, "0");
	}
	
	public static String leftPaddingSpace(Object number, int destLen) {
		return leftPadding(number, destLen, " ");
	}
	
	public static String rightPaddingSpace(Object number, int destLen) {
		return rightPadding(number, destLen, " ");
	}
	
	public static String leftPadding(Object number, int destLen, String paddStr) {
		if (number == null) {
			String deString = "";
			for (int index = 0; index < destLen; index++) {
				deString += paddStr;
			}
			return deString;
		}
		String numStr = String.valueOf(number);
		int numStrLen = numStr.length();
		if (numStrLen < destLen) {
			for (int index = 0; index < (destLen - numStrLen); index++) {
				numStr = paddStr  + numStr;
			}
			return numStr;
		} else {
			return numStr.toString();
		}
	}
	
	
	public static String rightPadding(Object number, int destLen, String paddStr) {
		if (number == null) {
			String deString = "";
			for (int index = 0; index < destLen; index++) {
				deString += paddStr;
			}
			return deString;
		}
		String numStr = String.valueOf(number);
		int numStrLen = numStr.length();
		if (numStrLen < destLen) {
			for (int index = 0; index < (destLen - numStrLen); index++) {
				numStr = numStr + paddStr;
			}
			return numStr;
		} else {
			return numStr.toString();
		}
	}
	
	
	public static String byteRightPaddingSpace(Object number, int destLen) {
		if (number == null) {
			String deString = "";
			for (int index = 0; index < destLen; index++) {
				deString += " ";
			}
			return deString;
		}
		String numStr = String.valueOf(number);
		byte[] byteArray = numStr.getBytes();
		
		char[] charArray = numStr.toCharArray();
		int byteLength = 0;
		for (int index = 0; index < charArray.length; index++) {
			byte[] tempByteArray = String.valueOf(charArray[index]).getBytes();
			byteLength += tempByteArray.length;
			if (byteLength > destLen) {
				byteLength -= tempByteArray.length;
				break;
			}
		}
		
		if (byteLength >= destLen) {
			byte[] tempByte = new byte[byteLength];
			for (int index = 0; index < tempByte.length; index++) {
					tempByte[index] = byteArray[index];
			}
			return new String(tempByte);
		} else {
			byte[] tempByte = new byte[destLen];
			for (int index = 0; index < destLen; index++) {
				if (index < byteLength) {
					tempByte[index] = byteArray[index];
				} else {
					tempByte[index] = ' ';
				}
			}
			return new String(tempByte);
		}
	}
	
	/**
	 * 功能:左填充字符函数
	 * @param number 待填充的对象
	 * @param 目标长度
	 * @param 填充字符 
	 * @return
	 */
	public static String leftBytePaddingChar(Object number, int destLen, byte paddingChar) {
		return bytePaddingChar(number, destLen, paddingChar, true);
	}
	
	
	/**
	 * 功能:右填充字符函数
	 * @param number 待填充的对象
	 * @param 目标长度
	 * @param 填充字符 
	 * @return
	 */
	public static String rightBytePaddingChar(Object number, int destLen, byte paddingChar) {
		return bytePaddingChar(number, destLen, paddingChar, false);
	}
			
	
	/**
	 * 功能:填充字符函数
	 * @param number 待填充的对象
	 * @param 目标长度
	 * @param 填充字符
	 * @param true:左填充,false:右填充
	 * @return
	 */
	public static String bytePaddingChar(Object number, int destLen, byte paddingChar, boolean isLeft) {
		if (number == null) {
			String deString = "";
			for (int index = 0; index < destLen; index++) {
				deString += paddingChar;
			}
			return deString;
		}
		String numStr = String.valueOf(number);
		byte[] byteArray = numStr.getBytes();
		
		char[] charArray = numStr.toCharArray();
		int byteLength = 0;
		for (int index = 0; index < charArray.length; index++) {
			byte[] tempByteArray = String.valueOf(charArray[index]).getBytes();
			byteLength += tempByteArray.length;
			if (byteLength > destLen) {
				byteLength -= tempByteArray.length;
				break;
			}
		}
		byte[] tempByte = new byte[byteLength];
		if (byteLength >= destLen) {
			for (int index = 0; index < tempByte.length; index++) {
				tempByte[index] = byteArray[index];
			} 
		} else { 
			tempByte = new byte[destLen];
			for (int index = 0; index < destLen; index++) {
				if (isLeft == false) {
					if (index < byteLength) {
						tempByte[index] = byteArray[index];
					} else {
						tempByte[index] = paddingChar;
					}
				} else {
					if (index < byteLength) {
						tempByte[index + destLen - byteLength] = byteArray[index];
					} else {
						tempByte[destLen - index - 1] = Byte.parseByte(String.valueOf(paddingChar));
					}
				}
			}
			
		}
		return new String(tempByte);

	}
	
	
	/**
	 * 获取异常信息
	 * @param e
	 * @return
	 */
	public static String getExceptionMsg(Exception e){
		
		String errorMsg = e.getMessage();
		if(isNullString(errorMsg)){
			errorMsg = e.getCause().getMessage();
		}
		return errorMsg;
		
	}
	
	
	/**
	 * 判断字符串数组是否包含某个字符串值
	 * @param valueList
	 * @param value
	 * @return
	 */
	public static boolean containValue(String[] valueList,String value){
		if(isNullObjectList(valueList)){
			return false;
		}
		for (int i = 0; i < valueList.length; i++) {
			String valueItem = valueList[i];
			if(value.equals(valueItem)){
				return true;
			}
		}
		return false;
	}
	
	
	
	public static String paramListToString(Object[] paramList){
		StringBuffer sb = new StringBuffer();
		if(null==paramList){
			return "";
		}
		for (int i = 0; i < paramList.length; i++) {
			Object param = paramList[i];
			if(null!=param){
				sb.append(param.toString());
				sb.append(" , ");
			}
		}
		return sb.toString();
	}
	
	
	
	public static String getRequstParamStr(Map paraMap,String encodeing){
		StringBuffer sb = new StringBuffer();
		Set keySet = paraMap.keySet();
		Iterator it = keySet.iterator();
		sb.append("1=1");
		while (it.hasNext()) {
			String paraName = (String)it.next();
			String[] paraList = (String[])paraMap.get(paraName);
			for (int i = 0; i < paraList.length; i++) {
				String paraValue = paraList[i];
				try {
					paraValue = new String(paraValue.getBytes("8859_1"),encodeing); 
					paraValue = java.net.URLEncoder.encode(paraValue,encodeing);
				} catch (Exception e) {
					log.error("getRequstParamStr对URL参数进行转码出错，paraValue="+paraValue+",encodeing="+encodeing);
				}
				sb.append("&"+paraName+"="+paraValue);
			}
			
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 根据格式取得double str
	 * format=0.00
	 * @param jeDouble
	 * @return
	 */
	public static String getMoneyStr(double jeDouble,String formatStr) {
		
		DecimalFormat format = new DecimalFormat(formatStr); 
		String doubleString = format.format(jeDouble);
		return doubleString;
	}
	
	
	/**
	 * 功能：二行制转字符串
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs += "0" + stmp;
			}
			else
			{
				hs += stmp;
			}
		}
		return hs.toLowerCase();
	}
	
	
	
	/**
	 * 功能：用md5算法加密str字串
	 * 
	 * @param str
	 *            String 要加密的字串
	 * @return String 成功能返回加密好的字串，失败返回null
	 */
	public static String encrypt_MD5(String str)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] b = md.digest(str.getBytes());
			return byte2hex(b);
		}
		catch (NoSuchAlgorithmException ex)
		{
			return null;
		}
	}
	
	
	
	/**
	 * 功能:获取系统前一天时间
	 * @param formate:日期格式字符串
	 * @return 返回指定格式的字符串
	 */ 
	public static String getPerDate(String formate){
		long mills = System.currentTimeMillis();
		mills -= 3600*1000*24;
		Date date = new Date(mills);
		SimpleDateFormat format = new SimpleDateFormat(formate);//"yyyyMMdd"
		String dateStr = format.format(date);
		return dateStr;
	}
	
	/**
	 * 获取当然时间
	 * @param formate:日期格式字符串
	 * @return
	 */
	public static String getCurDate(String formate){
		long mills = System.currentTimeMillis();
		Date date = new Date(mills);
		SimpleDateFormat format = new SimpleDateFormat(formate);//"yyyyMMdd"
		String dateStr = format.format(date);
		return dateStr;
	}
	
	/**
	 * 判断是否为null或者空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullString(String str){
		
		if(null==str||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}		
	}
	
	
	/**
	 * 去除字符串前后空格和制表符等
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		if(!isNullString(str)){
			return str.trim();
		}
		return str;
	}
	
	
	/**
	 * 判断是否为空对象数组
	 * @param objects
	 * @return
	 */
	public static boolean isNullObjectList(Object[] objects){
		if(null==objects||objects.length<1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为空List
	 * @param objects
	 * @return
	 */
	public static boolean isNullList(List list){
		if(null==list||list.size()<1){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 增加参数
	 * @param params
	 * @param param
	 * @return
	 */
	public static Object[] addParament(Object[] params,Object param){
		if(null == params){
			params = new Object[]{};
		}
		Object[] newParams = new Object[params.length+1];
		for(int i=0;i<params.length;i++){
			newParams[i]=params[i];
		}
		newParams[params.length]=param;
		params = newParams;
		return newParams;		
	}
	
	
	/**
	 * 增加参数
	 * @param params
	 * @param param
	 * @return
	 */
	public static String[] appendString(String[] params,String param){
		if(null == params){
			params = new String[]{};
		}
		String[] newParams = new String[params.length+1];
		for(int i=0;i<params.length;i++){
			newParams[i]=params[i];
		}
		newParams[params.length]=param;
		params = newParams;
		return newParams;		
	}
	
	/**
	 * 增加参数
	 * @param params
	 * @param param
	 * @return
	 */
	public static Object[] addParament(Object[] params,Object[] param_add){
		Object[] newParams = new Object[]{};
		if(null!=params&&params.length>0){
			for (int i = 0; i < params.length; i++) {
				newParams = addParament(newParams, params[i]);
			}
		}
		if(null!=param_add&&param_add.length>0){
			for (int i = 0; i < param_add.length; i++) {
				newParams = addParament(newParams, param_add[i]);
			}
		}
		return newParams;		
	}
	
	
	/**
	 * 增加参数
	 * @param params
	 * @param param
	 * @return
	 */
	public static String[] appendStringArray(String[] params,String[] param_add){
		String[] newParams = new String[]{};
		if(null!=params&&params.length>0){
			for (int i = 0; i < params.length; i++) {
				newParams = appendString(newParams, params[i]);
			}
		}
		if(null!=param_add&&param_add.length>0){
			for (int i = 0; i < param_add.length; i++) {
				newParams = appendString(newParams, param_add[i]);
			}
		}
		return newParams;		
	}

	public static String subBytes(byte[] bytes, int startIndex, int endIndex) {
		byte[] destBytes = new byte[endIndex - startIndex];
		for (int index = startIndex; index < endIndex; index++) {
			destBytes[index - startIndex] = bytes[index];
		}
		return new String(destBytes);
	}
	
	
	public static String getNumFormat(String numStr, String fmt)
	{
		double db = getDouble(numStr, 0.0D);
		return getNumFormat(db, fmt);
	}

	public static String getNumFormat(String numStr)
	{
		return getNumFormat(numStr, "0.00");
	}
	
}
