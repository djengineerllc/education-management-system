package com.ems.common.model.vo;

public class GradeVO {
	
	private Integer id;
	private String gradeName;
	public GradeVO() {
	}
	public GradeVO(String gradeName){
		this.gradeName = gradeName;
	}
	public GradeVO(Integer id,String gradeName){
		this.id = id;
		this.gradeName = gradeName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}
