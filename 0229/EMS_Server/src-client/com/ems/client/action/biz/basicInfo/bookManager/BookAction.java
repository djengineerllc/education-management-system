package com.ems.client.action.biz.basicInfo.bookManager;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.common.model.vo.BookVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;
import conf.hibernate.BookBO;

@ActionScope(scope=Scope.APPLICATION)
public class BookAction extends DirectAction  {
	
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadBook(JsonArray params) {
		BookVO bookVO_qry = BeanUtils.toBeanFromJsonFirst(params, BookVO.class);
		
		List<BookBO> books = this.basicInfoService.findBookByVO(bookVO_qry);
		
		return new ExtPagingVO(books);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		BookBO bookBO = BeanUtils.toBeanFromMap(formParameters, BookBO.class);
		
		ExtFormVO result = new ExtFormVO();
		List<BookBO> books = this.basicInfoService.getAll(BookBO.class, " id desc ");
		for(BookBO book_:books){
			if(book_.getBookName().equals(bookBO.getBookName())) {
				result.addError("BookName", String.format("教材[%s]已重复", bookBO.getBookName()));
				return result;
			}
		}
		bookBO.setCreateTime(new Date());
		this.basicInfoService.save(bookBO);
		
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		BookBO bookBO = null;
		if(id != null){
			bookBO = this.basicInfoService.findById(BookBO.class, id);
		}
		
		return new ExtFormVO(bookBO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		BookBO bookBO = BeanUtils.toBeanFromMap(formParameters, BookBO.class);
		ExtFormVO result = new ExtFormVO();
		this.basicInfoService.update(bookBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(BookBO.class, id));
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