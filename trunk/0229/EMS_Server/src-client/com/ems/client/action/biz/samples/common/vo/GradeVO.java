package com.ems.client.action.biz.samples.common.vo;

public class GradeVO {
	
	private Integer gradeId;
	private String gradeName;
	public GradeVO() {
	}
	public GradeVO(String gradeName){
		this.gradeName = gradeName;
	}
	public GradeVO(Integer gradeId,String gradeName){
		this.gradeId = gradeId;
		this.gradeName = gradeName;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
