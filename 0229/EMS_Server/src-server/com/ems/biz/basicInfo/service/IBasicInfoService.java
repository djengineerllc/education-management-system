package com.ems.biz.basicInfo.service;

import java.io.Serializable;
import java.util.List;

import com.ems.client.action.biz.samples.common.vo.BookVO;
import com.ems.client.action.biz.samples.common.vo.ProfessVO;
import com.ems.client.action.biz.samples.common.vo.ProjectVO;
import com.ems.client.action.biz.samples.common.vo.RoomVO;
import com.ems.client.action.biz.samples.common.vo.SubjectVO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;

import conf.hibernate.Book;
import conf.hibernate.Profess;
import conf.hibernate.Project;
import conf.hibernate.Room;
import conf.hibernate.Subject;

public interface IBasicInfoService {
	
	public void save(Object obj) throws EMSRollbackableException;
	
	/**
	 * 获取指定类型全部信息
	 * @param <T>
	 * @param clazz
	 * @param orderBy 排序字符串 不用排序则用NULL
	 * @return
	 * @throws EMSException
	 */
	public <T> List<T> getAll(Class<T> clazz,String orderBy) throws EMSException;
	
	public <T>T findById(Class<T> entityClass,Serializable id) throws EMSException;
	/**
	 * 查找项目信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<Project> findProjectByVO(ProjectVO projectVO) throws EMSException;
	
	/**
	 * 查找专业信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<Profess> findProfessByVO(ProfessVO professVO) throws EMSException;
	
	/**
	 * 查找课程信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<Subject> findSubjectByVO(SubjectVO subjectVO) throws EMSException;
	
	/**
	 * 查找教室信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<Room> findRoomByVO(RoomVO roomVO) throws EMSException;
	
	/**
	 * 查找教材信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<Book> findBookByVO(BookVO bookVO) throws EMSException;
	
	public void delete(Object obj) throws EMSRollbackableException;
	
	public void update(Object obj) throws EMSRollbackableException;
	
}
