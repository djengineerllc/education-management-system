package com.ems.client.action.biz.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class TestAction {
	// Dynamic data: the data itself is a dynamic map, so we can return
	// arbitrary data!
	public static class LocationInfo {
		public boolean success = true;
		public Map<String, String> data = new HashMap<String, String>();

	}

	// Fixed format data: the data itself is an inner Data class
	public static class PhoneInfo {
		public static class Data {
			public String cell;
			public String office;
			public String home;
		}

		public boolean success = true;
		public Data data = new Data();
	}

	// Fixed format data: the data itself is an inner Data class
	public static class BasicInfo {
		public static class Data {
			public String foo;
			public String name;
			public String company;
			public String email;
		}

		public boolean success = true;
		public Data data = new Data();
	}

	@DirectMethod
	public BasicInfo getBasicInfo(Long userId, String foo) {
		assert userId != null;
		assert foo != null;

		BasicInfo result = new BasicInfo();
		result.data.foo = foo;
		result.data.name = "Aaron Conran";
		result.data.company = "Ext JS, LLC";
		result.data.email = "aaron@extjs.com";
		return result;
	}

	@DirectMethod
	public PhoneInfo getPhoneInfo(Long userId) {
		assert userId != null;

		PhoneInfo result = new PhoneInfo();
		result.data.cell = "443-555-1234";
		result.data.office = "1-800-CALLEXT";
		result.data.home = "";
		return result;
	}

	@DirectMethod
	public LocationInfo getLocationInfo(Long userId) {
		assert userId != null;

		LocationInfo result = new LocationInfo();
		result.data.put("street", "1234 Red Dog Rd.");
		result.data.put("city", "Seminole");
		result.data.put("state", "FL");
		result.data.put("zip", "33776");
		return result;
	}

	private static class SubmitResult {
		public boolean success = true;
		public Map<String, String> errors;
		@SuppressWarnings("unused")
		public Map<String, String> debug_formPacket;
	}

	@DirectFormPostMethod
	public SubmitResult updateBasicInfo(Map<String, String> formParameters,
			Map<String, FileItem> fileFields) {
		assert formParameters != null;
		assert fileFields != null;

		SubmitResult result = new SubmitResult();

		String email = formParameters.get("email");
		result.success = !email.equals("aaron@extjs.com");
		if (!result.success) {
			result.errors = new HashMap<String, String>();
			result.errors.put("email", "already taken");
		}

		result.debug_formPacket = formParameters;
		return result;
	}
	
	
	public static class GridRow {
	    public GridRow( String name, int turnover) {
	      this.name = name;
	      this.turnover = turnover;
	    }
	    
	    public String name;
	    public int turnover;
	  }
	  
	  public static class SortInfo {
	    public String property;
	    public String direction;
	  }
	  
	  @DirectMethod
	  public List<GridRow> getGrid( JsonArray params ) {
	    // We know this is the structure, but the 'right' way to do this is
	    // to define a Java class that maps the information we are receiving
	    JsonObject sortInfo = (JsonObject)params.get(0).getAsJsonObject().get("sort").getAsJsonArray().get(0);
	    
	    assert sortInfo != null;
	    List<GridRow> data = new ArrayList<GridRow>();
	    String field = sortInfo.get("property" ).getAsString();
	    String direction = sortInfo.get("direction" ).getAsString();
	    
	    if( field.equals("name")) {
	      data.add( new GridRow("ABC Accounting", 50000));
	      data.add( new GridRow("Ezy Video Rental", 106300));
	      data.add( new GridRow("Greens Fruit Grocery", 120000));
	      data.add( new GridRow("Icecream Express", 73000));
	      data.add( new GridRow("Ripped Gym", 88400));
	      data.add( new GridRow("Smith Auto Mechanic", 222980));
	    }
	    else {
	      data.add( new GridRow("ABC Accounting", 50000));
	      data.add( new GridRow("Icecream Express", 73000));
	      data.add( new GridRow("Ripped Gym", 88400));
	      data.add( new GridRow("Ezy Video Rental", 106300));
	      data.add( new GridRow("Greens Fruit Grocery", 120000));
	      data.add( new GridRow("Smith Auto Mechanic", 222980));
	    }
	    
	    if( direction.equals( "DESC")) {
	      Collections.reverse(data);
	    }

	    return data;
	  }  
}
