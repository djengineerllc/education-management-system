package com.ems.common.model.vo;

public class BookVO {
	
	private Integer id;
	private String bookName;
	private String publishName;
	private String author;
	private String isbnNo;
	
	public BookVO() {
		super();
	}
	
	public BookVO(Integer id, String bookName) {
		super();
		this.id = id;
		this.bookName = bookName;
	}


	public BookVO(Integer id, String bookName, String publishName,
			String author, String isbnNo) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.publishName = publishName;
		this.author = author;
		this.isbnNo = isbnNo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
