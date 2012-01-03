package com.ems.biz.applyonline.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.model.info.applyonline.QueryApplyInfo;
import com.ems.common.util.StringUtils;

import conf.hibernate.ApplyOnlineInfo;
import conf.hibernate.Project;

public class ApplyOnlineServiceImpl implements IApplyOnlineService{
	private ICommonDAO commonDAO;
	
	public List<Project> findAllProject() throws Exception {
		return this.commonDAO.findAll(Project.class);
	}
	
	public void saveApplyInfo(ApplyOnlineInfo applyOnlineInfo)throws Exception{
		applyOnlineInfo.setCreateTime(new Date());
		applyOnlineInfo.setApplyTime(new Date());
		this.commonDAO.save(applyOnlineInfo);
	}
	
	public List<ApplyOnlineInfo> findApplyInfo(QueryApplyInfo queryApplyInfo) throws Exception{
		if(queryApplyInfo == null){
			throw new Exception("参数异常!");
		}
		if(StringUtils.isNullBlank(queryApplyInfo.getStuName())
				&& StringUtils.isNullBlank(queryApplyInfo.getStuSex())
				&& StringUtils.isNullBlank(queryApplyInfo.getApplyTime())){
			throw new Exception("查询条件不能都为空!");
		}
		StringBuffer hql = new StringBuffer();
		hql.append(" from ApplyOnlineInfo where 1=1 ");
		List paras = new ArrayList<String>();
		if(!StringUtils.isNullBlank(queryApplyInfo.getStuName())){
			hql.append(" and stuName like ");
			hql.append("'%"+queryApplyInfo.getStuName()+"%'");
		}
		if(!StringUtils.isNullBlank(queryApplyInfo.getStuSex())){
			hql.append(" and stuSex = ");
			hql.append("'"+queryApplyInfo.getStuSex()+"'");
		}
		if(!StringUtils.isNullBlank(queryApplyInfo.getApplyTime())){
			hql.append(" and YEAR(applyTime) = ");
			hql.append("'"+queryApplyInfo.getApplyTime().substring(0,4)+"'");
		}
		return this.commonDAO.findByHql(hql.toString());
	}
	
	public ApplyOnlineInfo findApplyOnlineInfoById(Integer id) throws Exception{
		return this.commonDAO.findById(ApplyOnlineInfo.class, id);
	}
	
	public void updateApplyOnlineInfo(ApplyOnlineInfo applyOnlineInfo) throws Exception{
		this.commonDAO.update(applyOnlineInfo);
	}
	
	public void deleteApplyOnlineInfo(Integer id) throws Exception{
		this.commonDAO.deleteById(ApplyOnlineInfo.class, id);
	}
	
	
	
	/*------------get---set-----------------------*/
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
}
