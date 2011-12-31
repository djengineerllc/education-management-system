Ext.define('ems.biz.demo.widgetdemo.WidgetDemo', {
	extend: 'ems.core.Module',
	
//	autoLoadActions: false,
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		me.RV('DialogDemoView', {renderTo: me.uiIdPrefix + 'demo-dialog-panel'});
	},
	
	activate: function(params) {
		var me = this;
		me.callParent(arguments);
	}
});