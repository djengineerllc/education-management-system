Ext.define('ems.biz.samples.xgridsample.XGridSampleUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function() {
        var me = this;
        return {
			border: false,
			frame: false,
			layout: 'anchor',
			defaults: {
				border: false,
				frame: false,
				xtype: 'panel'
			},
			items: [{
				anchor: '100% 5%',
				layout: {
					type: 'hbox',
					pack: 'center',
					align: 'middle'
				},
				defaults: {
					xtype: 'button'
				},
				items: [{
					text: "实例1",
					listeners: me.MRA('click', 'sample1')
				},{
					text: "实例2",
					listeners: me.MRA('click', 'sample2')
				}]
			}, {
				anchor: '100% 95%',
				layout:'anchor',
				itemId: 'sample-panel'
			}]
        };
    }
});