Ext.define('ems.biz.basicInfo.bookManager.Book', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'BookEditUI',
	readViewId: 'BookReadUI',
    
//    ,init: function() {
//        var me = this;
//        me.callParent(arguments);
//    },
//    
//    activate: function(params) {
//        var me = this;
//        me.callParent(arguments);
//    }
	
	_onSuccess: function(bizAction) {
		this.callParent(arguments);
		Dic.distroyStore('Book');
	}
});