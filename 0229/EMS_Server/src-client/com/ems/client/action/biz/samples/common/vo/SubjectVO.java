package com.ems.client.action.biz.samples.common.vo;

public class SubjectVO {
	
	private Integer id;
	private String subjectNo;
	private String subjectCnName;
	private String subjectEnName;
	private Double subjectScore;
	private Integer subjectTime;
	
	public SubjectVO() {
		super();
	}
	
	
	public SubjectVO(Integer id, String subjectNo, String subjectCnName,
			String subjectEnName, Double subjectScore, Integer subjectTime) {
		super();
		this.id = id;
		this.subjectNo = subjectNo;
		this.subjectCnName = subjectCnName;
		this.subjectEnName = subjectEnName;
		this.subjectScore = subjectScore;
		this.subjectTime = subjectTime;
	}
	

	public SubjectVO(String subjectNo, String subjectCnName,
			String subjectEnName, Double subjectScore, Integer subjectTime) {
		super();
		this.subjectNo = subjectNo;
		this.subjectCnName = subjectCnName;
		this.subjectEnName = subjectEnName;
		this.subjectScore = subjectScore;
		this.subjectTime = subjectTime;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}
	public String getSubjectCnName() {
		return subjectCnName;
	}
	public void setSubjectCnName(String subjectCnName) {
		this.subjectCnName = subjectCnName;
	}
	public String getSubjectEnName() {
		return subjectEnName;
	}
	public void setSubjectEnName(String subjectEnName) {
		this.subjectEnName = subjectEnName;
	}
	public Double getSubjectScore() {
		return subjectScore;
	}
	public void setSubjectScore(Double subjectScore) {
		this.subjectScore = subjectScore;
	}


	public Integer getSubjectTime() {
		return subjectTime;
	}


	public void setSubjectTime(Integer subjectTime) {
		this.subjectTime = subjectTime;
	}
	
}
