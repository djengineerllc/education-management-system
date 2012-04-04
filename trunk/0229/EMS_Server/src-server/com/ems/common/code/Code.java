package com.ems.common.code;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ems.common.code.cache.ICache;
import com.ems.common.code.externalcode.HqlCodeCollector;

import conf.hibernate.CodeTableBO;

/**
 * <p> Title: 表码管理 </p>
 * <p> Description: </p>
 * @作者 Chiknin
 * @创建时间 2010.12.31
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本       修改人         修改时间         修改内容描述
 * ----------------------------------------
 * 1.00  Chiknin  2010.12.31 初始化版本
 * ----------------------------------------
 * </pre>
 */
@SuppressWarnings("unchecked")
public class Code implements InitializingBean {

	private static final Logger logger = Logger.getLogger(Code.class);

	/** 代码组别分隔符 */
	public static final String CODE_GROUP_SEPARATOR = " |,;";

	/** 代码集合缓存 */
	private static ICache codesCache;

	private static HibernateTemplate hibernateTemplate;
	private static Map<String, ISingleCodeCollector> singleCodeCollectors;
	private static HqlCodeCollector hqlCodeCollector;

	public void afterPropertiesSet() throws Exception {
		loadAll();
	}
	public static void refresh() {
		synchronized (codesCache) {
			loadAll();
		}
	}
	public static void refresh(String codeType) {
		if (StringUtils.isBlank(codeType)) {
			return;
		}
		synchronized (codesCache) {
			codesCache.remove(codeType);
			
			List<CodeTableBO> codeBOList = null;
			if (singleCodeCollectors.containsKey(codeType)) {
				codeBOList = singleCodeCollectors.get(codeType).collect();
			} else if (hqlCodeCollector != null && hqlCodeCollector.hasCodeType(codeType)) {
				codeBOList = hqlCodeCollector.collect(codeType);
			} else {
				codeBOList = hibernateTemplate.find("FROM CodeTableBO bo WHERE bo.status = '1' AND bo.codeType = ? ORDER BY bo.codeType,bo.ordinal ASC", codeType);
			}
			if (codeBOList != null && codeBOList.size() > 0) {
				codesCache.put(codeType, codeBOList);
			}
		}
	}

	/**
	 * 根据代码类型获取代码列表
	 * 所有Code操作方法入口类
	 * @param codeType 代码类型
	 * @return 代码列表
	 */
	public static List<CodeTableBO> getCodes(String codeType) {
		List<CodeTableBO> codeBOList = (List<CodeTableBO>) codesCache.get(codeType);
		if (codeBOList == null) {
			refresh(codeType);
			codeBOList = (List<CodeTableBO>) codesCache.get(codeType);
		}

		return codeBOList;
	}

	/**
	 * 根据代码类型和代码键获取代码
	 * @param codeType 代码类型
	 * @param key 代码键
	 * @return 代码
	 */
	public static CodeTableBO getCode(String codeType, String key) {
		List<CodeTableBO> codeBOList = getCodes(codeType);
		if (codeBOList != null && codeBOList.size() > 0) {
			for (CodeTableBO codeBO : codeBOList) {
				if (codeBO.getCodeKey().equals(key)) {
					return codeBO;
				}
			}
		}

		return null;
	}

	/**
	 * 根据代码类型和代码值获取代码
	 * @param codeType 代码类型
	 * @param value 代码值
	 * @return 代码
	 */
	public static CodeTableBO getCodeByValue(String codeType, String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		List<CodeTableBO> codeBOList = getCodes(codeType);
		if (codeBOList != null && codeBOList.size() > 0) {
			for (CodeTableBO codeBO : codeBOList) {
				if (codeBO.getCodeValue().equals(value)) {
					return codeBO;
				}
			}
		}

		return null;
	}

	/**
	 * 根据代码类型和代码值获取组
	 * @param codeType 代码类型
	 * @param value 代码值
	 * @return 代码
	 */
	public static String getGroupByValue(String codeType, String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}

		List<CodeTableBO> codeBOList = getCodes(codeType);
		if (codeBOList != null && codeBOList.size() > 0) {
			for (CodeTableBO codeBO : codeBOList) {
				if (codeBO.getCodeValue().equals(value)) {
					return codeBO.getCodeGroup();
				}
			}
		}

