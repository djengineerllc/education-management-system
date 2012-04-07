Ext.define('ems.biz.basicInfo.courseManager.view.CourseReadUI', {
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
			width: 260,
			height: 170,
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
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: '课程ID',
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
	                fieldLabel: '课时',
	                name: 'courseTime'
	            }]
			}]
		};
	}
});