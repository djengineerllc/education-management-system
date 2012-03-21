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
                    Ext.ux.CellFieldDropZone.superclass.constructor.call(me, me.view.el);
                },
                single: true
            });
            
            grid.on('celldblclick', function(table, cell, cellIndex, record, row, rowIndex, e) {
            	var column = grid.getColumn(cellIndex), dataIndex = column.dataIndex;
            	record.set(dataIndex, '');
            }, me);
        } else {
            grid.on('render', me.init, me, {single: true});
        }
    },

    containerScroll: true,

    getTargetFromEvent: function(e) {
        var me = this,
            v = me.view;

        var cell = e.getTarget(v.cellSelector);
        if (cell) {
            var row = v.findItemByChild(cell),
                columnIndex = cell.cellIndex;

            if (row && Ext.isDefined(columnIndex)) {
            	var column = me.grid.getColumn(columnIndex);
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

//      Check that a field is being dragged.
//        var f = dragData.field;
//        if (!f) {
//            return;
//        }

//        var type = target.record.fields.get(target.fieldName).type,
//            types = Ext.data.Types;
//        switch(type){
//            case types.FLOAT:
//            case types.INT:
//                if (!f.isXType('numberfield')) {
//                    return;
//                }
//                break;
//            case types.DATE:
//                if (!f.isXType('datefield')) {
//                    return;
//                }
//                break;
//            case types.BOOL:
//                if (!f.isXType('checkbox')) {
//                    return;
//                }
//        }
		
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