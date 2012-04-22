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
	
	uses: [
		'Ext.form.CheckboxGroup',
		'Ext.form.RadioGroup',
		'Ext.form.field.Display'
	],
	
	DIC_STORE_PREFIX: 'ems.dic.',
	
	DEFAULT_COMBOBOX_HEADER_OPTION: {value: '', name: '全部'},
	
	getDicData: function(param) {
		return (Ems.syncDirectRequest('ems.system.System', 'getDicData', [param || {}]).result) || [];
	},
	getDicDataAsync: function(param, callback, scope) {
		Ems.A('ems.system.System', {
			m: 'getDicData',
			p: param,
			cb: function(data) {
				Ext.callback(callback, scope, [data]);
			}
		});
	},
	
	getStore: function(dicType) {
		
		var me = this,
			storeId = (me.DIC_STORE_PREFIX + dicType),
			dicStore = Ext.data.StoreManager.lookup(storeId);
		if (!dicStore) {
			var data = me.getDicData({type: dicType}); //Ems.syncDirectRequest('ems.system.System', 'getDicData', [{type: dicType}]).result;
			dicStore = Ext.create('ems.system.data.store.DicStore', {
				storeId: storeId,
				simpleSortMode: true,
				remoteSort: false
			});
			dicStore.loadData(data);
		}
		
		return dicStore;
	},
	distroyStore: function(dicType) {
		var me = this,
			storeId = (me.DIC_STORE_PREFIX + dicType),
			dicStore = Ext.data.StoreManager.lookup(storeId);
		if (dicStore) {
//			dicStore.destroy();
			dicStore.destroyStore();
		}
	},
	
	getEmptyStore: function(config) {
		var me = this,
			dicStore = Ext.create('ems.system.data.store.DicStore', {
				autoDestroy: true
			});
//			hdrOpt = config && config.headerOption;
		
//		if (hdrOpt != undefined && hdrOpt !== false) {
//			var hdrOptData = Ext.isObject(hdrOpt) ? hdrOpt : me.DEFAULT_COMBOBOX_HEADER_OPTION, 
//				data = [hdrOptData];
//				
//			dicStore.loadData(data);
//			delete config.headerOption;
//		}
		
		return dicStore;
	},
	
	localComboBox: function(config) {
		return Ext.create('Ext.form.field.ComboBox', Ext.applyIf(config || {}, {
			queryMode: 'local',
			forceSelection: false,
			editable: false,
			valueField: 'value',
			displayField: 'name',
			store: this.getEmptyStore(config)
		}));
	},
	
	comboBox: function(dicType, config) {
		var me = this, 
			dicStore = me.getStore(dicType),
			hdrOpt = config && config.headerOption;
		
		if (hdrOpt != undefined && hdrOpt !== false) {
			var hdrOptData = Ext.isObject(hdrOpt) ? hdrOpt : me.DEFAULT_COMBOBOX_HEADER_OPTION, 
				records = dicStore.getRange(), 
				data = [hdrOptData];
				
			Ext.each(records, function(record) {
				data.push(record.data);
			});
			
			dicStore = Ext.create('ems.system.data.store.DicStore', {
				autoDestroy: true
			});
			dicStore.loadData(data);
			
			delete config.headerOption;
		}
		
		var config = Ext.apply(config || {}, {
			queryMode: 'local',
			forceSelection: false,
			editable: false,
			valueField: 'value',
			displayField: 'name',
			store: dicStore
		}),
		comboBox = Ext.create('Ext.form.field.ComboBox', config);
		
		if (dicStore.getCount() > 0) {
			if (comboBox.value == undefined) {
				var record, value;
				if (comboBox.valueKey) {
					record = comboBox.findRecord('key', comboBox.valueKey);
				} else {
					record = dicStore.getAt(0);
				}
				if (record) {
					value = record.data[comboBox.valueField];
				}
				
				if (value != undefined) {
					comboBox.originalValue = value;
					comboBox.setValue(value);
				}
			}
		}
		
		return comboBox;
	},
	
	checkboxGroup: function(dicType, config) {
		return this._createFieldContainer(dicType, 'checkboxGroup', config);
	},
	
	radioGroup: function(dicType, config) {
		return this._createFieldContainer(dicType, 'radioGroup', config);
	},
	
	_fieldContainerConfig: {
		checkboxGroup: {
			className: 'Ext.form.CheckboxGroup',
			getNameFn: function(fieldContainer, data) {
				return fieldContainer.groupName + '_' + data['key'];
			}
		},
		radioGroup: {
			className: 'Ext.form.RadioGroup',
			getNameFn: function(fieldContainer, data) {
				return fieldContainer.groupName;
			}
		}
	},
	_createFieldContainer: function(dicType, ctType, config) {
		var me = this,
			dicStore = me.getStore(dicType),
			records = dicStore.getRange(),
			fcCfg = me._fieldContainerConfig[ctType],
			items = [];
		
		Ext.each(records, function(record) {
			var data = record.data,
				itemName = fcCfg.getNameFn(config, data),
				checked = false;
			
			 if (config.value != undefined) {
			 	checked = Ext.isArray(config.value) ? Ext.Array.contains(config.value, data.value) : config.value == data.value;
			 } else {
			 	if (config.valueKey != undefined) {
					checked = Ext.isArray(config.valueKey) ? Ext.Array.contains(config.valueKey, data.key) : config.valueKey == data.key;
				}	
			 }
			
			items.push({
				boxLabel: data.name,
				name: itemName,
				inputValue: data.value,
				checked: checked
			});
		});
		delete config.value;
		
		return Ext.create(fcCfg.className, Ext.apply({items: items}, config));
	},
	
	renderer: function(dicType) {
		var me = this,
			dicStore = me.getStore(dicType);
		
		return function(value) { //, metaData, record, rowIndex, colIndex, store, view) {
			var dicRecord = dicStore.findRecord('value', value);
			if (dicRecord) {
				return dicRecord.data.name;
			}
			
			return value || '';
		};
	},
	
	displayfield: function(dicType, config) {
		var me = this, 
		displayField = Ext.create('Ext.form.field.Display', Ext.apply(config, {
			listeners: {
				change: function(comp, newValue, oldValue, eOpts) {
					if (newValue) {
						var dicStore = me.getStore(dicType),
							record = dicStore.findRecord('value', newValue);
						if (record) {
							this.setRawValue(this.valueToRaw(record.data['name'] || newValue));
						}
					}
				}
			}
		}));
		if (config.value) {
			var dicStore = me.getStore(dicType),
				record = dicStore.findRecord('value', config.value);
			if (record) {
				displayField.setRawValue(displayField.valueToRaw(record.data['name'] || config.value));
			}
		}
		
		return displayField;
	},
	
	column: function(dicType, config) {
		if (Ext.isString(dicType)) {
			dicType = {dicType: dicType};
		}
		
		return Ext.applyIf(config || {}, {
			renderer: Dic.renderer(dicType.dicType),
			field: Dic.comboBox(dicType.dicType, dicType)
		});
	}
});