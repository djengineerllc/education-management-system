package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

public class RoleMenuRelBO extends BaseBO {
	
	private Integer roleId;
	private Integer menuId;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}