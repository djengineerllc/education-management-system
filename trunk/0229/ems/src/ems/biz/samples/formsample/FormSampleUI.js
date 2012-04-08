Ext.define('ems.biz.samples.formsample.FormSampleUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function(){
        var me = this;
        return {
			items: {
				xtype: 'form',
				itemId: 'formPanelSample',
				border: false,
				bodyPadding: 10,
				api: {
					load: me.DF('getFormData'),
					submit: me.DF('submitFormData')
				},
				paramOrder: ['userId'],
				fieldDefaults: {
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%'
				},
				items: [{
	                fieldLabel: '姓名',
	                name: 'userName'
	            },
				Dic.checkboxGroup('Sex', {
					fieldLabel: '性别',
					groupName: 'sexcheckboxgroup',
					valueKey: 'S1'
				}),
				Dic.radioGroup('Sex', {
					fieldLabel: '性别2',
					groupName: 'sex'
				}),
				{
					xtype: 'datefield',
	                fieldLabel: '生日',
	                name: 'birthday'
	            }, {
	                fieldLabel: '电子邮件',
	                name: 'email'
	            }, 
            	Dic.comboBox('SysYear', {
					fieldLabel: '系统年',
                    name: 'sysYear'
				})
	            ],
				buttons: [{
					text: '加载数据',
					handler: function() {
						this.up('form').getForm().load({
							params: {
								userId: 'chiknin'
							}
						})
					}
				}, {
					text: '提交数据',
					handler: function() {
						this.up('form').getForm().submit();
					}
				}]
			}
        };
    }
});