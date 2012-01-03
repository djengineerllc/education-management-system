package com.ems.biz.auth.service.impl;

import com.ems.biz.auth.service.ILoginService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.exception.EMSException;

import conf.hibernate.UserBO;

public class LoginServiceImpl implements ILoginService{

	private ICommonDAO commonDAO;
	
	public UserBO login(String loginName, String password) throws EMSException {
		UserBO userBO = (UserBO)commonDAO.findByHql("from " + UserBO.class.getName() + " bo wehre bo.loginName = ?", loginName);
		if(null != userBO && userBO.getPassword().equals(password)){
			return userBO;
		}
		throw new EMSException("用户名或密码错误，请重新登陆！");
	}
	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}

	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	

}
