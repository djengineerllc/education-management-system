// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.widget.XGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.xgrid',
	
	uses: [
		'Ext.data.DirectStore',
		'Ext.grid.RowNumberer',
		'Ext.selection.CheckboxModel',
		'Ext.toolbar.Paging',
		'Ext.ux.RowExpander',
		'ems.core.widget.plugin.XGridSearchForm',
		'ems.core.widget.plugin.XGridPrinter'
	],
	
	frame: false,
	border: false,
	columnLines: false,
	sortableColumns: false,
	stripeRows: true,
	
	rowNumberer: true,
	
	loadDF: null,
	
	searchForm: null,
	
	storeConfig: null,
	
	selMode: null, // single / simple / multi / null
	
	editingMode: null, // cellediting / rowediting / null
	editingConfig: null,
	
	expandRowBodyTpl: null,
	
	print: true, // 开启打印功能
	
	paging: false,
	idProperty: 'id',
	totalProperty: 'totalCount',
	root: 'items',
	
    initComponent: function() {
        var me = this, 
			config = me._defaultXGridConfig();
		
		if (me.store == undefined) {
//			if (me.loadDF == null) {
//				Ext.Error.raise({
//					sourceClass: me.$className,
//					sourceMethod: 'initComponent',
//					msg: 'function loadDF be not null'
//                });
//			}
			
			config.store = me._buildStore();
		}
		if (me.selModel == undefined && me.selMode != undefined) {
			config.selModel = me._buildSelModel();
		}
		if (me.plugins == undefined) {
			config.plugins = me._buildPlugins();
		}
		if (me.rowNumberer) {
			config.columns = [Ext.create('Ext.grid.RowNumberer')].concat(config.columns || []);
		}
		
        Ext.applyIf(me, config);
        this.callParent(arguments);
    },
    bridgeToolbars: function() {
    	var me = this;
    	this.callParent(arguments);
    	if (me.paging == true) {
			me.dockedItems = me.dockedItems.concat(
				Ext.create('Ext.toolbar.Paging', {
					dock: 'bottom',
					store: me.store,
					displayInfo: true
				}
			));
		}
    },
//	onDestroy: function() {
//		this.callParent(arguments);
//		this.store.destroy();
//		this.store = null;
//	},
	
	getAllColumn: function() {
		var me = this, col_i, col, subCol_i, allCol = [];
       	for (col_i in me.columns) {
       		col = me.columns[col_i];
       		
       		if (Ext.isArray(col.columns)) {
       			for (subCol_i in col.columns) {
       				allCol.push(col.columns[subCol_i]);
       			}
       		} else {
       			allCol.push(col);
       		}
       	}
       	
       	return allCol;
	},
	
	_buildStore: function() {
		var me = this,
			cols = me.getAllColumn(),//me.columns,
			fields = [];
		
		Ext.each(cols, function(col) {
			var field = {name: col.dataIndex}, 
				attrVal;
			Ext.each(me._modelFieldAttrs, function(attr) {
				attrVal = col[attr];
				if (attrVal != undefined) {
					field[attr] = attrVal;
				}
			}, me);
			if (field.dateFormat == undefined && col.format != undefined) {
				field['dateFormat'] = col.format;
			}
			
			fields.push(field);
		}, me);
		
		var config = Ext.applyIf(me.storeConfig || {}, {
			directFn: me.loadDF,
			idProperty: me.idProperty,
			totalProperty: me.totalProperty,
			root: me.root,
			simpleSortMode: true,
//			sortParam: 'sortField',
//			directionParam: 'sortDir',
			
			autoDestroy: true,
			remoteSort: true,
//			sorters: [{
//		         property: 'name',
//		        direction: 'ASC'
//		    }],
			fields: fields
		});
//		if (me.paging) {
//			Ext.apply(config, {
//				
//			});
//		}
		return Ext.create('Ext.data.DirectStore', config);
	},
	_buildSelModel: function() {
		var me = this;
		
		var config = {
			selType: 'checkboxmodel',
			mode: me.selMode
		};
		
		return config;
	},
	_buildPlugins: function() {
		var me = this;
		
		var plugins = [];
		
		if (me.searchForm) {
			plugins.push(
				Ext.create('ems.core.widget.plugin.XGridSearchForm')
			);
		}
		if (me.print) {
			plugins.push(
				Ext.create('ems.core.widget.plugin.XGridPrinter')
			);
		}
		
		if (me.editingMode != undefined) {
			plugins.push(
				Ext.apply({
					ptype: me.editingMode
				}, me.editingConfig)
			);
		}
		if (me.expandRowBodyTpl != undefined) {
			plugins.push(
				Ext.create('Ext.ux.RowExpander', {
					rowBodyTpl : me.expandRowBodyTpl
				})
			);
		}
		
		return plugins;
	},
	
    _defaultXGridConfig: function(){
		var me = this;
        return {
        };
    },
	
	_modelFieldAttrs: [
		'type',
		'convert',
		'dateFormat',
		'useNull',
		'defaultValue',
		'mapping',
		'sortType',
		'allowBlank',
		'persist',
		'sortDir'
	]
});