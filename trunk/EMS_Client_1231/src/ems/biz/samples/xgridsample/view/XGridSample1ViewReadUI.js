Ext.define('ems.biz.samples.xgridsample.view.XGridSample1ViewReadUI', {
    extend: 'ems.core.UI',
	
	constructor: function(config) {
		this.callParent(arguments);
	},
	
	uiConfig: function() {
		var me = this;
		return {
			width: 300,
			height: 200,
			items: [{
				html: '...'
			}]
		};
	}
});