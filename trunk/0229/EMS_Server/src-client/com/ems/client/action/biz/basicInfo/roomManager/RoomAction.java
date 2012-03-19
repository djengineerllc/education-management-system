package com.ems.client.action.biz.basicInfo.roomManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.ems.client.action.biz.samples.common.vo.ProjectVO;
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

@ActionScope(scope=Scope.APPLICATION)
public class RoomAction extends DirectAction {


	
	private Logger logger = Logger.getLogger(this.getClass()); 
	private static Integer idCounter = 10;
	
	private static Map<Integer, RoomVO> room = new HashMap<Integer, RoomVO>();
	static {
		room.put(1, new RoomVO(1, "北楼101","2011-2012学期",50,"再用",""));
		room.put(2, new RoomVO(2, "北楼102","2011-2012学期",50,"再用",""));
		room.put(3, new RoomVO(3, "北楼201","2011-2012学期",50,"再用",""));
		room.put(4, new RoomVO(4, "北楼202","2011-2012学期",50,"再用",""));
		room.put(5, new RoomVO(5, "多媒体楼501","2011-2012学期",100,"再用","计算机专业专用"));
		room.put(6, new RoomVO(6, "多媒体楼502","2011-2012学期",100,"再用","计算机专业专用"));
	}
	
	@DirectMethod
	public ExtPagingVO loadRoom(JsonArray params) {
		try{
			List<RoomVO> roomVOList = new ArrayList<RoomVO>();
			for (Map.Entry<Integer, RoomVO> user : room.entrySet()) {
				roomVOList.add(user.getValue());
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
		
		if (room.containsKey(roomVO.getRoomName())) {
			result.addError("RoomName", String.format("教室[%s]已重复", roomVO.getRoomName()));
			return result;
		}
		
		roomVO.setId(++idCounter);
		room.put(roomVO.getId(), roomVO);
		return result;
	}
	@DirectMethod
	public ExtFormVO read(Integer roomId) {
		System.out.println("getFormData gradeId = " + roomId);
		RoomVO roomVO = room.get(roomId);
		return new ExtFormVO(roomVO);
	}
	@DirectFormPostMethod
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		RoomVO roomVO = BeanUtils.toBeanFromMap(formParameters, RoomVO.class);
		ExtFormVO result = new ExtFormVO();
		room.put(roomVO.getId(),roomVO);
		return result;
	}
	
	@DirectMethod
	public ExtFormVO delete(Integer[] ids) {
		for (Integer id : ids) {
			room.remove(id);
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
