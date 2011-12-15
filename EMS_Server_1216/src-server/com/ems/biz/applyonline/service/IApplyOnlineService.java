package com.ems.biz.applyonline.service;

import java.util.List;
import conf.hibernate.Project;

public interface IApplyOnlineService {
	
	/**
	 * 获取所有报名项目信息
	 * @return
	 * @throws Exception
	 */
	public List<Project> findAllProject() throws Exception;

}
