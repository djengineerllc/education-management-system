package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class RoomBO extends BaseBO {

	private String roomName;
	private Integer termId;
	private String roomSize;
	private String roomStatus;
	private String roomComment;
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getTermId() {
		return termId;
	}
	public void setTermId(Integer termId) {
		this.termId = termId;
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
}