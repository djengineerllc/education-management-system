Ext.define('ems.biz.samples.crud.view.UserEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
//	getApis: function(){
//		var me = this;
//		return {
//			load: me.DF('loadUser'),
//			submit:  me.DF('submitUser')
////			c: {
////				submit: 'submitUser'
////			},
////			u: {
////				submit: 'submitUser'
////			}
//		}
//	},
	
	uiConfig: function() {
		var me = this;
		return {
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
	                fieldLabel: '姓名',
	                name: 'userName'
	            },
				Dic.radioGroup('Sex', {
					fieldLabel: '性别',
					groupName: 'sex',
					allowBlank: false
				}), {
					xtype: 'datefield',
	                fieldLabel: '生日',
	                name: 'birthday',
					format: 'Y-m-d'
	            }, {
	                fieldLabel: '电子邮件',
	                name: 'email',
					vtype: 'email'
	            }]
			}]
		};
	}
});