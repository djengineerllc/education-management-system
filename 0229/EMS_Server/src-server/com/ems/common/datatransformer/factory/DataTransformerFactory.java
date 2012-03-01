package com.ems.common.datatransformer.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.util.Assert;

import com.ems.common.datatransformer.api.IDataTransformer;
import com.ems.common.datatransformer.api.IHandler;
import com.ems.common.datatransformer.api.IRenderer;
import com.ems.common.datatransformer.api.IValidator;
import com.ems.common.datatransformer.definition.CellFieldGroupDefinition;
import com.ems.common.datatransformer.definition.CellFieldSetDefinition;
import com.ems.common.datatransformer.definition.ClassMetadataDefinition;
import com.ems.common.datatransformer.definition.ConstantDefinition;
import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.DataFieldSetDefinition;
import com.ems.common.datatransformer.definition.DataTransformerDefinition;
import com.ems.common.datatransformer.definition.DataTransformersDefinitionReader;
import com.ems.common.datatransformer.definition.HandlerDefinition;
import com.ems.common.datatransformer.definition.IDataTransformersDefinitionReader;
import com.ems.common.datatransformer.definition.RendererDefinition;
import com.ems.common.datatransformer.definition.TextFieldGroupDefinition;
import com.ems.common.datatransformer.definition.TextFieldSetDefinition;
import com.ems.common.datatransformer.definition.ValidatorDefinition;
import com.ems.common.datatransformer.definition.parser.ParserDefinition;
import com.ems.common.datatransformer.objectfactory.IObjectFactory;
import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 */
public class DataTransformerFactory implements IDataTransformerFactory {
	
	public static final String DT_CONFIG_FILE = "DataTransformers.xml";
	
	public static final String CONSTANT_ENV_OBJECTFACTORY_REGISTORY	= "Env.ObjectFactoryRegistry";
	public static final String CONSTANT_ENV_PARSERDEFINITION_HANDLER_REGISTORY	= "Env.ParserDefinitionHandlerRegistry";
	
	private Map<String, Object> singletonObjects;
	private Map<String,IObjectFactory> objectFactorys;
	private IDataTransformersDefinitionReader dtsdReader;
	private Map<String, IDataTransformer<?,?>> dataTransformers;
	
	public DataTransformerFactory() {
		this.refresh();
	}
	
	public String getObjectFactoryName() {
		return DataTransformerFactory.class.getSimpleName();
	}
	
	public DataTransformerDefinition getDataTransformerDefinition(String name) {
		return dtsdReader.getDataTransformer(name);
	}
	public void addDynamicDataTransformerDefinition(DataTransformerDefinition dtd) {
		dtsdReader.addDataTransformerDefinition(dtd);
	}

	public IDataTransformer<?, ?> getDataTransformer(String name) {
		DataTransformerDefinition dtd = dtsdReader.getDataTransformer(name);
		Assert.notNull(dtd, "not found DataTransformer '" + name + "'");
		ParserDefinition pd = dtd.getParser();
		
		return dataTransformers.get(pd.getParserType());
	}
	
	public String getConstantValue(String name) {
		if (dtsdReader.getConstant(name) != null) {
			return dtsdReader.getConstant(name).getValue();
		}
		
		return null;
	}
	public String getConstantValue(String name, String defaultValue) {
		String value = this.getConstantValue(name);
		if (value == null) return defaultValue;
		
		return value;
	}
	public <T> T getConstantValue(String name, Class<T> requiredType) {
		return ObjectUtil.to(this.getConstantValue(name), requiredType);
	}
	public <T> T getConstantValue(String name, Class<T> requiredType, T defaultValue) {
		T value = this.getConstantValue(name, requiredType);
		if (value == null) return defaultValue;
		
		return value;
	}
	public Properties getConstantsOfPrefix(String prefix) {
		List<ConstantDefinition> constants = dtsdReader.getConstants();
		if (constants != null && constants.size() > 0) {
			boolean hasPreFix = StringUtils.isNotBlank(prefix);
			Properties props = new Properties();
			for (ConstantDefinition constant : constants) {
				if (hasPreFix) {
					if (constant.getName().length() < prefix.length()) {
						continue;
					}
					if (constant.getName().startsWith(prefix)) {
						String key = constant.getName().substring(prefix.length());
						props.put(key, constant.getValue());
					}
				} else {
					props.put(constant.getName(), constant.getValue());
				}
			}
			
			return props;
		}
		
		return null;
	}
	
