/*
 * Copyright © 2008, 2009 Pedro Agulló Soliveres.
 * 
 * This file is part of DirectJNgine.
 *
 * DirectJNgine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * Commercial use is permitted to the extent that the code/component(s)
 * do NOT become part of another Open Source or Commercially developed
 * licensed development library or toolkit without explicit permission.
 *
 * DirectJNgine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DirectJNgine.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * This software uses the ExtJs library (http://extjs.com), which is 
 * distributed under the GPL v3 license (see http://extjs.com/license).
 */

package com.softwarementors.extjs.djn.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class TestAction {
  @DirectMethod
  public String doEcho( String data ) {
    return data;
  }
  
  @DirectMethod
  public double multiply( String num ) {
    double num_ = Double.parseDouble(num);
    return num_ * 8.0;
  }
  
  public static class Data {
    public String firstName;
    public String lastName;
    public int age;
  }
  
  @DirectMethod
  public String showDetails( Data data ) {
    return "Hi " + data.firstName + " " + data.lastName + ", you are " + data.age + " years old.";
  }
  
  public static class Node {
    public String id;
    public String text;
    public boolean leaf;
    
    public String textKey;
    public String moduleId;
    public String moduleName;
    public String moduleNameKey;
    public String moduleConfig;
    
	public Node() {
		super();
	}

	public Node(String id, String text, boolean leaf) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	public Node(String id, String text, boolean leaf, String textKey,
			String moduleId, String moduleName, String moduleNameKey,
			String moduleConfig) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.textKey = textKey;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleNameKey = moduleNameKey;
		this.moduleConfig = moduleConfig;
	}
  }
  
  @DirectMethod 
  public List<Node> getTree( String id) {
    List<Node> result = new ArrayList<Node>();
    if(id == null || id == "" || id.equals("root")) {
      for( int i = 1; i <= 5; ++i ) {
        Node node = new Node();
        node.id = "n" + i;
        node.text = "Node " + i;
        node.leaf = false;
        result.add(node);
      }
    }
    else if( id.length() == 2) {
      String num = id.substring(1);
      for( int i = 1; i <= 5; ++i ) {
        Node node = new Node();
        node.id = id + i;
        node.text = "Node " + num + "." + i;
        node.leaf = true;
        result.add(node);
      }
    }
    return result;
  }
  
  ////////////////////
  @DirectMethod 
  public List<Node> getMenu(String id) {
	  List<Node> result = new ArrayList<Node>();
	  
	  if ("root".equals(id)) { //StringUtils.isEmpty(id) ||
		  result.add(new Node("01", "学籍管理", false));
		  result.add(new Node("02", "师资管理", false));
		  result.add(new Node("03", "教室管理", false));
		  result.add(new Node("04", "教材管理", false));
	  } else {
		  if ("01".equals(id)) {
			  result.add(new Node("0101", "学籍管理0101", false));
			  result.add(new Node("0102", "学籍管理0102", true, null, "ems.biz.test.Test", "学籍管理0102Title", null, null));
			  result.add(new Node("0103", "学籍管理0103", true, null, "ems.biz.test.Test", "学籍管理0103Title", null, null));
		  } else if ("0101".equals(id)) {
			  result.add(new Node("010101", "学籍管理010101", true, null, "ems.biz.test.Test", "学籍管理010101Title", null, null));
		  } else if ("02".equals(id)) {
			  result.add(new Node("0201", "师资管理0201", true, null, "ems.biz.test.Test", null, null, null));
			  result.add(new Node("0202", "师资管理0202", true, null, "ems.biz.test.Test", null, null, null));
			  result.add(new Node("0203", "师资管理0203", true, null, "ems.biz.test.Test", null, null, null));
		  }
	  }
	  return result;
  }
  ////////////////////////////////
  
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
