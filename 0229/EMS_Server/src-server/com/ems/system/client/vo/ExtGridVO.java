package com.ems.system.client.vo;

/**
 * @author Chiknin
 */
public class ExtGridVO extends ExtBaseVO {
	
	public static final String DIRECTION_ASC = "ASC";
	public static final String DIRECTION_DESC = "DESC";
	
	private Integer page;
	private Integer start;
	private Integer limit;
	private String sort;
	private String dir;
	
	public boolean isAsc() {
		return DIRECTION_ASC.equalsIgnoreCase(this.sort);
	}
	public boolean isDesc() {
		return DIRECTION_DESC.equalsIgnoreCase(this.dir);
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}