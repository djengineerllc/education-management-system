Ext.define('ems.biz.basicInfo.userManager.User', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'UserEditUI',
	readViewId: 'UserReadUI',
    
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
		Dic.distroyStore('Teacher');
	}
});