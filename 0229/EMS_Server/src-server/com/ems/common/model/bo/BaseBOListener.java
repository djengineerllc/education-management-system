package com.ems.common.model.bo;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.ems.common.util.DateUtil;

/**
 * @author chiknin
 */
public class BaseBOListener extends EmptyInterceptor {
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, 
		String[] propertyNames, Type[] types) 
	{
		if (entity instanceof BaseBO) {
			for (int i = 0; i < propertyNames.length; i++) {
				String propertyName = propertyNames[i];
				if ("createTime".equals(propertyName)) {
					if (state[i] == null) {
						state[i] = DateUtil.currData();
					}
				} else if ("updateTime".equals(propertyName)) {
					state[i] = DateUtil.currData();
				}
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, 
		String[] propertyNames, Type[] types) 
	{
		if (entity instanceof BaseBO) {
			for (int i = 0; i < propertyNames.length; i++) {
				String propertyName = propertyNames[i];
				if ("createTime".equals(propertyName)) {
					if (currentState[i] == null) {
						currentState[i] = DateUtil.currData();
					}
				} else if ("updateTime".equals(propertyName)) {
					currentState[i] = DateUtil.currData();
				}
			}
			
			return true;
		}
		
		return false;
	}

}
