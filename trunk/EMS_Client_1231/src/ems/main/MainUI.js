Ext.define('ems.main.MainUI', {
	extend: 'Ext.container.Viewport',
	requires: [
		'Ext.layout.container.Border',
		'Ext.layout.container.Accordion',
		'Ext.tab.Panel',
		'Ext.tree.Panel'
//		,
//		'Ext.ux.TabScrollerMenu',
//		'Ext.toolbar.TextItem'
	],
	
	uses: [
		'Ext.ux.statusbar.StatusBar'
	],
	
	moduleId: null,
	renderTo: null,
	idp: null,
	locVal: null,
	MRA: null,
	DF: null,
	
	initComponent: function() {
		var me = this,
			uiConfig = me.uiConfig();
			
		Ext.apply(me, uiConfig);
		Ext.apply(this.initialConfig, uiConfig);
		
		me.callParent(arguments);
	},
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'border',
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				cls: 'ems-header',
				height: 100,
				layout: {
                    type: 'hbox',
                    align: 'stretch'
                },
				defaults: {
					border: false
				},
				items: [{
					cls: 'ems-logo',
					width: 450
				}, {
					flex: 1,
					margins:'70 0 0 0',
					html: '教务管理系统'
				}, {
					margins:'5 5 0 0',
					items: [{
						xtype: 'button',
						text: '退出系统',
						listeners: me.MRA('click', 'onLogout')
					}, {
						xtype: 'button',
						text: 'printAllScripts',
						handler: function() {
							var scripts = Ext.query('script');
							Ext.each(scripts, function(scripts) {
								var path = scripts.src;
								if (path.indexOf('lib/ext') == -1) {
									return;
								}
								
								path = path.substring(path.indexOf('lib/ext'), path.indexOf('?_dc'));
								var a = path.substring(0, path.lastIndexOf('\/'));
								var b = path.substring(path.lastIndexOf('\/') + 1);
								console.log(Ext.String.format('{"text" : "{0}", "path" : "{1}/"},',  b, a));
							});
						}
					}]
				}]
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
								layout: 'anchor',
								border: false,
								frame: false,
								autoScroll: true,
								closable: true,
								defaults: {
									layout: 'anchor',
									anchor: '100% 100%'
								}
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
//					plugins: [{
//						ptype: 'tabscrollermenu',
//						maxText: 1,
//						pageSize: 1
//					}],
					items: [{
						title: '首页',
						html: 'Creating more tabs...'
					}],
					listeners: me.MRA('tabchange', 'onWorkItemChange')
				}]
		    }, {
				region: 'south',
				border: false,
				items: [
					Ext.create('Ext.ux.statusbar.StatusBar', {
					border: false,
					defaultText: '系统公告',
//					statusAlign: 'right',
		            items: ['-', {
						xtype: 'tbtext',
						id: 'clock',
						listeners: {
							render: function() {
								Ems.A('ems.system.System', {
									m: 'getSystemTime',
									cb: function(result, e) {
										var clock = Ext.getCmp('clock');
										Ext.TaskManager.start({
											run: function() {
												clock.update(Ext.Date.format(new Date(), 'g:i:s A'));
											},
											interval: 1000
										});
									}
								})
							}
						}
					}]
	            })]
			}]
		};
	}
});