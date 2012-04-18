Ext.define('ems.biz.basicInfo.educatManager.view.EducatEditUI', {
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
				},Dic.comboBox('Teacher', {
						fieldLabel: '教师',
						name: 'teacherId',
					headerOption: true
					}),Dic.comboBox('Course', {
						fieldLabel: '课程',
						name: 'courseNo',
					headerOption: true
					}),Dic.comboBox('Term', {
						fieldLabel: '学期',
						name: 'termId',
					headerOption: true
					}),Dic.comboBox('Class', {
						fieldLabel: '班级',
						name: 'classId',
					headerOption: true
					})]
			}]
		};
	}
});