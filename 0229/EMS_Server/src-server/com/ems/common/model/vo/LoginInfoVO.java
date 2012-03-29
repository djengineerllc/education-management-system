package com.ems.common.model.vo;

import java.io.Serializable;

public class LoginInfoVO implements Serializable {
	
	private Integer userId;
	private String userName;
	private String loginName;
	
	private Integer roleId;
	private String roleName;
	
	private String currTermId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
//	private UserRoleVO currRole;
//	private List<UserRoleVO> allRoles; //Map<String, UserRoleVO> roles;
	
}