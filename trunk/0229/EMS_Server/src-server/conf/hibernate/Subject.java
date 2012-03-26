package conf.hibernate;

import java.util.Date;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	private Integer id;
	private String subjectNo;
	private String subjectName;
	private String subjectEngName;
	private Double subjectScore;
	private Integer subjectTime;
	private String subjectType;
	private String subjectComment;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** full constructor */
	public Subject(String subjectNo, String subjectName, String subjectEngName,
			Double subjectScore, Integer subjectTime, String subjectType,
			String subjectComment) {
		this.subjectNo = subjectNo;
		this.subjectName = subjectName;
		this.subjectEngName = subjectEngName;
		this.subjectScore = subjectScore;
		this.subjectTime = subjectTime;
		this.subjectType = subjectType;
		this.subjectComment = subjectComment;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectNo() {
		return this.subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectEngName() {
		return this.subjectEngName;
	}

	public void setSubjectEngName(String subjectEngName) {
		this.subjectEngName = subjectEngName;
	}

	public Double getSubjectScore() {
		return this.subjectScore;
	}

	public void setSubjectScore(Double subjectScore) {
		this.subjectScore = subjectScore;
	}

	public Integer getSubjectTime() {
		return this.subjectTime;
	}

	public void setSubjectTime(Integer subjectTime) {
		this.subjectTime = subjectTime;
	}

	public String getSubjectType() {
		return this.subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getSubjectComment() {
		return this.subjectComment;
	}

	public void setSubjectComment(String subjectComment) {
		this.subjectComment = subjectComment;
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