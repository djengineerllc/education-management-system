Ext.define('ems.biz.basicInfo.projectManager.ProjectUI', {
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
//				title: '项目信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadProject'),
                searchForm: {
                    items: [{
                        fieldLabel: '项目名',
                        name: 'projectName'
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '项目ID',
                    flex: 1
                }, {
                    dataIndex: 'projectName',
                    text: '项目名',
                    flex: 1
                }]
            }]
        }
    }
})