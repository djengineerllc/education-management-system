package com.ems.client.action.biz.basicInfo.bookManager;

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

import conf.hibernate.Book;

@ActionScope(scope=Scope.APPLICATION)
public class BookAction extends DirectAction  {
	


	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadBook(JsonArray params) {
		try{
			List<BookVO> bookVOList = new ArrayList<BookVO>();
			BookVO bookVO_qry = BeanUtils.toBeanFromJsonFirst(params, BookVO.class);
			BookVO bookVO = null;
			List<Book> books = this.basicInfoService.findBookByVO(bookVO_qry);
			for(Book book : books){
				bookVO = new BookVO();
				bookVO.setId(book.getId());
				bookVO.setBookName(book.getBookName());
				bookVO.setIsbnNo(book.getIsbnNo());
				bookVO.setPublishName(book.getPublishName());
				bookVO.setAuthor(book.getAuthor());
				bookVOList.add(bookVO);
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
		List<Book> books = this.basicInfoService.getAll(Book.class, " id desc ");
		for(Book book_:books){
			if(book_.getBookName().equals(bookVO.getBookName())) {
				result.addError("BookName", String.format("教材[%s]已重复", bookVO.getBookName()));
				return result;
			}
		}
		Book book = new Book();
		book.setBookName(bookVO.getBookName());
		book.setAuthor(bookVO.getAuthor());
		book.setIsbnNo(bookVO.getIsbnNo());
		book.setPublishName(bookVO.getPublishName());
		book.setCreateTime(new Date());
		this.basicInfoService.save(book);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData bookId = " + id);
		BookVO bookVO = null;
		Book book = null;
		if(id != null){
			book = this.basicInfoService.findById(Book.class, id);
			bookVO = new BookVO();
			bookVO.setId(id);
			bookVO.setBookName(book.getBookName());
			bookVO.setAuthor(book.getAuthor());
			bookVO.setIsbnNo(book.getIsbnNo());
			bookVO.setPublishName(book.getPublishName());
		}
		return new ExtFormVO(bookVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		BookVO bookVO = BeanUtils.toBeanFromMap(formParameters, BookVO.class);
		ExtFormVO result = new ExtFormVO();
		Book book = this.basicInfoService.findById(Book.class, bookVO.getId());
		book.setBookName(bookVO.getBookName());
		book.setAuthor(bookVO.getAuthor());
		book.setIsbnNo(bookVO.getIsbnNo());
		book.setPublishName(bookVO.getPublishName());
		book.setUpdateTime(new Date());
		this.basicInfoService.update(book);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Book.class, id));
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
