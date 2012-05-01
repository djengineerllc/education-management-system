Ext.define('ems.biz.stuMag.regist.view.RegistEditUI', {
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
	                fieldLabel: '学号',
	                name: 'stuNo',
	                readOnly: me.bizAction == 'c' ? false : true,
	                columnWidth: 1/3
	            },{
	                fieldLabel: '密码',
	                name: 'password',
	                columnWidth: 1/3
	            },Dic.comboBox('StudentStatus', {
					fieldLabel: '状态',
					name: 'status',
					columnWidth: 1/3
				}),Dic.comboBox('Grade', {
					fieldLabel: '年级',
					name: 'gradeId',
					columnWidth: 0.25
				}),Dic.comboBox('Project', {
					fieldLabel: '项目',
					name: 'projectId',
					columnWidth: 0.25
				}),Dic.comboBox('Professional', {
					fieldLabel: '专业',
					name: 'professId',
					columnWidth: 0.25
				}),Dic.comboBox('Class', {
					fieldLabel: '班级',
					name: 'classId',
					columnWidth: 0.25
				}),{
	                fieldLabel: '姓名',
	                name: 'userName',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '拼音',
	                name: 'pinyin',
	                columnWidth: 0.25
	            },Dic.comboBox('Sex', {
					fieldLabel: '性别',
					name: 'sex',
					columnWidth: 0.25
				}),{
	                fieldLabel: '民族',
	                name: 'ethnic',
	                columnWidth: 0.25
	            },{
	            	xtype: 'datefield',
	                fieldLabel: '入学时间',
	                format: 'Y-m-d',
	                name: 'admissionTime',
	                columnWidth: 0.5
	            },{
	            	xtype: 'datefield',
	                fieldLabel: '离校时间',
	                format: 'Y-m-d',
	                name: 'leaveSchoolTime',
	                columnWidth: 0.5
	            },{
	                fieldLabel: '户籍所在地',
	                name: 'domicile',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '身份证',
	                name: 'IDNumber',
	                columnWidth: 1/3
	            },{
	            	xtype: 'datefield',
	                fieldLabel: '出生日期',
	                format: 'Y-m-d',
	                name: 'birthDate',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '学生联系电话',
	                name: 'contact',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '家庭固定电话',
	                name: 'homeFixTel',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '电子邮箱',
	                name: 'email',
	                columnWidth: 1/3
	            },{
	                fieldLabel: '联系地址',
	                name: 'contactAddress',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '联系地址邮编',
	                name: 'contractAddZipCode',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '家庭地址',
	                name: 'homeAddress',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '家庭地址邮编',
	                name: 'homeAddZipCode',
	                columnWidth: 0.25
	            },Dic.comboBox('EducationLevel', {
					fieldLabel: '入学学历',
					name: 'admissionQualif',
					columnWidth: 0.25
				}),{
	                fieldLabel: '毕业学校',
	                name: 'graduateSchool',
	                columnWidth: 0.25
	            },{
	                fieldLabel: '专业',
	                name: 'profession',
	                columnWidth: 0.25
	            },Dic.comboBox('SysYear', {
					fieldLabel: '毕业年份',
					name: 'graduateYear',
					columnWidth: 0.25
				}),{
	                fieldLabel: '高考省份',
	                name: 'gkProvince',
	                 columnWidth: 0.2
	            },Dic.comboBox('GkType', {
					fieldLabel: '高考类别',
					name: 'gkType',
					columnWidth: 0.2
				}),{
	                fieldLabel: '高考总分',
	                name: 'gkScore',
	                columnWidth: 0.2
	            },{
	                fieldLabel: '英语单科',
	                name: 'gkEnglishScore',
	                columnWidth: 0.2
	            },Dic.comboBox('SysYear', {
					fieldLabel: '高考年份',
					name: 'gkYear',
					columnWidth: 0.2
				}),{
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
	            }]
			}]
		};
	}
});