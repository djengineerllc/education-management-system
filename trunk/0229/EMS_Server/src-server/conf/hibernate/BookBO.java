package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class BookBO extends BaseBO {

	private String bookName;
	private String publishName;
	private String author;
	private String isbnNo;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPublishName() {
		return publishName;
	}
	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbnNo() {
		return isbnNo;
	}
	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}
}