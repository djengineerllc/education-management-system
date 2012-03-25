Ext.define('ems.login.LoginUI', {
	extend: 'Ext.container.Viewport',
    
	initComponent: function() {
		var me = this,
			uiConfig = me.uiConfig();
			
		Ext.apply(me, uiConfig);
        Ext.apply(this.initialConfig, uiConfig);
		
		me.callParent(arguments);
	},
	
    uiConfig: function() {
        var me = this;
        return {
			border: false,
			layout: {
				type: 'hbox',
				pack: 'center',
				align: 'middle'
			},
			items: [{
				border: false,
				itemId: 'loginFormPanel'
			}]
        };
    }
});