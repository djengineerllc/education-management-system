package com.ems.common.model.vo;

public class CourseVO {
	
	private Integer id;
	private String courseNo;
	private String courseCnName;
	private String courseEnName;
	private Double courseScore;
	private Integer courseTime;
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
	public String getCourseCnName() {
		return courseCnName;
	}
	public void setCourseCnName(String courseCnName) {
		this.courseCnName = courseCnName;
	}
	public String getCourseEnName() {
		return courseEnName;
	}
	public void setCourseEnName(String courseEnName) {
		this.courseEnName = courseEnName;
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
}