package com.ems.client.action.biz.basicInfo.bookManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.client.action.biz.samples.common.vo.BookVO;
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
public class BookAction extends DirectAction  {
	


	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, BookVO> book = new HashMap<Integer, BookVO>();
	static {
		book.put(1, new BookVO(1, "雅思1","人民教育出版社","Jordan","ISBN001"));
		book.put(2, new BookVO(2, "雅思2","人民教育出版社","David","ISBN002"));
		book.put(3, new BookVO(3, "雅思3","人民教育出版社","Kober","ISBN003"));
		book.put(4, new BookVO(4, "雅思4","人民教育出版社","James","ISBN004"));
	}
	
	@DirectMethod
	public ExtPagingVO loadBook(JsonArray params) {
		try{
			List<BookVO> bookVOList = new ArrayList<BookVO>();
			for (Map.Entry<Integer, BookVO> user : book.entrySet()) {
				bookVOList.add(user.getValue());
			}
			return new ExtPagingVO(bookVOList);
		}catch(Exception e){
			logger.error("loadBook--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		BookVO bookVO = BeanUtils.toBeanFromMap(formParameters, BookVO.class);
		
		ExtFormVO result = new ExtFormVO();
		
		if (book.containsKey(bookVO.getBookName())) {
			result.addError("BookName", String.format("教材[%s]已重复", bookVO.getBookName()));
			return result;
		}
		
		bookVO.setBookId(++idCounter);
		book.put(bookVO.getBookId(), bookVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer bookId) {
		System.out.println("getFormData bookId = " + bookId);
		BookVO bookVO = book.get(bookId);
		return new ExtFormVO(bookVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		BookVO bookVO = BeanUtils.toBeanFromMap(formParameters, BookVO.class);
		ExtFormVO result = new ExtFormVO();
		book.put(bookVO.getBookId(),bookVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			book.remove(id);
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
