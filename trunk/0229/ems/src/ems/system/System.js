// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.system.System', {
	extend: 'ems.core.Module',
	silent: true,
//	autoLoadActions: false,

	requires: [
		'ems.system.Dic'
	],
	
	init: function() {
		this.callParent(arguments);
	}
});