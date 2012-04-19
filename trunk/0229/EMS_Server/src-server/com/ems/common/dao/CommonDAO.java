package com.ems.common.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ems.common.exception.EMSException;
import com.ems.common.util.BeanUtils;

public class CommonDAO implements ICommonDAO {

	protected JdbcTemplate jdbcTemplate;
	protected HibernateTemplate hibernateTemplate;

	/**
	 * 保存实体对象
	 */
	public void save(Object obj) {
		hibernateTemplate.save(obj);
	}

	public void saveAll(Collection collection) {
		hibernateTemplate.saveOrUpdateAll(collection);
	}
	
	public void saveOrUpdate(Collection collection) {
		hibernateTemplate.saveOrUpdateAll(collection);
	}

	public <T> T findById(Class<T> entityClass, Serializable id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

	/**
	 * 功能：查询表中所有的记录
	 * 
	 * @param clazz
	 *            该表映射的类
	 * @return 返回所有记录
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz, String orderBy) {
		StringBuffer hql = new StringBuffer("from " + clazz.getName());
		if (orderBy != null && !"".equals(orderBy)) {
			hql.append(" order by ").append(orderBy);
		}
		return (List<T>) hibernateTemplate.find(hql.toString());
	}

	/**
	 * 根据HQL语句查找实体
	 * 
	 * @param hql
	 * @param values
	 * @return List
	 */
	public List findByHql(String hql, Object... values) {
		return hibernateTemplate.find(hql, values);
	}

	public void delete(Object obj) {
		hibernateTemplate.delete(obj);
	}

	public void deleteById(Class clazz, Serializable id) {
		hibernateTemplate.delete(this.findById(clazz, id));
	}
	
	public void delete(List objectList){
		if(objectList != null){
			for(Iterator it = objectList.iterator();it.hasNext();){
				this.hibernateTemplate.delete(it.next());
			}
		}
	}

	public void update(Object obj) {
		hibernateTemplate.update(obj);
	}

	public List findByHql(String hql) {
		return hibernateTemplate.find(hql);
	}

	/**
	 * 根据查询条件查询表中的记录
	 * 
	 * @param clazz
	 *            该表映射的类
	 * @param fields
	 *            映射的字段名
	 * @param operators
	 *            操作符数组
	 * @param values
	 *            字段对应的值
	 * @return 返回满足条件的记录
	 */
	@SuppressWarnings({ "unchecked" })
	public <T> List<T> find(Class<T> clazz, final String[] fields,
			final String[] operators, final Object[] values) {
		if (fields == null || values == null || fields.length < 1
				|| values.length < 1 || values.length != fields.length) {
			throw new RuntimeException("所传参数错误！");
		}
		final StringBuilder sql = new StringBuilder("from "
				+ clazz.getSimpleName() + " where 1=1");

		return (List<T>) hibernateTemplate.execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						for (int index = 0; index < fields.length; index++) {
							sql.append(" and " + fields[index]
									+ operators[index] + " :param" + index);
						}
						Query query = session.createQuery(sql.toString());
						for (int index = 0; index < values.length; index++) {
							query = query.setParameter("param" + index,
									values[index]);
						}
						return query.list();
					}
				});
	}

	/**
	 * 使用jdbcTemplate查询
	 * 
	 * @param sql
	 *            查询语句
	 * @return 结果集(List of HashMap)
	 */
	public List findBySql(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findBySql(final String queryString, final Object[] parameters) {
		return (List) this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(queryString);
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						query.setParameter(i, parameters[i]);
					}
				}
				return query.list();
			}			
		});
	}

	// -----------------
	// START: by chiknin
	// -----------------
	public Object unquieResult(String hql, Object... values) {
		List result = this.findByHql(hql, values);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}

		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findListByHql(final String hqlString, final Map<String, Object> paramMap) {
		return ((List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(hqlString);
				
				if (paramMap != null) {
					String key = null;
					Object value = null;
					for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
						key = entry.getKey();
						value = entry.getValue();
						if (value != null && value instanceof Object[]) {
							q.setParameterList(key, (Object[]) value);
						} else if (value != null && value instanceof Collection) {
							q.setParameterList(key, (Collection) value);
						} else {
							q.setParameter(key, value);
						}
					}
				}
				
				return q.list();
			}
		}));
	}
	
	public List findListByHql(String hqlString, Object paramObj) {
		Map<String, Object> paramMap = null;
		if (paramObj != null) {
			if (paramObj instanceof Map<?, ?>) {
				paramMap = (Map<String, Object>) paramObj;
			} else if (paramObj instanceof Object[]) {
				throw new UnsupportedOperationException();
			} else if (paramObj instanceof List) {
				throw new UnsupportedOperationException();
			} else {
				paramMap = new HashMap<String, Object>();
				BeanUtils.copyBeanToMap(paramObj, paramMap);
			}
		}
		
		return this.findListByHql(hqlString, paramMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findPageListByHql(final String hqlString, final List paramValues, final int firstResult, final int maxResults) {
		return (List) hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(hqlString);
				
				if (paramValues != null) {
					for (int i = 0; i < paramValues.size(); i++) {
						q.setParameter(i, paramValues.get(i));
					}
				}
				
				q.setFirstResult(firstResult);
				q.setMaxResults(maxResults);
				
				return q.list();
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int executeHql(final String hqlString, final List paramValues) {
		return hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(hqlString);
				if (paramValues != null) {
					for (int i = 0; i < paramValues.size(); i++) {
						q.setParameter(i, paramValues.get(i));
					}
				}
				
				return q.executeUpdate();
			}
		});
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int executeHql(final String hqlString, final Object paramValue) {
		List paramValues = null;
		if (paramValue != null) {
			paramValues = new ArrayList(1);
			paramValues.add(paramValue);
		}
		
		return this.executeHql(hqlString, paramValues);
	}
	
	public List findByValueBean(String queryString, Object valueBean) throws EMSException{
		return this.hibernateTemplate.findByValueBean(queryString, valueBean);
	}
	
	// -----------------
	// END: by chiknin
	// -----------------

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
}