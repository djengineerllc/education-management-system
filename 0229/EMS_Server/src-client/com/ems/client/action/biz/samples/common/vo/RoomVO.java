package com.ems.client.action.biz.samples.common.vo;

public class RoomVO {
	
	private Integer id;
	private String roomName;
	private String termName;
	private Integer roomSize;
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
			Integer roomSize, String roomStatus, String roomComment) {
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
	public Integer getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(Integer roomSize) {
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
	
}
