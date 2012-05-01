package com.ems.biz.stuMag.bs.impl;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.stuMag.bs.IStudentManageBS;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;

import conf.hibernate.StudentBO;

/**
 * 学生信息管理
 * @author all
 *
 */
@Service("studentManageBS")
public class StudentManageBSImpl implements IStudentManageBS{
	
	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;

	public List<StudentBO> findByStudentBO(StudentBO studentBO)	throws EMSException {
		StringBuffer hql = new StringBuffer();
		hql.append("from " + StudentBO.class.getName() + " as bo where 1 = 1");
		if(StringUtils.isNotBlank(studentBO.getStuNo())){
			hql.append(" and bo.stuNo = :stuNo");
		}
		if(StringUtils.isNotBlank(studentBO.getUserName())){
			hql.append(" and bo.userName = :userName");
		}
		if(StringUtils.isNotBlank(studentBO.getLoginName())){
			hql.append(" and bo.loginName = :loginName");
		}
		if(null != studentBO.getGradeId()){
			hql.append(" and bo.gradeId = :gradeId");
		}
		if(null != studentBO.getProjectId()){
			hql.append(" and bo.projectId = :projectId");
		}
		if(null != studentBO.getProfessId()){
			hql.append(" and bo.professId = :professId");
		}
		if(null != studentBO.getClassId()){
			hql.append(" and bo.classId = :classId");
		}
		if(StringUtils.isNotBlank(studentBO.getStatus())){
			hql.append(" and bo.status = :status");
		}
		return this.commonDAO.findByValueBean(hql.toString(), studentBO);
	}
	
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	public void deleteByIds(Integer[] ids) throws EMSException {
		if(ArrayUtils.isEmpty(ids)) return;
		
		for(int i = 0; i < ids.length; i++){
			commonDAO.delete(commonDAO.findById(StudentBO.class, ids[i]));
		}
	}
	
	public StudentBO findByStuNo(String stuNo) throws EMSException{
		String findHQL = "from " + StudentBO.class.getName() + " bo where bo.stuNo = ?";
		List<StudentBO> students = this.commonDAO.findByHql(findHQL, stuNo);
		if(!students.isEmpty()){
			return students.get(0);
		}
		return null;
	}

	public void update(Object obj) throws EMSException {
		this.commonDAO.update(obj);
	}

	public StudentBO findById(Integer id) throws EMSException {
		return this.commonDAO.findById(StudentBO.class, id);
	}

	public void create(Object obj) throws EMSException {
		this.commonDAO.save(obj);
	}

}
