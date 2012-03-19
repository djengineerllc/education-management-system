Ext.define('ems.biz.syllabus.data.store.SyllabusStore', {
	extend: 'Ext.data.TreeStore',
    requires: ['ems.biz.syllabus.data.model.SyllabusModel'],
    model: 'ems.biz.syllabus.data.model.SyllabusModel',
    autoDestroy: true,
    
    constructor: function(){
        var me = this;
        me.proxy = {
//            type: 'direct',
//            directFn: Ems.DF('ems.main.Main', 'getMenu'),
//            paramOrder: ['node'],
//            extraParams: {}
        	type: 'ajax',
        	url: 'test_syllabus_data.json'
        }
        
        this.callParent(arguments);
    }
});