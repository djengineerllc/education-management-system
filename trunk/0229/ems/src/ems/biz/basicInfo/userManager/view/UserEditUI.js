Ext.define('ems.biz.basicInfo.userManager.view.UserEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['id'],
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%'
				},
				items: [{
					xtype: 'hiddenfield',
					name: 'id'
				},{
	                fieldLabel: '帐号',
	                name: 'loginName',
	                allowBlank: false
	            },{
	                fieldLabel: '姓名',
	                name: 'userName',
	                allowBlank: false
	            },
	            Dic.comboBox('Role', {
					fieldLabel: '角色',
					name: 'roleId',
					value: '',
					allowBlank: false
				}),{
	                fieldLabel: 'E-Mail',
	                name: 'email',
	                vtype: 'email'
	            },{
	                fieldLabel: '联系电话',
	                name: 'contact'
	            }]
			}]
		};
	}
});