package com.ems.common.model.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Chiknin
 */
public class BaseBO implements Serializable {
	
	private Integer id;
	private Date createTime;
	private Date updateTime;
	
//	public boolean equals(Object obj) {
//	}
//
//	public int hashCode() {
//	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
