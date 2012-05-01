Ext.define('ems.biz.stuMag.regist.Regist', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'RegistEditUI',
	readViewId: 'RegistReadUI',
	addViewId: 'RegistAddUI',
    
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
		Dic.distroyStore('Regist');
	}
});