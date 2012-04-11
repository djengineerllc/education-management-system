package conf.hibernate;

import java.util.Date;

import com.ems.common.code.CodeRefresh;
import com.ems.common.model.bo.BaseBO;

@CodeRefresh({"Teacher"})
public class UserInfoBO extends BaseBO {
	
	private String loginName;
	private String userName;
	private String password;
	private String email;
	private String contact;
	private Date lastLoginTime;
	private String sex;//性别
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}