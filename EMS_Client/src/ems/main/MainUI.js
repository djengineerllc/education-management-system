Ext.define('ems.main.MainUI', {
	extend: 'Ext.container.Viewport',
	requires: [
		'Ext.layout.BorderLayout',
		'Ext.tab.Panel',
		'Ext.ux.TabScrollerMenu',
		'Ext.data.proxy.Direct'
	],
	
	moduleId: null,
	renderTo: null,
	idp: null,
	locVal: null,
	MRA: null,
	DF: null,
	
	initComponent: function() {
		var me = this;
		Ext.apply(me, me.getInitConfig());
		me.callParent(arguments);
	},
	
	getInitConfig: function() {
		var me = this;
		return {
			layout: {
				type: 'border',
				padding: 5
			},
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				height: 60,
				html: 'north'
			}, {
				region: 'west',
				layout: 'fit',
				title: "功能菜单",
				collapsible: true,
				split: true,
				border: false,
				width: 180,
				items: [{
					layout: 'accordion',
					menuId: 'root',
					listeners: me.MRA('beforerender', 'loadMenu', {
						panelConfig: {
							xtype: 'treepanel',
							rootVisible: false,
							lines: false,
							height: '100%',
							border: false,
							listeners: me.MRA('itemclick', 'addWorkItem', {
								closable: true
							})
						}
					})
				}]
		    }, {
				region: 'center',
				layout: 'fit',
				border: false,
				items: [{
					xtype: 'tabpanel',
					activeTab: 0,
					id: 'workspace',
//					maskOnDisable: true,
//					cls: 'wscls',
					plugins: [{
						ptype: 'tabscrollermenu',
						maxText  : 1,
						pageSize : 1
					}],
					items: [{
						title: '首页',
						html: 'Creating more tabs...'
					}],
					listeners: me.MRA('tabchange', 'onWorkItemChange')
				}]
		    }, {
				region: 'south',
				height: 30
			}]
		};
	}
});