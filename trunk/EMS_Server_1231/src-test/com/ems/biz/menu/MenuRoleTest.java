package com.ems.biz.menu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ems.biz.common.service.impl.CommonDAOBS;
import com.ems.common.LoadSpringXml;

import conf.hibernate.MenuBO;
import conf.hibernate.RoleBO;

public class MenuRoleTest extends LoadSpringXml {

	/**
	 * 保存
	 */
	public void des_testSave() {
		MenuBO menuBO = new MenuBO();
		menuBO.setLeaf(false); 
		menuBO.setModuleId("test1");
		menuBO.setModuleName("测试1");
		menuBO.setParentId(null);
		menuBO.setText("adsfadsfa");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		bs.save(menuBO);
	}
	
	public void des_testSaveRole() {
		RoleBO roleBO = new RoleBO();
		roleBO.setRoleCd("R0001");
		roleBO.setRoleName("学生");
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<MenuBO> menuList = bs.findByHql("from " + MenuBO.class.getName() + " bo where bo.id=1");
		Set<MenuBO> menuSet = new HashSet<MenuBO>();
		menuSet.addAll(menuList);
		roleBO.setMenuSet(menuSet);
		bs.save(roleBO);
	}
	
	public void testFindRoleMenuList(){
		CommonDAOBS bs = (CommonDAOBS)this.getBean("commonDAOBS");
		List<RoleBO> roleList = bs.findByHql("from " + RoleBO.class.getName() + " bo where bo.roleCd = 'R0001'");
		for(Iterator<RoleBO> it = roleList.iterator(); it.hasNext();){
			System.out.println(it.next().getMenuSet().size());
		}
	}
}
