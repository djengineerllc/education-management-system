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
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new ScoreVO(scBO.id, stBO.id, stBO.userName, scBO.termId, scBO.courseNo, scBO.scoreValue, scBO.scoreLevel) ");
		hql.append("FROM StudentBO stBO LEFT JOIN ScoreBO scBO ON stBO.id = scBO.stuId ");
		hql.append("WHERE stBO.classId = :classId AND scBO.termId = :termId AND scBO.courseNo = :courseNo ");
		hql.append("ORDER BY stBO.id ASC");
		
		List<ScoreVO> scoreVOList = commonDAO.findListByHql(hql.toString(), queryVO);
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
