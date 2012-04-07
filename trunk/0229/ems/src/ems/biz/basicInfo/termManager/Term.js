Ext.define('ems.biz.basicInfo.termManager.Term', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'TermEditUI',
	readViewId: 'TermReadUI',
    
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
		Dic.distroyStore('Term');
	}
});