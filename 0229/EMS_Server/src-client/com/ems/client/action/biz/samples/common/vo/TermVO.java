package com.ems.client.action.biz.samples.common.vo;

public class TermVO {
	
	private Integer termId;
	private String termName;
	private String isCurrentTerm;
	
	public TermVO(Integer termId) {
		super();
		this.termId = termId;
	}
	public TermVO(Integer termId, String termName, String isCurrentTerm) {
		super();
		this.termId = termId;
		this.termName = termName;
		this.isCurrentTerm = isCurrentTerm;
	}
	public TermVO() {
		super();
	}
	
	public TermVO(Integer termId, String termName) {
		super();
		this.termId = termId;
		this.termName = termName;
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
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
