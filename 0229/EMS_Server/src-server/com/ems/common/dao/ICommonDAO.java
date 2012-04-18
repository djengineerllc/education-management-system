package com.ems.common.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ems.common.exception.EMSException;

public interface ICommonDAO {
	
	/**
	 * 保存实体对象
	 */
	public void save(Object obj);
	
	/**
	 * 保存集合对象
	 * @param collection
	 */
	public void saveAll(Collection collection);
	
	/**
	 * 保存或更新
	 * @param collection
	 */
	public void saveOrUpdate(Collection collection);
	
	
	/**
	 * 更新实体
	 * @param obj
	 */
	public void update(Object obj);
	
	/**
	 * 删除实体
	 * @param obj
	 */
	public void delete(Object obj);
	
	/**
	 * 根据主键删除ID
	 * @param clazz
	 * @param id
	 */
	public void deleteById(Class clazz,Serializable id);
	
	/**
	 * 批量删除
	 * @param clazz
	 * @param id
	 */
	public void delete(List objectList);
	
	/**
	 * 功能：查询表中所有的记录
	 * @param clazz 该表映射的类
	 * @param orderBy 排序字符串  不用排序则用NULL
	 * @return 返回所有记录
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(Class<T> clazz,String orderBy);
	
	/**
	 * 根据ID查找实体
	 * @param <T>
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T>T findById(Class<T> entityClass,Serializable id);
	
	/**
	 * 根据HQL语句查找实体
	 * @param hql
	 * @return List
	 */
	public List findByHql(String hql);
	
	/**
	 * 根据HQL语句查找实体
	 * @param hql
	 * @param valueParam
	 * @return List
	 */
	public List findByHql(String hql, Object... valueParam);
	
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
			final  Object[] values) ;
	
	/**
	 * 使用jdbcTemplate查询
	 * 
	 * @param sql
	 *            查询语句
	 * @return 结果集(List of HashMap)
	 */
	public List findBySql(String sql) ;
	
	
	public List findBySql(final String queryString, final Object[] parameters) ;
	
	// ==================
	// 
	// ==================
	
	public Object unquieResult(String hql, Object... value);
	public List findListByHql(final String hqlString, final Map<String, Object> paramMap);
	public List findPageListByHql(final String hqlString, final List paramValues, final int firstResult, final int maxResults);
	public int executeHql(final String hqlString, final List paramValues);
	public int executeHql(final String hqlString, final Object paramValue);
	
	public List findByValueBean(String queryString, Object valueBean) throws EMSException;
}
