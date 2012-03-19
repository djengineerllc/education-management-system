package conf.hibernate;

import java.util.Date;


/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class Term implements java.io.Serializable {

	// Fields

	private Integer id;
	private String termName;
	private String isCurrentTerm;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Term() {
	}

	/** minimal constructor */
	public Term(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Term(Integer id, String termName, String isCurrentTerm) {
		this.id = id;
		this.termName = termName;
		this.isCurrentTerm = isCurrentTerm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getIsCurrentTerm() {
		return this.isCurrentTerm;
	}

	public void setIsCurrentTerm(String isCurrentTerm) {
		this.isCurrentTerm = isCurrentTerm;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}