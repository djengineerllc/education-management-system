package conf.hibernate;

import java.util.List;

/**
 * EmsUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class UserBO implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginName;
	private String userName;
	private String password;
	private String passwordMail;
	private List<RoleBO> roleList;
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
	public String getPasswordMail() {
		return passwordMail;
	}
	public void setPasswordMail(String passwordMail) {
		this.passwordMail = passwordMail;
	}
	public List<RoleBO> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleBO> roleList) {
		this.roleList = roleList;
	}
	

}