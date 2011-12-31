Ext.define('ems.biz.samples.crud.User', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'UserEditUI', // require
	readViewId: 'UserReadUI' // require
    
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