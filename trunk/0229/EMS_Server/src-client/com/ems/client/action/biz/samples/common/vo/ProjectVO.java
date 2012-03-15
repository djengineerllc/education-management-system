package com.ems.client.action.biz.samples.common.vo;

public class ProjectVO {
	
	private Integer projectId;
	private String projectName;
	
	
	public ProjectVO(Integer projectId) {
		super();
		this.projectId = projectId;
	}
	public ProjectVO(Integer projectId, String projectName) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
	}
	public ProjectVO() {
		super();
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	

}
