package com.ems.client.action.biz.certificate.common.vo;

import com.ems.system.client.vo.ExtGridVO;

public class CertQueryVO extends ExtGridVO {
	private String stuNo;
	private String stuGrade;
	private String stuClass;
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(String stuGrade) {
		this.stuGrade = stuGrade;
	}
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
}
