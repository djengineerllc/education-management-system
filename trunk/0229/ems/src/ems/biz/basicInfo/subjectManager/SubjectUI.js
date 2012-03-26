Ext.define('ems.biz.basicInfo.subjectManager.SubjectUI', {
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
//				title: '课程信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadSubject'),
                searchForm: {
                    items: [{
                        fieldLabel: '课程中文名',
                        name: 'subjectCnName'
                    },{
                        fieldLabel: '课程英文名',
                        name: 'subjectEnName'
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '课程ID',
                    flex: 1
                }, {
                    dataIndex: 'subjectNo',
                    text: '课程编号',
                    flex: 1
                }, {
                    dataIndex: 'subjectCnName',
                    text: '课程中文名',
                    flex: 1
                },{
                    dataIndex: 'subjectEnName',
                    text: '课程英文名',
                    flex: 1
                },{
                    dataIndex: 'subjectScore',
                    text: '学分',
                    flex: 1
                },{
                    dataIndex: 'subjectTime',
                    text: '课时',
                    flex: 1
                }]
            }]
        }
    }
})