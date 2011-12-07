Ext.define('ems.core.widget.plugin.XGridSearchForm', {
	
	alias: 'plugin.xgridsearchform',
	
	grid: null,
	
	init: function(grid) {
		var me = this,
			g = me.grid = grid,
			sf = g.searchForm;
			
		if (sf) {
			var searchFormConfig = Ext.apply(me._defaultSearchFormConfig(), sf);
			g.addDocked(searchFormConfig, 0);
		}
	},
	
	_defaultSearchFormConfig: function() {
		var me = this;
		return {
			dock: 'top',
            xtype: 'form',
            title: '查询筛选',
			collapsible: true,
//			frame: true,
            defaultType: 'textfield',
//			bodyPadding: 5,
			border: false,
//			columns: 2,
//			layout: 'column',
			layout: {
				pack: 'center',
		        type: 'table',
		        // The total column count must be specified here
		        columns: 2
		    },
		    defaults: {
		        // applied to each contained panel
		        
		    },
			fieldDefaults: {
	            labelAlign: 'right',
	            msgTarget: 'side'
	        },
            defaults: {
//                anchor: '100%',
				bodyStyle: 'padding:5px'
            },
			buttons:  [{
				text: '查询',
	            handler: function() {
					var f = this.up('form').getForm();
					if (!f.isValid()) {
						return;
					}
					
	                var v = f.getValues();
					me.grid.store.load({
						params: v
					});
	            }
			}, {
				text: '重置',
				handler: function() {
					var f = this.up('form').getForm();
					f.reset();
				}
			}]
		}
	},
	
	destroy: function() {
		var me = this;
		me.grid = null;
	}
});