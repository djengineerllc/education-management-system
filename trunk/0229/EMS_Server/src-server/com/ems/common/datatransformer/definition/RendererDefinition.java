package com.ems.common.datatransformer.definition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.ems.common.datatransformer.util.ObjectUtil;

/**
 * @author Chiknin
 */
public class RendererDefinition extends ClassMetadataDefinition {
	
	public static final String PHASE_BEFORE = "before";
	public static final String PHASE_AFTER = "after";
	
	private String style;
	private Map<String, String> styleMap = new ConcurrentHashMap<String, String>();
	
	private String phase = PHASE_AFTER;
	
	public String getStyleValue(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		
		return styleMap.get(name.toLowerCase());
	}
	public <T> T getStyleValue(String name, Class<T> requiredType) {
		String value = styleMap.get(name.toLowerCase());
		if (value == null) {
			throw new IllegalArgumentException("not found style: " + name);
		}
		
		return ObjectUtil.to(value, requiredType);
	}
	
	private volatile boolean mergedStyle = false;
	public void mergeStyle(Map<String, String> srcStyle, boolean overwrite) {
		if (!mergedStyle) {
			for (String srcKey : srcStyle.keySet()) {
				if (styleMap.containsKey(srcKey) && !overwrite) {
					continue;
				}
				styleMap.put(srcKey, srcStyle.get(srcKey));
			}
			mergedStyle = true;
		}
	}

	public void setStyle(String style) {
		this.style = style;
		this.styleMap = splitStyle(style);
		mergedStyle = false;
	}
	public String getStyle() {
		return style;
	}
	public Map<String, String> getStyleMap() {
		return styleMap;
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> splitStyle(String styleStr) {
		if (StringUtils.isBlank(styleStr)) {
			return Collections.EMPTY_MAP;
		}
		
		Map<String,String> styleMap = new HashMap<String, String>();
		String[] stylePairGroup = styleStr.split(";");
		for (String stylePair : stylePairGroup) {
			if (StringUtils.isNotBlank(stylePair)) {
				String[] stylePairArr = stylePair.split(":");
				if (stylePairArr != null) {
					if (stylePairArr.length == 2) {
						styleMap.put(stylePairArr[0].trim().toLowerCase(), stylePairArr[1]);
					} else if (stylePairArr.length == 1) {
						styleMap.put(stylePairArr[0].trim().toLowerCase(), "");
					}
				}
			}
		}
		
		return styleMap;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
}