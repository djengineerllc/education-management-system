Ext.define('ems.biz.test.data.store.TestStore', {
	extend: 'Ext.data.Store',
	requires: [
		'ems.biz.test.data.model.TestModel'
	],
	
    model: 'ems.biz.test.data.model.TestModel',
	autoLoad: true,
	autoDestroy: true,
    remoteSort: true,
	
    sorters: [{
        property: 'name',
        direction: 'ASC'
    }],
	
    proxy: {
        type: 'direct',
        directFn: Ems.DF('ems.biz.test.Test','getGrid')
    },
	
	onBeforeDestroy: function() {
		this.destroyStore();
		this.model.destroy();
	}
});