package com.ems.common.datatransformer.definition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiknin
 */
public class CellFieldGroupDefinition extends CellFieldDefinition {
	private String mappingClass;
	private String itemMappingClass;
	
	private Integer count;
	
	private List<CellFieldDefinition> cells = new ArrayList<CellFieldDefinition>();

	public String getMappingClass() {
		return mappingClass;
	}
	public void setMappingClass(String mappingClass) {
		this.mappingClass = mappingClass;
	}
	public String getItemMappingClass() {
		return itemMappingClass;
	}
	public void setItemMappingClass(String itemMappingClass) {
		this.itemMappingClass = itemMappingClass;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void addCell(CellFieldDefinition cell) {
		this.cells.add(cell);
	}
	public List<CellFieldDefinition> getCells() {
		return cells;
	}
	public void setCells(List<CellFieldDefinition> cells) {
		this.cells = cells;
	}
}