package com.ems.common.model.vo;

import java.util.Date;

public class RegistrationDisVO {
	
	private Integer id;
	
	/** 是否报到 */
	private boolean checkInFlag;
	
	/** 是否完费 */
	private boolean endFeeFlag;
	
	/** 是否注册 */
	private boolean registrationFlag;
	
	private String registYear;//注册时间 Y
	
	private String stuNo; // 学号
	
	private String userName;
	
	private String sex;
	
	private String status;
	
	private Integer gradeId;//年级ID
	
	private Integer projectId;//项目ID
	
	private Integer professId;//专业ID
	
	private Integer classId;//专业ID
	
	private String pinyin;//姓名拼音
	
	private String ethnic;//民族
	
	private Date admissionTime;//入学时间
	
	private Date leaveSchoolTime;//离校时间
	
	private String domicile;//户籍所在地
	
	private String idNumber;//身份证号
	
	private String birthDate;//出生日期
	
	private String homeFixTel;//家庭固定电话
	
	private String contactAddress;//联系地址
	
	private String contractAddZipCode;//联系地址邮编
	
	private String homeAddress;//家庭地址
	
	private String homeAddZipCode;//家庭地址邮编
	
	
	//家庭情况
	private String fatherName;
	
	private String fatherWorkUnit;
	
	private String fatherPost;
	
	private String fatherContactTel;
	
	private String motherName;
	
	private String motherWorkUnit;
	
	private String motherPost;
	
	private String motherContactTel;

	public boolean isCheckInFlag() {
		return checkInFlag;
	}

	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}

	public boolean isEndFeeFlag() {
		return endFeeFlag;
	}

	public void setEndFeeFlag(boolean endFeeFlag) {
		this.endFeeFlag = endFeeFlag;
	}

	public boolean isRegistrationFlag() {
		return registrationFlag;
	}

	public void setRegistrationFlag(boolean registrationFlag) {
		this.registrationFlag = registrationFlag;
	}

	public String getRegistYear() {
		return registYear;
	}

	public void setRegistYear(String registYear) {
		this.registYear = registYear;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getProfessId() {
		return professId;
	}

	public void setProfessId(Integer professId) {
		this.professId = professId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	public Date getLeaveSchoolTime() {
		return leaveSchoolTime;
	}

	public void setLeaveSchoolTime(Date leaveSchoolTime) {
		this.leaveSchoolTime = leaveSchoolTime;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getHomeFixTel() {
		return homeFixTel;
	}

	public void setHomeFixTel(String homeFixTel) {
		this.homeFixTel = homeFixTel;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContractAddZipCode() {
		return contractAddZipCode;
	}

	public void setContractAddZipCode(String contractAddZipCode) {
		this.contractAddZipCode = contractAddZipCode;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeAddZipCode() {
		return homeAddZipCode;
	}

	public void setHomeAddZipCode(String homeAddZipCode) {
		this.homeAddZipCode = homeAddZipCode;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherWorkUnit() {
		return fatherWorkUnit;
	}

	public void setFatherWorkUnit(String fatherWorkUnit) {
		this.fatherWorkUnit = fatherWorkUnit;
	}

	public String getFatherPost() {
		return fatherPost;
	}

	public void setFatherPost(String fatherPost) {
		this.fatherPost = fatherPost;
	}

	public String getFatherContactTel() {
		return fatherContactTel;
	}

	public void setFatherContactTel(String fatherContactTel) {
		this.fatherContactTel = fatherContactTel;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherWorkUnit() {
		return motherWorkUnit;
	}

	public void setMotherWorkUnit(String motherWorkUnit) {
		this.motherWorkUnit = motherWorkUnit;
	}

	public String getMotherPost() {
		return motherPost;
	}

	public void setMotherPost(String motherPost) {
		this.motherPost = motherPost;
	}

	public String getMotherContactTel() {
		return motherContactTel;
	}

	public void setMotherContactTel(String motherContactTel) {
		this.motherContactTel = motherContactTel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
