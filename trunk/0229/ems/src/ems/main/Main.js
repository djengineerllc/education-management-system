Ext.define('ems.main.Main', {
	extend: 'ems.core.Module',
	requires: [
		'Ext.layout.container.Border',
		'Ext.tab.Panel',
		'Ext.form.field.ComboBox'
	],
	
	uses: [
		'ems.main.MainActions',
		'ems.main.data.store.MenuStore',
		'ems.main.MainUI'
	],
	
	constructor: function() {
		var me = this;
		me.overrideExtClassess();
		me.callParent(arguments);
	},
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		
		me.locale = { // TODO 资源加载器 国际化资源由Server提供 目前不需要国际化支持
			nav: {
				title1: '$Accordion Item 1',
				title2: '$Accordion Item 2',
				title3: '$Accordion Item 3' 
			}
		};
	},
	
	loadMenu: function(params, request) {
		var me = this,
			r = request,
			p = r.p,
			pc = p.panelConfig || {},
			eo = r.eventSource,
			menuId = eo.menuId || 'root';
		
		me.A({
			m: 'getMenu',
			p: menuId,
			cb: function(result, event) {
				Ext.each(result, function(item) {
					var treePanel = Ext.merge({}, pc, {
						store: Ext.create('ems.main.data.store.MenuStore', {
							defaultRootId: item.id,
							autoLoad: false
						})
					});
					item.items = [treePanel];
					
					var menuItem = eo.add(me._toMenuItem(item));
//					menuItem.on
				}, me);
			},
			s: me
		});
	},
	
	getWorkspace: function() {
		return Ext.getCmp('workspace');
	},
	
	addWorkItem: function(params, request) {
		var me = this,
			ea = request.eventArgs,
			record = ea ? ea[1] : {},
			workItemConfig = Ext.apply({}, params),
			moduleId = workItemConfig.moduleId = (workItemConfig.moduleId || record.get('moduleId'));
		if (!moduleId) {
			return;
		}
		
		var workspace = me.getWorkspace(),
			workItem = workspace.getChildByElement(moduleId);
		if (workItem) {
			me.activeWorkItem(moduleId, request);
			return;
		}
		
		Ext.applyIf(workItemConfig, {
			moduleConfig: (record.get ? (Ext.decode(record.get('moduleConfig') || '{}')) : null),
			id: moduleId,
			title: record.get ? (record.get('moduleName') ? record.get('moduleName') : record.get('text')) : '',
			autoScroll: true
		});
//		Ems.RM(moduleId, workItemConfig.moduleConfig, function(module) {
//			workItemConfig.items = [module.ui];
//			workItem = workspace.add(workItemConfig); // add workItem(tab)
//			workItem.on('beforedestroy', me._doDestroyWorkItem, this);
//			
//			me.activeWorkItem(workItem, request);
//		}, me);
		
		workItemConfig.items = [];
		workItem = workspace.add(workItemConfig); // add workItem(tab)
		workItem.on('beforedestroy', me._doDestroyWorkItem, me);
		
		Ems.mask();
		Ext.defer(function() {
			Ems.RM(moduleId, workItemConfig.moduleConfig, function(module) {
				workItem.add(module.ui);
				me.activeWorkItem(workItem, request);
				Ems.unmask();
			}, me);
		}, 30, me); // 120
	},
	_doDestroyWorkItem: function(workItem) {
		var moduleId = workItem.moduleId;
		Ems.destroyModule(moduleId);
	},
	
	onWorkItemChange: function(params, request) {
		var me = this,
			ea = request.eventArgs, 
			tabPanel = ea[0], 
			workItem = ea[1], 
			prevWorkItem = ea[2],
			moduleId = workItem.moduleId;
		
		if (moduleId) {
			Ems.MR(moduleId, {
				m: 'activate',
				p: request
			});
		}
	},
	getActiveWorkItem: function() {
		return this.getWorkspace().getActiveTab();
	},
	
	activeWorkItem: function(moduleId, request) {
		var me = this,
			moduleId = moduleId,
			workspace = me.getWorkspace(),
			workItem;
		
		if (Ext.isString(moduleId)) {
			workItem = workspace.getChildByElement(moduleId);
		} else {
			moduleId = moduleId.moduleId;
			workItem = moduleId;
		}
		
		if (workItem) {
			workspace.setActiveTab(workItem);
			Ems.MR(moduleId, {
				m: 'activate',
				p: request
			});
		}
	},
	
	_toMenuItem: function(item, listeners) {
		var me = this,
			ls = listeners || {};
		item.text = (item.textKey ? me.locVal(item.textKey) : item.text) || '',
		item.title = (item.titleKey ? me.locVal(item.titleKey) : item.title) || item.text;
		item.listeners = ls;
		
		return item;
	},
	
	onLogout: function (params, request) {
		EU.showConfirmDialog({
			msg: '您确认退出系统？',
			callback: function(btnId, value) {
				if (btnId == 'yes') {
					Ems.MR('ems.login.Login', {
						m: 'logout'
					});
				}
			}
		});
    },
	
	onSwitchRole: function(params, request) {
		alert('切换角色'); // TODO 
	},
	
	overrideExtClassess: function() {
		Ext.form.field.ComboBox.override({
			getSelectedIndex: function() {
				var value = this.getValue(),
					record = this.findRecord(this.valueField || this.displayField, value);
				return this.store.indexOf(record);
			}
		});
	}
});