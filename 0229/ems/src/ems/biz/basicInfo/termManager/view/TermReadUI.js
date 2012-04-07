Ext.define('ems.biz.basicInfo.termManager.view.TermReadUI', {
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
			height: 90,
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
					fieldLabel: '学期ID',
	                name: 'id'
				},{
	                fieldLabel: '学期名',
	                name: 'termName'
	            },
                Dic.displayfield('Indicator', {
					fieldLabel: '是否当前学期',
	                name: 'isCurrentTerm'
				})]
			}]
		};
	}
});