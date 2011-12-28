Ext.define('ems.login.view.LoginFormUI', {
    extend: 'ems.core.UI',
	
	uiConfig: function(){
		var me = this;
		return {
			border: true,
			bodyPadding: '0 0 5 0',
			el: 'ems-workspace',
			items: [Ext.create('Ext.form.Panel', {
//				xtype: 'form',
				iconCls: 'icon-identity-card',
				itemId: 'loginForm',
				title: '厦门大学教务管理信息',
				border: false,
				bodyPadding: '10 10 0 10',
				api: {
					submit: me.DF('login')
				},
				fieldDefaults: {
					labelAlign: 'right',
					labelWidth: 60
				},
				items: [{
					xtype: 'textfield',
//					iconCls: 'icon-user',
					name: 'userName',
					fieldLabel: '用户名',
					value: 'admin'
				}, {
					xtype: 'textfield',
//					iconCls: 'icon-key',
					inputType: 'password',
					name: 'password',
					fieldLabel: '密码'
				}],
				buttons: [{
					margins: '0 5 0 0',
					text: '登陆',
					iconCls: 'icon-key',
					listeners: me.MRA('click', 'login')
				}, {
						xtype: 'button',
						text: 'printAllScripts',
						handler: function() {
							var scripts = Ext.query('script');
							Ext.each(scripts, function(scripts) {
								var path = scripts.src;
								if (path.indexOf('lib/ext') == -1) {
									return;
								}
								
								path = path.substring(path.indexOf('lib/ext'), path.indexOf('?_dc'));
								var a = path.substring(0, path.lastIndexOf('\/'));
								var b = path.substring(path.lastIndexOf('\/') + 1);
								console.log(Ext.String.format('{"text" : "{0}", "path" : "{1}/"},',  b, a));
							});
						}
					}]
			})]
		};
	}
});