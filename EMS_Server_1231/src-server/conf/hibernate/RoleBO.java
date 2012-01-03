package conf.hibernate;

import java.util.Set;

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
	
	private Set<MenuBO> menuSet;

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

	public Set<MenuBO> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<MenuBO> menuSet) {
		this.menuSet = menuSet;
	}

}