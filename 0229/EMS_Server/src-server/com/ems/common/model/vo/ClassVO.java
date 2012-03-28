package com.ems.common.model.vo;

public class ClassVO {
	private Integer id;
	private Integer gradeId;
	private String className;
	private String gradeName;
	private Integer studentNum;
	
	public ClassVO() {
		super();
	}
	public ClassVO(Integer id, Integer gradeId) {
		super();
		this.id = id;
		this.gradeId = gradeId;
	}
	public ClassVO(Integer id) {
		super();
		this.id = id;
	}
	
	public ClassVO(Integer id, Integer gradeId, String className,
			String gradeName) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.className = className;
		this.gradeName = gradeName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}
	
}
