Ext.define('ems.biz.test.view.ViewOne', {
    extend: 'ems.core.UI',
	
	constructor: function() {
		this.callParent(arguments);
	},
	
	destroy: function() {
		this.callParent(arguments);
	},
	
	uiConfig: function() {
		var me = this;
		return {
			items: [{
				xtype: 'form',
				// configs for FormPanel
				title: 'Basic Information',
				border: false,
				bodyPadding: 10,
				// configs for BasicForm
				api: {
					// The server-side method to call for load() requests
					load: me.DF('getBasicInfo'),
					// The server-side must mark the submit handler as a 'formHandler'
					submit: me.DF('updateBasicInfo'), //TestAction.updateBasicInfo
				},
				// specify the order for the passed params
				
				paramOrder: ['uid', 'foo'],
				//			paramsAsHash: true,
				dockedItems: [{
					dock: 'bottom',
					xtype: 'toolbar',
					ui: 'footer',
					style: 'margin: 0 5px 5px 0;',
					items: ['->', {
						text: 'Submit',
						handler: function(){
							basicInfo.getForm().submit({
								params: {
									foo: 'bar',
									uid: 34
								}
							});
						}
					}, {
						text: 'LOAD',
						handler: function(){
							this.up().up().getForm().load({
								// pass 2 arguments to server side getBasicInfo method (len=2)
								params: {
									foo: 'bar',
									uid: 34
								}
							});
						}
					}]
				}],
				defaultType: 'textfield',
				defaults: {
					anchor: '100%'
				},
				items: [{
					fieldLabel: 'Name',
					name: 'name'
				}, {
					fieldLabel: 'Email',
					msgTarget: 'side',
					name: 'email'
				}, {
					fieldLabel: 'Company',
					name: 'company'
				}]
			}]
		}
	}
});