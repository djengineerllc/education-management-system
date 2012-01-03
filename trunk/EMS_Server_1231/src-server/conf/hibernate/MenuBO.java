package conf.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * EmsMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class MenuBO implements java.io.Serializable {

	private Integer id;
	private String text;
	private boolean leaf;
	private String moduleId;
	private String moduleName;
	private MenuBO parentBO;
	private Set<MenuBO> childMenuSet;
	
	public MenuBO() {
		super();
	}
	
	
	public MenuBO(String text, boolean leaf) {
		this.text = text;
		this.leaf = leaf;
	}

	public MenuBO(String text, boolean leaf, String moduleId, String moduleName) {
		super();
		this.text = text;
		this.leaf = leaf;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}


	public Set<MenuBO> getChildMenuSet() {
		return childMenuSet;
	}
	public void setChildMenuSet(Set<MenuBO> childMenuSet) {
		this.childMenuSet = childMenuSet;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public MenuBO getParentBO() {
		return parentBO;
	}
	public void setParentBO(MenuBO parentBO) {
		this.parentBO = parentBO;
	}
	
	public Set<MenuBO> addChild(MenuBO menuBO){
		if(this.childMenuSet == null){
			childMenuSet = new HashSet<MenuBO>();
		}
		childMenuSet.add(menuBO);
		menuBO.setParentBO(this);
		return this.childMenuSet;
	}
	
}