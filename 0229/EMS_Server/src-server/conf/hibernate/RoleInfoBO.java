package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

public class RoleInfoBO extends BaseBO {
	
	private String roleCd;
	private String roleName;
	
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
}