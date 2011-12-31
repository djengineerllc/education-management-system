Ext.define('ems.biz.applyonline.apply.ApplyUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function(){
        var me = this;
        
        var applyInfoForm = Ext.createWidget('form', {
            itemId: '_form',
            xtype: 'form',
			border: false,
            title: '网上报名',
            bodyPadding: 10,
            api: {
                submit: me.DF('saveApplyOnlineInfo')
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
                    applyInfoForm.getForm().submit({
                         success: function(form, action) {
       							Ext.MessageBox.alert("Success","操作成功!");
    						},
					    failure: function(form, action) {
					        switch (action.failureType) {
					            case Ext.form.action.Action.CLIENT_INVALID:
					                Ext.Msg.alert('Failure', 'Form fields may not be submitted with invalid values');
					                break;
					            case Ext.form.action.Action.CONNECT_FAILURE:
					                Ext.Msg.alert('Failure', 'Ajax communication failed');
					                break;
					            case Ext.form.action.Action.SERVER_INVALID:
					               Ext.Msg.alert('Failure', action.result.msg);
					       }
    }
                    });
                }
            }],
            items: [{
            	xtype:'fieldset',
				title: '项目/专业选择 (Program Details)',
				columnWidth: 1,
				layout: 'column',
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.5
				},
				items:[
				Dic.comboBox('project', {
					fieldLabel: '首选项目',
					name: 'projectName'
				}), {
                fieldLabel: '专业',
                name: 'zyName'
            }, Dic.comboBox('project', {
					fieldLabel: '次选项目',
					name: 'projectNameSec'
				}), {
                fieldLabel: '专业',
                name: 'zyNameSec'
            }]}, {
            	xtype:'fieldset',
				title: '个人信息 (Personal Information)',
				columnWidth: 1,
				layout: 'column',
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.25
				},
				items:[
				{
                fieldLabel: '姓名',
                name: 'stuName'
            	}, Dic.comboBox('Sex', {
					fieldLabel: '性别',
					name: 'stuSex'
				}), {
                fieldLabel: '身份证号',
                name: 'stuIDNo',
                columnWidth: 0.5
            }, {
                xtype: 'datefield',
                format: 'Y-m-d',
                fieldLabel: '出生日期',
                name: 'stuBirthday'
            }, {
                fieldLabel: '民族',
                name: 'stuMz'
            }, {
                fieldLabel: '户籍所在地',
                name: 'stuProvice',
                columnWidth: 0.5
            }, {
                fieldLabel: '联系(邮寄)地址',
                name: 'stuAddress',
                columnWidth: 0.75
            }, {
                fieldLabel: '邮编',
                name: 'stuZip'
            }, {
                fieldLabel: '家庭地址',
                name: 'familyAddress',
                columnWidth: 0.75
            }, {
                fieldLabel: '邮编',
                name: 'familyZip'
            },{
                fieldLabel: '联系人',
                name: 'contactPerson'
            }, {
                fieldLabel: '手机',
                name: 'mobile'
            },{
                fieldLabel: '固定电话',
                name: 'phone'
            }, {
                fieldLabel: '电子邮箱',
                name: 'email'
            },Dic.comboBox('indicator', {
					fieldLabel: '有被拒签的经历',
					name: 'isReject'
				}), {
					fieldLabel: '拒签情况',
					name: 'rejectType'
            }
            ]},{
            	xtype:'fieldset',
				title: '学习经历 (Education Information)',
				columnWidth: 1,
				layout: 'column',
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.25
				},
				items:[
				{
                fieldLabel: '毕业学校',
                name: 'graduateSchool',
                columnWidth: 0.5
            	},{
                fieldLabel: '专业',
                name: 'graduateZy'
            	}, {
            		xtype: 'datefield',
            		format: 'Y-m-d',
                	fieldLabel: '毕业时间',
                	name: 'graduateTime'
            }, Dic.comboBox('education_level', {
					fieldLabel: '目前学历',
					name: 'education',
					columnWidth: 0.25
				}),{
                fieldLabel: '英语水平 (如IELTS,TOEFL,CET等)',
                name: 'englishLevel',
                columnWidth: 0.75
            }, {
                fieldLabel: '高考总分',
                name: 'gkTotal',
                columnWidth: 0.5
            }, {
            	xtype: 'datefield',
            	format: 'Y-m-d',
                fieldLabel: '高考年份',
                name: 'gkYear'
            }, {
                fieldLabel: '高考省份',
                name: 'gkProvice'
            }, Dic.comboBox('gk_type', {
					fieldLabel: '高考类别',
					name: 'gkType',
					columnWidth: 0.2
				}), {
                fieldLabel: '考生号',
                name: 'gkStuNo',
                columnWidth: 0.4
            },{
                fieldLabel: '准考证号',
                name: 'gkNo',
                columnWidth: 0.4
            }
            ]},{
            	xtype:'fieldset',
				title: '家庭情况 (Family Information)',
				columnWidth: 1,
				layout: 'column',
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.25
				},
				items:[
				{
                fieldLabel: '父亲姓名',
                name: 'fatherName',
                columnWidth: 0.25
            	},{
                fieldLabel: '父亲工作单位',
                name: 'fatherWork',
                columnWidth: 0.25
            	},{
                fieldLabel: '父亲职务',
                name: 'fatherWorkPosition',
                columnWidth: 0.25
            	},{
                fieldLabel: '父亲联系电话',
                name: 'fatherTel',
                columnWidth: 0.25
            	},{
                fieldLabel: '母亲姓名',
                name: 'motherName',
                columnWidth: 0.25
            	},{
                fieldLabel: '母亲工作单位',
                name: 'motherWork',
                columnWidth: 0.25
            	},{
                fieldLabel: '母亲职务',
                name: 'motherWorkPosition',
                columnWidth: 0.25
            	},{
                fieldLabel: '母亲联系电话',
                name: 'motherTel',
                columnWidth: 0.25
            	}
            ]}
            ]
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
