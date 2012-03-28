package com.ems.common.model.vo;

import java.io.Serializable;

/**
 * 数据字典VO
 * @author Chiknin
 */
public class DicVO implements Serializable {
	
	private Integer id;
	private String type;
	private String key;
	private String value;
	private String name;
	private String desc;
	private String group;
	
	public DicVO() {
	}
	
	public DicVO(Integer id, String type, String key, String value, String name) {
		super();
		this.id = id;
		this.type = type;
		this.key = key;
		this.value = value;
		this.name = name;
	}

	public DicVO(Integer id, String type, String key, String value, String name, String desc, String group) {
		this.id = id;
		this.type = type;
		this.key = key;
		this.value = value;
		this.name = name;
		this.desc = desc;
		this.group = group;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}