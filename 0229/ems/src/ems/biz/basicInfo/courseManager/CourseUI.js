Ext.define('ems.biz.basicInfo.courseManager.CourseUI', {
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
                loadDF: me.DF('loadCourse'),
                searchForm: {
                    items: [{
                        fieldLabel: '课程中文名',
                        name: 'courseName',
                        labelWidth: 80
                    },{
                        fieldLabel: '课程英文名',
                        name: 'courseEngName',
                        labelWidth: 80
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '课程ID',
                    flex: 1
                }, {
                    dataIndex: 'courseNo',
                    text: '课程编号',
                    flex: 1
                }, {
                    dataIndex: 'courseName',
                    text: '课程中文名',
                    flex: 3
                },{
                    dataIndex: 'courseEngName',
                    text: '课程英文名',
                    flex: 3
                },{
                    dataIndex: 'courseScore',
                    text: '学分',
                    flex: 1
                },{
                    dataIndex: 'courseTime',
                    text: '课时',
                    flex: 1
                }]
            }]
        }
    }
})