Ext.define('ems.core.widget.XGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.xgrid',
	
	frame: false,
	border: false,
	columnLines: false,
		
	rowNumberer: true,
	
	loadDF: Ext.emptyFn,
	
	searchForm: {},
	
	selMode: null, // single / simple / multi / null
	
	editingMode: null, // cellediting / rowediting / null
	
	expandRowBodyTpl: null,
	
	paging: true,
	
    initComponent: function(){
        var me = this, 
			config = me._defaultXGridConfig();
		
		if (me.store == undefined) {
			config.store = me._buildStore();
		}
		if (me.selModel == undefined && me.selMode != undefined) {
			config.selModel = me._buildselModel();
		}
		if (me.plugins == undefined) {
			config.plugins = me._buildPlugins();
		}
        Ext.applyIf(me, config);
		
		if (me.rowNumberer) {
			me.columns = [{ xtype:'rownumberer' }].concat(me.columns || []);
		}
        
        this.callParent(arguments);
		
		if (me.paging == true) {
			me.addDocked(Ext.create('Ext.toolbar.Paging', {
				dock: 'bottom',
				store: config.store,
				displayInfo: true
			}));
		}
		
		me.store.load({
			params: {
				a: "A"
			}
		});
    },
	
	_buildStore: function() {
		var me = this,
			cols = me.columns,
			fields = [];
		
		Ext.each(cols, function(col) {
			var field = { name: col.dataIndex}, 
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
			autoDestroy: true,
			fields: fields,
		    proxy: {
		        type: 'direct',
		        directFn: me.loadDF
		    },
			remoteSort: true,
		    sorters: [{
		        property: 'name',
		        direction: 'ASC'
		    }]
		});
		
		return Ext.create('Ext.data.Store', config);
	},
	_buildselModel: function() {
		var me = this;
		
		var config = {
			selType: 'checkboxmodel',
			mode: me.selMode
		};
		
		return config;
	},
	_buildPlugins: function() {
		var me = this;
		
		var plugins = [
			Ext.create('ems.core.widget.plugin.XGridSearchForm')
		];
		
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
        }
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
	],
	
	destroy: function() {
		var me = this;
		Ext.destroy(me.store);
		me.callParent(arguments);
	}
});