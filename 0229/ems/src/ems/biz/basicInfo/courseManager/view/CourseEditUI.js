Ext.define('ems.biz.basicInfo.courseManager.view.CourseEditUI', {
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
					fieldLabel: '课程编号',
					name: 'courseNo'
				},{
					fieldLabel: '课程中文名',
					name: 'courseName'
				}, {
	                fieldLabel: '课程英文名',
	                name: 'courseEngName'
	            }, {
	                fieldLabel: '学分',
	                name: 'courseScore'
	            }, {
	            	xtype: 'numberfield',
	                fieldLabel: '课时',
	                name: 'courseTime'
	            }]
			}]
		};
	}
});