		return null;
	}
	/**
	 * 根据代码类型获取代码列表, 并转化为Map类型,Map-key为代码键,Map-value为代码值
	 * @param codeType 代码类型
	 * @param groups 组别
	 * @return 代码列表转换后的Map
	 */
	public static Map<String, String> getCodesToMap_KV(String codeType, String... groups) {
		List<CodeTableBO> codeList = getCodes(codeType, groups);
		if (codeList != null && codeList.size() > 0) {
			Map<String, String> codeMap = new HashMap<String, String>();
			for (CodeTableBO code : codeList) {
				codeMap.put(code.getCodeKey(), code.getCodeValue());
			}

			return codeMap;
		}

		return null;
	}

	/**
	 * 根据代码类型和代码组别列表获取代码列表
	 * @param codeType 代码类型
	 * @param groups 代码组别列表
	 * @return 代码列表
	 */
	public static List<CodeTableBO> getCodes(String codeType, String... groups) {
		if (groups == null || groups[0] == null) {
			return getCodes(codeType);
		}

		List<CodeTableBO> codeBOList = getCodes(codeType);
		if (codeBOList != null && codeBOList.size() > 0) {
			List<CodeTableBO> result = new ArrayList<CodeTableBO>();
			for (CodeTableBO codeBO : codeBOList) {
				String[] codeGroups = StringUtils.split(codeBO.getCodeGroup(), CODE_GROUP_SEPARATOR);
				if (codeGroups == null || codeGroups.length == 0) {
					continue;
				}

				for (String group : groups) {
					int index = ArrayUtils.indexOf(codeGroups, group);
					if (index > -1) {
						result.add(codeBO);
					}
				}
			}

			return result;
		}

		return null;
	}

	/**
	 * 根据代码类型和代码键获取代码值
	 * @param codeType 代码类型
	 * @param key 代码键
	 * @return 代码值
	 */
	public static String getValue(String codeType, String key) {
		CodeTableBO codeBO = getCode(codeType, key);
		if (codeBO != null) {
			return codeBO.getCodeValue();
		}

		return null;
	}

	/**
	 * 根据代码类型和代码名称获取代码值
	 * @param codeType 代码类型
	 * @param name 代码名称
	 * @return 代码值
	 */
	public static String getValueByName(String codeType, String name) {
		List<CodeTableBO> codeBOList = getCodes(codeType);
		if (codeBOList != null && codeBOList.size() > 0) {
			for (CodeTableBO code : codeBOList) {
				if (StringUtils.equals(name, code.getCodeName())) {
					return code.getCodeValue();
				}
			}
		}

		return null;
	}

	/***
	 * 根据代码类型获取代码列表, 获取列表列表首个代码值
	 * @param codeType 代码类型
	 * @return 首个代码值
	 */
	public static String getValueOfFirstCodes(String codeType) {
		List<CodeTableBO> codes = getCodes(codeType);
		if (codes != null && codes.size() > 0) {
			return codes.get(0).getCodeValue();
		}

		return null;
	}

	/**
	 * 根据代码类型和代码键获取代码名称
	 * @param codeType 代码类型
	 * @param key 代码键
	 * @return 代码名称
	 */
	public static String getName(String codeType, String key) {
		CodeTableBO codeBO = getCode(codeType, key);
		if (codeBO != null) {
			return codeBO.getCodeName();
		}

		return null;
	}
	public static String getName(String codeType, Object key) {
		if (key != null) {
			return getName(codeType, key.toString());
		}
		
		return null;
	}

	/**
	 * 根据代码类型和代码值获取代码名称
	 * @param codeType 代码类型
	 * @param value 代码值
	 * @return 代码名称
	 */
	public static String getNameByValue(String codeType, String value) {
		CodeTableBO codeBO = getCodeByValue(codeType, value);
		if (codeBO != null) {
			return codeBO.getCodeName();
		}

		return null;
	}

	/**
	 * 根据代码类型或代码键获取代码值, 是否等于目标值,如果等于返回true, 否之false
	 * @param codeType 代码类型
	 * @param key 代码键
	 * @param descValue 目标值
	 * @return true 或 false
	 */
	public static boolean eqValue(String codeType, String key, Object descValue) {
		String srcValue = getValue(codeType, key);
		if (StringUtils.isBlank(srcValue)) {
			return false;
		}
		if (descValue == null) {
			return false;
		}

		return srcValue.equals(descValue.toString());
	}
	public static boolean neValue(String codeType, String key, Object descValue) {
		return !eqValue(codeType, key, descValue);
	}

	/**
	 * 加载代码表FBAS_CODEM
	 */
	private static void loadAll() {
		logger.info("开始加载代码表");
		codesCache.removeAll();

		List<CodeTableBO> codeBOList = hibernateTemplate.find("FROM CodeTableBO bo WHERE bo.status = '1' ORDER BY bo.codeType,bo.ordinal ASC");

		if (hqlCodeCollector != null) {
			List<CodeTableBO> hqlCodeBOList = hqlCodeCollector.collect();
			if (hqlCodeBOList != null && hqlCodeBOList.size() > 0) {
				codeBOList.addAll(hqlCodeBOList);
			}
		}

		for (CodeTableBO codeMBO : codeBOList) {
			String codeType = codeMBO.getCodeType();
			codesCache.get(codeType);
			List<CodeTableBO> codes = (List<CodeTableBO>) codesCache.get(codeType);
			if (codes != null) {
				codes.add(codeMBO);
			} else {
				codes = new ArrayList<CodeTableBO>();
				codes.add(codeMBO);
				codesCache.put(codeType, codes);
				logger.info("加载代码[" + codeType + "]成功");
			}
		}

		if (singleCodeCollectors != null && singleCodeCollectors.size() > 0) {
			Collection<ISingleCodeCollector> sccList = singleCodeCollectors.values();
			for (ISingleCodeCollector scc : sccList) {
				codesCache.put(scc.getCodeType(), scc.collect());
				logger.info("加载代码[" + scc.getCodeType() + "]成功");
			}
		}

		logger.info("结束加载代码表");
	}

	public void setCodesCache(ICache c) {
		codesCache = c;
	}
	public void setHibernateTemplate(HibernateTemplate ht) {
		hibernateTemplate = ht;
	}
	public void setSingleCodeCollectorList(List<ISingleCodeCollector> ccl) {
		if (ccl != null && ccl.size() > 0) {
			singleCodeCollectors = new HashMap<String, ISingleCodeCollector>(ccl.size());
			for (ISingleCodeCollector cc : ccl) {
				singleCodeCollectors.put(cc.getCodeType(), cc);
			}
		} else {
			singleCodeCollectors = Collections.EMPTY_MAP;
		}
	}
	public void setHqlCodeCollector(HqlCodeCollector hcc) {
		hqlCodeCollector = hcc;
	}
	
	public static void main(String[] args) {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("conf/spring/AC.xml");
		
		List<CodeTableBO> codes = Code.getCodes("Sex");
		
		if (codes != null) {
			for (CodeTableBO code : codes) {
				System.out.println(ToStringBuilder.reflectionToString(code));
			}
		}
	}
}