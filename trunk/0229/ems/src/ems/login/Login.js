Ext.define('ems.login.Login', {
	extend: 'ems.core.Module',
	
	requires: [
		'ems.login.Session'
	],
	
	_loginInfo: null,
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		
		me.RV('LoginFormUI');
	},
	
	login: function(params, request) {
		var me = this,
			formPanel = me.down('#loginForm');
			loginForm = formPanel.getForm();
			
		if (!loginForm.isValid()) {
			return;
		}
		
//		Ems.mask();
		loginForm.submit({
			success: function(form, action) {
				var result = action.result;
				Session.setLoginInfo(result.data);
				
				if (result.props && result.props.selectRole) {
					me.RV('SelectRoleFormUI', {
						loginInfo: Session.getLoginInfo()
					});
					EU.showMsg('登陆成功', '请您选择登录系统的角色');
				} else {
					me.destroyUI();
					Ems.RM('ems.main.Main');
				}
				
//				Ems.unmask();
			},
			failure: function(form, action) {
//				Ems.unmask();
			}
		});
	},
	
	logout: function(params, request) {
		var me = this;
		me.A({
			m: 'logout',
			cb: function(result, e) {
				Session.setLoginInfo(null);
				Ems.requestViewportModule();
//				window.location.reload();
			}
		});
	},
	
	selectRole: function(params, request) {
		var me = this,
			formPanel = me.down('#selectRoleForm'),
			selectRoleForm = formPanel.getForm();
			
		if (!selectRoleForm.isValid()) {
			return;
		}
		
		me.A({
			m: 'selectRole',
			p: [
				formPanel.down('#userName').getValue(),
				formPanel.down('#role').getValue()
			],
			cb: function(result, e) {
				if (result.success) {
					Session.setLoginInfo(result.data);
					me.destroyUI();
					Ems.RM('ems.main.Main');
				}
			}
		});
	},
	
	gotoLogin: function(params, request) {
		this.RV('LoginFormUI');
	}
});