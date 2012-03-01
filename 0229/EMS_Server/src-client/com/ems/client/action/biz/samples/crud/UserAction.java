package com.ems.client.action.biz.samples.crud;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.client.action.biz.samples.common.vo.UserInfoVO;
import com.ems.client.action.biz.samples.xgridsample.vo.XGridSample1ViewQueryVO;
import com.ems.common.datatransformer.helper.DataTransformerHelper;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectCrudAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.ems.system.client.vo.ExtUploadResultVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

@ActionScope(scope=Scope.APPLICATION)
public class UserAction extends DirectCrudAction {
	
	private static Integer idCounter = 10;
	
	private static Map<Integer, UserInfoVO> users = new HashMap<Integer, UserInfoVO>();
	static {
		users.put(1, new UserInfoVO(1, "小萌", "1", "2011-10-10", "xm@163.com"));
		users.put(2, new UserInfoVO(2, "小乐", "2", "2011-11-11", "xl@163.com"));
	}
	
	@DirectMethod
	public ExtPagingVO loadList(JsonArray params) {
		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
		
		
		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
		for (Map.Entry<Integer, UserInfoVO> user : users.entrySet()) {
			userInfoVOList.add(user.getValue());
		}
//		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
		
		return new ExtPagingVO(userInfoVOList);
//		throw new IllegalArgumentException("xf自定义异常");
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (users.containsKey(userInfoVO.getUserName())) {
			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
			return result;
		}
		
		userInfoVO.setId(++idCounter);
		users.put(userInfoVO.getId(), userInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData userId = " + id);
		
		UserInfoVO userInfoVO = users.get(id);
//			new UserInfoVO();
//		userInfoVO.setUserName("Chiknin");
//		userInfoVO.setSex("1");
//		userInfoVO.setBirthday("2011-11-11");
//		userInfoVO.setEmail("chiknin@gmail.com");
//		
//		userInfoVO.setSexcheckboxgroup_S1("1");
//		userInfoVO.setSexcheckboxgroup_S2("6");
		
		return new ExtFormVO(userInfoVO);
	}
	
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		users.put(userInfoVO.getId(), userInfoVO);
		
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			users.remove(id);
		}
		
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html");
//		response.reset();
//		PrintWriter pw = response.getWriter();
//		pw.write(
//			"<SCRIPT LANGUAGE=\"JavaScript\">" +
//				"alert('系统异常错误信息:" + 123 + "详细信息:" + 456 + "')" +
//			"</SCRIPT>"
//		);
//		pw.flush();
//		pw.close();
		
		List gridList = new ArrayList();
		gridList.add(new UserInfoVO(1, "小萌", "1", "2011-10-10", "xm@163.com"));
		gridList.add(new UserInfoVO(2, "小乐", "2", "2011-11-11", "xl@163.com"));
		gridList.add(new UserInfoVO(3, "小傅", "2", "2011-11-11", "xf@163.com"));
	
		Map rootVO = new HashMap();
		rootVO.put("emailTitle", "电子邮件");
		rootVO.put("list", gridList);
		
		byte[] data = (byte[]) DataTransformerHelper.transform("DT_test_excel_export", rootVO);
//		FileOutputStream fos = new FileOutputStream(new File("test_excel.xls"));
//		fos.write(bs);
//		fos.close();
//		
//		InputStream is = new FileInputStream("test_excel.xls");
//		Map l = (Map)DataTransformerHelper.transform("DT_test_excel_import", is);
//		for (Object o : (List)l.get("list")) {
//			System.out.println(ToStringBuilder.reflectionToString(o));
//		}
		
		this.download(response, data, "用户表格.xls");
	}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.downloadFile(response, "conf/templates/excel/用户表格.xls", "用户表格-导入模板.xls");
	}
	
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		for (Map.Entry<String,FileItem> entry : fileFields.entrySet()) {
			FileItem fileItem = entry.getValue();
			InputStream inputStream = null;
			try {
				System.out.println("-=====getContentType " + fileItem.getContentType());
				System.out.println("-=====getFieldName " + fileItem.getFieldName());
				System.out.println("-=====getName " + fileItem.getName());
				System.out.println("-=====getSize  " + fileItem.getSize());
				
				inputStream = fileItem.getInputStream();
				Map sheetData = (Map)DataTransformerHelper.transform("DT_test_excel_import", inputStream);
				List dataList = (List) sheetData.get("dataList");
				
				ExtUploadResultVO uploadResultVO = new ExtUploadResultVO();
				uploadResultVO.setFileName(fileItem.getName());
				uploadResultVO.setFileSize(fileItem.getSize());
				uploadResultVO.setFileContentType(fileItem.getContentType());
				
				uploadResultVO.setSuccessData(new ExtPagingVO("userName", dataList));
				
				formVO.setData(uploadResultVO);
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		return formVO;
	}
	
//	@DirectMethod
//	public ExtPagingVO loadUserList(JsonArray params) {
//		XGridSample1ViewQueryVO queryInfoVO = BeanUtils.toBeanFromJsonFirst(params, XGridSample1ViewQueryVO.class);
//		
//		
//		List<UserInfoVO> userInfoVOList = new ArrayList<UserInfoVO>();
//		for (Map.Entry<String, UserInfoVO> user : users.entrySet()) {
//			userInfoVOList.add(user.getValue());
//		}
////		userInfoVOList.add(new UserInfoVO(queryInfoVO.getUserName(), queryInfoVO.getSex(), "2011-01-01", "xf@163.com"));
//		
//		return new ExtPagingVO("userName", userInfoVOList);
////		throw new IllegalArgumentException("异常");
//	}
//	
//	@DirectMethod
//	public ExtFormVO loadUser(String id) {
//		System.out.println("getFormData userId = " + id);
//		
//		UserInfoVO userInfoVO = users.get(id);
////			new UserInfoVO();
////		userInfoVO.setUserName("Chiknin");
////		userInfoVO.setSex("1");
////		userInfoVO.setBirthday("2011-11-11");
////		userInfoVO.setEmail("chiknin@gmail.com");
////		
////		userInfoVO.setSexcheckboxgroup_S1("1");
////		userInfoVO.setSexcheckboxgroup_S2("6");
//		
//		return new ExtFormVO(userInfoVO);
//	}
//	
//	@DirectFormPostMethod
//	public ExtFormVO submitUser(Map<String, String> formParameters, Map<String, FileItem> fileFields) {
//		UserInfoVO userInfoVO = BeanUtils.toBeanFromMap(formParameters, UserInfoVO.class);
//		
//		ExtFormVO result = new ExtFormVO();
//		
//		if (users.containsKey(userInfoVO.getUserName())) {
//			result.addError("userName", String.format("用户名[%s]已重复", userInfoVO.getUserName()));
//		}
//		
//		users.put(userInfoVO.getUserName(), userInfoVO);
//		
//		return result;
//	}
}