package com.ems.client.action.biz.certificate.common.vo;

import java.io.Serializable;

public class StudentInfoVO implements Serializable {
	
	private Integer stuId;
	private String stuNo;
	private String stuName;
	private String stuSex;
	private String stuGrade;
	private String stuClass;
	private String stuProj;
	private String stuProf;
	private String stuStatus;
	
	public StudentInfoVO() {
	}
	
	public StudentInfoVO(Integer stuId, String stuNo, String stuName,
			String stuSex, String stuGrade, String stuClass, String stuProj,
			String stuProf, String stuStatus) {
		this.stuId = stuId;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuGrade = stuGrade;
		this.stuClass = stuClass;
		this.stuProj = stuProj;
		this.stuProf = stuProf;
		this.stuStatus = stuStatus;
	}
	
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
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
	public String getStuProj() {
		return stuProj;
	}
	public void setStuProj(String stuProj) {
		this.stuProj = stuProj;
	}
	public String getStuProf() {
		return stuProf;
	}
	public void setStuProf(String stuProf) {
		this.stuProf = stuProf;
	}
	public String getStuStatus() {
		return stuStatus;
	}
	public void setStuStatus(String stuStatus) {
		this.stuStatus = stuStatus;
	}
}