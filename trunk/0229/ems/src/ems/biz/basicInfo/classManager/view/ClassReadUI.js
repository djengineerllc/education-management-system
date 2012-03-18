Ext.define('ems.biz.basicInfo.classManager.view.ClassReadUI', {
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
				paramOrder: ['classId'],
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: '班级ID',
	                name: 'classId'
				},{
	                fieldLabel: '班级名',
	                name: 'className'
	            },{
	                fieldLabel: '年级名',
	                name: 'gradeName'
	            }]
			}]
		};
	}
});