package com.ems.system.client.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
@SuppressWarnings("unchecked")
public class ExtPagingVO extends ExtBaseVO {
	
	private String id = "id";
	private Integer totalCount = 0;
	private List items;
	
	public ExtPagingVO() {
		this.items = new ArrayList();
		this.totalCount = 0;
	}
	
	public ExtPagingVO(String id, List items) {
		this.id = id;
		this.items = items;
		this.totalCount = items.size();
	}

	public ExtPagingVO(String id, Integer totalCount, List items) {
		this.id = id;
		this.totalCount = totalCount;
		this.items = items;
	}

	public ExtPagingVO(Integer totalCount, List items) {
		this.totalCount = totalCount;
		this.items = items;
	}
	
	public ExtPagingVO(List items) {
		this.totalCount = items.size();
		this.items = items;
	}
	
	public ExtPagingVO addItem(Object item) {
		this.items.add(item);
		
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}
}