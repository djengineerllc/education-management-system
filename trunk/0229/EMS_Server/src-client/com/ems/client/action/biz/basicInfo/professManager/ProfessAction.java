package com.ems.client.action.biz.basicInfo.professManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.client.action.biz.samples.common.vo.ProfessVO;
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
public class ProfessAction extends DirectAction {
	


	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, ProfessVO> profess = new HashMap<Integer, ProfessVO>();
	static {
		profess.put(1, new ProfessVO(1, "计算机网络",1,"预本硕连读项目"));
		profess.put(2, new ProfessVO(2, "国际理财",1,"预本硕连读项目"));
		profess.put(3, new ProfessVO(3, "国际贸易与商务",1,"预本硕连读项目"));
		profess.put(4, new ProfessVO(4, "商务会计",1,"预本硕连读项目"));
		profess.put(5, new ProfessVO(5, "教育机构管理与领导",2,"英国南安普顿大学教育硕士项目"));
		profess.put(6, new ProfessVO(6, "教育实践与革新",2,"英国南安普顿大学教育硕士项目"));
		profess.put(7, new ProfessVO(7, "计算机辅助教学",2,"英国南安普顿大学教育硕士项目"));
		profess.put(8, new ProfessVO(8, "工商管理",3,"美国布里诺大学工商管理硕士项目"));
	}
	
	@DirectMethod
	public ExtPagingVO loadProfess(JsonArray params) {
		try{
			List<ProfessVO> professVOList = new ArrayList<ProfessVO>();
			for (Map.Entry<Integer, ProfessVO> user : profess.entrySet()) {
				professVOList.add(user.getValue());
			}
			return new ExtPagingVO(professVOList);
		}catch(Exception e){
			logger.error("loadProfess--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessVO professVO = BeanUtils.toBeanFromMap(formParameters, ProfessVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (profess.containsKey(professVO.getId())) {
			result.addError("professName", String.format("专业[%s]已重复", professVO.getProfessName()));
			return result;
		}
		
		professVO.setId(++idCounter);
		professVO.setProjectId(++idCounter);
		profess.put(professVO.getId(), professVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer professId) {
		System.out.println("getFormData gradeId = " + professId);
		ProfessVO professVO = profess.get(professId);
		return new ExtFormVO(professVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ProfessVO professVO = BeanUtils.toBeanFromMap(formParameters, ProfessVO.class);
		ExtFormVO result = new ExtFormVO();
		profess.put(professVO.getId(),professVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			profess.remove(id);
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
