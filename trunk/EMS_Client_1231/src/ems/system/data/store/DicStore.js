Ext.define('ems.system.data.store.DicStore', {
	extend: 'Ext.data.DirectStore',
	requires: [
		'ems.system.data.model.DicModel'
	],
	model: 'ems.system.data.model.DicModel'
});