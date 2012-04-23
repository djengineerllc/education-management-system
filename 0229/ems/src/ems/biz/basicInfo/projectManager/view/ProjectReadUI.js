Ext.define('ems.biz.basicInfo.projectManager.view.ProjectReadUI', {
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
			width: 350,
			height: 120,
			autoScroll: true,
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				autoScroll: true,
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
					fieldLabel: '项目ID',
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