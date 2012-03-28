package com.ems.biz.basicInfo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ems.biz.basicInfo.service.IBasicInfoService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.model.vo.BookVO;
import com.ems.common.model.vo.CourseVO;
import com.ems.common.model.vo.ProfessVO;
import com.ems.common.model.vo.ProjectVO;
import com.ems.common.model.vo.RoomVO;
import com.ems.common.util.StringUtils;

import conf.hibernate.BookBO;
import conf.hibernate.CourseBO;
import conf.hibernate.ProfessBO;
import conf.hibernate.ProjectBO;
import conf.hibernate.RoomBO;

public class BasicInfoService implements IBasicInfoService {
	
	private ICommonDAO commonDAO;

	public void save(Object obj) throws EMSRollbackableException {
		this.commonDAO.save(obj);
	}

	public <T> T findById(Class<T> entityClass, Serializable id)
			throws EMSException {
		return this.commonDAO.findById(entityClass, id);
	}
	
	public List<ProjectBO> findProjectByVO(ProjectVO projectVO) throws EMSException{
		String hql = "from ProjectBO where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(projectVO.getProjectName())){
			hql = hql+" and projectName like ? "; 
			valueParam.add("%"+projectVO.getProjectName()+"%");
		}
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}
	
	public List<ProfessBO> findProfessByVO(ProfessVO professVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from ProfessBO where 1=1 ");
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
	
	public List<CourseBO> findCourseByVO(CourseVO courseVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from CourseBO where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(courseVO.getCourseCnName())){
			hql.append(" and courseName like ? "); 
			valueParam.add("%"+courseVO.getCourseCnName()+"%");
		}
		if(!StringUtils.isNullBlank(courseVO.getCourseEnName())){
			hql.append(" and courseEngName like ? "); 
			valueParam.add("%"+courseVO.getCourseEnName()+"%");
		}
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<RoomBO> findRoomByVO(RoomVO roomVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from RoomBO where 1=1 ");
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
	
	public List<BookBO> findBookByVO(BookVO bookVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from BookBO where 1=1 ");
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
