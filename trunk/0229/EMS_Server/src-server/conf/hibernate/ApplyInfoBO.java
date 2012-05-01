package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

public class ApplyInfoBO extends BaseBO{
	
	/** 报名编号 */
	private String appNum;
	
	private String applyStatus;
	
	/** 学号 */
	private String stuNo;
	
	/**报名时间*/
	private String applyDate;
	
	/** 录取项目 */
	private Integer admissionProjectId;
	
	/** 录取专业 */
	private Integer admissionProfessId; 
	
	/****** 项目专业选择*******************/
	private Integer firstProjectId;/** 首选项目 */
	
	/** 首选项目对应专业 */
	private Integer firstProfessId;
	
	/** 次选项目 */
	private Integer secondProjectId;
	
	/** 次选项目对应专业 */
	private Integer secondProfessId;
	
	/*********** 个人信息 ***********************/
	/** 姓名 */
	private String name;
	
	private String sex;
	
	private String idNum;
	
	private String birthDate;//y-m-d
	
	private String ethnic;//民族
	
	private String domicile;//户籍所在地
	
	private String contactAddress;//联系地址
	
	private String contractAddZipCode;//联系地址邮编
	
	private String homeAddress;//家庭地址
	
	private String homeAddZipCode;//家庭地址邮编
	
	private String contactPersonName;//联系人
	
	private String cttMobileNum;//手机号码
	
	private String cttFixNum;//固定号码
	
	private String cttEmail;
	
	private boolean refusedVisaFlag;//是否有被拒签
	
	private String visaType;
	
	private String visaCounty;
	
	private String visaYear;
	
	private String visaRefReason;//拒签原因
	
	/**************学习经历***********************/
	private String graduateSchool;//毕业学校
	
	private String graduateProfession;//专业
	
	private String graduateYear;//毕业年份
	
	private String currentDegree;//目前学历
	
	private Double englishScore;//英语水平
	
	private Double gkTotalScore;//高考总分
	
	private Double gkEnglishScore;//高考英语分数
	
	private Double gkMathematicsScore;//高考数学分数
	
	private String gkType;//高考类别
	
	private String gkYear;
	
	private String gkProvince;
	
	private String candidates;
	
	private String ticketNum;//准考证号
	
	/**************家庭情况***********************/
	private String fatherName;
	
	private String fatherWorkUnit;
	
	private String fatherPost;
	
	private String fatherContactTel;
	
	private String motherName;
	
	private String motherWorkUnit;
	
	private String motherPost;
	
	private String motherContactTel;
	
	/**************招生信息来源以及资料清单***********************/
	private String admissionsSource;
	
	private String submitDataList;

	public Integer getFirstProjectId() {
		return firstProjectId;
	}

	public void setFirstProjectId(Integer firstProjectId) {
		this.firstProjectId = firstProjectId;
	}

	public Integer getFirstProfessId() {
		return firstProfessId;
	}

	public void setFirstProfessId(Integer firstProfessId) {
		this.firstProfessId = firstProfessId;
	}

	public Integer getSecondProjectId() {
		return secondProjectId;
	}

	public void setSecondProjectId(Integer secondProjectId) {
		this.secondProjectId = secondProjectId;
	}

	public Integer getSecondProfessId() {
		return secondProfessId;
	}

	public void setSecondProfessId(Integer secondProfessId) {
		this.secondProfessId = secondProfessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
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

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getCttMobileNum() {
		return cttMobileNum;
	}

	public void setCttMobileNum(String cttMobileNum) {
		this.cttMobileNum = cttMobileNum;
	}

	public String getCttFixNum() {
		return cttFixNum;
	}

	public void setCttFixNum(String cttFixNum) {
		this.cttFixNum = cttFixNum;
	}

	public String getCttEmail() {
		return cttEmail;
	}

	public void setCttEmail(String cttEmail) {
		this.cttEmail = cttEmail;
	}

	public boolean isRefusedVisaFlag() {
		return refusedVisaFlag;
	}

	public void setRefusedVisaFlag(boolean refusedVisaFlag) {
		this.refusedVisaFlag = refusedVisaFlag;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaCounty() {
		return visaCounty;
	}

	public void setVisaCounty(String visaCounty) {
		this.visaCounty = visaCounty;
	}

	public String getVisaYear() {
		return visaYear;
	}

	public void setVisaYear(String visaYear) {
		this.visaYear = visaYear;
	}

	public String getVisaRefReason() {
		return visaRefReason;
	}

	public void setVisaRefReason(String visaRefReason) {
		this.visaRefReason = visaRefReason;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateProfession() {
		return graduateProfession;
	}

	public void setGraduateProfession(String graduateProfession) {
		this.graduateProfession = graduateProfession;
	}

	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public String getCurrentDegree() {
		return currentDegree;
	}

	public void setCurrentDegree(String currentDegree) {
		this.currentDegree = currentDegree;
	}

	public Double getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(Double englishScore) {
		this.englishScore = englishScore;
	}

	public Double getGkTotalScore() {
		return gkTotalScore;
	}

	public void setGkTotalScore(Double gkTotalScore) {
		this.gkTotalScore = gkTotalScore;
	}

	public Double getGkEnglishScore() {
		return gkEnglishScore;
	}

	public void setGkEnglishScore(Double gkEnglishScore) {
		this.gkEnglishScore = gkEnglishScore;
	}

	public Double getGkMathematicsScore() {
		return gkMathematicsScore;
	}

	public void setGkMathematicsScore(Double gkMathematicsScore) {
		this.gkMathematicsScore = gkMathematicsScore;
	}

	public String getGkType() {
		return gkType;
	}

	public void setGkType(String gkType) {
		this.gkType = gkType;
	}

	public String getGkYear() {
		return gkYear;
	}

	public void setGkYear(String gkYear) {
		this.gkYear = gkYear;
	}

	public String getGkProvince() {
		return gkProvince;
	}

	public void setGkProvince(String gkProvince) {
		this.gkProvince = gkProvince;
	}

	public String getCandidates() {
		return candidates;
	}

	public void setCandidates(String candidates) {
		this.candidates = candidates;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
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

	public String getAdmissionsSource() {
		return admissionsSource;
	}

	public void setAdmissionsSource(String admissionsSource) {
		this.admissionsSource = admissionsSource;
	}

	public String getSubmitDataList() {
		return submitDataList;
	}

	public void setSubmitDataList(String submitDataList) {
		this.submitDataList = submitDataList;
	}

	public String getAppNum() {
		return appNum;
	}

	public void setAppNum(String appNum) {
		this.appNum = appNum;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getAdmissionProjectId() {
		return admissionProjectId;
	}

	public void setAdmissionProjectId(Integer admissionProjectId) {
		this.admissionProjectId = admissionProjectId;
	}

	public Integer getAdmissionProfessId() {
		return admissionProfessId;
	}

	public void setAdmissionProfessId(Integer admissionProfessId) {
		this.admissionProfessId = admissionProfessId;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	
}
