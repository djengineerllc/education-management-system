// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.main.data.store.MenuStore', {
    extend: 'Ext.data.TreeStore',
    requires: ['ems.main.data.model.MenuModel'],
    model: 'ems.main.data.model.MenuModel',
    autoDestroy: true,
    
    constructor: function(){
        var me = this;
        me.proxy = {
            type: 'direct',
            directFn: Ems.DF('ems.main.Main', 'getMenu'),
            paramOrder: ['node'],
            extraParams: {}
        }
        
        this.callParent(arguments);
    }
});
