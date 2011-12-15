// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.system.Dic', {
	singleton: true,
	alternateClassName: 'Dic',
	
	requires: [
		'Ext.data.StoreManager',
		'ems.system.data.store.DicStore'
	],
	
	DIC_STORE_PREFIX: 'ems.dic.',
	
	datas: null,
	
	constructor: function() {
		var me = this;
		me.callParent(arguments);
		me.datas = {};
	},
	
	getData: function(dicType) {
		var me = this,
			data = me.datas[dicType];
		
		if (!data) {
			data = me.datas[dicType] = [
				{type: 'Sex', key: 'm', value: 'm', name: '男', desc: null, group: null},
				{type: 'Sex', key: 'f', value: 'f', name: '女', desc: null, group: null}
			];
//			Ems.A('ems.system.System', {
//				m: 'getDicData',
//				p: dicType,
//				cb: function(result) {
//					me.datas[dicType] = result;
//					cb(result);
//				}
//			});
		}
		
		return data;
	},
	
	getStore: function(dicType) {
		var me = this,
			storeId = (me.DIC_STORE_PREFIX + dicType),
			dicStore = Ext.data.StoreManager.lookup(storeId);
		
		if (!dicStore) {
			dicStore = Ext.create('ems.system.data.store.DicStore', {
				sotreId: storeId,
				data: me.getData(dicType)
			});
		}
		
		return dicStore;
	},
	
	getCombo: function(dicType, config) {
		var config = Ext.apply(config || {}, {
			xtype: 'combo',
			queryMode: 'local',
			valueField: 'value',
			displayField: 'name',
			store: Dic.getStore(dicType)
		});
		
		return config;
	},

	getRenderer: function() {
		
	}
});