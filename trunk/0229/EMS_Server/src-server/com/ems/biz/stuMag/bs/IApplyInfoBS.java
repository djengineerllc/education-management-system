package com.ems.biz.stuMag.bs;

import java.util.List;

import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.ApplyInfoVO;

import conf.hibernate.ApplyInfoBO;

public interface IApplyInfoBS {
	
	List<ApplyInfoBO> findApplyInfoBO(ApplyInfoVO applyInfoVO) throws EMSException;

	void create(ApplyInfoBO applyInfoBO) throws EMSException;

	ApplyInfoBO findById(Integer id) throws EMSException;

	void update(ApplyInfoBO applyInfoBO) throws EMSException;

	void deleteByIds(Integer[] ids) throws EMSException;
	
	void admission(ApplyInfoBO applyInfoBO) throws EMSException;
}
