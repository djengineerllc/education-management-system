package com.ems.common.model.vo;

import java.io.Serializable;

public class ScoreVO implements Serializable {
	
	private Integer id;
	private Integer stuId;
	private String stuName;
	private Integer termId;
	private String courseNo;
	private String scoreValue;
	private String scoreLevel;
	
	public ScoreVO(Integer id, Integer stuId, String stuName, Integer termId,
			String courseNo, String scoreValue, String scoreLevel) {
		
		this.id = id;
		this.stuId = stuId;
		this.stuName = stuName;
		this.termId = termId;
		this.courseNo = courseNo;
		this.scoreValue = scoreValue;
		this.scoreLevel = scoreLevel;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(String scoreValue) {
		this.scoreValue = scoreValue;
	}
	public String getScoreLevel() {
		return scoreLevel;
	}
	public void setScoreLevel(String scoreLevel) {
		this.scoreLevel = scoreLevel;
	}
}