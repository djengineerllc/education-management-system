package com.ems.common.datatransformer.definition;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ems.common.datatransformer.definition.support.DefaultDefinitionRegistry;
import com.ems.common.datatransformer.definition.support.IDefinitionRegistry;
import com.ems.common.datatransformer.factory.object.template.ITemplateEngine;
import com.ems.common.datatransformer.factory.object.template.digester.DigesterTemplateEngine;

/**
 * @author Chiknin
 */
public class DataTransformersDefinitionReader implements IDataTransformersDefinitionReader {
	
	public static final String DT_CONFIG_RULE_FILE = "com/ems/common/datatransformer/definition/rule/DataTransformerConfigRule.xml";
	
	private ITemplateEngine tplEngine;
	
	private DataTransformersDefinition dataTransformersDefinition;
	private IDefinitionRegistry<ConstantDefinition> cdRegistry;
	private IDefinitionRegistry<HandlerDefinition> hdRegistry;
	private IDefinitionRegistry<RendererDefinition> rdRegistry;
	private IDefinitionRegistry<ValidatorDefinition> vdRegistry;
	private IDefinitionRegistry<DataFieldSetDefinition> dfsdRegistry;
	private IDefinitionRegistry<DataTransformerDefinition> dtdRegistry;
	
	public DataTransformersDefinitionReader() {
		tplEngine = new DigesterTemplateEngine();
		cdRegistry = new DefaultDefinitionRegistry<ConstantDefinition>();
		hdRegistry = new DefaultDefinitionRegistry<HandlerDefinition>();
		rdRegistry = new DefaultDefinitionRegistry<RendererDefinition>();
		vdRegistry = new DefaultDefinitionRegistry<ValidatorDefinition>();
		dfsdRegistry = new DefaultDefinitionRegistry<DataFieldSetDefinition>();
		dtdRegistry = new DefaultDefinitionRegistry<DataTransformerDefinition>();
	}
	
	public void read(String configPath) {
		this.clearRegistry();
		dataTransformersDefinition = this.load(configPath);
		this.importResouces(new ArrayList<ImportDefinition>(dataTransformersDefinition.getImports()), dataTransformersDefinition);
		this.prepare(dataTransformersDefinition);
	}
	public ClassLoader getClassLoader() {
		return null; //TODO 
	}

	public DataTransformersDefinition getDefinition() {
		return this.getDataTransformers();
	}
	public DataTransformersDefinition getDataTransformers() {
		return dataTransformersDefinition;
	}
	public ConstantDefinition getConstant(String name) {
		return cdRegistry.get(name);
	}
	public List<ConstantDefinition> getConstants() {
		return dataTransformersDefinition.getConstants();
	}
	public HandlerDefinition getHandler(String name) {
		return hdRegistry.get(name);
	}
	public RendererDefinition getRenderer(String name) {
		return rdRegistry.get(name);
	}
	public ValidatorDefinition getValidator(String name) {
		return vdRegistry.get(name);
	}
	public DataFieldSetDefinition getDataFieldSet(String name) {
		return dfsdRegistry.get(name);
	}
	public DataTransformerDefinition getDataTransformer(String name) {
		return dtdRegistry.get(name);
	}
	
	public void addDataTransformerDefinition(DataTransformerDefinition dtd) {
		synchronized (dtdRegistry) {
			if (dtdRegistry.containsName(dtd.getName())) {
				List<DataTransformerDefinition> dtdList = dataTransformersDefinition.getDataTransformers();
				for (int i = 0; i < dtdList.size(); i++) {
					if (dtdList.get(i).getName().equals(dtd.getName())) {
						dtdList.set(i, dtd);
						break;
					}
				}
			} else {
				dataTransformersDefinition.addDataTransformer(dtd);
			}
			
			dtdRegistry.register(dtd.getName(), dtd);
		}
	}
	
	private DataTransformersDefinition load(String configPath) {
		Resource config = new ClassPathResource(configPath, this.getClassLoader());
		InputStream inputStream = null;
		try {
			inputStream = config.getInputStream();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		
		Properties settings = new Properties();
		
		
		return (DataTransformersDefinition) tplEngine.process(DT_CONFIG_RULE_FILE, inputStream, settings);
	}
	private void importResouces(List<ImportDefinition> ids, DataTransformersDefinition parentDtsd) {
		if (ids == null || ids.size() == 0) {
			return;
		}
		
		for (ImportDefinition id : ids) {
			String resource = id.getResource();
			DataTransformersDefinition dtsd = this.load(resource);
			this.importResouces(new ArrayList<ImportDefinition>(dtsd.getImports()), dtsd);
			parentDtsd.merge(dtsd);
		}
	}
	
	private void prepare(DataTransformersDefinition dtsd) {
		for (ConstantDefinition cd : dtsd.getConstants()) {
			cdRegistry.register(cd.getName(), cd);
		}
		for (HandlerDefinition hd : dtsd.getHandlers()) {
			hdRegistry.register(hd.getName(), hd);
		}
		for (RendererDefinition rd : dtsd.getRenderers()) {
			rdRegistry.register(rd.getName(), rd);
		}
		for (ValidatorDefinition vd : dtsd.getValidators()) {
			vdRegistry.register(vd.getName(), vd);
		}
		for (DataFieldSetDefinition sfSetd : dtsd.getDataFieldSets()) {
			dfsdRegistry.register(sfSetd.getName(), sfSetd);
		}
		for (DataTransformerDefinition dtd : dtsd.getDataTransformers()) {
			dtdRegistry.register(dtd.getName(), dtd);
		}
	}
	
	private void clearRegistry() {
		if (cdRegistry != null) cdRegistry.clear();
		if (hdRegistry != null) hdRegistry.clear();
		if (rdRegistry != null) rdRegistry.clear();
		if (vdRegistry != null) vdRegistry.clear();
		if (dfsdRegistry != null) dfsdRegistry.clear();
		if (dtdRegistry != null) dtdRegistry.clear();
	}
}