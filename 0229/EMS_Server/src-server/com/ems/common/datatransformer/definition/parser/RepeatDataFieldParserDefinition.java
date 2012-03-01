package com.ems.common.datatransformer.definition.parser;

import com.ems.common.datatransformer.definition.DataFieldDefinition;
import com.ems.common.datatransformer.definition.RepeatDefinition;

/**
 * @author Chiknin
 */
public abstract class RepeatDataFieldParserDefinition<T extends DataFieldDefinition> extends ParserDefinition {
	protected RepeatDefinition<T> repeat;
	
	public RepeatDefinition<T> getRepeat() {
		return repeat;
	}
	public void setRepeat(RepeatDefinition<T> repeat) {
		this.repeat = repeat;
	}
}