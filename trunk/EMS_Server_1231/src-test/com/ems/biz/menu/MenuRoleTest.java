package com.ems.biz.menu;

import java.util.Iterator;
import java.util.List;

import com.ems.biz.common.service.impl.CommonDAOBS;
import com.ems.common.LoadSpringXml;

import conf.hibernate.MenuBO;
import conf.hibernate.RoleBO;
import conf.hibernate.UserBO;

public class MenuRoleTest extends LoadSpringXml {
	
	public void $testSelect(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findAll(MenuBO.class);
		for(Iterator<MenuBO> it = menuList.iterator(); it.hasNext();){
			System.out.println(it.next().getText());
		}
	}
	
	public void $testDelete(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findAll(RoleBO.class);
		for(Iterator<RoleBO> it = roleList.iterator(); it.hasNext();){
			bs.delete(it.next());
		}
		
	}
	
	public void $testAddAdminRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in ('系统管理', '管理员管理', '权限管理', '用户角色管理'," +
				"'角色管理'," +
				"'菜单管理'," +
				"'用户管理'," +
				"'教师管理'," +
				"'学生管理'," +
				"'辅导员管理'" +
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0001");
		roleBO.setRoleName("系统管理员");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void $testAddAdminUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("admin");
		userBO.setUserName("系统管理员");
		userBO.setPassword("admin");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0001'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
	public void $testAddTeacherRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in ('成绩管理', '选修课成绩', '必修课成绩', '手工录入'," +
				"'批量导入'," +
				"'考勤管理'," +
				"'考勤统计'," +
				"'选修课管理'," +
				"'选修课管理'," +
				"'我的课表'," +
				"'查询'" +
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0002");
		roleBO.setRoleName("教师");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void $testAddTeacherUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("teacher");
		userBO.setUserName("教师1");
		userBO.setPassword("teacher");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0002'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
	public void $testAddFdyRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in ('成绩管理', '选修课成绩', '必修课成绩', '用户管理'," +
				"'学生管理'," +
				"'报名管理'," +
				"'查询'," +
				"'手工录入'," +
				"'批量导入'," +
				"'考勤管理'," +
				"'考勤录入'," +
				"'考勤统计'," +
				
				"'打印证明'," +
				"'在学证明'," +
				"'预毕业证明'," +
				"'毕业证明'," +
				"'就读证明'," +
				"'成绩证明'" +
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0003");
		roleBO.setRoleName("辅导员");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void $testAddFdyUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("fdy");
		userBO.setUserName("辅导员1");
		userBO.setPassword("fdy");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0003'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
	public void $testAddStuRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in ('成绩管理', '选修课成绩', '必修课成绩', '用户管理'," +
				"'学生管理'," +
				"'报名管理'," +
				"'查询'," +
				"'考勤管理'," +
				"'考勤统计'," +
				
				"'选修课管理'," +
				"'选修课管理'," +
				"'我的课表'," +
				"'查询'" +
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0004");
		roleBO.setRoleName("学生");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void $testAddStuUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("student");
		userBO.setUserName("学生1");
		userBO.setPassword("student");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0004'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
	public void $testAddCWRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in (" +
				"'报名管理'," +
				"'查询'" +
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0005");
		roleBO.setRoleName("财务");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void $testAddCWUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("caiwu");
		userBO.setUserName("财务1");
		userBO.setPassword("caiwu");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0005'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
	public void testAddJWCRole(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " as bo where bo.text in ("
				+"'系统管理',"   
				+"'年级管理',"
				+"'学期管理',"
				+"'专业管理',"
				+"'班级管理',"
				+"'课程管理',"
				+"'成绩管理',"
				+"'选修课成绩',"
				+"'必修课成绩',"
				+"'手工录入',"
				+"'批量导入',"
				+"'报名管理',"
				+"'查询',"
				+"'考勤管理',"
				+"'考勤统计',"
				+"'课程管理',"
				+"'必须课开课',"
				+"'选修课开课',"
				+"'物资管理',"
				+"'教室管理',"
				+"'教材管理',"
				+"'排课管理',"
				+"'自动排课',"
				+"'手工排课',"
				+"'我的课表',"
				+"'查询',"
				+"'打印证明',"
				+"'在学证明',"
				+"'预毕业证明',"
				+"'毕业证明',"
				+"'就读证明',"
				+"'成绩证明'"+
				")");
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0006");
		roleBO.setRoleName("教务处");
		roleBO.setMenuList(menuList);
		bs.save(roleBO);
	}
	
	public void testAddJWCUser(){
		UserBO userBO = new UserBO();
		userBO.setLoginName("jwc");
		userBO.setUserName("教务处1");
		userBO.setPassword("jwc");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " as bo where bo.roleCd = 'R0006'");
		userBO.setRoleList(roleList);
		bs.save(userBO);
	}
	
}
