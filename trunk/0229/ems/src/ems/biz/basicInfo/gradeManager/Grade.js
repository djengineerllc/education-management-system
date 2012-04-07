Ext.define('ems.biz.basicInfo.gradeManager.Grade', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'GradeEditUI',
	readViewId: 'GradeReadUI',
    
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
		Dic.distroyStore('Grade');
	}
});