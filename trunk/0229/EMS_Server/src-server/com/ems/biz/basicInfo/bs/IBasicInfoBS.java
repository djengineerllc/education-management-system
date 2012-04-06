package com.ems.biz.basicInfo.bs;

import java.io.Serializable;
import java.util.List;

import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.model.vo.BookVO;
import com.ems.common.model.vo.ClassVO;
import com.ems.common.model.vo.CourseVO;
import com.ems.common.model.vo.GradeVO;
import com.ems.common.model.vo.ProfessVO;
import com.ems.common.model.vo.ProjectVO;
import com.ems.common.model.vo.RoomVO;
import com.ems.common.model.vo.TermVO;
import com.ems.common.model.vo.UserInfoVO;

import conf.hibernate.BookBO;
import conf.hibernate.ClassBO;
import conf.hibernate.CourseBO;
import conf.hibernate.GradeBO;
import conf.hibernate.ProfessBO;
import conf.hibernate.ProjectBO;
import conf.hibernate.RoomBO;
import conf.hibernate.TermBO;
import conf.hibernate.UserInfoBO;

public interface IBasicInfoBS {
	
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
	 * 查找学期信息
	 * @param classVO
	 * @return
	 * @throws EMSException
	 */
	public List<TermBO> findTermByVO(TermVO termVO) throws EMSException;
	
	
	/**
	 * 查找年级信息
	 * @param classVO
	 * @return
	 * @throws EMSException
	 */
	public List<GradeBO> findGradeByVO(GradeVO gradeVO) throws EMSException;
	
	
	/**
	 * 查找班级信息
	 * @param classVO
	 * @return
	 * @throws EMSException
	 */
	public List<ClassBO> findClassByVO(ClassVO classVO) throws EMSException;
	public List<ClassBO> findClassById(List<Integer> id) throws EMSException;
	
	/**
	 * 查找项目信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<ProjectBO> findProjectByVO(ProjectVO projectVO) throws EMSException;
	
	/**
	 * 查找专业信息
	 * @param professVO
	 * @return
	 * @throws EMSException
	 */
	public List<ProfessBO> findProfessByVO(ProfessVO professVO) throws EMSException;
	
	/**
	 * 查找课程信息
	 * @param courseVO
	 * @return
	 * @throws EMSException
	 */
	public List<CourseBO> findCourseByVO(CourseVO courseVO) throws EMSException;
	public List<CourseBO> findCourseByNo(List<String> no) throws EMSException;
	
	/**
	 * 查找教室信息
	 * @param roomVO
	 * @return
	 * @throws EMSException
	 */
	public List<RoomBO> findRoomByVO(RoomVO roomVO) throws EMSException;
	
	/**
	 * 查找教材信息
	 * @param projectVO
	 * @return
	 * @throws EMSException
	 */
	public List<BookBO> findBookByVO(BookVO bookVO) throws EMSException;
	
	/**
	 * 查找用户信息
	 * @param userInfoVO
	 * @return
	 * @throws EMSException
	 */
	public List<UserInfoVO> findUserByVO(UserInfoVO userInfoVO) throws EMSException;
	public List<UserInfoBO> findUserInfoById(List<Integer> id) throws EMSException;
	
	/**
	 * 
	 * @param id userId
	 * @return
	 * @throws EMSException
	 */
	public UserInfoVO findUserVOById(Integer id) throws EMSException;
	
	public void delete(Object obj) throws EMSRollbackableException;
	
	public void update(Object obj) throws EMSRollbackableException;
	
	public TermBO findCurrTerm() throws EMSException;
	
	public void createUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException;
	
	public void updateUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException;
	
	public void deleteUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException;
}
