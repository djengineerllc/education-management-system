package com.ems.client.action.biz.samples.common.vo;

public class ProjectVO {
	
	private Integer id;
	private String projectName;
	
	
	public ProjectVO(Integer id) {
		super();
		this.id = id;
	}
	public ProjectVO(Integer id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}
	public ProjectVO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}