package conf.hibernate;

import com.ems.common.code.CodeRefresh;
import com.ems.common.model.bo.BaseBO;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */
@CodeRefresh("Class")
public class ClassBO extends BaseBO {

	private Integer gradeId;
	private String className;
	private Integer studentNum;
	private String isGraduate;
	
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
	public String getIsGraduate() {
		return isGraduate;
	}
	public void setIsGraduate(String isGraduate) {
		this.isGraduate = isGraduate;
	}
}