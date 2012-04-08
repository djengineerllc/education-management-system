Ext.define('ems.biz.stuMag.addNewStu.Stu', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'StuEditUI',
	readViewId: 'StuReadUI',
    
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
		Dic.distroyStore('Stu');
	}
});