package com.ems.common.code;

import java.util.List;

import conf.hibernate.CodeTypeBO;

/**
 * @author Chiknin
 */
public interface ICodeCollector {
	public List<CodeTypeBO> collect();
}