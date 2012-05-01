package com.ems.biz.stuMag.bs.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.stuMag.bs.IStudentManageBS;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.model.vo.RegistrationDisVO;
import com.ems.common.model.vo.RegistrationInfoVO;

import conf.hibernate.RegistrationBO;
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
	
	public List<RegistrationDisVO> findRegistDisVO(RegistrationInfoVO registrationInfoVO) throws EMSException{
		StringBuffer findHQL = new StringBuffer("from " + RegistrationBO.class.getName() + " as bo where 1=1");
		if(StringUtils.isNotBlank(registrationInfoVO.getUserName())){
			registrationInfoVO.setUserName("%"+registrationInfoVO.getUserName()+"%");
			findHQL.append(" and bo.student.userName like :userName");
		}
		if(StringUtils.isNotBlank(registrationInfoVO.getStuNo())){
			findHQL.append(" and bo.student.stuNo = :stuNo");
		}
		if(null != registrationInfoVO.getProjectId()){
			findHQL.append(" and bo.student.projectId = :projectId");
		}
		if(null != registrationInfoVO.getProfessId()){
			findHQL.append(" and bo.student.professId = :professId");
		}
		if(null != registrationInfoVO.getGradeId()){
			findHQL.append(" and bo.student.gradeId = :gradeId");
		}
		if(null != registrationInfoVO.getCheckInFlag()){
			findHQL.append(" and bo.checkInFlag = :checkInFlag");
		}
		if(null != registrationInfoVO.getEndFeeFlag()){
			findHQL.append(" and bo.endFeeFlag = :endFeeFlag");
		}
		if(null != registrationInfoVO.getRegistrationFlag()){
			findHQL.append(" and bo.registrationFlag = :registrationFlag");
		}
		List<RegistrationBO> registBOList = this.commonDAO.findByValueBean(findHQL.toString(), registrationInfoVO);
		
		List<RegistrationDisVO> registDisVOList = new ArrayList<RegistrationDisVO>(registBOList.size());
		for(Iterator<RegistrationBO> it = registBOList.iterator(); it.hasNext();){
			RegistrationDisVO vo = new RegistrationDisVO();
			RegistrationBO bo = it.next();
			BeanUtils.copyProperties(vo, bo.getStudent(), new String[]{"id"});
			BeanUtils.copyProperties(vo, bo);
			registDisVOList.add(vo);
		}
		return registDisVOList;
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
	
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
	

}
