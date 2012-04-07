Ext.define('ems.biz.basicInfo.professManager.Profess', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'ProfessEditUI',
	readViewId: 'ProfessReadUI',
    
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
		Dic.distroyStore('Professional');
	}
});