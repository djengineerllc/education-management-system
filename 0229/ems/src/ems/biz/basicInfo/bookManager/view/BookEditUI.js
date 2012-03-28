Ext.define('ems.biz.basicInfo.bookManager.view.BookEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
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
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%'
				},
				items: [{
					xtype: 'hiddenfield',
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