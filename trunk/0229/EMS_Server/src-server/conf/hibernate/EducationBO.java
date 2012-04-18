package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * TbEduction entity. @author MyEclipse Persistence Tools
 */

public class EducationBO extends BaseBO {

	// Fields

	private Integer teacherId;
	private String courseNo;
	private Integer classId;
	private Integer termId;

	// Constructors

	/** default constructor */
	public EducationBO() {
	}

	/** minimal constructor */
	public EducationBO(Integer teacherId, String courseNo) {
		this.teacherId = teacherId;
		this.courseNo = courseNo;
	}

	/** full constructor */
	public EducationBO(Integer teacherId, String courseNo,Integer classId, Integer termId) {
		this.teacherId = teacherId;
		this.courseNo = courseNo;
		this.classId = classId;
		this.termId = termId;
	}

	// Property accessors


	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getCourseNo() {
		return this.courseNo;
	}

	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}

	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getTermId() {
		return this.termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

}