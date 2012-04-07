Ext.define('ems.biz.basicInfo.roomManager.Room', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	editViewId: 'RoomEditUI',
	readViewId: 'RoomReadUI',
    
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
		Dic.distroyStore('Room');
	}
});