Ext.define('ems.biz.stuMag.addNewStu.view.StuEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'fit',
			items: [{
				xtype: 'form',
				layout: 'column',
				border: false,
				bodyPadding: 10,
				paramOrder: ['id'],
				fieldDefaults: {
					labelWidth: 60,
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
	                name: 'userName',
	                readOnly: true,
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
	                name: 'loginName',
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
	                name: 'admissionTime',
	                columnWidth: 0.5
	            },{
	            	xtype: 'datefield',
	                fieldLabel: '离校时间',
	                name: 'admissionTime',
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
					name: 'admissionQualif'
				}),{
	                fieldLabel: '毕业学校',
	                name: 'graduateSchool'
	            },{
	                fieldLabel: '专业',
	                name: 'profession'
	            },{
	                fieldLabel: '毕业年份',
	                name: 'graduateYear'
	            },{
	                fieldLabel: '高考省份',
	                name: 'gkProvince'
	            },{
	                fieldLabel: '高考类别',
	                name: 'gkType'
	            },{
	                fieldLabel: '高考总分',
	                name: 'gkScore'
	            },{
	                fieldLabel: '英语单科',
	                name: 'gkEnglishScore'
	            },{
	                fieldLabel: '高考年份',
	                name: 'gkYear'
	            },{
	                fieldLabel: '父亲姓名',
	                name: 'fatherName'
	            },{
	                fieldLabel: '父亲工作单位',
	                name: 'fatherWorkUnit'
	            },{
	                fieldLabel: '父亲职位',
	                name: 'fatherPost'
	            },{
	                fieldLabel: '父亲联系电话',
	                name: 'fatherContactTel'
	            },{
	                fieldLabel: '母亲姓名',
	                name: 'motherName'
	            },{
	                fieldLabel: '母亲工作单位',
	                name: 'motherWorkUnit'
	            },{
	                fieldLabel: '母亲职位',
	                name: 'motherPost'
	            },{
	                fieldLabel: '母亲联系电话',
	                name: 'motherContactTel'
	            }]
			}]
		};
	}
});