Ext.define('ems.biz.stuMag.applyinfo.ApplyInfo', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'ApplyInfoEditUI',
	readViewId: 'ApplyInfoReadUI',
	addViewId: 'ApplyInfoAddUI',
    
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
		Dic.distroyStore('ApplyInfo');
	}
});