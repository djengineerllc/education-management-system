Ext.define('ems.biz.stuMag.applyinfo.view.ApplyInfoEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
	initData: function() {
		var me = this;
		me.callParent(arguments);
	},
	
	uiConfig: function() {
		var me = this;
		return {
//			layout: 'fit',
			width: 760,
			items: [{
				xtype: 'form',
				layout: 'column',
				border: false,
				bodyPadding: 10,
				paramOrder: ['id'],
				fieldDefaults: {
					labelWidth: 80,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					columnWidth: 0.25
				},
				items: [{
					xtype: 'hiddenfield',
					name: 'id'
				},{
	                fieldLabel: '报名编号',
	                name: 'appNum',
	                columnWidth: 1
	            },Dic.comboBox('Project', {
					fieldLabel: '首选项目',
					name: 'firstProjectId',
					columnWidth: 1/2
				}),Dic.comboBox('Professional', {
					fieldLabel: '专业',
					name: 'firstProfessId',
					columnWidth: 1/2
				}),Dic.comboBox('Project', {
					fieldLabel: '次选项目',
					name: 'secondProjectId',
					columnWidth: 1/2
				}),Dic.comboBox('Professional', {
					fieldLabel: '专业',
					name: 'secondProfessId',
					columnWidth: 1/2
				}),{
	                fieldLabel: '姓名',
	                name: 'name',
	                columnWidth: 1/4
	            },Dic.comboBox('Sex', {
					fieldLabel: '性别',
					name: 'sex',
					columnWidth: 1/4
				}),{
	                fieldLabel: '身份证',
	                name: 'idNum',
	                columnWidth: 1/2
	            },{
	            	xtype: 'datefield',
	                fieldLabel: '出生日期',
	                format: 'Y-m-d',
	                name: 'birthDate',
	                columnWidth: 1/4
	            },{
	                fieldLabel: '民族',
	                name: 'ethnic',
	                columnWidth: 1/4
	            },{
	                fieldLabel: '户籍所在地',
	                name: 'domicile',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '联系地址',
	                name: 'contactAddress',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '联系地址邮编',
	                name: 'contractAddZipCode',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '家庭地址 ',
	                name: 'homeAddress',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '家庭地址邮编',
	                name: 'homeAddZipCode',
	                columnWidth: 1/2
	            },Dic.comboBox('Indicator', {
					fieldLabel: '是否有被拒签的经历',
					name: 'refusedVisaFlag',
					labelWidth: 120,
					columnWidth: 1
				}),{
					xtype: 'displayfield',
					fieldLabel: '拒签情况',
					columnWidth: 1
				},Dic.comboBox('VisaType', {
					fieldLabel: '签证类型 ',
					name: 'visaType',
					columnWidth: 1/2
				}),Dic.comboBox('County', {
					fieldLabel: '国家',
					name: 'visaCounty',
					columnWidth: 1/2
				}),Dic.comboBox('SysYear', {
					fieldLabel: '时间',
					name: 'visaYear',
					columnWidth: 1/2
				}),{fieldLabel: '原因',
	                name: 'visaRefReason',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '毕业学校',
	                name: 'graduateSchool',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '专业',
	                name: 'graduateProfession',
	                columnWidth: 1/4
	            },Dic.comboBox('SysYear', {
					fieldLabel: '毕业时间',
					name: 'graduateYear',
					columnWidth: 1/4
				}),Dic.comboBox('EducationLevel', {
					fieldLabel: '目前学历: ',
					name: 'currentDegree',
					columnWidth: 1/2
				}),{
	                fieldLabel: '英语水平',
	                name: 'englishScore',
	                 columnWidth: 1/2
	            },{
	                fieldLabel: '高考省份',
	                name: 'gkProvince',
	                 columnWidth: 1/3
	            },Dic.comboBox('SysYear', {
					fieldLabel: '高考年份',
					name: 'gkYear',
					columnWidth: 1/3
				}),Dic.comboBox('GkType', {
					fieldLabel: '高考类别',
					name: 'gkType',
					columnWidth: 1/3
				}),{
	                fieldLabel: '高考总分',
	                name: 'gkTotalScore',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '高考英语',
	                name: 'gkEnglishScore',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '高考数学',
	                name: 'gkMathematicsScore',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '考生号',
	                name: 'candidates',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '准考证号',
	                name: 'ticketNum',
	                columnWidth: 1/2
	            },{
	                fieldLabel: '父亲姓名',
	                name: 'fatherName',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '父亲工作单位',
	                name: 'fatherWorkUnit',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '父亲职位',
	                name: 'fatherPost',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '父亲联系电话',
	                name: 'fatherContactTel',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '母亲姓名',
	                name: 'motherName',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '母亲工作单位',
	                name: 'motherWorkUnit',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '母亲职位',
	                name: 'motherPost',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '母亲联系电话',
	                name: 'motherContactTel',
	                columnWidth: 0.25
	            },{
	            	xtype:'displayfield',
	            	fieldLabel: '招生信息来源',
	            	columnWidth: 1
	            },Dic.checkboxGroup('AdmissionSourceType',{
	            	fieldLabel: '多选1',
					name: 'admissionsSource',
					groupName: 'admissionsSourceGroup',
					columnWidth: 1
	            }),Dic.checkboxGroup('AdmissionSourceType',{
	            	fieldLabel: '多选2',
					name: 'submitDataList',
					groupName: 'admissionTypeGroup',
					columnWidth: 1
	            })]
			}]
		};
	}
});