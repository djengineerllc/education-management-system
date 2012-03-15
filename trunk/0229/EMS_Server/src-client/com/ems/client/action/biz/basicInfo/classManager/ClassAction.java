package com.ems.client.action.biz.basicInfo.classManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.ems.client.action.biz.samples.common.vo.ClassVO;
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
public class ClassAction extends DirectAction  {
	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, ClassVO> classManager = new HashMap<Integer, ClassVO>();
	static {
		classManager.put(1, new ClassVO(1, 1,"2010级","10Pre-Me"));
		classManager.put(2, new ClassVO(2, 1,"2010级","10Pre-B"));
		classManager.put(3, new ClassVO(3, 1,"2010级","10London"));
		classManager.put(4, new ClassVO(4, 1,"2010级","10Jap"));
	}

	@DirectMethod
	public ExtPagingVO loadClass(JsonArray params) {
		try{
			List<ClassVO> classVOList = new ArrayList<ClassVO>();
			for (Map.Entry<Integer, ClassVO> user : classManager.entrySet()) {
				classVOList.add(user.getValue());
			}
			return new ExtPagingVO(classVOList);
		}catch(Exception e){
			logger.error("loadGrade--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ClassVO classVO = BeanUtils.toBeanFromMap(formParameters, ClassVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (classManager.containsKey(classVO.getClassId())) {
			result.addError("className", String.format("班级[%s]已重复", classVO.getClassName()));
			return result;
		}
		
		classVO.setClassId(++idCounter);
		classManager.put(classVO.getClassId(), classVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer classId) {
		System.out.println("getFormData classId = " + classId);
		ClassVO classVO = classManager.get(classId);
		return new ExtFormVO(classVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		ClassVO classVO = BeanUtils.toBeanFromMap(formParameters, ClassVO.class);
		ExtFormVO result = new ExtFormVO();
		classManager.put(classVO.getClassId(),classVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			classManager.remove(id);
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
