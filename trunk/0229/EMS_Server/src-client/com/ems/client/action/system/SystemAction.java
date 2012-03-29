package com.ems.client.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ems.common.code.Code;
import com.ems.common.model.vo.DicVO;
import com.ems.common.util.BeanUtils;
import com.ems.common.util.DateUtil;
import com.ems.system.client.DirectAction;
import com.google.gson.JsonArray;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.softwarementors.extjs.djn.servlet.ssm.ActionScope;
import com.softwarementors.extjs.djn.servlet.ssm.Scope;

import conf.hibernate.CodeTableBO;

@ActionScope(scope=Scope.APPLICATION)
public class SystemAction extends DirectAction {
	
	@DirectMethod
	public Date getSystemTime() {
		return DateUtil.currData();
	}
	
	@DirectMethod
	public List<DicVO> getDicData(JsonArray params) {
		Map<String, String> paramMap = BeanUtils.toMapFromJsonFirst(params);
		String dicType = paramMap.get("type");
		String group = paramMap.get("group");
		
		List<CodeTableBO> codes = null;
		if (StringUtils.isNotEmpty(group)) {
			codes = Code.getCodes(dicType, group);
		} else {
			codes = Code.getCodes(dicType);
		}
		
		List<DicVO> dics = new ArrayList<DicVO>();
		DicVO dicVO = null;
		if (codes != null && codes.size() > 0) {
			for (CodeTableBO code : codes) {
				dicVO = new DicVO();
				dicVO.setId(code.getId());
				dicVO.setType(code.getCodeType());
				dicVO.setKey(code.getCodeKey());
				dicVO.setValue(code.getCodeValue());
				dicVO.setName(code.getCodeName());
				dicVO.setDesc(code.getCodeDesc());
				dicVO.setGroup(code.getCodeGroup());
				dics.add(dicVO);
			}
		}
		
		return dics;
	}
}