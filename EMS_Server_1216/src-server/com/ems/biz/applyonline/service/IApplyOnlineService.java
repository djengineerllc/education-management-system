package com.ems.biz.applyonline.service;

import java.util.List;

import com.ems.common.model.info.applyonline.QueryApplyInfo;

import conf.hibernate.ApplyOnlineInfo;
import conf.hibernate.Project;

public interface IApplyOnlineService {
	
	/**
	 * 获取所有报名项目信息
	 * @return
	 * @throws Exception
	 */
	public List<Project> findAllProject() throws Exception;
	
	/**
	 * 保存网上报名信息
	 * @param applyOnlineInfo
	 * @throws Exception
	 */
	public void saveApplyInfo(ApplyOnlineInfo applyOnlineInfo)throws Exception;
	
	/**
	 * 根据条件查找学生网上报名信息
	 * @param queryApplyInfo
	 * @return
	 * @throws Exception
	 */
	public List<ApplyOnlineInfo> findApplyInfo(QueryApplyInfo queryApplyInfo) throws Exception;

}
