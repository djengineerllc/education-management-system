package com.ems.common.model.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
	private Integer id;
	private String userName;
	private String sex;
	private String birthday;
	private String email;
	
	public UserInfoVO() {
	}
	public UserInfoVO(String userName, String sex, String birthday, String email) {
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
	}
	public UserInfoVO(Integer id, String userName, String sex, String birthday, String email) {
		this.id = id;
		this.userName = userName;
		this.sex = sex;
		this.birthday = birthday;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	private String sexcheckboxgroup_S1;
	private String sexcheckboxgroup_S2;

	public String getSexcheckboxgroup_S1() {
		return sexcheckboxgroup_S1;
	}
	public void setSexcheckboxgroup_S1(String sexcheckboxgroup_S1) {
		this.sexcheckboxgroup_S1 = sexcheckboxgroup_S1;
	}
	public String getSexcheckboxgroup_S2() {
		return sexcheckboxgroup_S2;
	}
	public void setSexcheckboxgroup_S2(String sexcheckboxgroup_S2) {
		this.sexcheckboxgroup_S2 = sexcheckboxgroup_S2;
	}

}
