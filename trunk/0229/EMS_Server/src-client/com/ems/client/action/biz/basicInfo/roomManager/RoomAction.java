package com.ems.client.action.biz.basicInfo.roomManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.client.action.biz.samples.common.vo.RoomVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.Room;
import conf.hibernate.Term;

/**
 * 教室管理
 * @author zhuchaole
 *
 */

@ActionScope(scope=Scope.APPLICATION)
public class RoomAction extends DirectAction {


	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private IBasicInfoService basicInfoService = (IBasicInfoService)super.getBean("basicInfoService");
	
	@DirectMethod
	public ExtPagingVO loadRoom(JsonArray params) {
		try{
			List<RoomVO> roomVOList = new ArrayList<RoomVO>();
			RoomVO roomVO_qry = BeanUtils.toBeanFromJsonFirst(params, RoomVO.class);
			List<Room> rooms = this.basicInfoService.findRoomByVO(roomVO_qry);
			for(Room room_:rooms){
				RoomVO roomVO = new RoomVO();
				roomVO.setId(room_.getId());
				roomVO.setRoomName(room_.getRoomName());
				roomVO.setRoomSize(room_.getRoomSize());
				roomVO.setTermId(room_.getTermId());
				roomVO.setTermName(this.basicInfoService.findById(Term.class,room_.getTermId()).getTermName());
				roomVO.setRoomStatus(room_.getRoomStatus());
				roomVOList.add(roomVO);
			}
			return new ExtPagingVO(roomVOList);
		}catch(Exception e){
			logger.error("loadRoom--error--",e);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		RoomVO roomVO = BeanUtils.toBeanFromMap(formParameters, RoomVO.class);
		ExtFormVO result = new ExtFormVO();
		List<Room> rooms = this.basicInfoService.getAll(Room.class, null);
		for(Room room_ : rooms){
			if (room_.getRoomName().equals(roomVO.getRoomName())) {
				result.addError("RoomName", String.format("教室[%s]已重复", roomVO.getRoomName()));
				return result;
			}
		}
		Room room = new Room();
		room.setRoomName(roomVO.getRoomName());
		room.setRoomSize(roomVO.getRoomSize());
		room.setRoomStatus(roomVO.getRoomStatus());
		room.setTermId(roomVO.getTermId());
		room.setCreateTime(new Date());
		this.basicInfoService.save(room);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		System.out.println("getFormData id = " + id);
		RoomVO roomVO = null;
		Room room = null;
		if(id != null){
			room = this.basicInfoService.findById(Room.class, id);
			roomVO = new RoomVO();
			roomVO.setId(room.getId());
			roomVO.setRoomName(room.getRoomName());
			roomVO.setRoomSize(room.getRoomSize());
			roomVO.setRoomStatus(room.getRoomStatus());
			roomVO.setTermId(room.getTermId());
			roomVO.setTermName(this.basicInfoService.findById(Term.class, room.getTermId()).getTermName());
		}
		return new ExtFormVO(roomVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		RoomVO roomVO = BeanUtils.toBeanFromMap(formParameters, RoomVO.class);
		ExtFormVO result = new ExtFormVO();
		Room room = this.basicInfoService.findById(Room.class, roomVO.getId());
		room.setRoomName(roomVO.getRoomName());
		room.setRoomSize(roomVO.getRoomSize());
		room.setRoomStatus(roomVO.getRoomStatus());
		room.setTermId(roomVO.getTermId());
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			this.basicInfoService.delete(this.basicInfoService.findById(Room.class, id));
		}
		return new ExtFormVO();
	}
	
	@DirectMethod
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {}
	
	@DirectMethod
	public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
	}
	@DirectFormPostMethod
	public ExtFormVO batchImport(Map<String, String> formParameters, Map<String, FileItem> fileFields) throws IOException {
		ExtFormVO formVO = new ExtFormVO();
		return formVO;
	}




}
