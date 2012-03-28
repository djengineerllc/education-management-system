package com.ems.common.model.vo;

public class ProfessVO {
	
	private Integer id;
	private String professName;
	private Integer projectId;
	private String projectName;
	
	public ProfessVO() {
		super();
	}
	
	
	public ProfessVO(Integer id, String professName) {
		super();
		this.id = id;
		this.professName = professName;
	}


	public ProfessVO(Integer id, String professName, Integer projectId,
			String projectName) {
		super();
		this.id = id;
		this.professName = professName;
		this.projectId = projectId;
		this.projectName = projectName;
	}
	

	public ProfessVO(Integer id, Integer projectId) {
		super();
		this.id = id;
		this.projectId = projectId;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfessName() {
		return professName;
	}
	public void setProfessName(String professName) {
		this.professName = professName;
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
