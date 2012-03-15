Ext.define('ems.biz.basicInfo.subjectManager.view.SubjectReadUI', {
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
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['subjectId'],
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: '课程ID',
	                name: 'subjectId'
				},{
					fieldLabel: '课程编号',
					name: 'subjectNo'
				},{
					fieldLabel: '课程中文名',
					name: 'subjectCnName'
				}, {
	                fieldLabel: '课程英文名',
	                name: 'subjectEnName'
	            }, {
	                fieldLabel: '学分',
	                name: 'subjectScore'
	            }, {
	                fieldLabel: '课时',
	                name: 'subjectTime'
	            }]
			}]
		};
	}
});