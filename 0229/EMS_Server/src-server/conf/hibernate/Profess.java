package conf.hibernate;

/**
 * Profess entity. @author MyEclipse Persistence Tools
 */

public class Profess implements java.io.Serializable {

	// Fields

	private Integer id;
	private String professName;
	private Integer projectId;

	// Constructors

	/** default constructor */
	public Profess() {
	}

	/** full constructor */
	public Profess(String professName, Integer projectId) {
		this.professName = professName;
		this.projectId = projectId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProfessName() {
		return this.professName;
	}

	public void setProfessName(String professName) {
		this.professName = professName;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

}