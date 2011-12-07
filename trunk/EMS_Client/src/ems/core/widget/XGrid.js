Ext.define('ems.core.widget.XGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.xgrid',
	
	frame: true,
	border: false,
	columnLines: false,
	
	rowNumberer: false,
	loadDF: Ext.emptyFn,
	searchForm: {},
	selMode: 'null', // single / simple / multi / null
	editingMode: 'null', // cellediting / rowediting / null
	expandRowBodyTpl: null,
	columns: [],
	buttons: [],
	
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
		};
		
        Ext.applyIf(me, config);
        
        this.callParent(arguments);
		
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
		
		if (me.rowNumberer) {
			fields.push({ xtype:'rownumberer' });
		}
		
		Ext.each(cols, function(col) {
			var field = { name: col.dataIndex}, 
				attrVal;
			Ext.each(me._ModelFieldAttrs, function(attr) {
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
		return Ext.create('Ext.selection.CheckboxModel', {mode: me.selMode});
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
			// TODO
        }
    },
	
	_ModelFieldAttrs: [
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
		me.store.destroy(true);
		me.callParent(arguments);
	}
});