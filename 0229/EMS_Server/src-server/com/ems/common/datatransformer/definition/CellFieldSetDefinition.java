package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class CellFieldSetDefinition extends DataFieldDefinition implements DataFieldSetDefinition {
	
	private String ref;
	
	private List<DataFieldDefinition> cells = new ArrayList<DataFieldDefinition>();
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	
	public void addCell(DataFieldDefinition cell) {
		this.cells.add(cell);
	}
	public List<DataFieldDefinition> getCells() {
		return cells;
	}
	public void setCells(List<DataFieldDefinition> cells) {
		this.cells = cells;
	}
	public List<DataFieldDefinition> getItems() {
		return cells;
	}
}