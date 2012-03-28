package com.ems.common.code.externalcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ems.common.code.ICodeCollector;

import conf.hibernate.CodeTableBO;

/**
 * 根据SQL获取Code
 * 
 * @author Chiknin
 * 
 */
public class HqlCodeCollector implements ICodeCollector {

	private static final Logger logger = Logger.getLogger(HqlCodeCollector.class);

//	private ICommonDAO commonDAO;

	private Map<String, String> codeHqlMap;

	public List<CodeTableBO> collect() {
		if (codeHqlMap == null || codeHqlMap.size() == 0) {
			return null;
		}

		List<CodeTableBO> codeBOList = new ArrayList<CodeTableBO>();
		for (String codeType : codeHqlMap.keySet()) {
			List<CodeTableBO> result = this.collect(codeType);
			if (result != null && result.size() > 0) {
				codeBOList.addAll(result);
			}
		}

		return codeBOList;
	}

	public List<CodeTableBO> collect(String codeType) {
		String hql = codeHqlMap.get(codeType);
		if (StringUtils.isEmpty(hql)) {
			return Collections.EMPTY_LIST;
		}

//		try {
//			List<CodeTypeBO> codeBOList = commonDAO.findByHql(hql);
//			if (codeBOList != null && codeBOList.size() > 0) {
//				for (CodeTypeBO codeBO : codeBOList) {
//					codeBO.setCodeType(codeType);
//				}
//				return codeBOList;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//		}

		return null;
	}

	public boolean hasCodeType(String codeType) {
		return codeHqlMap.containsKey(codeType);
	}

//	public void setCommonDAO(ICommonDAO commonDAO) {
//		this.commonDAO = commonDAO;
//	}

	public Map<String, String> getCodeHqlMap() {
		return codeHqlMap;
	}

	public void setCodeHqlMap(Map<String, String> codeHqlMap) {
		this.codeHqlMap = codeHqlMap;
	}
}