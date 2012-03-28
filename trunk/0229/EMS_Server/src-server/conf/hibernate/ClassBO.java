package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class ClassBO extends BaseBO {

	private Integer gradeId;
	private String className;
	private Integer studentNum;
	
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}
}