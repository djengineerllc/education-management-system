package com.ems.common.model.vo;

public class ApplyInfoVO {
	
	private String name;
	
	private String sex;
	
	private String applyStatus;
	
	/** 首选项目 */
	private Integer firstProjectId;
	
	/** 首选项目对应专业 */
	private Integer firstProfessId;
	
	/** 次选项目 */
	private Integer secondProjectId;
	
	/** 次选项目对应专业 */
	private Integer secondProfessId;
	
	private String startDate;
	
	private String endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Integer getFirstProjectId() {
		return firstProjectId;
	}

	public void setFirstProjectId(Integer firstProjectId) {
		this.firstProjectId = firstProjectId;
	}

	public Integer getFirstProfessId() {
		return firstProfessId;
	}

	public void setFirstProfessId(Integer firstProfessId) {
		this.firstProfessId = firstProfessId;
	}

	public Integer getSecondProjectId() {
		return secondProjectId;
	}

	public void setSecondProjectId(Integer secondProjectId) {
		this.secondProjectId = secondProjectId;
	}

	public Integer getSecondProfessId() {
		return secondProfessId;
	}

	public void setSecondProfessId(Integer secondProfessId) {
		this.secondProfessId = secondProfessId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
