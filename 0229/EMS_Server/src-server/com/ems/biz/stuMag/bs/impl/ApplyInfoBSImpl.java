package com.ems.biz.stuMag.bs.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.stuMag.bs.IApplyInfoBS;
import com.ems.common.code.Code;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.ApplyInfoVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.DateUtil;

import conf.hibernate.ApplyInfoBO;
import conf.hibernate.StudentBO;

@Service("applyInfoBS")
public class ApplyInfoBSImpl implements IApplyInfoBS {
	
	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;

	@SuppressWarnings("unchecked")
	public List<ApplyInfoBO> findApplyInfoBO(ApplyInfoVO applyInfoVO) throws EMSException {
		StringBuffer hql = new StringBuffer();
		hql.append("from " + ApplyInfoBO.class.getName() + " as bo where 1 = 1");
		if(StringUtils.isNotBlank(applyInfoVO.getName())){
			hql.append(" and bo.name like :name");
		}
		if(StringUtils.isNotBlank(applyInfoVO.getSex())){
			hql.append(" and bo.sex = :sex");
		}
		if(null != applyInfoVO.getFirstProjectId()){
			hql.append(" and bo.firstProjectId = :firstProjectId");
		}
		if(null != applyInfoVO.getFirstProfessId()){
			hql.append(" and bo.firstProfessId = :firstProfessId");
		}
		if(null != applyInfoVO.getSecondProjectId()){
			hql.append(" and bo.secondProjectId = :secondProjectId");
		}
		if(null != applyInfoVO.getSecondProfessId()){
			hql.append(" and bo.secondProfessId = :secondProfessId");
		}
		if(StringUtils.isNotBlank(applyInfoVO.getStartDate())){
			hql.append(" and bo.applyDate >= :startDate");
		}
		if(StringUtils.isNotBlank(applyInfoVO.getEndDate())){
			hql.append(" and bo.applyDate <= :endDate");
		}
		return this.commonDAO.findByValueBean(hql.toString(), applyInfoVO);
	}

	public void create(ApplyInfoBO applyInfoBO) throws EMSException {
		if(StringUtils.isBlank(applyInfoBO.getAppNum())){
			applyInfoBO.setApplyStatus(Code.getValue("ApplyStatus", "S5"));
		}else{
			applyInfoBO.setApplyStatus(Code.getValue("ApplyStatus", "S1"));
		}
		commonDAO.save(applyInfoBO);
	}

	public ApplyInfoBO findById(Integer id) throws EMSException {
		return commonDAO.findById(ApplyInfoBO.class, id);
	}

	public void update(ApplyInfoBO applyInfoBO) throws EMSException {
		if(StringUtils.isBlank(applyInfoBO.getAppNum())){
			applyInfoBO.setApplyStatus(Code.getValue("ApplyStatus", "S5"));
		}else{
			applyInfoBO.setApplyStatus(Code.getValue("ApplyStatus", "S1"));
		}
		this.commonDAO.update(applyInfoBO);
	}

	public void deleteByIds(Integer[] ids) throws EMSException {
		if(null == ids || ids.length == 0) return;
		int i = 0;
		for(Integer id = ids[i]; i < ids.length; i++){
			this.commonDAO.deleteById(ApplyInfoBO.class, id);
		}
	}
	
	/**
	 * 录取-不录取
	 */
	public void admission(ApplyInfoBO applyInfoBO) throws EMSException {
		ApplyInfoBO oldApplyInfoBO = this.findById(applyInfoBO.getId());
		// 1.如果是已经交费 抛出异常
		if(Code.getValue("ApplyStatus", "S4").equals(applyInfoBO.getApplyStatus())){
			throw new EMSException("该学生已经交费，不允许进行录取等操作");
		}
		oldApplyInfoBO.setApplyStatus(applyInfoBO.getApplyStatus());
		
		if(Code.getValue("ApplyStatus", "S2").equals(applyInfoBO.getApplyStatus())){
		// 2.如果是已经交费，那么填写入学生表
			oldApplyInfoBO.setAdmissionProfessId(applyInfoBO.getAdmissionProfessId());
			oldApplyInfoBO.setAdmissionProjectId(applyInfoBO.getAdmissionProjectId());
			oldApplyInfoBO.setStuNo(applyInfoBO.getStuNo());
			// invoke insert into tb_student table
			this.commonDAO.save(this.makeStudentBO(oldApplyInfoBO));
		}
		this.update(oldApplyInfoBO);
	}
	
	private StudentBO makeStudentBO(ApplyInfoBO applyInfoBO) throws EMSException{
		StudentBO stuBO = new StudentBO();
		BeanUtils.copyProperties(applyInfoBO, stuBO, new String[]{"id"});
		stuBO.setGkScore(applyInfoBO.getGkTotalScore());
		stuBO.setAdmissionQualif(applyInfoBO.getCurrentDegree());
		stuBO.setContact(applyInfoBO.getContactPersonName());
		stuBO.setUserName(applyInfoBO.getName());
		stuBO.setCreateTime(DateUtil.currData());
		stuBO.setUpdateTime(DateUtil.currData());
		return stuBO;
	}

}
