Ext.define('ems.biz.stuMag.applyinfo.ApplyInfoUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
    
    uiConfig: function(){
        var me = this;
        return {
			//layout: 'fit',
            items: [{
            	layout: 'column',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadApplyInfos'),
                searchForm: {
                    items: [{
                        fieldLabel: '姓名',
                        name: 'name',
                        columnWidth: 1/3
                    },Dic.comboBox('Sex', {
						fieldLabel: '性别',
						name: 'sex',
						columnWidth: 1/3,
						headerOption: true
					}),Dic.comboBox('ApplyStatus', {
						fieldLabel: '录取状态',
						name: 'applyStatus',
						columnWidth: 1/3,
						headerOption: true
					}),{
		            	xtype: 'datefield',
		                fieldLabel: '开始时间',
		                format: 'Y-m-d',
		                name: 'startDate',
		                columnWidth: 1/2
		            },{
		            	xtype: 'datefield',
		                fieldLabel: '结束时间',
		                format: 'Y-m-d',
		                name: 'endDate',
		                columnWidth: 1/2
		            },Dic.comboBox('Project', {
						fieldLabel: '首选项目',
						name: 'firstProjectId',
						columnWidth: 1/4,
						headerOption: true
					}),Dic.comboBox('Professional', {
						fieldLabel: '专业',
						columnWidth: 1/4,
						name: 'firstProfessId',
						headerOption: true
					}),Dic.comboBox('Project', {
						fieldLabel: '次选项目',
						name: 'secondProjectId',
						columnWidth: 1/4,
						headerOption: true
					}),Dic.comboBox('Professional', {
						fieldLabel: '专业',
						name: 'secondProfessId',
						columnWidth: 1/4,
						headerOption: true
					})]
                },
                columns: [{
                    dataIndex: 'name',
                    text: '姓名',
                    flex: 1
                }, {
                    dataIndex: 'sex',
                    text: '性别',
                    renderer: Dic.renderer('Sex'),
                    flex: 1
                } ,{
                    dataIndex: 'firstProjectId',
                    text: '首选项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                },{
                    dataIndex: 'firstProfessId',
                    text: '专业',
                    renderer: Dic.renderer('Professional'),
                    flex: 1
                } ,{
                    dataIndex: 'secondProjectId',
                    text: '次选项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                },{
                    dataIndex: 'secondProfessId',
                    text: '专业',
                    renderer: Dic.renderer('Professional'),
                    flex: 1
                },{
                    dataIndex: 'applyStatus',
                    text: '录取状态',
                    renderer: Dic.renderer('ApplyStatus'),
                    flex: 1
                },{
                    dataIndex: 'applyDate',
                    text: '报名时间',
                    flex: 1
                }]
            }]
        }
    }
})