package com.ems.client.action.biz.samples.common.vo;

public class TermVO {
	
	private Integer id;
	private String termName;
	private String isCurrentTerm;
	
	public TermVO(Integer id) {
		super();
		this.id = id;
	}
	public TermVO(Integer id, String termName, String isCurrentTerm) {
		super();
		this.id = id;
		this.termName = termName;
		this.isCurrentTerm = isCurrentTerm;
	}
	public TermVO() {
		super();
	}
	
	public TermVO(Integer id, String termName) {
		super();
		this.id = id;
		this.termName = termName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getIsCurrentTerm() {
		return isCurrentTerm;
	}
	public void setIsCurrentTerm(String isCurrentTerm) {
		this.isCurrentTerm = isCurrentTerm;
	}
	

}
