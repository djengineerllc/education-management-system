Ext.define('ems.biz.stuMag.addNewStu.view.StuReadUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
	uiConfig: function() {
		var me = this;
		return {
			width: 230,
			height: 130,
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