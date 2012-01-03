Ext.define('ems.biz.samples.crud.view.UserReadUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
//	getApis: function(){
//		var me = this;
//		return {
//			load: me.DF('loadUser'),
//			d: {
//				submit: me.DF('submitUser')
//			}
//		}
//	},
	
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
	                name: 'userName'
	            },
//				{
//	                fieldLabel: '性别',
//	                name: 'sex'
//	            }, 
				Dic.displayfield('Sex', {
					fieldLabel: '性别',
	                name: 'sex'
				}), 
				{
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