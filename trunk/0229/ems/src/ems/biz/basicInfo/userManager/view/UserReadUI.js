Ext.define('ems.biz.basicInfo.userManager.view.UserReadUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
	uiConfig: function() {
		var me = this;
		return {
			width: 230,
			height: 130,
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
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: 'ID',
	                name: 'id'
				},{
	                fieldLabel: '帐号',
	                name: 'loginName'
	            },{
	                fieldLabel: '姓名',
	                name: 'userName'
	            },{
	                fieldLabel: '角色',
	                name: 'roleName'
	            },{
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