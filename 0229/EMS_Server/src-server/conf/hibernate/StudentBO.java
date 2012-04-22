package conf.hibernate;



public class StudentBO extends UserInfoBO {

	//loginName		学生姓名
	//userName		学号
	
	private String stuNo; // 学号
	
	private Integer gradeId;//年级ID
	
	private Integer projectId;//项目ID
	
	private Integer professId;//专业ID
	
	private Integer classId;//专业ID
	
	private String pinyin;//姓名拼音
	
	private String ethnic;//民族
	
	private String admissionTime;//入学时间 年-月
	
	private String leaveSchoolTime;//离校时间 年-月
	
	private String domicile;//户籍所在地
	
	private String idNumber;//身份证号
	
	private String birthDate;//出生日期 年-月-日
	
	private String homeFixTel;//家庭固定电话
	
	private String contactAddress;//联系地址
	
	private String contractAddZipCode;//联系地址邮编
	
	private String homeAddress;//家庭地址
	
	private String homeAddZipCode;//家庭地址邮编
	
	
	// 学习经历
	private String admissionQualif;//入学学历
	
	private String graduateSchool;//毕业学校
	
	private String profession;//专业
	
	private String graduateYear;//毕业年份
	
	private String gkProvince;//高考省份
	
	private String gkType;//高考类别
	
	private Double gkScore;//高考总分
	
	private Double gkEnglishScore;//英语总分
	
	private String gkYear;//高考年份
	
	private String status;//学生状态
	

	//家庭情况
	private String fatherName;
	
	private String fatherWorkUnit;
	
	private String fatherPost;
	
	private String fatherContactTel;
	
	private String motherName;
	
	private String motherWorkUnit;
	
	private String motherPost;
	
	private String motherContactTel;
	
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

	public String getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}

	public String getLeaveSchoolTime() {
		return leaveSchoolTime;
	}

	public void setLeaveSchoolTime(String leaveSchoolTime) {
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
<<<<<<< .mine

	public String getBirthDate() {
=======

	public Date getBirthDate() {
>>>>>>> .r661
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

	public String getAdmissionQualif() {
		return admissionQualif;
	}

	public void setAdmissionQualif(String admissionQualif) {
		this.admissionQualif = admissionQualif;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getGraduateYear() {
		return graduateYear;
	}

	public void setGraduateYear(String graduateYear) {
		this.graduateYear = graduateYear;
	}

	public String getGkProvince() {
		return gkProvince;
	}

	public void setGkProvince(String gkProvince) {
		this.gkProvince = gkProvince;
	}

	public String getGkType() {
		return gkType;
	}

	public void setGkType(String gkType) {
		this.gkType = gkType;
	}

	public Double getGkScore() {
		return gkScore;
	}

	public void setGkScore(Double gkScore) {
		this.gkScore = gkScore;
	}

	public Double getGkEnglishScore() {
		return gkEnglishScore;
	}

	public void setGkEnglishScore(Double gkEnglishScore) {
		this.gkEnglishScore = gkEnglishScore;
	}

	public String getGkYear() {
		return gkYear;
	}

	public void setGkYear(String gkYear) {
		this.gkYear = gkYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	
}
