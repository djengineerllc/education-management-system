package conf.hibernate;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gradeName;

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** full constructor */
	public Grade(String gradeName) {
		this.gradeName = gradeName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

}