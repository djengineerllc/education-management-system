package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

public class RegistrationBO extends BaseBO{
	
	/** 是否报到 */
	private boolean checkInFlag;
	
	/** 是否完费 */
	private boolean endFeeFlag;
	
	/** 是否注册 */
	private boolean registrationFlag;
	
	private String registYear;//注册时间 Y
	
	/** 对应的学生信息 */
	private StudentBO student;
	

	public boolean isCheckInFlag() {
		return checkInFlag;
	}

	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}

	public boolean isEndFeeFlag() {
		return endFeeFlag;
	}

	public void setEndFeeFlag(boolean endFeeFlag) {
		this.endFeeFlag = endFeeFlag;
	}

	public boolean isRegistrationFlag() {
		return registrationFlag;
	}

	public void setRegistrationFlag(boolean registrationFlag) {
		this.registrationFlag = registrationFlag;
	}

	public StudentBO getStudent() {
		return student;
	}

	public void setStudent(StudentBO student) {
		this.student = student;
	}

	public String getRegistYear() {
		return registYear;
	}

	public void setRegistYear(String registYear) {
		this.registYear = registYear;
	}
	
}