	public IHandler getHandler(String name) {
		HandlerDefinition hd = dtsdReader.getHandler(name);
		return this.getHandler(hd);
	}
	public IHandler getHandler(HandlerDefinition hd) {
		if (hd == null) {
			return null;
		}
		
		HandlerDefinition refHd = null;
		String ref = hd.getRef();
		if (StringUtils.isNotBlank(ref)) {
			refHd = dtsdReader.getHandler(ref);
			Assert.notNull(refHd, "not found Handler '" + ref + "'");
			hd.setRefClassMetadataDefinition(refHd);
		} else {
			refHd = hd;
		}
		
		IHandler handler = this.getObject(refHd, IHandler.class);
		
		return handler;
	}

	public IRenderer getRenderer(String name) {
		RendererDefinition rd = dtsdReader.getRenderer(name);
		return this.getRenderer(rd);
	}
	public IRenderer getRenderer(RendererDefinition rd) {
		if (rd == null) {
			return null;
		}
		
		RendererDefinition refRd = null;
		String ref = rd.getRef();
		if (StringUtils.isNotBlank(ref)) {
			refRd = dtsdReader.getRenderer(ref);
			Assert.notNull(refRd, "not found Renderer '" + ref + "'");
			rd.mergeStyle(refRd.getStyleMap(), false); // merge style for parent style
			rd.setRefClassMetadataDefinition(refRd);
		} else {
			refRd = rd;
		}
		
		IRenderer renderer = this.getObject(refRd, IRenderer.class);
		
		return renderer;
	}
	
	public IValidator getValidator(String name) {
		ValidatorDefinition vd = dtsdReader.getValidator(name); 
		return this.getValidator(vd);
	}
	public IValidator getValidator(ValidatorDefinition vd) {
		if (vd == null) {
			return null;
		}
		
		ValidatorDefinition refVd = null;
		String ref = vd.getRef();
		if (StringUtils.isNotBlank(ref)) {
			refVd = dtsdReader.getValidator(ref);
			Assert.notNull(refVd, "not found Validator '" + ref + "'");
			vd.setRefClassMetadataDefinition(refVd);
		} else {
			refVd = vd;
		}
		
		IValidator validator = this.getObject(refVd, IValidator.class);
		
		return validator;
	}
	
	public List<DataFieldDefinition> getDataFieldSet(String name) {
		DataFieldSetDefinition dfsd = dtsdReader.getDataFieldSet(name);
		return this.getDataFieldSet(dfsd);
	}
	
	public List<DataFieldDefinition> getDataFieldSet(DataFieldSetDefinition fsd) {
		if (fsd == null) {
			return null;
		}
		
		DataFieldSetDefinition rfsd = null;
		String ref = fsd.getRef();
		Map<String, DataFieldDefinition> overwriteDfdMap = null;
		if (StringUtils.isNotBlank(ref)) {
			rfsd = dtsdReader.getDataFieldSet(ref);
			Assert.notNull(rfsd, "not found DataFieldSet '" + ref + "'");
			
			if (fsd.getItems().size() > 0) {
				overwriteDfdMap = new HashMap<String, DataFieldDefinition>();
				for (DataFieldDefinition owDfd : fsd.getItems()) {
					if (owDfd instanceof TextFieldSetDefinition 
							|| owDfd instanceof TextFieldGroupDefinition 
							|| owDfd instanceof CellFieldSetDefinition 
							|| owDfd instanceof CellFieldGroupDefinition) {
						continue;
					} else if (owDfd instanceof DataFieldDefinition) {
						if (StringUtils.isNotBlank(owDfd.getName())) {
							overwriteDfdMap.put(owDfd.getName(), owDfd);
						}
					}
				}
			}
		} else {
			rfsd = fsd;
		}
		
		List<DataFieldDefinition> dfds = new ArrayList<DataFieldDefinition>();
		for (DataFieldDefinition rdfd : rfsd.getItems()) {
			if (rdfd instanceof DataFieldSetDefinition) {
				List<DataFieldDefinition> rdfds = this.getDataFieldSet((DataFieldSetDefinition) rdfd);
				if (rdfds != null && rdfds.size() > 0) {
					dfds.addAll(rdfds); // TODO not test....
				}
			} else if (rdfd instanceof DataFieldDefinition) {
				if (overwriteDfdMap != null && overwriteDfdMap.containsKey(rdfd.getName())) {
					dfds.add(overwriteDfdMap.get(rdfd.getName()));
				} else {
					dfds.add(rdfd);
				}
			}
		}
		
		return dfds;
	}
	
