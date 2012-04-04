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
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BaseBO)) {
			return false;
		}
		
		final BaseBO bo = (BaseBO) other;
		if (this.id != null && bo.getId() != null && this.id.equals(bo.getId())) {
			return true;
		}
		
		return false;
	}

	public int hashCode() {
		if (this.id == null) {
			return super.hashCode();
		}
		
		return (29 * super.hashCode()) + this.id.hashCode();
	}

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
