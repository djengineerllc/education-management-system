Ext.define('ems.biz.applyonline.apply.Apply', {
	extend: 'ems.core.Module',
	
	autoLoadActions: false,
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		//me.ui.down('form').getForm().load();
	},
	
	activate: function(params) {
		var me = this;
		me.callParent(arguments);
	}
});