package com.ems.client.action.biz.basicInfo.termManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import com.ems.client.action.biz.samples.common.vo.TermVO;
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
public class TermAction extends DirectAction {

	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, TermVO> term = new HashMap<Integer, TermVO>();
	static {
		term.put(1, new TermVO(1, "2010-2011第一学期"));
		term.put(2, new TermVO(2, "2010-2011第二学期"));
		term.put(3, new TermVO(3, "2009-2010第一学期"));
		term.put(4, new TermVO(4, "2009-2010第一学期"));
	}
	
	@DirectMethod
	public ExtPagingVO loadTerm(JsonArray params) {
		try{
			List<TermVO> termVOList = new ArrayList<TermVO>();
			for (Map.Entry<Integer, TermVO> user : term.entrySet()) {
				termVOList.add(user.getValue());
			}
			return new ExtPagingVO(termVOList);
		}catch(Exception e){
			logger.error("loadGrade--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermVO termVO = BeanUtils.toBeanFromMap(formParameters, TermVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (term.containsKey(termVO.getTermId())) {
			result.addError("termName", String.format("学期[%s]已重复", termVO.getTermName()));
			return result;
		}
		
		termVO.setTermId(++idCounter);
		term.put(termVO.getTermId(), termVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer termId) {
		System.out.println("getFormData termId = " + termId);
		TermVO termVO = term.get(termId);
		return new ExtFormVO(termVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		TermVO termVO = BeanUtils.toBeanFromMap(formParameters, TermVO.class);
		ExtFormVO result = new ExtFormVO();
		term.put(termVO.getTermId(),termVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			term.remove(id);
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}


}
