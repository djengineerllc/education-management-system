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
	                name: 'loginName'
	            },{
	                fieldLabel: '姓名',
	                name: 'userName'
	            },Dic.comboBox('Role', {
					fieldLabel: '角色',
					name: 'roleId',
					value:-1
				}),{
	                fieldLabel: 'E-Mail',
	                name: 'email'
	            },{
	                fieldLabel: '联系电话',
	                name: 'contact'
	            }]
			}]
		};
	}
});