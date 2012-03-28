package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * course entity. @author MyEclipse Persistence Tools
 */

public class CourseBO extends BaseBO {

	private String courseNo;
	private String courseName;
	private String courseEngName;
	private Double courseScore;
	private Integer courseTime;
	private String courseType;
	private String courseComment;

	public String getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseEngName() {
		return this.courseEngName;
	}

	public void setCourseEngName(String courseEngName) {
		this.courseEngName = courseEngName;
	}

	public Double getCourseScore() {
		return this.courseScore;
	}

	public void setCourseScore(Double courseScore) {
		this.courseScore = courseScore;
	}

	public Integer getCourseTime() {
		return this.courseTime;
	}

	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseType() {
		return this.courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public String getCourseComment() {
		return this.courseComment;
	}

	public void setCourseComment(String courseComment) {
		this.courseComment = courseComment;
	}
}