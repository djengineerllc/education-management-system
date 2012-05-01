Ext.define('ems.biz.stuMag.admission.Admission', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'AdmissionEditUI',
	readViewId: 'AdmissionReadUI',
    
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
		Dic.distroyStore('Admission');
	}
});