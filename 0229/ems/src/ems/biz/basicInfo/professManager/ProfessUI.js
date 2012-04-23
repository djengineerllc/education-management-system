Ext.define('ems.biz.basicInfo.professManager.ProfessUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
//	enableCreateAction: true,
//	enableUpdateAction: true,
//	enableDeleteAction: true,
//	enableReadAction: true,
    
    uiConfig: function(){
        var me = this;
        return {
			layout: 'fit',
            items: [{
//				title: '专业信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadProfess'),
                searchForm: {
                    items: [Dic.comboBox('Project', {
					fieldLabel: '项目名',
					name: 'projectId',
					headerOption: true
				}),{
                        fieldLabel: '专业名',
                        name: 'professName'
                    }]
                },
                columns: [{
                    dataIndex: 'projectId',
                    text: '项目ID',
                    flex: 1
                }, {
                    dataIndex: 'projectName',
                    text: '项目名',
                    flex: 2
                },{
                    dataIndex: 'id',
                    text: '专业ID',
                    flex: 1
                },{
                    dataIndex: 'professName',
                    text: '专业中文名',
                    flex: 2
                },{
                    dataIndex: 'professNameEn',
                    text: '专业英文名',
                    flex: 2
                }]
            }]
        }
    }
})