Ext.define('ems.biz.basicInfo.gradeManager.GradeUI', {
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
//				title: '年级信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadGrade'),
                searchForm: {
                    items: [{
                        fieldLabel: '年级名',
                        name: 'gradeName'
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '年级ID',
                    flex: 1
                }, {
                    dataIndex: 'gradeName',
                    text: '年级名',
                    flex: 1
                }]
            }]
        }
    }
})