package com.ems.biz.auth.service;

import java.util.List;

import com.ems.common.exception.EMSException;

import conf.hibernate.MenuBO;
import conf.hibernate.UserBO;


public interface ILoginService {
	
	/**
	 * 根据用户名和密码登陆
	 * @param loginName
	 * @param password
	 * @return 返回用户信息
	 * @throws EMSException
	 */
	UserBO login(String loginName, String password) throws EMSException;
	
	/**
	 * 切换到角色返回一级菜单
	 * @param roleCd
	 * @return
	 * @throws EMSException
	 */
	List<MenuBO> changeToRole(String roleCd) throws EMSException;
	
	/**
	 * 根据当前菜单id，返回下面的所有菜单信息
	 * @param menuId
	 * @param roleCd
	 * @return
	 * @throws EMSException
	 */
	List<MenuBO> getMenuList(String roleCd, Integer menuId) throws EMSException;
	
}