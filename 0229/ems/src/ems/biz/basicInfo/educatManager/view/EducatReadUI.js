Ext.define('ems.biz.basicInfo.educatManager.view.EducatReadUI', {
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
			width: 250,
			height: 120,
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
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: '教师',
	                name: 'teacherName'
				},{
					fieldLabel: '课程',
	                name: 'courseName'
				},{
					fieldLabel: '学期',
	                name: 'termName'
				},{
					fieldLabel: '班级',
	                name: 'className'
				}]
			}]
		};
	}
});