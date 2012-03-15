Ext.define('ems.biz.basicInfo.termManager.TermUI', {
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
//				title: '学期信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadTerm'),
                searchForm: {
                    items: [{
                        fieldLabel: '学期名',
                        name: 'termName'
                    }]
                },
                columns: [{
                    dataIndex: 'termId',
                    text: '学期ID',
                    flex: 1
                }, {
                    dataIndex: 'termName',
                    text: '学期名',
                    flex: 1
                }]
            }]
        }
    }
})