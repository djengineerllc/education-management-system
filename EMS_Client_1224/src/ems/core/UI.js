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
	
	initComponent: function() {//debugger;
		var me = this,
			uiConfig = me.uiConfig();
			
		Ext.apply(me, uiConfig);
        Ext.apply(this.initialConfig, uiConfig);
		
		me.callParent(arguments);
	}
});