package com.ems.common.code.externalcode;

import java.util.Collections;
import java.util.List;

import com.ems.common.code.ISingleCodeCollector;

import conf.hibernate.CodeTypeBO;

/**
 * @author Chiknin
 * 实例
 */
public class ProductCodeCollector implements ISingleCodeCollector {
	
	public static final String CODE_KEY = "Product";
	public String getCodeType() {
		return CODE_KEY;
	}

	public List<CodeTypeBO> collect() {
		return Collections.EMPTY_LIST;
	}
//	@SuppressWarnings("unchecked")
//	public List<FBasCodeMBO> collect() {
//		List<FBusPdctMBO> pdctMBOList = hibernateTemplate.find("FROM FBusPdctMBO bo WHERE bo.state = 1 AND bo.opState = null");
//		
//		List<FBasCodeMBO> pdctCodeList = new ArrayList<FBasCodeMBO>();
//		for (FBusPdctMBO pdctMBO : pdctMBOList) {
//			FBasCodeMBO codeMBO = new FBasCodeMBO();
//			codeMBO.setCodeType(this.getCodeType());
//			codeMBO.setCodeKey(pdctMBO.getProductcode());
//			codeMBO.setCodeValue(NumberUtils.toString(pdctMBO.getId()));
//			codeMBO.setCodeName(pdctMBO.getProductdesc());
//			
//			codeMBO.setCodeGroup(this.getProductGroup(pdctMBO));
//			
//			pdctCodeList.add(codeMBO);
//		}
//		
//		return pdctCodeList;
//	}
//
//	/**
//	 * 分析获取业务品种 分组信息
//	 * @param pdctMBO 业务品种
//	 * @return 业务品种分组信息
//	 */
//	private String getProductGroup(FBusPdctMBO pdctMBO) {
//		StringBuilder groups = new StringBuilder();
//		if (Code.eqValue("AreaFlag", "S1", pdctMBO.getAreaflag())) {
//			if(Code.eqValue("SordFlag", "S1", pdctMBO.getSordflag())){
//				groups.append("internal,");//国内保理
//			}
//		}else if(Code.eqValue("AreaFlag", "S2", pdctMBO.getAreaflag())){
//			if(Code.eqValue("SordFlag", "S1", pdctMBO.getSordflag()) && Code.eqValue("Indicator", "S1", pdctMBO.getInsuranceflag())){
//				groups.append("internationalcredit,");//国际信保
//				groups.append("eftfbcre,");
//			}else if(Code.eqValue("SordFlag", "S1", pdctMBO.getSordflag()) && Code.eqValue("Indicator", "S0", pdctMBO.getInsuranceflag())){
//				if(Code.eqValue("ArMark", "S1", pdctMBO.getArmark())){
//					groups.append("internationaltfb,");//国际商票
//					groups.append("eftfbcre,");
//				}
//			}else if(Code.eqValue("SordFlag", "S2", pdctMBO.getSordflag())){
//				groups.append("internationalef,");//国际EF
//				groups.append("efif,");
//				groups.append("eftfbcre,");
//			}
//		}else if(Code.eqValue("AreaFlag", "S3", pdctMBO.getAreaflag())){
//			groups.append("internationalif,");//国际IF
//			groups.append("efif,");
//		}else if(Code.eqValue("AreaFlag", "S4", pdctMBO.getAreaflag())){
//			groups.append("internaldouble,");//国内行双
//		}
//		//add by wanghuiqian
//		if(Code.neValue("AreaFlag", "S3", pdctMBO.getAreaflag())){
//			groups.append("loanProduct,");//可融资业务品种
//		}
//		
//		if (groups.length() == 0) {
//			return null;
//		}
//		
//		return groups.substring(0, groups.length() - 1);
//	}
}