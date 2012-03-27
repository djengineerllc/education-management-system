package com.ems.biz.basicInfo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.client.action.biz.samples.common.vo.BookVO;
import com.ems.client.action.biz.samples.common.vo.ProfessVO;
import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.client.action.biz.samples.common.vo.RoomVO;
import com.ems.client.action.biz.samples.common.vo.SubjectVO;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.util.StringUtils;

import conf.hibernate.Book;
import conf.hibernate.Profess;
import conf.hibernate.Project;
import conf.hibernate.Room;
import conf.hibernate.Subject;

public class BasicInfoService implements IBasicInfoService {
	
	private ICommonDAO commonDAO;

	public void save(Object obj) throws EMSRollbackableException {
		this.commonDAO.save(obj);
	}

	public <T> T findById(Class<T> entityClass, Serializable id)
			throws EMSException {
		return this.commonDAO.findById(entityClass, id);
	}
	
	public List<Project> findProjectByVO(ProjectVO projectVO) throws EMSException{
		String hql = "from Project where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(projectVO.getProjectName())){
			hql = hql+" and projectName like ? "; 
			valueParam.add("%"+projectVO.getProjectName()+"%");
		}
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}
	
	public List<Profess> findProfessByVO(ProfessVO professVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from Profess where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(professVO.getProjectId() != -1){
			hql.append(" and projectId = ? "); 
			valueParam.add(professVO.getProjectId());
		}
		if(!StringUtils.isNullBlank(professVO.getProfessName())){
			hql.append(" and professName like ? "); 
			valueParam.add("%"+professVO.getProfessName()+"%");
		}
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<Subject> findSubjectByVO(SubjectVO subjectVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from Subject where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(subjectVO.getSubjectCnName())){
			hql.append(" and subjectName like ? "); 
			valueParam.add("%"+subjectVO.getSubjectCnName()+"%");
		}
		if(!StringUtils.isNullBlank(subjectVO.getSubjectEnName())){
			hql.append(" and subjectEngName like ? "); 
			valueParam.add("%"+subjectVO.getSubjectEnName()+"%");
		}
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<Room> findRoomByVO(RoomVO roomVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from Room where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(roomVO.getRoomName())){
			hql.append(" and roomName like ? "); 
			valueParam.add("%"+roomVO.getRoomName()+"%");
		}
		if(roomVO.getTermId() != -1){
			hql.append(" and termId = ? "); 
			valueParam.add(roomVO.getTermId());
		}
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<Book> findBookByVO(BookVO bookVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from Book where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(bookVO.getBookName())){
			hql.append(" and bookName like ? "); 
			valueParam.add("%"+bookVO.getBookName()+"%");
		}
		if(!StringUtils.isNullBlank(bookVO.getAuthor())){
			hql.append(" and author like ? "); 
			valueParam.add("%"+bookVO.getAuthor()+"%");
		}
		if(!StringUtils.isNullBlank(bookVO.getIsbnNo())){
			hql.append(" and isbnNo = ? "); 
			valueParam.add(bookVO.getIsbnNo());
		}
		if(!StringUtils.isNullBlank(bookVO.getPublishName())){
			hql.append(" and publishName like ? "); 
			valueParam.add("%"+bookVO.getPublishName()+"%");
		}
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}

	public <T> List<T> getAll(Class<T> clazz,String orderBy) throws EMSException {
		return this.commonDAO.findAll(clazz,orderBy);
	}
	
	public void delete(Object obj) throws EMSRollbackableException {
		this.commonDAO.delete(obj);
	}
	
	public void update(Object obj) throws EMSRollbackableException{
		this.commonDAO.update(obj);
	}

	
	/** GET **/
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

}
