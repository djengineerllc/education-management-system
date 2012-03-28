package conf.hibernate;

/**
 * CodeType entity. @author MyEclipse Persistence Tools
 */

public class CodeTableBO implements java.io.Serializable {

	// Fields

	private Integer id;
	private String codeType;
	private String codeKey;
	private String codeValue;
	private String codeName;
	private String codeDesc;
	private String codeGroup;
	private Integer ordinal;
	private String canModifyInd;
	private String status;

	// Constructors

	/** default constructor */
	public CodeTableBO() {
	}

	/** minimal constructor */
	public CodeTableBO(String codeKey) {
		this.codeKey = codeKey;
	}
	
	public CodeTableBO(String codeValue, String codeName) {
		this.codeKey = codeValue;
		this.codeValue = codeValue;
		this.codeName = codeName;
	}
	
	public CodeTableBO(String codeValue, String codeName, String codeGroup) {
		this.codeKey = codeValue;
		this.codeValue = codeValue;
		this.codeName = codeName;
		this.codeGroup = codeGroup;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeKey() {
		return this.codeKey;
	}

	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	public String getCodeValue() {
		return this.codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeDesc() {
		return this.codeDesc;
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
		return this.ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getCanModifyInd() {
		return this.canModifyInd;
	}

	public void setCanModifyInd(String canModifyInd) {
		this.canModifyInd = canModifyInd;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}