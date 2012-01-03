package com.ems.biz.auth.service;

import com.ems.common.exception.EMSException;

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
	
}