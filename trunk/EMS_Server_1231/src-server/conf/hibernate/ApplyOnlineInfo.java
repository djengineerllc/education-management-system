package conf.hibernate;

import java.util.Date;

/**
 * ApplyOnlineInfo entity. @author MyEclipse Persistence Tools
 */

public class ApplyOnlineInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectName;
	private String zyName;
	private String projectNameSec;
	private String zyNameSec;
	private String stuName;
	private String stuSex;
	private String stuIDNo;
	private Date stuBirthday;
	private String stuMz;
	private String stuProvice;
	private String stuCity;
	private String stuAddress;
	private String stuZip;
	private String familyAddress;
	private String familyZip;
	private String contactPerson;
	private String mobile;
	private String phone;
	private String email;
	private String isReject;
	private String rejectType;
	private String rejectCountry;
	private Date rejectTime;
	private String rejectReason;
	private String graduateSchool;
	private String graduateZy;
	private Date graduateTime;
	private String education;
	private String englishLevel;
	private Integer gkTotal;
	private Integer gkYw;
	private Integer gkSx;
	private String gkYear;
	private String gkProvice;
	private String gkType;
	private String gkStuNo;
	private String gkNo;
	private String fatherName;
	private String fatherWork;
	private String fatherWorkPosition;
	private String fatherTel;
	private String motherName;
	private String motherWork;
	private String motherWorkPosition;
	private String motherTel;
	private String zsInfoSource;
	private Date applyTime;
	private Date createTime;
	private Date updateTime;
	private String bmNo;

	// Constructors

	/** default constructor */
	public ApplyOnlineInfo() {
	}

	/** full constructor */
	public ApplyOnlineInfo(String projectName, String zyName,
			String projectNameSec, String zyNameSec, String stuName,
			String stuSex, String stuIDNo, Date stuBirthday, String stuMz,
			String stuProvice, String stuCity, String stuAddress,
			String stuZip, String familyAddress, String familyZip,
			String contactPerson, String mobile, String phone, String email,
			String isReject, String rejectType, String rejectCountry,
			Date rejectTime, String rejectReason, String graduateSchool,
			String graduateZy, Date graduateTime, String education,
			String englishLevel, Integer gkTotal, Integer gkYw, Integer gkSx,
			String gkYear, String gkProvice, String gkType, String gkStuNo,
			String gkNo, String fatherName, String fatherWork,
			String fatherWorkPosition, String fatherTel, String motherName,
			String motherWork, String motherWorkPosition, String motherTel,
			String zsInfoSource, Date applyTime, Date createTime,
			Date updateTime) {
		this.projectName = projectName;
		this.zyName = zyName;
		this.projectNameSec = projectNameSec;
		this.zyNameSec = zyNameSec;
		this.stuName = stuName;
		this.stuSex = stuSex;
		this.stuIDNo = stuIDNo;
		this.stuBirthday = stuBirthday;
		this.stuMz = stuMz;
		this.stuProvice = stuProvice;
		this.stuCity = stuCity;
		this.stuAddress = stuAddress;
		this.stuZip = stuZip;
		this.familyAddress = familyAddress;
		this.familyZip = familyZip;
		this.contactPerson = contactPerson;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.isReject = isReject;
		this.rejectType = rejectType;
		this.rejectCountry = rejectCountry;
		this.rejectTime = rejectTime;
		this.rejectReason = rejectReason;
		this.graduateSchool = graduateSchool;
		this.graduateZy = graduateZy;
		this.graduateTime = graduateTime;
		this.education = education;
		this.englishLevel = englishLevel;
		this.gkTotal = gkTotal;
		this.gkYw = gkYw;
		this.gkSx = gkSx;
		this.gkYear = gkYear;
		this.gkProvice = gkProvice;
		this.gkType = gkType;
		this.gkStuNo = gkStuNo;
		this.gkNo = gkNo;
		this.fatherName = fatherName;
		this.fatherWork = fatherWork;
		this.fatherWorkPosition = fatherWorkPosition;
		this.fatherTel = fatherTel;
		this.motherName = motherName;
		this.motherWork = motherWork;
		this.motherWorkPosition = motherWorkPosition;
		this.motherTel = motherTel;
		this.zsInfoSource = zsInfoSource;
		this.applyTime = applyTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getZyName() {
		return this.zyName;
	}

	public void setZyName(String zyName) {
		this.zyName = zyName;
	}

	public String getProjectNameSec() {
		return this.projectNameSec;
	}

	public void setProjectNameSec(String projectNameSec) {
		this.projectNameSec = projectNameSec;
	}

	public String getZyNameSec() {
		return this.zyNameSec;
	}

	public void setZyNameSec(String zyNameSec) {
		this.zyNameSec = zyNameSec;
	}

	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuSex() {
		return this.stuSex;
	}

	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}

	public String getStuIDNo() {
		return this.stuIDNo;
	}

	public void setStuIDNo(String stuIDNo) {
		this.stuIDNo = stuIDNo;
	}

	public Date getStuBirthday() {
		return this.stuBirthday;
	}

	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}

	public String getStuMz() {
		return this.stuMz;
	}

	public void setStuMz(String stuMz) {
		this.stuMz = stuMz;
	}

	public String getStuProvice() {
		return this.stuProvice;
	}

	public void setStuProvice(String stuProvice) {
		this.stuProvice = stuProvice;
	}

	public String getStuCity() {
		return this.stuCity;
	}

	public void setStuCity(String stuCity) {
		this.stuCity = stuCity;
	}

	public String getStuAddress() {
		return this.stuAddress;
	}

	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}

	public String getStuZip() {
		return this.stuZip;
	}

	public void setStuZip(String stuZip) {
		this.stuZip = stuZip;
	}

	public String getFamilyAddress() {
		return this.familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	public String getFamilyZip() {
		return this.familyZip;
	}

	public void setFamilyZip(String familyZip) {
		this.familyZip = familyZip;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsReject() {
		return this.isReject;
	}

	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}

	public String getRejectType() {
		return this.rejectType;
	}

	public void setRejectType(String rejectType) {
		this.rejectType = rejectType;
	}

	public String getRejectCountry() {
		return this.rejectCountry;
	}

	public void setRejectCountry(String rejectCountry) {
		this.rejectCountry = rejectCountry;
	}

	public Date getRejectTime() {
		return this.rejectTime;
	}

	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}

	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateZy() {
		return this.graduateZy;
	}

	public void setGraduateZy(String graduateZy) {
		this.graduateZy = graduateZy;
	}

	public Date getGraduateTime() {
		return this.graduateTime;
	}

	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEnglishLevel() {
		return this.englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}

	public Integer getGkTotal() {
		return this.gkTotal;
	}

	public void setGkTotal(Integer gkTotal) {
		this.gkTotal = gkTotal;
	}

	public Integer getGkYw() {
		return this.gkYw;
	}

	public void setGkYw(Integer gkYw) {
		this.gkYw = gkYw;
	}

	public Integer getGkSx() {
		return this.gkSx;
	}

	public void setGkSx(Integer gkSx) {
		this.gkSx = gkSx;
	}

	public String getGkYear() {
		return this.gkYear;
	}

	public void setGkYear(String gkYear) {
		this.gkYear = gkYear;
	}

	public String getGkProvice() {
		return this.gkProvice;
	}

	public void setGkProvice(String gkProvice) {
		this.gkProvice = gkProvice;
	}

	public String getGkType() {
		return this.gkType;
	}

	public void setGkType(String gkType) {
		this.gkType = gkType;
	}

	public String getGkStuNo() {
		return this.gkStuNo;
	}

	public void setGkStuNo(String gkStuNo) {
		this.gkStuNo = gkStuNo;
	}

	public String getGkNo() {
		return this.gkNo;
	}

	public void setGkNo(String gkNo) {
		this.gkNo = gkNo;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherWork() {
		return this.fatherWork;
	}

	public void setFatherWork(String fatherWork) {
		this.fatherWork = fatherWork;
	}

	public String getFatherWorkPosition() {
		return this.fatherWorkPosition;
	}

	public void setFatherWorkPosition(String fatherWorkPosition) {
		this.fatherWorkPosition = fatherWorkPosition;
	}

	public String getFatherTel() {
		return this.fatherTel;
	}

	public void setFatherTel(String fatherTel) {
		this.fatherTel = fatherTel;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherWork() {
		return this.motherWork;
	}

	public void setMotherWork(String motherWork) {
		this.motherWork = motherWork;
	}

	public String getMotherWorkPosition() {
		return this.motherWorkPosition;
	}

	public void setMotherWorkPosition(String motherWorkPosition) {
		this.motherWorkPosition = motherWorkPosition;
	}

	public String getMotherTel() {
		return this.motherTel;
	}

	public void setMotherTel(String motherTel) {
		this.motherTel = motherTel;
	}

	public String getZsInfoSource() {
		return this.zsInfoSource;
	}

	public void setZsInfoSource(String zsInfoSource) {
		this.zsInfoSource = zsInfoSource;
	}

	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBmNo() {
		return bmNo;
	}

	public void setBmNo(String bmNo) {
		this.bmNo = bmNo;
	}

}