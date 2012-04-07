Ext.define('ems.biz.basicInfo.classManager.view.ClassEditUI', {
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
					labelWidth: 60,
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
				},Dic.comboBox('Grade', {
					fieldLabel: '年级名',
					name: 'gradeId'
					//valueKey: 'g1'
				}),{
	                fieldLabel: '班级名',
	                name: 'className'
	            },{
	            	xtype: 'numberfield',
	                fieldLabel: '班级人数',
	                name: 'studentNum'
	            }]
			}]
		};
	}
});