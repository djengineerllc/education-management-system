Ext.define('ems.biz.applyonline.query.QueryUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
	requires: [
		'ems.core.widget.XGrid'
	],
	isListUI: true,
    uiConfig: function(){
        var me = this;
		return {
			layout: 'fit',
			items: [
			{
			xtype: 'xgrid',
			selMode: 'multi',
			paging: true,
			loadDF: me.DF('getStuInfo'),
			searchForm: {
				items: [{
		            fieldLabel: '学生姓名',
		            name: 'stuName'
		        }
				,
				Dic.comboBox('Sex', {
					fieldLabel: '性别',
					name: 'stuSex'
					,valueKey: 'S1'
				})
				, {
					xtype: 'datefield',
					format: 'Y',
		            fieldLabel: '报名年份',
		            name: 'applyTime'
		        }]
			},
			columns: [{
		        dataIndex: 'stuName',
		        text: '学生姓名',
				flex: 1
		    }
			, {
		        dataIndex: 'stuSex',
		        text: '性别',
				flex: 1
				, renderer: Dic.renderer('Sex')
		    },
		    {
				dataIndex: 'projectName',
		        text: '首选项目',
				flex: 1
			},
			{
				dataIndex: 'zyName',
		        text: '首选专业',
				flex: 1
			},
			{
				dataIndex: 'projectNameSec',
		        text: '次选项目',
				flex: 1
			},
			{
				dataIndex: 'zyNameSec',
		        text: '次选专业',
				flex: 1
			},
			{
				dataIndex: 'stuIDNo',
		        text: '身份证号码',
				flex: 1
			},
			{
				dataIndex: 'stuMz',
		        text: '民族',
				flex: 1
			}
			,{
				dataIndex: 'stuBirthday',
		        text: '生日',
				flex: 1
			}, {
				dataIndex: 'email',
		        text: '电子邮件',
				flex: 1
			}],
			buttonAlign: 'center',
			buttons: [{
				text: 'New',
				handler: me.onNew
			}, {
				text: 'Modify'
			}, {
				text: 'Delete'
			}]
		}]
		}
	}
});
