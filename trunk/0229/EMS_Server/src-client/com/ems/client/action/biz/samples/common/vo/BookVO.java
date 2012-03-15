package com.ems.client.action.biz.samples.common.vo;

public class BookVO {
	
	private Integer bookId;
	private String bookName;
	private String publishName;
	private String author;
	private String isbnNo;
	
	public BookVO() {
		super();
	}
	
	public BookVO(Integer bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}


	public BookVO(Integer bookId, String bookName, String publishName,
			String author, String isbnNo) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.publishName = publishName;
		this.author = author;
		this.isbnNo = isbnNo;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
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
