Ext.define('ems.biz.basicInfo.projectManager.view.ProjectEditUI', {
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
				},{
	                fieldLabel: '项目中文名',
	                name: 'projectName'
	            }, {
	            	fieldLabel: '项目英文名',
	                name: 'projectNameEn'
	            }]
			}]
		};
	}
});