package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class SyllabusBO extends BaseBO {
	
	private Integer termId;
	private Integer classId;
	private String lesson;
	private String oeInd;
	private String week;
	private Integer courseId;
	private Integer teacherId;
	private Integer roomId;
	
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getLesson() {
		return lesson;
	}
	public void setLesson(String lesson) {
		this.lesson = lesson;
	}
	public String getOeInd() {
		return oeInd;
	}
	public void setOeInd(String oeInd) {
		this.oeInd = oeInd;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
}