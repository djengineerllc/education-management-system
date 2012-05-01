package com.ems.common.model.vo;

public class RegistrationInfoVO {
	
	private String stuNo; // 学号
	
	private String userName;
	
	private Integer gradeId;//年级ID
	
	private Integer projectId;//项目ID
	
	private Integer professId;//专业ID
	
	private Integer classId;//专业ID
	
	/** 是否报到 */
	private Boolean checkInFlag;
	
	/** 是否完费 */
	private Boolean endFeeFlag;
	
	/** 是否注册 */
	private Boolean registrationFlag;
	
	/** 注册时间 Y */
	private String registYear;

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProfessId() {
		return professId;
	}

	public void setProfessId(Integer professId) {
		this.professId = professId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Boolean getCheckInFlag() {
		return checkInFlag;
	}

	public void setCheckInFlag(Boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}

	public Boolean getEndFeeFlag() {
		return endFeeFlag;
	}

	public void setEndFeeFlag(Boolean endFeeFlag) {
		this.endFeeFlag = endFeeFlag;
	}

	public Boolean getRegistrationFlag() {
		return registrationFlag;
	}

	public void setRegistrationFlag(Boolean registrationFlag) {
		this.registrationFlag = registrationFlag;
	}

	public String getRegistYear() {
		return registYear;
	}

	public void setRegistYear(String registYear) {
		this.registYear = registYear;
	}

}
