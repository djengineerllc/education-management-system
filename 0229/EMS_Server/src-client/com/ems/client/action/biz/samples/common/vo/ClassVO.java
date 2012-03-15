package com.ems.client.action.biz.samples.common.vo;

public class ClassVO {
	private Integer classId;
	private Integer gradeId;
	private String className;
	private String gradeName;
	
	public ClassVO() {
		super();
	}
	public ClassVO(Integer classId, Integer gradeId) {
		super();
		this.classId = classId;
		this.gradeId = gradeId;
	}
	public ClassVO(Integer classId) {
		super();
		this.classId = classId;
	}
	
	public ClassVO(Integer classId, Integer gradeId, String className,
			String gradeName) {
		super();
		this.classId = classId;
		this.gradeId = gradeId;
		this.className = className;
		this.gradeName = gradeName;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
