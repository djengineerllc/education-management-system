package com.ems.common.model.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
	private Integer id;
	private Integer roleId;
	private String roleName;
	private String loginName;
	private String userName;
	private String password;
	private String email;
	private String contact;
	
	public UserInfoVO() {
	}
	public UserInfoVO(String loginName, String userName, String password, String email) {
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public UserInfoVO(Integer id, String loginName, String userName, String password, String email) {
		this.id = id;
		this.loginName = loginName;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
