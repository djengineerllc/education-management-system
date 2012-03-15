Ext.define('ems.biz.basicInfo.bookManager.view.BookEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
//	getApis: function(){
//		var me = this;
//		return {
//			load: me.DF('loadUser'),
//			submit:  me.DF('submitUser')
////			c: {
////				submit: 'submitUser'
////			},
////			u: {
////				submit: 'submitUser'
////			}
//		}
//	},
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['bookId'],
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
					name: 'bookId'
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