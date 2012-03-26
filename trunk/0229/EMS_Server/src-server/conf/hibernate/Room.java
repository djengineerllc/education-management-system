package conf.hibernate;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Room entity. @author MyEclipse Persistence Tools
 */

public class Room implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomName;
	private Integer termId;
	private String roomSize;
	private String roomStatus;
	private String roomComment;
	private Date createTime;
	private Date updateTime;

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** full constructor */
	public Room(String roomName, Integer termId, String roomSize,
			String roomStatus, String roomComment, Timestamp createTime,
			Timestamp updateTime) {
		this.roomName = roomName;
		this.termId = termId;
		this.roomSize = roomSize;
		this.roomStatus = roomStatus;
		this.roomComment = roomComment;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getTermId() {
		return this.termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public String getRoomSize() {
		return this.roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomStatus() {
		return this.roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public String getRoomComment() {
		return this.roomComment;
	}

	public void setRoomComment(String roomComment) {
		this.roomComment = roomComment;
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