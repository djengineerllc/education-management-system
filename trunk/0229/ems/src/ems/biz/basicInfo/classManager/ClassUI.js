Ext.define('ems.biz.basicInfo.classManager.ClassUI', {
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
//				title: '班级信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadClass'),
                searchForm: {
                    items: [{
                        fieldLabel: '班级名',
                        name: 'className'
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '班级ID',
                    flex: 1
                }, {
                    dataIndex: 'className',
                    text: '班级名',
                    flex: 1
                },{
                    dataIndex: 'gradeName',
                    text: '年级名',
                    flex: 1
                }]
            }]
        }
    }
})