package com.ems.system.client;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.ems.system.client.vo.ExtFormVO;
import com.ems.system.client.vo.ExtPagingVO;
import com.google.gson.JsonArray;

/**
 * @author Chiknin
 */
public class DirectCurdAction extends DirectAction {
	
	/**
	 * 初始化表格
	 * @param params
	 * @return
	 */
	public ExtPagingVO loadList(JsonArray params) {
		throw new UnsupportedOperationException("未实现");
	}

	/**
	 * 读取
	 * @param id
	 * @return
	 */
	public ExtFormVO read(String id) {
		throw new UnsupportedOperationException("未实现");
	}
	
	/**
	 * 创建
	 * @param formParameters
	 * @param fileFields
	 * @return
	 */
	public ExtFormVO create(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		return this.submit(formParameters, fileFields);
	}
	
	/**
	 * 更新
	 * @param formParameters
	 * @param fileFields
	 * @return
	 */
	public ExtFormVO update(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		return this.submit(formParameters, fileFields);
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public ExtFormVO delete(List<String> ids) {
		throw new UnsupportedOperationException("未实现");
	}
	
	/**
	 * 提交（创建|更新）
	 * @param formParameters
	 * @param fileFields
	 * @return
	 */
	public ExtFormVO submit(Map<String, String> formParameters,	 Map<String, FileItem> fileFields) {
		throw new UnsupportedOperationException("未实现");
	}
}