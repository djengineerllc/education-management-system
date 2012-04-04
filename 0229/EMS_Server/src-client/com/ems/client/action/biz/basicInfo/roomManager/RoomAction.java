package com.ems.client.action.biz.basicInfo.roomManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.model.vo.RoomVO;
import com.ems.common.util.BeanUtils;
import com.ems.system.client.DirectAction;
import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.RoomBO;
import conf.hibernate.TermBO;

/**
 * 教室管理
 * @author zhuchaole
 *
 */

@ActionScope(scope=Scope.APPLICATION)
public class RoomAction extends DirectAction {

	private IBasicInfoBS basicInfoBS = this.getBean("basicInfoBS", IBasicInfoBS.class);
	
	@DirectMethod
	public ExtPagingVO loadRoom(JsonArray params) {
		List<RoomVO> roomVOList = new ArrayList<RoomVO>();
		RoomVO roomVO_qry = BeanUtils.toBeanFromJsonFirst(params, RoomVO.class);
		List<RoomBO> rooms = basicInfoBS.findRoomByVO(roomVO_qry);
		for(RoomBO room_:rooms){
			RoomVO roomVO = new RoomVO();
			roomVO.setId(room_.getId());
			roomVO.setRoomName(room_.getRoomName());
			roomVO.setRoomSize(room_.getRoomSize());
			roomVO.setTermId(room_.getTermId());
			roomVO.setTermName(basicInfoBS.findById(TermBO.class,room_.getTermId()).getTermName());
			roomVO.setRoomStatus(room_.getRoomStatus());
			roomVO.setRoomComment(room_.getRoomComment());
			roomVOList.add(roomVO);
		}
		return new ExtPagingVO(roomVOList);
	}
	
	@DirectFormPostMethod
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		RoomBO roomBO = BeanUtils.toBeanFromMap(formParameters, RoomBO.class);
		ExtFormVO result = new ExtFormVO();
		List<RoomBO> rooms = basicInfoBS.getAll(RoomBO.class, null);
		for(RoomBO room_ : rooms){
			if (room_.getRoomName().equals(roomBO.getRoomName())) {
				result.addError("RoomName", String.format("教室[%s]已重复", roomBO.getRoomName()));
				return result;
			}
		}
		roomBO.setCreateTime(new Date());
		basicInfoBS.save(roomBO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer id) {
		RoomVO roomVO = null;
		RoomBO room = null;
		if(id != null){
			room = basicInfoBS.findById(RoomBO.class, id);
			roomVO = new RoomVO();
			roomVO.setId(room.getId());
			roomVO.setRoomName(room.getRoomName());
			roomVO.setRoomSize(room.getRoomSize());
			roomVO.setRoomStatus(room.getRoomStatus());
			roomVO.setTermId(room.getTermId());
			roomVO.setRoomComment(room.getRoomComment());
			roomVO.setTermName(basicInfoBS.findById(TermBO.class, room.getTermId()).getTermName());
		}
		return new ExtFormVO(roomVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		RoomBO roomBO = BeanUtils.toBeanFromMap(formParameters, RoomBO.class);
		ExtFormVO result = new ExtFormVO();
		roomBO.setUpdateTime(new Date());
		basicInfoBS.update(roomBO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			basicInfoBS.delete(basicInfoBS.findById(RoomBO.class, id));
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