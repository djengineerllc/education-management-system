package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class GradeBO extends BaseBO {
	
	private String gradeName;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}