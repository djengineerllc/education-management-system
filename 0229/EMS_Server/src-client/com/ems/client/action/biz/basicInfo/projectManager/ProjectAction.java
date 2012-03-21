package com.ems.client.action.biz.basicInfo.projectManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.Project;

@ActionScope(scope=Scope.APPLICATION)
public class ProjectAction extends DirectAction {

	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadProject(JsonArray params) {
		try{
			ProjectVO projectVO_qry = BeanUtils.toBeanFromJsonFirst(params, ProjectVO.class);
			List<Project> projects = basicInfoService.findProjectByVO(projectVO_qry);
			List<ProjectVO> projectVOList = new ArrayList<ProjectVO>();
			ProjectVO projectVO = null;
			for(Project project : projects){
				projectVO = new ProjectVO();
				projectVO.setId(project.getId());
				projectVO.setProjectName(project.getProjectName());
				projectVO.setProjectComment(project.getProjectComment());
				projectVOList.add(projectVO);
			}
			return new ExtPagingVO(projectVOList);
		}catch(Exception e){
			logger.error("loadProject--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProjectVO projectVO = BeanUtils.toBeanFromMap(formParameters, ProjectVO.class);
		ExtFormVO result = new ExtFormVO();
		List<Project> projects = basicInfoService.getAll(Project.class, null);
		for(Project project_:projects){
			if (project_.getProjectName().equals(projectVO.getProjectName())) {
				result.addError("ProjectName", String.format("项目[%s]已重复", projectVO.getProjectName()));
				return result;
			}
		}
		Project project = new Project();
		project.setProjectName(projectVO.getProjectName());
		project.setProjectComment(projectVO.getProjectComment());
		project.setCreateTime(new Date());
		this.basicInfoService.save(project);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData getId = " + id);
		ProjectVO projectVO = null;
		Project project = null;
		if(id != null){
			project = this.basicInfoService.findById(Project.class, id);
			projectVO = new ProjectVO();
			projectVO.setId(id);
			projectVO.setProjectName(project.getProjectName());
			projectVO.setProjectComment(project.getProjectComment());
		}
		return new ExtFormVO(projectVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProjectVO projectVO = BeanUtils.toBeanFromMap(formParameters, ProjectVO.class);
		ExtFormVO result = new ExtFormVO();
		Project project = this.basicInfoService.findById(Project.class, projectVO.getId());
		project.setProjectName(projectVO.getProjectName());
		project.setProjectComment(projectVO.getProjectComment());
		project.setUpdateTime(new Date());
		this.basicInfoService.update(project);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Project.class, id));
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}


}
