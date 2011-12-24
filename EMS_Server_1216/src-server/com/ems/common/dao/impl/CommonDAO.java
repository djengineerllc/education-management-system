package com.ems.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ems.common.dao.ICommonDAO;

import conf.hibernate.CodeType;


public class CommonDAO extends HibernateDaoSupport implements ICommonDAO{
	
	private static Log log = LogFactory.getLog(CommonDAO.class);
	
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 保存实体对象
	 */
	public void save(Object obj) {
		this.getHibernateTemplate().save(obj);
	}
	
	public <T>T findById(Class<T> entityClass,Serializable id){
		return (T)this.getHibernateTemplate().get(entityClass, id);
	}
	
	/**
	 * 功能：查询表中所有的记录
	 * @param clazz 该表映射的类
	 * @return 返回所有记录
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz) {
		return (List<T>) getHibernateTemplate().find("from " + clazz.getSimpleName());
	}

	public void delete(Object obj) {
		this.getHibernateTemplate().delete(obj);
	}
	
	public void deleteById(Class clazz,Serializable id){
		this.getHibernateTemplate().delete(this.findById(clazz, id));
	}

	public void update(Object obj) {
		this.getHibernateTemplate().update(obj);
	}

	public List findByHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据查询条件查询表中的记录
	 * @param clazz 该表映射的类
	 * @param fields 映射的字段名
	 * @param operators 操作符数组
	 * @param values 字段对应的值
	 * @return 返回满足条件的记录
	 */
	@SuppressWarnings({ "unchecked"})
	public <T> List<T> find(Class<T> clazz, final String[] fields, final String[] operators,
			final  Object[] values) {
		if (fields == null || values == null || fields.length < 1 || values.length < 1 
				|| values.length!=fields.length) {
			throw new RuntimeException("所传参数错误！");
		}
		final StringBuilder sql = new StringBuilder("from " + clazz.getSimpleName() + " where 1=1");

		return (List<T>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {				
				for (int index = 0; index < fields.length; index++) {
					sql.append(" and " + fields[index] + operators[index] + " :param" + index);
				}
				Query query = session.createQuery(sql.toString());	
				for (int index = 0; index < values.length; index++) {
					query = query.setParameter("param" + index, values[index]);
				}
				return query.list();
			}
		});
	}
	
	public List<CodeType> getCodeTypeByType(String codeType){
		return this.find(CodeType.class, new String[]{"codeType"}, 
				new String[]{"="}, new String[]{codeType});
	}
	
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	

}
