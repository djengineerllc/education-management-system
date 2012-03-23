Ext.define('ems.core.widget.CellFieldDropZone', {
	extend: 'Ext.dd.DropZone',
	
	constructor: function(config){
		Ext.apply(this, config);
	},

	init: function(grid) {
		var me = this;
		if (grid.rendered) {
			me.grid = grid;
			grid.getView().on({	
				render: function(v) {
					me.view = v;
					ems.core.widget.CellFieldDropZone.superclass.constructor.call(me, me.view.el);
				},
				single: true
			});
			
			grid.on('celldblclick', function(table, cell, cellIndex, record, row, rowIndex, e) { // TODO row.rowIndex != rowIndex
				var column = grid.getColumn(cellIndex, row.rowIndex), dataIndex = column.dataIndex;
				if (column.ddTarget) {
					record.set(dataIndex, record.raw[dataIndex] || '');
				}
			}, me);
		} else {
			grid.on('render', me.init, me, {single: true});
		}
	},
	
	containerScroll: true,
	
	getTargetFromEvent: function(e) {
		var me = this, 
			v = me.view;
			
		var cell = e.getTarget(v.cellSelector, me.grid.getEl());
		if (cell) {
			var row = v.findItemByChild(cell),
				columnIndex = cell.cellIndex;
			
			if (row && Ext.isDefined(columnIndex)) {
				var column = me.grid.getColumn(columnIndex, row.rowIndex); // columnIndex 0..n  ; rowIndex 1..n
				
				return {
					node: cell,
					record: v.getRecord(row),
					fieldName: column.dataIndex, //me.grid.columns[columnIndex].dataIndex
					ddTarget: column.ddTarget
				};
			}
		}
	},
	
	onNodeEnter: function(target, dd, e, dragData) {
		delete this.dropOK;
		if (!target) {
			return;
		}
		
		if (this.onNodeEnterFilter(target.fieldName, target.ddTarget, dragData.ddSource, target, dd, e, dragData) === false) {
			return;
		}
		this.dropOK = true;
		Ext.fly(target.node).addCls('x-drop-target-active');
	},
	onNodeEnterFilter: function(fieldName, ddTarget, ddSource, target, dd, e, dragData) {
		if (ddTarget == ddSource) {
			return true;
		}
		return false;
	},
	
	onNodeOver: function(target, dd, e, dragData) {
		return this.dropOK ? this.dropAllowed : this.dropNotAllowed;
	},
	
	onNodeOut: function(target, dd, e, dragData) {
		Ext.fly(target.node).removeCls('x-drop-target-active');
	},
	
	onNodeDrop: function(target, dd, e, dragData) {
		if (this.dropOK) {
			target.record.set(target.fieldName, dragData.value);
			return true;
		}
	}
});