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
				paramOrder: ['professId'],
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
					name: 'professId'
				},{
					xtype: 'hiddenfield',
					name: 'projectId'
				}, {
	                fieldLabel: '项目名',
	                name: 'projectName'
	            },{
	                fieldLabel: '专业名',
	                name: 'professName'
	            }]
			}]
		};
	}
});