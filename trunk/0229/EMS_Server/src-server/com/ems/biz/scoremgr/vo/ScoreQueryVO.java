package com.ems.biz.scoremgr.vo;

import java.io.Serializable;
import java.util.List;

public class ScoreQueryVO implements Serializable {
	
	private Integer termId;
	private Integer classId;
	private String courseNo;
	private List<Integer> stuIds;
	private List<String> stuNos;
	
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
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public List<Integer> getStuIds() {
		return stuIds;
	}
	public void setStuIds(List<Integer> stuIds) {
		this.stuIds = stuIds;
	}
	public List<String> getStuNos() {
		return stuNos;
	}
	public void setStuNos(List<String> stuNos) {
		this.stuNos = stuNos;
	}
}