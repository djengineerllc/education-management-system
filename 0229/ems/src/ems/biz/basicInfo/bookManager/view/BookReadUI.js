Ext.define('ems.biz.basicInfo.bookManager.view.BookReadUI', {
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
					fieldLabel: '教材ID',
	                name: 'id'
				},{
	                fieldLabel: '教材名称',
	                name: 'bookName'
	            },{
	                fieldLabel: '出版社',
	                name: 'publishName'
	            },{
	                fieldLabel: '作者',
	                name: 'author'
	            },{
	                fieldLabel: 'ISBN号',
	                name: 'isbnNo'
	            }]
			}]
		};
	}
});