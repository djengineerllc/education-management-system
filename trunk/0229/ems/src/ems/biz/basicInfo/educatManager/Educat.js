Ext.define('ems.biz.basicInfo.educatManager.Educat', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'EducatEditUI',
	readViewId: 'EducatReadUI',
    
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
		Dic.distroyStore('Education');
	}
});