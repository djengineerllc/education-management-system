package com.ems.common.code.externalcode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ems.common.code.ISingleCodeCollector;

import conf.hibernate.CodeTableBO;

public class SysYearCodeCollector implements ISingleCodeCollector {
	public static final String CODE_KEY = "SysYear";
	public String getCodeType() {
		return CODE_KEY;
	}

	public List<CodeTableBO> collect() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		int minYear = 2000;
		int maxYear = c.get(Calendar.YEAR);
		
		List<CodeTableBO> codes = new ArrayList<CodeTableBO>();
		CodeTableBO code = null;
		for (int i = maxYear; i >= minYear; i--) {
			code = new CodeTableBO();
			code.setCodeType(this.getCodeType());
			code.setCodeKey(i + "");
			code.setCodeValue(i + "");
			code.setCodeName(i + "");
			codes.add(code);
		}
		
		return codes;
	}
}