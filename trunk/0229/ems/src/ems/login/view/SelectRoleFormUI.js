Ext.define('ems.login.view.SelectRoleFormUI', {
    extend: 'ems.core.UI',
	
	initComponent: function() {
		var me = this,
			loginInfo = me.loginInfo;
		me.callParent(arguments);
		
		me.down('#userName').setValue(loginInfo.userName);
		me.down('#displayUserName').setValue(loginInfo.userName);
		me.down('#role').store.loadData(loginInfo.roles);
	},
    
    uiConfig: function(){
        var me = this;
        return {
			border: true,
			bodyPadding: '0 0 5 0',
			width: 250,
			height: 130,
			items: [{
				xtype: 'form',
				iconCls: 'icon-identity-card',
				itemId: 'selectRoleForm',
				title: '厦门大学教务管理系统',
				border: false,
				bodyPadding: '10 10 0 10',
				paramOrder: ['userName', 'role'],
				api: {
					submit: me.DF('selectRole')
				},
				fieldDefaults: {
					labelAlign: 'right',
					labelWidth: 60
				},
				items: [{
					xtype: 'hiddenfield',
					itemId: 'userName',
					name: 'userName'
				},{
					xtype: 'displayfield',
//					iconCls: 'icon-user',
					itemId: 'displayUserName',
					fieldLabel: '用户名'
				}, {
					xtype: 'combobox',
//					iconCls: 'icon-user',
					itemId: 'role',
					name: 'role',
					fieldLabel: '角色',
					emptyText: '请选择角色',
					allowBlank: false,
					queryMode: 'local',
					forceSelection: false,
					editable: false,
					valueField: 'name', //'id',
					displayField: 'name'
				}],
				buttons: [{
					margins: '0 15 0 0',
					text: '确认',
					iconCls: 'icon-key',
					listeners: me.MRA('click', 'selectRole')
				}, {
					text: '返回',
					listeners: me.MRA('click', 'gotoLogin')
				}]
			}]
		};
    }
});