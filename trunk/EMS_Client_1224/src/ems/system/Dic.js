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
	
	getStore: function(dicType, config) {
		var me = this,
			storeId = (me.DIC_STORE_PREFIX + dicType),
			dicStore = Ext.data.StoreManager.lookup(storeId);
		if (!dicStore) {
			dicStore = Ext.create('ems.system.data.store.DicStore', Ext.apply({
				storeId: storeId,
//				autoLoad: true,
				proxy: {
			        type: 'direct',
			        directFn: Ems.DF('ems.system.System', 'getDicData', null, false),
					paramOrder: ['dicType'],
					extraParams: {
						dicType: dicType
					}
				}
			}, config));
		}
		
		return dicStore;
	},
	
	comboBox: function(dicType, config) {
		var me = this, 
			dicStore = me.getStore(dicType, {
				autoLoad: true
			});
		
		var config = Ext.apply(config || {}, {
			queryMode: 'local',
			forceSelection: false,
			editable: false,
			valueField: 'value',
			displayField: 'name',
			store: dicStore
		});
		
		var comboBox = Ext.create('Ext.form.field.ComboBox', config);
		
		
		comboBox.mon(dicStore, 'load', function() {
			if (this.value == undefined) {
				var s = this.store;
				if (s.getCount() > 0) {
					var value;
					if (this.valueKey) {
						value = this.findRecord('key', this.valueKey);
					} else {
						value = s.getAt(0).data[this.valueField];
					}
					
					if (value) {
						this.originalValue = value;
						this.setValue(value);
					}
				}
			}
		}, comboBox);
		
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
			fieldContainer = Ext.create(me._fieldContainerConfig[ctType].className, config);
		fieldContainer.ctType = ctType;
		
		if (dicStore.getCount() <= 0) {
//			var onLoad = function() {
//				me._onDataOfFieldContainer(fieldContainer, dicStore);
//				
//				dicStore.un('load', onLoad);
//				onLoad = null;
//			};
//			dicStore.on('load', onLoad);
//			if (!dicStore.isLoading()) {
//				dicStore.load();
//			}
			dicStore.load(function() {
				me._onDataOfFieldContainer(fieldContainer, dicStore);
			});
		} else {
			me._onDataOfFieldContainer(fieldContainer, dicStore);
		}
		
		return fieldContainer;
	},
	_onDataOfFieldContainer: function(fieldContainer, store) {
		var me = this,
			fc = fieldContainer,
			records = store.getRange(),
			items = [],
			groupName = fc.groupName;
		
		Ext.each(records, function(record) {
			var data = record.data,
				itemName = me._fieldContainerConfig[fc.ctType].getNameFn(fc, data);
			
			items.push({
				boxLabel: data.name,
				name: itemName,
				inputValue: data.value
			});
		});
		fc.removeAll(true);
		
		if (items.length > 0) {
			fc.add(items);
			
			if (fc.fieldLabel) { // Ext bug
				fc.fieldLabel = fc.fieldLabel + ':';
				fc.updateLabel();
			}
		}
	},

	renderer: function(dicType) {
		var me = this,
			dicStore = me.getStore(dicType, {
				autoLoad: true
			});
		
		return function(value) { //, metaData, record, rowIndex, colIndex, store, view) {
			var dicRecord = dicStore.findRecord('value', value);
			if (dicRecord) {
				return dicRecord.data.name;
			};
			
			return '';
		};
	}
});