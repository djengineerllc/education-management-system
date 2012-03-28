package conf.hibernate;

import com.ems.common.model.bo.BaseBO;


/**
 * Term entity. @author MyEclipse Persistence Tools
 */

public class TermBO extends BaseBO {

	private String termName;
	private String isCurrentTerm;
	
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getIsCurrentTerm() {
		return isCurrentTerm;
	}
	public void setIsCurrentTerm(String isCurrentTerm) {
		this.isCurrentTerm = isCurrentTerm;
	}
}