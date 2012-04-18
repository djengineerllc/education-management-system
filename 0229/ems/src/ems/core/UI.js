// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.UI', {
	extend: 'Ext.panel.Panel',

	border: false,	
	autoScroll: true,
	
	// START: module DI
	moduleId: null,
	
	renderTo: null,
	
	idp: null,
	
	locVal: null,
	
	MRA: null,
	
	DF: null,
	// START: module DI
	
	uiConfig: Ext.emptyFn,
	
	initData: Ext.emptyFn,
	
	initComponent: function() {
		var me = this,
			uiConfig = me.uiConfig();
			
		Ext.apply(me, uiConfig);
        Ext.apply(me.initialConfig, uiConfig);
		
		me.callParent(arguments);
		
		Ems.updateLocale();
	},
	
	afterRender: function() {
		var me = this;
		me.callParent(arguments);
		me.initData();
	},
	
	destroy: function() {
		moduleId = null;
		renderTo = null;
		idp = null;
		locVal = null;
		MRA = null;
		DF = null;
		this.callParent(arguments);
	}
});