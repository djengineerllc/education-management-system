package com.ems.biz.scoremgr.bs;

import java.util.List;

import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.ScoreVO;

public interface IScoreMgrBS {
	public List<ScoreVO> findScoreVOBy(ScoreQueryVO queryVO) throws EMSException;
}