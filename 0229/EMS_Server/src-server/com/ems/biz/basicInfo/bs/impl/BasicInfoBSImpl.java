package com.ems.biz.basicInfo.bs.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ems.biz.basicInfo.bs.IBasicInfoBS;
import com.ems.common.code.Code;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;
import com.ems.common.exception.EMSRollbackableException;
import com.ems.common.model.vo.BookVO;
import com.ems.common.model.vo.ClassVO;
import com.ems.common.model.vo.CourseVO;
import com.ems.common.model.vo.EducationVO;
import com.ems.common.model.vo.GradeVO;
import com.ems.common.model.vo.ProfessVO;
import com.ems.common.model.vo.ProjectVO;
import com.ems.common.model.vo.RoomVO;
import com.ems.common.model.vo.TermVO;
import com.ems.common.model.vo.UserInfoVO;
import com.ems.common.util.MD5;
import com.ems.common.util.StringUtils;

import conf.hibernate.BookBO;
import conf.hibernate.ClassBO;
import conf.hibernate.CourseBO;
import conf.hibernate.EducationBO;
import conf.hibernate.GradeBO;
import conf.hibernate.ProfessBO;
import conf.hibernate.ProjectBO;
import conf.hibernate.RoomBO;
import conf.hibernate.TermBO;
import conf.hibernate.UserInfoBO;
import conf.hibernate.UserRoleRelBO;

@Service("basicInfoBS")
public class BasicInfoBSImpl implements IBasicInfoBS {
	
	@Autowired
	@Qualifier("commonDAO")
	private ICommonDAO commonDAO;

	public void save(Object obj) throws EMSRollbackableException {
		this.commonDAO.save(obj);
	}

	public <T> T findById(Class<T> entityClass, Serializable id)
			throws EMSException {
		return this.commonDAO.findById(entityClass, id);
	}
	
	public List<TermBO> findTermByVO(TermVO termVO) throws EMSException{
		StringBuilder hql = new StringBuilder("from TermBO where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(termVO.getTermName())){
			hql.append(" and termName like ? "); 
			valueParam.add("%"+termVO.getTermName()+"%");
		}
		hql.append(" order by id desc");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<GradeBO> findGradeByVO(GradeVO gradeVO) throws EMSException{
		String hql = "from GradeBO where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(gradeVO.getGradeName())){
			hql = hql+" and gradeName like ? "; 
			valueParam.add("%"+gradeVO.getGradeName()+"%");
		}
		hql = hql+" order by id desc ";
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}
	
