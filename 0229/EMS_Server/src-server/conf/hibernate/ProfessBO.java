package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Profess entity. @author MyEclipse Persistence Tools
 */

public class ProfessBO extends BaseBO {

	private Integer projectId;
	private String professName;
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProfessName() {
		return professName;
	}
	public void setProfessName(String professName) {
		this.professName = professName;
	}
}