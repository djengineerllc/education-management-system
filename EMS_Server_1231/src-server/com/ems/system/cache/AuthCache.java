package com.ems.system.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ems.common.dao.ICommonDAO;

import conf.hibernate.MenuBO;
import conf.hibernate.RoleBO;

/**
 * 权限缓存
 * @author all
 *
 */
public class AuthCache{
	
	private static Map<String, List<MenuBO>> roleMenuMap = new HashMap<String, List<MenuBO>>();
	
	private static ICommonDAO commonDAO;
	
	private static boolean init = false;
	
	public synchronized static void init(){
		init = false;
		roleMenuMap.clear();
		List<RoleBO> roleList = commonDAO.findAll(RoleBO.class);
		for(Iterator<RoleBO> it = roleList.iterator(); it.hasNext();){
			RoleBO roleBO = it.next();
			roleMenuMap.put(roleBO.getRoleCd(), roleBO.getMenuList());
		}
		init = true;
	}
	
	public static List<MenuBO> getMenuList(String roleCd){
		if(!init){
			synchronized (AuthCache.class) {
				if(!init){
					init();
				}
			}
		}
		return roleMenuMap.get(roleCd);
	}

	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		AuthCache.commonDAO = commonDAO;
	}
}
