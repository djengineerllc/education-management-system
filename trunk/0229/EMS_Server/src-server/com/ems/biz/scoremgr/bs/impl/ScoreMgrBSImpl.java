package com.ems.biz.scoremgr.bs.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.scoremgr.bs.IScoreMgrBS;
import com.ems.biz.scoremgr.vo.ScoreQueryVO;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.model.vo.ScoreVO;

import conf.hibernate.ScoreBO;

@Service("scoreMgrBS")
public class ScoreMgrBSImpl implements IScoreMgrBS {
	
	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ScoreVO> findScoreVOBy(ScoreQueryVO queryVO) throws EMSException {
		StringBuilder scoreHql = new StringBuilder();
		scoreHql.append("SELECT new com.ems.common.model.vo.ScoreVO(scBO.id, scBO.stuId,stBO.stuNo, stBO.userName, scBO.termId, scBO.courseNo, scBO.scoreValue, scBO.scoreLevel) ");
		scoreHql.append("FROM ScoreBO scBO, StudentBO stBO ");
		scoreHql.append("WHERE scBO.stuId = stBO.id AND scBO.stuId IN (:stuIds) AND stBO.classId = :classId AND scBO.termId = :termId AND scBO.courseNo = :courseNo ");
		scoreHql.append("ORDER BY scBO.id ASC");
		
		List<ScoreVO> scoreVOList = commonDAO.findByValueBean(scoreHql.toString(), queryVO);
		if (scoreVOList.size() == 0) {
			scoreHql.delete(0, scoreHql.length());
			scoreHql.append("SELECT new com.ems.common.model.vo.ScoreVO(stBO.id, stBO.stuNo, stBO.userName) ");
			scoreHql.append("FROM StudentBO stBO ");
			scoreHql.append("WHERE stBO.id IN (:stuIds) AND stBO.classId = :classId ");
			scoreHql.append("ORDER BY stBO.id ASC");
			scoreVOList = commonDAO.findByValueBean(scoreHql.toString(), queryVO);
		}
		
		for (ScoreVO vo : scoreVOList) {
			if (vo.getId() == null) {
				vo.setTermId(queryVO.getTermId());
				vo.setCourseNo(queryVO.getCourseNo());
			}
		}
		
		return scoreVOList;
	}
	
	public void submitScore(List<ScoreBO> scoreBOList) throws EMSRollbackableException {
		commonDAO.saveOrUpdate(scoreBOList);
//		for (ScoreBO scoreBO : scoreBOList) {
//			if (scoreBO.getId() == null) {
//				commonDAO.save(scoreBO);
//			} else {
//				commonDAO.update(scoreBO);
//			}
//		}
	}
}
