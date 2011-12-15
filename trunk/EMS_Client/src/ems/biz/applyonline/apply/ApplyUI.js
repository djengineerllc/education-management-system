Ext.define('ems.biz.applyonline.apply.ApplyUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function(){
        var me = this;
        
        var applyInfoForm = Ext.createWidget('form', {
            itemId: '_form',
            xtype: 'form',
			border: false,
            title: 'applyonline Information',
            bodyPadding: 10,
            api: {
//                submit: me.DF('submit') //TestAction.updateBasicInfo
            },
//			layout: {
//				type: 'table',
//				columns: 4
//			},
			layout: 'column',
			fieldDefaults: {
				labelAlign: 'right'
//				,
//				labelWidth: 100,
//	            msgTarget: 'side'
			},
			defaults: {
				xtype: 'textfield',
				columnWidth: 0.25
			},
			buttons: ['->', {
                text: 'Submit',
                handler: function(){
                    applyInfo.getForm().submit({
                        params: {
                            foo: 'bar',
                            uid: 34
                        }
                    });
                }
            }],
            items: [{
                fieldLabel: '首选项目',
                name: 'projectName'
            }, {
                fieldLabel: '专业',
                name: 'zyName',
				columnWidth: 0.5
            }, {
                fieldLabel: '次选项目',
                name: 'projectNameSec'
            }, {
                fieldLabel: '专业',
                name: 'zyNameSec'
            }, {
                fieldLabel: '姓名',
                name: 'stuName'
            }, {
                fieldLabel: '性别',
                name: 'stuSex'
            }, {
                fieldLabel: '身份证号',
                name: 'stuIDNo'
            }, {
                xtype: 'datefield',
                fieldLabel: '出生日期',
                name: 'stuBirthday'
            }, {
                fieldLabel: '民族',
                name: 'stumz'
            }, {
                fieldLabel: '户籍所在地',
                name: 'stuProvice'
            }, {
                fieldLabel: '联系(邮寄)地址',
                name: 'stuAddress'
            }, {
                fieldLabel: '邮编',
                name: 'stuZip'
            }, {
                fieldLabel: '家庭地址 (与联系地址不一致时填写)',
                name: 'addressFamily'
            }, {
                fieldLabel: '邮编',
                name: 'familyZip'
            }, {
				xtype:'fieldset',
				title: '字段集',
				columnWidth: 1,
				layout: 'column',
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.5
				},
				items: [{
	                fieldLabel: '联系人',
	                name: 'contract'
	            }, {
	                fieldLabel: '手机',
	                name: 'mobile'
	            }, {
	                fieldLabel: '固定电话',
	                name: 'phone'
	            }, {
	                fieldLabel: '电子邮箱',
	                name: 'email'
	            }]
			}]
        });
        
        return {
            border: 0,
            anchor: '100% 100%',
			align: 'center',
            items: [applyInfoForm]
        };
    },
    
    initComponent: function(){
        this.callParent(arguments);
    }
});
