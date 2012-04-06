Ext.define('ems.main.view.HomeUI', {
	extend: 'ems.core.UI',
	
	initData: function() {
		var me = this;
	},
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'fit',
			frame: false,
			items: [{
				html: 'empty content...'
			}]
		}
	}
});