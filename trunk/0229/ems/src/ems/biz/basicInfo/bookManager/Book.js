Ext.define('ems.biz.basicInfo.bookManager.Book', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'BookEditUI', // require
	readViewId: 'BookReadUI' // require
    
//    ,init: function() {
//        var me = this;
//        me.callParent(arguments);
//    },
//    
//    activate: function(params) {
//        var me = this;
//        me.callParent(arguments);
//    }
});