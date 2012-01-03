package conf.hibernate;

import java.util.ArrayList;
import java.util.List;


/**
 * EmsMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MenuBO implements java.io.Serializable {

	private Integer id;
	private String text;
	private boolean leaf;
	private String moduleId;
	private String moduleName;
	private Integer parentId;
	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	private List<MenuBO> childMenuList;
	
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


	public List<MenuBO> getChildMenuList() {
		return childMenuList;
	}


	public void setChildMenuList(List<MenuBO> childMenuList) {
		this.childMenuList = childMenuList;
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
	public boolean addChild(MenuBO menuBO){
		if(this.childMenuList == null){
			childMenuList = new ArrayList<MenuBO>();
		}
//		menuBO.setParentBO(this);
		return childMenuList.add(menuBO);
	}
	
}