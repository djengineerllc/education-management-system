package com.ems.client.action.login.vo;

import java.io.Serializable;
import java.util.List;

import conf.hibernate.RoleBO;

public class LoginInfoVO implements Serializable {
	
	private String userName;
	private RoleBO currentRole;
	private List<RoleBO> roles; //Map<String, UserRoleVO> roles;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public RoleBO getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(RoleBO currentRole) {
		this.currentRole = currentRole;
	}
	public List<RoleBO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleBO> roles) {
		this.roles = roles;
	}
	
	
}