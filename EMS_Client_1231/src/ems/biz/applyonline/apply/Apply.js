Ext.define('ems.biz.applyonline.apply.Apply', {
	extend: 'ems.core.Module',
	
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		//me.uiEl.down('form').getForm().load();
	},
	
	activate: function(params) {
		var me = this;
		me.callParent(arguments);
	}
});