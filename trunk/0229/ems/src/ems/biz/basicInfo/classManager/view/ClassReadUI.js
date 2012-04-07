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
			width: 230,
			height: 110,
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
					fieldLabel: '班级ID',
	                name: 'id'
				},{
	                fieldLabel: '年级名',
	                name: 'gradeName'
	            },{
	                fieldLabel: '班级名',
	                name: 'className'
	            },{
	                fieldLabel: '班级人数',
	                name: 'studentNum'
	            }]
			}]
		};
	}
});