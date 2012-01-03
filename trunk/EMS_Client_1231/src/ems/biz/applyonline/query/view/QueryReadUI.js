Ext.define('ems.biz.applyonline.query.view.QueryReadUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
	uiConfig: function() {
		var me = this;
		return {
			width: 300,
			height: 200,
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
					xtype: 'hiddenfield',
					name: 'id'
				},{
	                fieldLabel: '姓名',
	                name: 'stuName'
	            },
				Dic.displayfield('Sex', {
					fieldLabel: '性别',
	                name: 'stuSex'
				}), 
				{
	                fieldLabel: '生日',
	                name: 'stuBirthday'
	            }, {
	                fieldLabel: '电子邮件',
	                name: 'email'
	            }]
			}]
		};
	}
});