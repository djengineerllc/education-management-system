package com.ems.biz.applyonline.action;


import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class ApplyOnLineAction {
	
	@DirectMethod
	public String test(String para){
		return "ApplyOnLineAction--test"+para;
	}

}
