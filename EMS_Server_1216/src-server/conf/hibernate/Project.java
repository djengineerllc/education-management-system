package conf.hibernate;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */

public class Project implements java.io.Serializable {

	// Fields

	private Integer id;
	private String projectName;
	private String studyCountry;
	private String studySchool;
	private String educationSystem;
	private String zsdx;
	private String cgtj;
	private String professional;

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Project(Integer id, String projectName, String studyCountry,
			String studySchool, String educationSystem, String zsdx,
			String cgtj, String professional) {
		this.id = id;
		this.projectName = projectName;
		this.studyCountry = studyCountry;
		this.studySchool = studySchool;
		this.educationSystem = educationSystem;
		this.zsdx = zsdx;
		this.cgtj = cgtj;
		this.professional = professional;
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

	public String getStudyCountry() {
		return this.studyCountry;
	}

	public void setStudyCountry(String studyCountry) {
		this.studyCountry = studyCountry;
	}

	public String getStudySchool() {
		return this.studySchool;
	}

	public void setStudySchool(String studySchool) {
		this.studySchool = studySchool;
	}

	public String getEducationSystem() {
		return this.educationSystem;
	}

	public void setEducationSystem(String educationSystem) {
		this.educationSystem = educationSystem;
	}

	public String getZsdx() {
		return this.zsdx;
	}

	public void setZsdx(String zsdx) {
		this.zsdx = zsdx;
	}

	public String getCgtj() {
		return this.cgtj;
	}

	public void setCgtj(String cgtj) {
		this.cgtj = cgtj;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

}