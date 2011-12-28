Ext.define('ems.biz.samples.xgridsample.XGridSample', {
	extend: 'ems.core.Module',
	
	init: function() {
		var me = this;
		me.callParent(arguments);
	},
	
	activate: function(params) {
		var me = this;
		me.callParent(arguments);
	},
	
	sample1: function(params, request) {
		var me = this,
			samplePanel = me.query('#sample-panel');
			
		me.RV('ems.biz.samples.xgridsample.view.XGridSample1ViewUI', {
			renderTo: samplePanel
		});
	},
	
	sample2: function(params, request) {
		var me = this,
			samplePanel = me.query('#sample-panel');
			
		me.RV('XGridSample2ViewUI', {
			renderTo: samplePanel
		});
	}
});