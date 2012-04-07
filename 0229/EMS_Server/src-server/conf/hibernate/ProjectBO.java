package conf.hibernate;

import com.ems.common.code.CodeRefresh;
import com.ems.common.model.bo.BaseBO;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@CodeRefresh("Project")
public class ProjectBO extends BaseBO {

	private String projectName;
	private String projectComment;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectComment() {
		return projectComment;
	}
	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}
}