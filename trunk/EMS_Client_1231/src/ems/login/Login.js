Ext.define('ems.login.Login', {
	extend: 'ems.core.Module',
	
	_loginInfo: null,
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		
		me.RV('LoginFormUI', {
			renderTo: me.down('#loginFormPanel')
		});
	},
	
	login: function(params, request) {
		var me = this,
			loginForm = me.down('#loginForm').getForm();
			
		if (!loginForm.isValid()) {
			return;
		};
		
		loginForm.submit({
			success: function(form, action) {
				if (form.selectRole) {
					var userRoleCombo = me.query('#userRole');
					
				} else {
					me._loginInfo = action.result.data;
					me.destroyUI();
					Ems.RM('ems.main.Main');
				}
			}
		});
	},
	
	logout: function(params, request) {
		var me = this;
		me.A({
			m: 'logout',
			cb: function(result, e) {
				me._loginInfo = null;
				Ems.requestViewportModule();
//				window.location.reload();
			}
		});
	},
	
	getLoginInfo: function(params, request) {
		return this._loginInfo;
	}
});