Ext.define('ems.biz.stuMag.regist.RegistUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
    
    uiConfig: function(){
        var me = this;
        return {
			layout: 'fit',
            items: [{
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadRegisDisVO'),
                searchForm: {
                	fieldColumns:3,
                    items: [{
                        fieldLabel: '学号',
                        name: 'stuNo'
                    },{
                        fieldLabel: '姓名',
                        name: 'userName'
                    },Dic.comboBox('Grade', {
						fieldLabel: '年级',
						name: 'gradeId',
						headerOption: true
					}),Dic.comboBox('Project', {
						fieldLabel: '项目',
						name: 'projectId',
						headerOption: true
					}),Dic.comboBox('Professional', {
						fieldLabel: '专业',
						name: 'professId',
						headerOption: true
					}),Dic.comboBox('Class', {
						fieldLabel: '班级',
						name: 'classId',
						headerOption: true
					}),Dic.comboBox('Indicator', {
						fieldLabel: '是否报到',
						name: 'checkInFlag',
						headerOption: true
					}),Dic.comboBox('Indicator', {
						fieldLabel: '是否完费',
						name: 'endFeeFlag',
						headerOption: true
					}),Dic.comboBox('Indicator', {
						fieldLabel: '是否注册',
						name: 'registrationFlag',
						headerOption: true
					})]
                },
                columns: [{
                    dataIndex: 'stuNo',
                    text: '学号',
                    flex: 1
                }, {
                    dataIndex: 'userName',
                    text: '姓名',
                    flex: 1
                }, {
                    dataIndex: 'sex',
                    text: '性别',
                    renderer: Dic.renderer('Sex'),
                    flex: 1
                } ,{
                    dataIndex: 'gradeId',
                    text: '年级',
                    renderer: Dic.renderer('Grade'),
                    flex: 1
                }, {
                    dataIndex: 'projectId',
                    text: '项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                },{
                    dataIndex: 'professId',
                    text: '专业',
                    renderer: Dic.renderer('Professional'),
                    flex: 1
                },{
                    dataIndex: 'classId',
                    text: '班级',
                    renderer: Dic.renderer('Class'),
                    flex: 1
                },{
                    dataIndex: 'checkInFlag',
                    text: '是否报到',
                    renderer: Dic.renderer('Indicator'),
                    flex: 1
                },{
                    dataIndex: 'endFeeFlag',
                    text: '是否完费',
                    renderer: Dic.renderer('Indicator'),
                    flex: 1
                },{
                    dataIndex: 'registrationFlag',
                    text: '是否注册',
                    renderer: Dic.renderer('Indicator'),
                    flex: 1
                },{
                    dataIndex: 'status',
                    text: '状态',
                    renderer: Dic.renderer('StudentStatus'),
                    flex: 1
                }]
            }]
        }
    }
})