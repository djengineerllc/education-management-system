Ext.define('ems.biz.stuMag.admission.AdmissionUI', {
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
		            }]
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
                    dataIndex: 'admissionProjectId',
                    text: '录取项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                },{
                    dataIndex: 'admissionProfessId',
                    text: '录取专业',
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