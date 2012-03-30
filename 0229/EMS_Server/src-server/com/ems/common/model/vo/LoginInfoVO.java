package com.ems.common.model.vo;

import java.io.Serializable;

public class LoginInfoVO implements Serializable {
	
	private Integer userId;
	private String userName;
	private String loginName;
	
	private Integer roleId;
	private String roleCd;
	private String roleName;
	
	/*START: student info */
	private String stuNo;
	private Integer stuGradeId;
	private String stuGradeName;
	private Integer stuClassId;
	private String stuClassName;
	private Integer stuProjectId;
	private String stuProjectName;
	private Integer stuProfessId;
	private String stuProfessName;
	/*END: student info */
	
	private String currTerm;

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

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public Integer getStuGradeId() {
		return stuGradeId;
	}

	public void setStuGradeId(Integer stuGradeId) {
		this.stuGradeId = stuGradeId;
	}

	public String getStuGradeName() {
		return stuGradeName;
	}

	public void setStuGradeName(String stuGradeName) {
		this.stuGradeName = stuGradeName;
	}

	public Integer getStuClassId() {
		return stuClassId;
	}

	public void setStuClassId(Integer stuClassId) {
		this.stuClassId = stuClassId;
	}

	public String getStuClassName() {
		return stuClassName;
	}

	public void setStuClassName(String stuClassName) {
		this.stuClassName = stuClassName;
	}

	public Integer getStuProjectId() {
		return stuProjectId;
	}

	public void setStuProjectId(Integer stuProjectId) {
		this.stuProjectId = stuProjectId;
	}

	public String getStuProjectName() {
		return stuProjectName;
	}

	public void setStuProjectName(String stuProjectName) {
		this.stuProjectName = stuProjectName;
	}

	public Integer getStuProfessId() {
		return stuProfessId;
	}

	public void setStuProfessId(Integer stuProfessId) {
		this.stuProfessId = stuProfessId;
	}

	public String getStuProfessName() {
		return stuProfessName;
	}

	public void setStuProfessName(String stuProfessName) {
		this.stuProfessName = stuProfessName;
	}

	public String getCurrTerm() {
		return currTerm;
	}

	public void setCurrTerm(String currTerm) {
		this.currTerm = currTerm;
	}
//	private UserRoleVO currRole;
//	private List<UserRoleVO> allRoles; //Map<String, UserRoleVO> roles;
	
}