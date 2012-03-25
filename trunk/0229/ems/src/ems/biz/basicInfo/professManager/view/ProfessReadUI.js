Ext.define('ems.biz.basicInfo.professManager.view.ProfessReadUI', {
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
					fieldLabel: '项目ID',
	                name: 'projectId'
				},{
	                fieldLabel: '项目名',
	                name: 'projectName'
	            },{
					fieldLabel: '专业ID',
	                name: 'id'
				},{
	                fieldLabel: '专业名',
	                name: 'professName'
	            }]
			}]
		};
	}
});