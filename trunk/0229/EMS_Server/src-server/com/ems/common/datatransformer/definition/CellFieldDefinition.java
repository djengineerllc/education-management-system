package com.ems.common.datatransformer.definition;

import java.io.Serializable;

/**
 * @author Chiknin
 */
public class CellFieldDefinition extends DataFieldDefinition {
	private Integer row;
	private Integer column;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public CellIndex getCellIndex() {
		return new CellIndex(this);
	}
	
	public static class CellIndex implements Serializable {
		private Integer row;
		private Integer column;
		
		public CellIndex() {
		}
		
		public CellIndex(Integer row, Integer column) {
			this.row = row;
			this.column = column;
		}
		
		public CellIndex(CellFieldDefinition cfd) {
			this.row = cfd.getRow();
			this.column = cfd.getColumn();
		}

		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public Integer getColumn() {
			return column;
		}

		public void setColumn(Integer column) {
			this.column = column;
		}
	}
}