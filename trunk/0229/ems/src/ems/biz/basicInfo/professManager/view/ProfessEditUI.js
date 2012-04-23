Ext.define('ems.biz.basicInfo.professManager.view.ProfessEditUI', {
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
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['id'],
				fieldDefaults: {
					width: 300,
					labelWidth: 80,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%',
					allowBlank: false
				},
				items: [{
					xtype: 'hiddenfield',
					name: 'id'
				},
				Dic.comboBox('Project', {
					fieldLabel: '项目名',
					name: 'projectId',
					allowBlank: false,
					value: ''
				}),{
	                fieldLabel: '专业中文名',
	                name: 'professName'
	            },{
	                fieldLabel: '专业英文名',
	                name: 'professNameEn'
	            }]
			}]
		};
	}
});