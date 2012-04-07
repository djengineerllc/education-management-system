Ext.define('ems.biz.basicInfo.projectManager.Project', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'ProjectEditUI',
	readViewId: 'ProjectReadUI',
    
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
		Dic.distroyStore('Project');
	}
});