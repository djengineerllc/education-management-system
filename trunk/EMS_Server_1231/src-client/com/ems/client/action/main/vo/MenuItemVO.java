package com.ems.client.action.main.vo;

import java.io.Serializable;

/**
 * @author Chiknin
 * 菜单项
 */
public class MenuItemVO implements Serializable {
	
	private String id;
	private String text;
	private boolean leaf = true;

	private String textKey;
	private String moduleId;
	private String moduleName;
	private String moduleNameKey;
	private String moduleConfig;
	
	public MenuItemVO() {
	}
	
	public MenuItemVO(String id, String text, boolean leaf) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
	}

	public MenuItemVO(String id, String text, boolean leaf, String moduleId,
			String moduleName, String moduleConfig) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleConfig = moduleConfig;
	}
	
	public MenuItemVO(String id, String text, String moduleId, String moduleName) {
		this.id = id;
		this.text = text;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}

	public MenuItemVO(String id, String text, boolean leaf, String textKey,
			String moduleId, String moduleName, String moduleNameKey,
			String moduleConfig) {
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.textKey = textKey;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleNameKey = moduleNameKey;
		this.moduleConfig = moduleConfig;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getTextKey() {
		return textKey;
	}
	public void setTextKey(String textKey) {
		this.textKey = textKey;
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
	public String getModuleNameKey() {
		return moduleNameKey;
	}
	public void setModuleNameKey(String moduleNameKey) {
		this.moduleNameKey = moduleNameKey;
	}
	public String getModuleConfig() {
		return moduleConfig;
	}
	public void setModuleConfig(String moduleConfig) {
		this.moduleConfig = moduleConfig;
	}
}