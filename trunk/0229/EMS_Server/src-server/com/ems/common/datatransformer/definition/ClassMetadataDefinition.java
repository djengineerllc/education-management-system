package com.ems.common.datatransformer.definition;

/**
 * @author Chiknin
 */
public class ClassMetadataDefinition extends Definition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	
	public static final String OBJECTFACTORY_NEW = "new";
	public static final String OBJECTFACTORY_SPRING = "spring";
	
	private String className;
	private String objectFactory;
	private String scope = SCOPE_SINGLETON;

	private String ref;
	private ClassMetadataDefinition refClassMetadataDefinition;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjectFactory() {
		return objectFactory;
	}
	public void setObjectFactory(String objectFactory) {
		this.objectFactory = objectFactory;
	}

	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public ClassMetadataDefinition getRefClassMetadataDefinition() {
		return refClassMetadataDefinition;
	}
	public void setRefClassMetadataDefinition(
			ClassMetadataDefinition refClassMetadataDefinition) {
		this.refClassMetadataDefinition = refClassMetadataDefinition;
	}
}