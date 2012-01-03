Ext.define('ems.biz.base.crud.CrudUI', {
    extend: 'ems.core.UI',
	
	isCurdUI: true,
	
	bizAction: null, // c[create] | r[read] | u[update] | d[delete]
	
	reqParams: null,
	
	apis: null,
	getApis: Ext.emptyFn,
	
	isListUI: false,
	enableCreateAction: true,
	enableUpdateAction: true,
	enableDeleteAction: true,
	enableReadAction: true,
	
	constructor: function() {
		var me = this;
		me.callParent(arguments);
		me.apis = me.getApis();
		if (!me.apis) {
			me.apis = {
				load: me.DF('read'),
			};
			if (me.enableCreateAction) {
				me.apis.c = {
					submit: me.DF('create')
				}
			}
			if (me.enableUpdateAction) {
				me.apis.u = {
					submit: me.DF('update')
				}
			}
			if (me.enableReadAction) {
				me.apis.d = {
					submit: me.DF('delete')
				}
			}
		}
	},
	
	initComponent: function() {
		var me = this;
		me.callParent(arguments);
		
		if (me.isListUI) {
			var tbar = [];
			if (me.enableCreateAction) {
				tbar.push({
					text: '新增',
					iconCls: 'icon-create',
					listeners: me.MRA('click', 'onCreate')
				});
			}
			if (me.enableUpdateAction) {
				tbar.push({
					text: '修改',
					iconCls: 'icon-update',
					listeners: me.MRA('click', 'onUpdate')
				});
			}
			if (me.enableDeleteAction) {
				tbar.push({
					text: '删除',
					iconCls: 'icon-delete',
					listeners: me.MRA('click', 'onDelete')
				});
			}
			if (me.enableReadAction) {
				tbar.push({
					text: '查看',
					iconCls: 'icon-read',
					listeners: me.MRA('click', 'onRead')
				});
			}
			
			if (tbar.length > 0) {
				me.down('xgrid').addDocked({
	                xtype: 'toolbar',
					dock: 'top',
	                items: tbar
	            });
			}
		}
	},
	
	afterRender: function() {
		var me = this;
		me.callParent(arguments);
		me.init();
	},
	
	init: function() {
		var me = this,
			params = me.reqParams;
		
//		if (me.bizAction == 'u' || me.bizAction == 'r') {
			if (params) {
				me.loadFormData({
					params: params
				});
			}
//		}
		
//		if (me.bizAction == 'c') {
//			if (me.reqFormInitData) {
//				form.setValues(me.reqFormInitData);
//			}
//		}
	},
	
	loadFormData: function(options) {
		var me = this,
			formPanel = me.down('form'),
			form = formPanel.getForm(),
			apis = me.apis,
			loadApi = (apis[me.bizAction] && apis[me.bizAction].load) || apis.load;
		
		formPanel.api = {
			load: loadApi
		};
		form.api = {
			load: loadApi
		};
		form.load(options);
	},
	
	submitFormData: function(options) {
		var me = this,
			formPanel = me.down('form'),
			form = formPanel.getForm(),
			apis = me.apis,
			submitApi = (apis[me.bizAction] && apis[me.bizAction].submit) || apis.submit;
		
//		if (me.bizAction == 'd') {
//			
//		} else {
			formPanel.api = {
				submit: submitApi
			};
			form.api = {
				submit: submitApi
			};
			form.submit(options);
//		}
	}
});