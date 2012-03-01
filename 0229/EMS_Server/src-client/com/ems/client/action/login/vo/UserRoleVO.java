package com.ems.client.action.login.vo;

import java.io.Serializable;


public class UserRoleVO implements Serializable {
	
	private Integer id;
	private String name;
	
	public UserRoleVO() {
	}

	public UserRoleVO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}