	public IDataTransformer<?, ?> getObject(String name) {
		return this.getDataTransformer(name);
	}
	
	public Map<String, IObjectFactory> getObjectFactorys() {
		return this.objectFactorys;
	}
	
	public synchronized void refresh() {
		singletonObjects = new ConcurrentHashMap<String, Object>();
		objectFactorys = null;
		dtsdReader = null;
		dataTransformers = null;
		
		dtsdReader = new DataTransformersDefinitionReader();
		dtsdReader.read(DT_CONFIG_FILE);
		
		this.initEnv();
	}
	
	@SuppressWarnings("unchecked")
	private <T> T getObject(ClassMetadataDefinition classMetadate, Class<T> clazz) {
		if (classMetadate == null) return null;
		
		String objectFactoryName = classMetadate.getObjectFactory();
		if (StringUtils.isBlank(objectFactoryName)) {
			objectFactoryName = dtsdReader.getDataTransformers().getObjectFactory();
		}
		
		T object = null;
		boolean isGetInCache = ClassMetadataDefinition.SCOPE_SINGLETON.equalsIgnoreCase(classMetadate.getScope()) 
								&& ClassMetadataDefinition.OBJECTFACTORY_NEW.equalsIgnoreCase(objectFactoryName);
		if (isGetInCache) {
			object = (T) singletonObjects.get(ObjectUtil.identityToString(classMetadate));
			if (object != null) {
				return object;
			}
		}
		
		IObjectFactory objectFactory = objectFactorys.get(objectFactoryName);
		object = (T) objectFactory.getObject(classMetadate.getClassName());
		
		if (isGetInCache) {
			singletonObjects.put(ObjectUtil.identityToString(classMetadate), object);
		}
		
		return object;
	}
	
	private synchronized void initEnv() {
		String splitRegex =  " |,|;|\\|";
		
		objectFactorys = new HashMap<String, IObjectFactory>();
		String ofRegistry = this.getConstantValue(CONSTANT_ENV_OBJECTFACTORY_REGISTORY);
		Assert.hasText(ofRegistry, "constant '"+ CONSTANT_ENV_OBJECTFACTORY_REGISTORY + "' must not be empty");
		String[] ofNames = ofRegistry.split(splitRegex);
		if (ofNames != null && ofNames.length > 0) {
			for (String ofName : ofNames) {
				String ofPrefix = CONSTANT_ENV_OBJECTFACTORY_REGISTORY + "." + ofName + ".";
				String ofClassName = this.getConstantValue(ofPrefix + "className");
				Assert.hasText(ofClassName, "constant '"+ ofPrefix + "className" +"' must not be empty");
				
				objectFactorys.put(ofName, (IObjectFactory) ObjectUtil.instance(ofClassName));
			}
		}
		
		dataTransformers = new HashMap<String, IDataTransformer<?,?>>();
		String pdhRegistry = this.getConstantValue(CONSTANT_ENV_PARSERDEFINITION_HANDLER_REGISTORY);
		Assert.hasText(pdhRegistry, "constant '"+ CONSTANT_ENV_PARSERDEFINITION_HANDLER_REGISTORY + "' must not be empty");
		String[] pdhNames = pdhRegistry.split(splitRegex);
		if (pdhNames != null && pdhNames.length > 0) {
			for (String pdhName : pdhNames) {
				String pdhPrefix = CONSTANT_ENV_PARSERDEFINITION_HANDLER_REGISTORY + "." + pdhName + ".";
				String pdhObjectFactory = this.getConstantValue(pdhPrefix + "objectFactory", ClassMetadataDefinition.OBJECTFACTORY_NEW);
				Assert.isTrue(objectFactorys.containsKey(pdhObjectFactory), "not found objectFactory '" + pdhObjectFactory + "'");
				String pdhClassName = this.getConstantValue(pdhPrefix + "className");
				Assert.hasText(pdhClassName, "constant '"+ pdhPrefix + "className" +"' must not be empty");
				
				IDataTransformer<?, ?> dt = (IDataTransformer<?, ?>) objectFactorys.get(pdhObjectFactory).getObject(pdhClassName);
				dt.setDataTransformerFactory(this);
				dataTransformers.put(pdhName, dt);
			}
		}
	}
}