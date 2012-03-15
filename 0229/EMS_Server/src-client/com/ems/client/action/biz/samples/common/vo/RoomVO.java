package com.ems.client.action.biz.samples.common.vo;

public class RoomVO {
	
	private Integer roomId;
	private String roomName;
	private String termName;
	private Integer roomSize;
	private String roomStatus;
	private String roomComment;
	
	
	
	public RoomVO() {
		super();
	}

	public RoomVO(Integer roomId, String roomName) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
	}
	
	
	public RoomVO(Integer roomId, String roomName, String termName,
			Integer roomSize, String roomStatus, String roomComment) {
		super();
		this.roomId = roomId;
		this.roomName = roomName;
		this.termName = termName;
		this.roomSize = roomSize;
		this.roomStatus = roomStatus;
		this.roomComment = roomComment;
	}


	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
