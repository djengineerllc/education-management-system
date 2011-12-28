Ext.define('ems.core.widget.plugin.XGridSearchForm', {
	
	alias: 'plugin.xgridsearchform',
	
	grid: null,
	
	init: function(grid) {
		var me = this,
			g = me.grid = grid,
			sf = g.searchForm;
			
		if (sf) {
			var config = me._defaultSearchFormConfig();
			Ext.apply(config.items[0], sf);
			g.addDocked(config, 0);
			
			g.mon(g.store, 'beforeload', function(store, operation) {
				operation.params = Ext.apply(operation.params || {}, this._getSearchParams());
				return true;
			}, me);
		}
	},
	
	_defaultSearchFormConfig: function() {
		var me = this;
		return {
			dock: 'top',
			itemId: 'xgridSearchForm',
			xtype: 'form',
//			title: '查询筛选',
//			titleCollapse: true,
//			collapsible: true,
			frame: me.grid.frame,
			border: false,
			bodyPadding: '10 10 0 10',
			layout: {
				type: 'hbox',
				pack: 'center',
				align: 'middle'
			},
			fieldDefaults: {
				labelAlign: 'right',
				labelWidth: 100
//				,msgTarget: 'side'
			},
			items: [{
	            xtype: 'container',
	            layout: {
					type: 'table',
					columns: 3
				},
				defaultType: 'textfield',
				defaults: {
//					,allowBlank: false,
//					selectOnFocus: false
				}
	        }],
			buttonAlign: 'center',
			buttons:  [{
				text: '查询',
				iconCls: 'icon-search',
	            handler: function() {
					var form = this.up('form').getForm();
					if (!form.isValid()) {
						return;
					};
					
					me._setSearchParams(form.getValues());
					me.grid.store.load();
	            }
			}, {
				text: '重置',
				handler: function() {
					var form = this.up('form').getForm();
					form.reset();
				}
			}]
			,dockedItems: [{
				dock: 'top',
				xtype: 'toolbar',
				border: true,
				items: [{
					xtype: 'label',
					baseCls: 'x-panel-header-text',
					text: '学生消息列表'
				}]
			}
//			, {
//				dock: 'bottom',
//				xtype: 'toolbar',
//				border: true,
//				items: [{
//					xtype: 'label',
//					baseCls: 'x-panel-header-text',
//					text: '查询结果'
//				}]
//			}
			]
		}
	},
	
	_setSearchParams: function(params) {
		this.grid._searchParams = params;
	},
	_getSearchParams: function() {
		return this.grid._searchParams;
	},
	
	destroy: function() {
		var me = this;
		delete this.grid._searchParams;
		me.grid = null;
	}
});