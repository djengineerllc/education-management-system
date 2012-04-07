Ext.define('ems.biz.basicInfo.courseManager.Course', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'CourseEditUI',
	readViewId: 'CourseReadUI',
    
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
		Dic.distroyStore('Course');
	}
});