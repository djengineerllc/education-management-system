package com.ems.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * @author Chiknin
 */
public class DateUtil {
	
	public static String DATE_FORMAT_FULL_STRING = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat DATE_FORMAT_FULL = new SimpleDateFormat(DATE_FORMAT_FULL_STRING);
	public static String DATE_FORMAT_DATE_STRING = "yyyy-MM-dd";
	public static SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(DATE_FORMAT_DATE_STRING);
	public static String DATE_FORMAT_TIME_STRING = "HH:mm:ss";
	public static SimpleDateFormat DATE_FORMAT_TIME = new SimpleDateFormat(DATE_FORMAT_TIME_STRING);
	
	public static Date currData() {
		return new Date();
	}
	
    public static Date parseFull(String source) {
    	return parse(source, DATE_FORMAT_FULL);
    }
    public static Date parseDate(String source) {
    	return parse(source, DATE_FORMAT_DATE);
    }
    public static Date parseTime(String source) {
    	return parse(source, DATE_FORMAT_TIME);
    }
    public static Date parse(String source, String pattern) {
    	return parse(source, new SimpleDateFormat(pattern));
    }
    public static Date parse(String source, SimpleDateFormat sdf) {
    	if (!StringUtils.hasText(source)) return null;
    	try {
    		return sdf.parse(source);
    	} catch (Exception e) {
    		throw new IllegalArgumentException(e.getMessage(), e);
    	}
    }
    public static Date parse(String source) {
    	if (source == null) {
    		return null;
    	}
    	
    	switch (source.length()) {
    		case 19:
    			return parseFull(source);
    		case 10:
    			return parseDate(source);
    		case 8:
    			return parseTime(source);
    		default: 
    			throw new IllegalArgumentException("Could not parse date");
    	}
    }
    

    public static String formatFull(Date date) {
    	return format(date, DATE_FORMAT_FULL);
    }
    public static String formatDate(Date date) {
    	return format(date, DATE_FORMAT_DATE);
    }
    public static String formatTime(Date date) {
    	return format(date, DATE_FORMAT_TIME);
    }
    public static String format(Date date, String pattern) {
    	return format(date, new SimpleDateFormat(pattern));
    }
    public static String format(Date date, DateFormat df) {
    	if (date == null) return null;
    	return df.format(date);
    }
    public static String format(String srcDate, String srcPattern, String descPattern) {
    	return format(parse(srcDate, srcPattern), descPattern);
    }

    public static Date add(Date date, int field, int amount) {
    	if (date == null) return null;
    	Calendar newDate = Calendar.getInstance();
    	newDate.setTime(date);
    	newDate.add(field, amount);

    	return newDate.getTime();
    }
    
    public static Date addDays(Date date, int amount) {
    	return add(date, Calendar.DAY_OF_YEAR, amount);
    }
    
    public static Date addYears(Date date, int amount) {
    	return add(date, Calendar.YEAR, amount);
    }

    public static Date nextAnyYear(Date date, int amount) {
    	if (date == null) return null;
    	Calendar srcDate = Calendar.getInstance();
    	srcDate.setTime(date);

    	Calendar newDate = Calendar.getInstance();
    	newDate.setTime(date);
    	newDate.set(Calendar.YEAR, srcDate.get(Calendar.YEAR) + amount);
    	newDate.set(Calendar.MONTH, srcDate.get(Calendar.MONTH));
    	newDate.set(Calendar.DAY_OF_MONTH, srcDate.get(Calendar.DAY_OF_MONTH) - 1);

    	return newDate.getTime();
    }

    /**
     * 判断日期大小
     * @param srcDate 源日期
     * @param descDate 目标日期
     * @return 0: srcDate等于descDate, 1: srcDate大于descDate, -1: srcDate小于descDate
     */
    public static int compareTo(Date srcDate, Date descDate) {
    	Calendar srcDateC = Calendar.getInstance();
    	srcDateC.setTime(srcDate);
    	Calendar descDateC = Calendar.getInstance();
    	descDateC.setTime(descDate);

    	int srcYear = srcDateC.get(Calendar.YEAR);
    	int descYear = descDateC.get(Calendar.YEAR);
    	if (srcYear == descYear) {
    		int srcDayOfYear = srcDateC.get(Calendar.DAY_OF_YEAR);
        	int descDayOfYear = descDateC.get(Calendar.DAY_OF_YEAR);
        	if (srcDayOfYear == descDayOfYear) {
        		return 0;
        	} else {
        		return srcDayOfYear > descDayOfYear ? 1 : -1;
        	}
    	} else {
    		return srcYear > descYear ? 1 : -1;
    	}
    }
}
