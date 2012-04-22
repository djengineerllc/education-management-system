package com.ems.biz.scoremgr.bs.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
	
	@Override
	public List<ScoreVO> findScoreVOBy(ScoreQueryVO queryVO) throws EMSException {
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT stu.id stuId, stu.stu_no stuNo, ui.user_name stuName, sco.id, sco.term_id termId, sco.course_no courseNo, sco.score_value scoreValue, sco.score_level scoreLevel ");
		sql.append("FROM tb_student stu ");
		sql.append("INNER JOIN ts_user_info ui ON stu.id = ui.id ");
		sql.append("LEFT JOIN (SELECT * FROM tb_score WHERE term_id = ? ");
		params.add(queryVO.getTermId());
		if (StringUtils.isNotEmpty(queryVO.getCourseNo())) {
			sql.append("AND course_no = ? ");
			params.add(queryVO.getCourseNo());
		}
		sql.append(") sco ON stu.id = sco.stu_id ");
		sql.append("WHERE 1 = 1 ");
		if (queryVO.getClassId() != null) {
			sql.append("AND stu.class_id = ? ");
			params.add(queryVO.getClassId());
		}
		if (queryVO.getStuIds() != null && queryVO.getStuIds().size() > 0) {
			sql.append("AND stu.id IN (?");
			params.add(queryVO.getStuIds().get(0));
			for (int i = 1; i < queryVO.getStuIds().size(); i++) {
				sql.append(",?");
				params.add(queryVO.getStuIds().get(i));
			}
			sql.append(") ");
		}
		if (queryVO.getStuNos() != null && queryVO.getStuNos().size() > 0) {
			sql.append("AND stu.stu_no IN (?");
			params.add(queryVO.getStuNos().get(0));
			for (int i = 1; i < queryVO.getStuNos().size(); i++) {
				sql.append(",?");
				params.add(queryVO.getStuNos().get(i));
			}
			sql.append(") ");
		}
		
		sql.append("ORDER BY stu.id ASC");
		
		List<ScoreVO> scoreVOList = commonDAO.findBySql(sql.toString(), params.toArray(), ScoreVO.class);
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
