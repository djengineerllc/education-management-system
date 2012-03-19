package com.ems.client.action.biz.basicInfo.projectManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
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

@ActionScope(scope=Scope.APPLICATION)
public class ProjectAction extends DirectAction {

	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, ProjectVO> project = new HashMap<Integer, ProjectVO>();
	static {
		project.put(1, new ProjectVO(1, "预本硕连读项目"));
		project.put(2, new ProjectVO(2, "法国兰斯管理学院本硕连读项目"));
		project.put(3, new ProjectVO(3, "中日本科项目"));
		project.put(4, new ProjectVO(4, "英国伦敦大学商务管理本科项目"));
		project.put(5, new ProjectVO(5, "英国南安普顿大学艺术设计本科项目"));
		project.put(6, new ProjectVO(6, "美国本科预科"));
	}
	
	@DirectMethod
	public ExtPagingVO loadProject(JsonArray params) {
		try{
			List<ProjectVO> projectVOList = new ArrayList<ProjectVO>();
			for (Map.Entry<Integer, ProjectVO> user : project.entrySet()) {
				projectVOList.add(user.getValue());
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
		
		if (project.containsKey(projectVO.getProjectName())) {
			result.addError("ProjectName", String.format("项目[%s]已重复", projectVO.getProjectName()));
			return result;
		}
		
		projectVO.setId(++idCounter);
		project.put(projectVO.getId(), projectVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer projectId) {
		System.out.println("getFormData gradeId = " + projectId);
		ProjectVO projectVO = project.get(projectId);
		return new ExtFormVO(projectVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProjectVO projectVO = BeanUtils.toBeanFromMap(formParameters, ProjectVO.class);
		ExtFormVO result = new ExtFormVO();
		project.put(projectVO.getId(),projectVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			project.remove(id);
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
