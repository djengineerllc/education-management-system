package conf.hibernate;

import java.util.Date;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class ClassBO implements java.io.Serializable {

	// Fields

	private Integer id;
	private String className;
	private Integer gradeId;
	private Date createTime;
	private Date updateTime;
	private Integer studentNum;

	// Constructors

	/** default constructor */
	public ClassBO() {
	}

	/** full constructor */
	public ClassBO(String className, Integer gradeId) {
		this.className = className;
		this.gradeId = gradeId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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

	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

}