	public List<ClassBO> findClassByVO(ClassVO classVO) throws EMSException{
		String hql = "from ClassBO where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(classVO.getGradeId() != null){
			hql = hql+" and gradeId = ? "; 
			valueParam.add(classVO.getGradeId());
		}
		if(!StringUtils.isNullBlank(classVO.getClassName())){
			hql = hql+" and className like ? "; 
			valueParam.add("%"+classVO.getClassName()+"%");
		}
		hql = hql+" order by id desc "; 
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}
	
	public List<ProjectBO> findProjectByVO(ProjectVO projectVO) throws EMSException{
		String hql = "from ProjectBO where 1=1 ";
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(projectVO.getProjectName())){
			hql = hql+" and (projectName like ? or projectNameEn like ?) "; 
			valueParam.add("%"+projectVO.getProjectName()+"%");
			valueParam.add("%"+projectVO.getProjectName()+"%");
		}
		hql = hql+" order by id desc "; 
		return this.commonDAO.findByHql(hql, valueParam.toArray());
	}
	
	public List<ProfessBO> findProfessByVO(ProfessVO professVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from ProfessBO where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(professVO.getProjectId() != null && professVO.getProjectId() != -1){
			hql.append(" and projectId = ? "); 
			valueParam.add(professVO.getProjectId());
		}
		if(!StringUtils.isNullBlank(professVO.getProfessName())){
			hql.append(" and (professName like ? or professNameEn like ?) "); 
			valueParam.add("%"+professVO.getProfessName()+"%");
			valueParam.add("%"+professVO.getProfessName()+"%");
		}
		hql.append(" order by id desc ");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<CourseBO> findCourseByVO(CourseVO courseVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from CourseBO where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(courseVO.getCourseName())){
			hql.append(" and courseName like ? "); 
			valueParam.add("%"+courseVO.getCourseName()+"%");
		}
		if(!StringUtils.isNullBlank(courseVO.getCourseEngName())){
			hql.append(" and courseEngName like ? "); 
			valueParam.add("%"+courseVO.getCourseEngName()+"%");
		}
		hql.append(" order by id desc ");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<RoomBO> findRoomByVO(RoomVO roomVO) throws EMSException{
		StringBuffer hql = new StringBuffer(" from RoomBO where 1=1 ");
		List<Object> valueParam = new ArrayList<Object>();
		if(!StringUtils.isNullBlank(roomVO.getRoomName())){
			hql.append(" and roomName like ? "); 
			valueParam.add("%"+roomVO.getRoomName()+"%");
		}
		if(roomVO.getTermId() != null && roomVO.getTermId() != -1){
			hql.append(" and termId = ? "); 
			valueParam.add(roomVO.getTermId());
		}
		hql.append(" order by id desc ");
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
		hql.append(" order by id desc ");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<EducationBO> findEducationByVO(EducationVO educationVO) throws EMSException{
		StringBuffer hql = new StringBuffer("SELECT ebo from EducationBO ebo, UserInfoBO uibo where ebo.teacherId = uibo.id ");
		List<Object> valueParam = new ArrayList<Object>();
		if(educationVO.getTeacherId() != null ){
			hql.append(" and ebo.teacherId = ? "); 
			valueParam.add(educationVO.getTeacherId());
		}
		if(!StringUtils.isNullBlank(educationVO.getTeacherName())) {
			hql.append(" and uibo.userName like ? "); 
			valueParam.add("%" + educationVO.getTeacherName() + "%");
		}
		
		if(educationVO.getClassId() != null){
			hql.append(" and ebo.classId = ? "); 
			valueParam.add(educationVO.getClassId());
		}
		if(educationVO.getTermId() != null){
			hql.append(" and ebo.termId = ? "); 
			valueParam.add(educationVO.getTermId());
		}
		if(!StringUtils.isNullBlank(educationVO.getCourseNo())){
			hql.append(" and ebo.courseNo = ? "); 
			valueParam.add(educationVO.getCourseNo());
		}
		hql.append(" order by ebo.id desc ");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<UserInfoBO> findTeacherByEducat(EducationVO educationVO) throws EMSException {
		StringBuffer hql = new StringBuffer("SELECT uibo from UserInfoBO uibo, EducationBO ebo where ebo.teacherId = uibo.id ");
		List<Object> valueParam = new ArrayList<Object>();
		if(educationVO.getTeacherId() != null ){
			hql.append(" and ebo.teacherId = ? "); 
			valueParam.add(educationVO.getTeacherId());
		}
		if(!StringUtils.isNullBlank(educationVO.getTeacherName())) {
			hql.append(" and uibo.userName like ? "); 
			valueParam.add("%" + educationVO.getTeacherName() + "%");
		}
		
		if(educationVO.getClassId() != null){
			hql.append(" and ebo.classId = ? "); 
			valueParam.add(educationVO.getClassId());
		}
		if(educationVO.getTermId() != null){
			hql.append(" and ebo.termId = ? "); 
			valueParam.add(educationVO.getTermId());
		}
		if(!StringUtils.isNullBlank(educationVO.getCourseNo())){
			hql.append(" and ebo.courseNo = ? "); 
			valueParam.add(educationVO.getCourseNo());
		}
		hql.append(" order by uibo.id desc ");
		return this.commonDAO.findByHql(hql.toString(), valueParam.toArray());
	}
	
	public List<UserInfoVO> findUserByVO(UserInfoVO userInfoVO) throws EMSException{
		StringBuffer sql = 
			new StringBuffer("select a.id,a.login_name,a.user_name,a.password,a.email,a.contact,b.role_id ");
		sql.append(" from ts_user_info a left outer join ts_user_role_rel b on a.id = b.user_id where 1 = 1");
		List<Object> valueParam = new ArrayList<Object>();
		if(userInfoVO.getId() != null && userInfoVO.getId() != -1){
			sql.append(" and a.id = ? "); 
			valueParam.add(userInfoVO.getId());
		}
		if(!StringUtils.isNullBlank(userInfoVO.getLoginName())){
			sql.append(" and a.login_name like ? "); 
			valueParam.add("%"+userInfoVO.getLoginName()+"%");
		}
		if(!StringUtils.isNullBlank(userInfoVO.getUserName())){
			sql.append(" and a.user_name like ? "); 
			valueParam.add("%"+userInfoVO.getUserName()+"%");
		}
		if(userInfoVO.getRoleId() != null && userInfoVO.getRoleId() != -1 ){
			sql.append(" and b.role_id = ? "); 
			valueParam.add(userInfoVO.getRoleId());
		}
		sql.append(" order by a.id desc ");
		List result = this.commonDAO.findBySql(sql.toString(), valueParam.toArray(), null);
		UserInfoVO userInfoVO_ret = null;
		List<UserInfoVO> userInfoVOes = new ArrayList<UserInfoVO>();
		if(result != null && result.size() >  0){
			for(int i = 0;i<result.size();i++){
				Object[] obj = (Object[])result.get(i);
				userInfoVO_ret = new UserInfoVO();
				userInfoVO_ret.setId((Integer)obj[0]);
				userInfoVO_ret.setLoginName(StringUtils.nullToSpace(obj[1]));
				userInfoVO_ret.setUserName(StringUtils.nullToSpace(obj[2]));
				userInfoVO_ret.setPassword(StringUtils.nullToSpace(obj[3]));
				userInfoVO_ret.setEmail(StringUtils.nullToSpace(obj[4]));
				userInfoVO_ret.setContact(StringUtils.nullToSpace(obj[5]));
				if(obj[6] != null){
					userInfoVO_ret.setRoleId((Integer)obj[6]);
					userInfoVO_ret.setRoleName(Code.getName("Role", userInfoVO_ret.getRoleId()+""));
				}
				userInfoVOes.add(userInfoVO_ret);
			}
		}
		return userInfoVOes;
	}
	
	public UserInfoVO findUserVOById(Integer id) throws EMSException {
		StringBuffer sql = 
			new StringBuffer("select a.id,a.login_name,a.user_name,a.password,a.email,a.contact,b.role_id ");
		sql.append(" from ts_user_info a left outer join ts_user_role_rel b on a.id = b.user_id ");
		sql.append(" where a.id = ? ");
		List result = this.commonDAO.findBySql(sql.toString(), new Object[]{id}, null);
		UserInfoVO userInfoVO_ret = new UserInfoVO();
		if(result != null && result.size() >  0){
			Object[] obj = (Object[])result.get(0);
			userInfoVO_ret.setId((Integer)obj[0]);
			userInfoVO_ret.setLoginName(StringUtils.nullToSpace(obj[1]));
			userInfoVO_ret.setUserName(StringUtils.nullToSpace(obj[2]));
			userInfoVO_ret.setPassword(StringUtils.nullToSpace(obj[3]));
			userInfoVO_ret.setEmail(StringUtils.nullToSpace(obj[4]));
			userInfoVO_ret.setContact(StringUtils.nullToSpace(obj[5]));
			if(obj[6] != null){
				userInfoVO_ret.setRoleId((Integer)obj[6]);
				userInfoVO_ret.setRoleName(Code.getName("Role", userInfoVO_ret.getRoleId()+""));
			}
		}
		return userInfoVO_ret;
	}
	
	public void createUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException{
		UserInfoBO userInfoBO = new UserInfoBO();
		userInfoBO.setLoginName(userInfoVO.getLoginName());
		userInfoBO.setUserName(userInfoVO.getUserName());
		userInfoBO.setPassword(MD5.MD5Encode(Code.getValue("SysParams", "defaultPassword")));
		userInfoBO.setEmail(userInfoVO.getEmail());
		userInfoBO.setContact(userInfoVO.getContact());
		this.commonDAO.save(userInfoBO);
		UserRoleRelBO userRoleRelBO = new UserRoleRelBO();
		userRoleRelBO.setUserId(userInfoBO.getId());
		userRoleRelBO.setRoleId(userInfoVO.getRoleId());
		this.commonDAO.save(userRoleRelBO);
	}
	
	public void updateUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException{
		UserInfoBO userInfoBO = this.commonDAO.findById(UserInfoBO.class, userInfoVO.getId());
		userInfoBO.setLoginName(userInfoVO.getLoginName());
		userInfoBO.setUserName(userInfoVO.getUserName());
		//userInfoBO.setPassword(MD5.MD5Encode(userInfoVO.getPassword()));
		userInfoBO.setEmail(userInfoVO.getEmail());
		userInfoBO.setContact(userInfoVO.getContact());
		this.commonDAO.update(userInfoBO);
		UserRoleRelBO userRoleRelBO = null;
		List<UserRoleRelBO>	userRoles = this.commonDAO.find(UserRoleRelBO.class, new String[]{"userId"}, 
				new String[]{"="}, new Object[]{userInfoVO.getId()});
		if(userRoles == null || userRoles.size() == 0){
			userRoleRelBO = new UserRoleRelBO();
			userRoleRelBO.setUserId(userInfoVO.getId());
		}else{
			userRoleRelBO = userRoles.get(0);
		}
		userRoleRelBO.setRoleId(userInfoVO.getRoleId());
		List<UserRoleRelBO> saveOrUpdateList = new ArrayList<UserRoleRelBO>();
		saveOrUpdateList.add(userRoleRelBO);
		this.commonDAO.saveOrUpdate(saveOrUpdateList);
	}
	
	public void deleteUserInfo(UserInfoVO userInfoVO) throws EMSRollbackableException{
		UserInfoBO userInfoBO = this.commonDAO.findById(UserInfoBO.class, userInfoVO.getId());
		this.commonDAO.delete(userInfoBO);
		List<UserRoleRelBO> userRoleRelBO = this.commonDAO.find(UserRoleRelBO.class, new String[]{"userId"}, 
				new String[]{"="}, new Object[]{userInfoVO.getId()});
		this.commonDAO.delete(userRoleRelBO);
	}
	
	public TermBO findCurrTerm() throws EMSException {
		return (TermBO) commonDAO.unquieResult("FROM TermBO bo WHERE bo.isCurrentTerm = ?", Code.getValue("Indicator", "S1"));
	}
	public List<ClassBO> findClassById(List<Integer> id) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return commonDAO.findListByHql("FROM ClassBO bo WHERE bo.id IN (:id)", paramMap);
	}
	public List<CourseBO> findCourseByNo(List<String> no) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("no", no);
		return commonDAO.findListByHql("FROM CourseBO bo WHERE bo.courseNo IN (:no)", paramMap);
	}
	public List<UserInfoBO> findUserInfoById(List<Integer> id) throws EMSException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return commonDAO.findListByHql("FROM UserInfoBO bo WHERE bo.id IN (:id)", paramMap);
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
}