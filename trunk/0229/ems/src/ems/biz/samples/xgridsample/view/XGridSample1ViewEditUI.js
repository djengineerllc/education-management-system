Ext.define('ems.biz.samples.xgridsample.view.XGridSample1ViewEditUI', {
    extend: 'ems.core.UI',
	
	afterRender: function() {
		var me = this;
		me.callParent(arguments);
		me.init();
	},
	
	init: function() {
		var me = this,
			params = me.reqParams;
		if (params) {
			me.down('form').getForm().load({
				params: params
			});
		}
	},
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'fit',
			items: [{
				xtype: 'form',
				itemId: 'xgridSample1ViewEditForm',
				border: false,
				bodyPadding: 10,
				api: {
					load: me.DF('getFormData'),
					submit: me.DF('submitFormData')
				},
				paramOrder: ['userName'],
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%'
				},
				items: [{
	                fieldLabel: '姓名',
	                name: 'userName'
	            },
				Dic.radioGroup('Sex', {
					fieldLabel: '性别2',
					groupName: 'sex'
				}), {
					xtype: 'datefield',
	                fieldLabel: '生日',
	                name: 'birthday'
	            }, {
	                fieldLabel: '电子邮件',
	                name: 'email'
	            }]
			}]
		};
	}
});