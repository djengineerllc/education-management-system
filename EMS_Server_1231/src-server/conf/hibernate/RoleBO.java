package conf.hibernate;

import java.util.List;

/**
 * EmsRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RoleBO implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roleCd;
	private String roleName;
	
	private List<MenuBO> menuList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<MenuBO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuBO> menuList) {
		this.menuList = menuList;
	}

}