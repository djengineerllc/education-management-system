Ext.define('ems.biz.basicInfo.classManager.Class', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'ClassEditUI',
	readViewId: 'ClassReadUI',
    
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
		Dic.distroyStore('Class');
	}
});