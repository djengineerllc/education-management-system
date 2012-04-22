package conf.hibernate;

import com.ems.common.model.bo.BaseBO;

/**
 * CodeType entity. @author MyEclipse Persistence Tools
 */

public class CodeTableBO extends BaseBO {

	// Fields

	private String codeType;
	private String codeKey;
	private String codeValue;
	private String codeName;
	private String codeNameEn;
	private String codeDesc;
	private String codeGroup;
	private Integer ordinal;
	private String canModifyInd;
	private String status;

	public CodeTableBO() {
	}
	
	public CodeTableBO(Object codeValue, String codeName) {
		this.codeKey = codeValue.toString();
		this.codeValue = codeValue.toString();
		this.codeName = codeName;
	}
	
	public CodeTableBO(Object codeValue, String codeName, Object codeGroup) {
		this.codeKey = codeValue.toString();
		this.codeValue = codeValue.toString();
		this.codeName = codeName;
		this.codeGroup = codeGroup != null ? codeGroup.toString() : null;
	}
	
	public CodeTableBO(Object codeValue, String codeName, String codeDesc, Object codeGroup) {
		this.codeKey = codeValue.toString();
		this.codeValue = codeValue.toString();
		this.codeName = codeName;
		this.codeDesc = codeDesc;
		this.codeGroup = codeGroup != null ? codeGroup.toString() : null;
	}
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	public String getCodeNameEn() {
		return codeNameEn;
	}

	public void setCodeNameEn(String codeNameEn) {
		this.codeNameEn = codeNameEn;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getCanModifyInd() {
		return canModifyInd;
	}

	public void setCanModifyInd(String canModifyInd) {
		this.canModifyInd = canModifyInd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}