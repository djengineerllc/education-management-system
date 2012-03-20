// A DropZone which cooperates with DragZones whose dragData contains
// a "field" property representing a form Field. Fields may be dropped onto
// grid data cells containing a matching data type.
Ext.define('Ext.ux.CellFieldDropZone', {
    extend: 'Ext.dd.DropZone',

    constructor: function(config){
    	Ext.apply(this, config);
    },

//  Call the DropZone constructor using the View's scrolling element
//  only after the grid has been rendered.
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
        } else {
            grid.on('render', me.init, me, {single: true});
        }
    },

//  Scroll the main configured Element when we drag close to the edge
    containerScroll: true,

    getTargetFromEvent: function(e) {
        var me = this,
            v = me.view;

//      Ascertain whether the mousemove is within a grid cell
        var cell = e.getTarget(v.cellSelector);
        if (cell) {

//          We *are* within a grid cell, so ask the View exactly which one,
//          Extract data from the Model to create a target object for
//          processing in subsequent onNodeXXXX methods. Note that the target does
//          not have to be a DOM element. It can be whatever the noNodeXXX methods are
//          programmed to expect.
            var row = v.findItemByChild(cell),
                columnIndex = cell.cellIndex;

            if (row && Ext.isDefined(columnIndex)) {
            	var i = -1, column, col, col_i, subCol, subCol_i;
            	for (col_i in me.grid.columns) {
            		col = me.grid.columns[col_i];
            		if (col.headerCounter > 0) {
            			for (subCol_i in col.items.items) {
            				subCol = col.items.items[subCol_i];
            				if (++i == columnIndex) {
            					column = subCol;
            					break;
            				}
            			}
            		} else {
            			if (++i == columnIndex) {
            				column = col;
        					break;
        				}
            		}
            	}
            	
                return {
                    node: cell,
                    record: v.getRecord(row),
                    fieldName: column.dataIndex, //me.grid.columns[columnIndex].dataIndex
                    ddTarget: column.ddTarget
                };
            }
        }
    },

//  On Node enter, see if it is valid for us to drop the field on that type of column.
    onNodeEnter: function(target, dd, e, dragData) {
        delete this.dropOK;
        if (!target) {
            return;
        }

//      Check that a field is being dragged.
        var f = dragData.field;
        if (!f) {
            return;
        }

//      Check whether the data type of the column being dropped on accepts the
//      dragged field type. If so, set dropOK flag, and highlight the target node.
        
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
		
        if (this.onNodeEnterFilter(target.fieldName, target.ddTarget, dragData.field.ddSource, target, dd, e, dragData) === false) {
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

//  Return the class name to add to the drag proxy. This provides a visual indication
//  of drop allowed or not allowed.
    onNodeOver: function(target, dd, e, dragData) {
        return this.dropOK ? this.dropAllowed : this.dropNotAllowed;
    },

//  highlight the target node.
    onNodeOut: function(target, dd, e, dragData) {
        Ext.fly(target.node).removeCls('x-drop-target-active');
    },

//  Process the drop event if we have previously ascertained that a drop is OK.
    onNodeDrop: function(target, dd, e, dragData) {
        if (this.dropOK) {
            target.record.set(target.fieldName, dragData.field.getValue());
            return true;
        }
    }
});