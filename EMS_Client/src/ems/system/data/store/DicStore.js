Ext.define('ems.system.data.store.DicStore', {
	extend: 'Ext.data.Store',
	requires: [
		'ems.system.data.model.DicModel'
	],
	
	model: 'ems.system.data.model.DicModel'
});