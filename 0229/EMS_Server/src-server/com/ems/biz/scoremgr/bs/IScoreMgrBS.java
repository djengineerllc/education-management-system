package com.ems.biz.scoremgr.bs;

import java.util.List;

import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.model.vo.ScoreVO;

import conf.hibernate.ScoreBO;

public interface IScoreMgrBS {
	public List<ScoreVO> findScoreVOBy(ScoreQueryVO queryVO) throws EMSException;
	public void submitScore(List<ScoreBO> scoreBOList) throws EMSRollbackableException;
}