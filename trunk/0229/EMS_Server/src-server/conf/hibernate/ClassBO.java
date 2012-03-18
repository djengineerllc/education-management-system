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
	private String status;
	private Date createTime;
	private Date updateTime;
	private Integer studentNum;
	
	public final static String status_0 = "0";//无效
	public final static String status_1 = "1";//有效

	// Constructors

	/** default constructor */
	public ClassBO() {
	}

	/** full constructor */
	public ClassBO(String className, Integer gradeId, String status) {
		this.className = className;
		this.gradeId = gradeId;
		this.status = status;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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