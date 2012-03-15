package com.ems.client.action.biz.basicInfo.gradeManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.ems.client.action.biz.samples.common.vo.GradeVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
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
public class GradeAction extends DirectAction {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, GradeVO> grade = new HashMap<Integer, GradeVO>();
	static {
		grade.put(1, new GradeVO(1, "2006级"));
		grade.put(2, new GradeVO(2, "2007级"));
		grade.put(3, new GradeVO(3, "2008级"));
		grade.put(4, new GradeVO(4, "2009级"));
	}
	
	@DirectMethod
	public ExtPagingVO loadGrade(JsonArray params) {
		try{
			List<GradeVO> gradeVOList = new ArrayList<GradeVO>();
			for (Map.Entry<Integer, GradeVO> user : grade.entrySet()) {
				gradeVOList.add(user.getValue());
			}
			return new ExtPagingVO(gradeVOList);
		}catch(Exception e){
			logger.error("loadGrade--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeVO gradeVO = BeanUtils.toBeanFromMap(formParameters, GradeVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (grade.containsKey(gradeVO.getGradeName())) {
			result.addError("gradeName", String.format("年级[%s]已重复", gradeVO.getGradeName()));
			return result;
		}
		
		gradeVO.setGradeId(++idCounter);
		grade.put(gradeVO.getGradeId(), gradeVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer gradeId) {
		System.out.println("getFormData gradeId = " + gradeId);
		GradeVO gradeVO = grade.get(gradeId);
		return new ExtFormVO(gradeVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		GradeVO gradeVO = BeanUtils.toBeanFromMap(formParameters, GradeVO.class);
		ExtFormVO result = new ExtFormVO();
		grade.put(gradeVO.getGradeId(),gradeVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			grade.remove(id);
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map rootVO = new HashMap();
		rootVO.put("emailTitle", "电子邮件");
		byte[] data = (byte[]) DataTransformerHelper.transform("DT_test_excel_export", rootVO);
		this.download(response, data, "用户表格.xls");
	}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.downloadFile(response, "conf/templates/excel/用户表格.xls", "用户表格-导入模板.xls");
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}
}
