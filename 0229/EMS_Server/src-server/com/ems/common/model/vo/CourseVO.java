package com.ems.common.model.vo;

public class CourseVO {
	
	private Integer id;
	private String courseNo;
	private String courseName;
	private String courseEngName;
	private Double courseScore;
	private Integer courseTime;
	private String courseType;
	private String courseComment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseEngName() {
		return courseEngName;
	}
	public void setCourseEngName(String courseEngName) {
		this.courseEngName = courseEngName;
	}
	public Double getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(Double courseScore) {
		this.courseScore = courseScore;
	}
	public Integer getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseComment() {
		return courseComment;
	}
	public void setCourseComment(String courseComment) {
		this.courseComment = courseComment;
	}
}