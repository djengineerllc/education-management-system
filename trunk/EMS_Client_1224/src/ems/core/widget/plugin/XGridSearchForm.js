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
		}
	},
	
	_defaultSearchFormConfig: function() {
		var me = this;
		return {
			dock: 'top',
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
				labelWidth: 100,
	            msgTarget: 'side'
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
	            handler: function() {
					var form = this.up('form').getForm();
					if (!form.isValid()) {
						return;
					};
					
	                var values = form.getValues();
					me.grid.store.load({
						params: values
					});
	            }
			}, {
				text: '重置',
				handler: function() {
					var form = this.up('form').getForm();
					form.reset();
				}
			}],
			dockedItems: [{
				dock: 'top',
				xtype: 'toolbar',
				border: true,
				items: [{
					xtype: 'label',
					baseCls: 'x-panel-header-text',
					text: '查询筛选'
				}]
			}, {
				dock: 'bottom',
				xtype: 'toolbar',
				border: true,
				items: [{
					xtype: 'label',
					baseCls: 'x-panel-header-text',
					text: '查询结果'
				}]
			}]
		}
	},
	
	destroy: function() {
		var me = this;
		me.grid = null;
	}
});