package com.ems.client.action.biz.samples.common.vo;

public class ProfessVO {
	
	private Integer professId;
	private String professName;
	private Integer projectId;
	private String projectName;
	
	public ProfessVO() {
		super();
	}
	
	
	public ProfessVO(Integer professId, String professName) {
		super();
		this.professId = professId;
		this.professName = professName;
	}


	public ProfessVO(Integer professId, String professName, Integer projectId,
			String projectName) {
		super();
		this.professId = professId;
		this.professName = professName;
		this.projectId = projectId;
		this.projectName = projectName;
	}
	

	public ProfessVO(Integer professId, Integer projectId) {
		super();
		this.professId = professId;
		this.projectId = projectId;
	}

	public Integer getProfessId() {
		return professId;
	}
	public void setProfessId(Integer professId) {
		this.professId = professId;
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
