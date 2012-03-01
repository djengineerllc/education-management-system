package com.ems.client.action.login.vo;

import java.io.Serializable;
import java.util.List;

public class LoginInfoVO implements Serializable {
	
	private String userName;
	private UserRoleVO currentRole;
	private List<UserRoleVO> roles; //Map<String, UserRoleVO> roles;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserRoleVO getCurrentRole() {
		return currentRole;
	}
	public void setCurrentRole(UserRoleVO currentRole) {
		this.currentRole = currentRole;
	}
//	public Map<String, UserRoleVO> getRoles() {
//		return roles;
//	}
//	public void setRoles(Map<String, UserRoleVO> roles) {
//		this.roles = roles;
//	}
	public List<UserRoleVO> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRoleVO> roles) {
		this.roles = roles;
	}
}