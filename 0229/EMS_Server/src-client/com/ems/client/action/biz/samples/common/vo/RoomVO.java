package com.ems.client.action.biz.samples.common.vo;

import com.ems.common.util.StringUtils;

public class RoomVO {
	
	private Integer id;
	private String roomName;
	private Integer termId;
	private String termName;
	private String roomSize;
	private String roomStatus;
	private String roomComment;
	
	
	
	public RoomVO() {
		super();
	}

	public RoomVO(Integer id, String roomName) {
		super();
		this.id = id;
		this.roomName = roomName;
	}
	
	
	public RoomVO(Integer id, String roomName, String termName,
			String roomSize, String roomStatus, String roomComment) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.termName = termName;
		this.roomSize = roomSize;
		this.roomStatus = roomStatus;
		this.roomComment = roomComment;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	
	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getRoomComment() {
		return roomComment;
	}
	public void setRoomComment(String roomComment) {
		this.roomComment = roomComment;
	}

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}
	
}
