Ext.define('ems.main.Main', {
	extend: 'ems.core.Module',
	requires: [
	],
	
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
							defaultRootId: item.id
						})
					});
					item.items = [treePanel];
					
					eo.add(me._toMenuItem(item));
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
			title: record.get ? record.get('moduleName') : null,
			autoScroll: true
		});
		Ems.RM(moduleId, workItemConfig.moduleConfig, function(module) {
			workItemConfig.items = [module.uiEl];
			workItem = workspace.add(workItemConfig); // add workItem(tab)
			workItem.on('beforedestroy', me._doDestroyWorkItem, this);
			
			me.activeWorkItem(workItem, request);
		}, me);
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
	}
});