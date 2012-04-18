Ext.define('ems.biz.basicInfo.educatManager.EducatUI', {
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
                loadDF: me.DF('loadEducat'),
                searchForm: {
                	fieldColumns:4,
                    items: [Dic.comboBox('Teacher', {
						fieldLabel: '教师',
						name: 'teacherId',
					headerOption: true
					}),Dic.comboBox('Class', {
						fieldLabel: '班级',
						name: 'classId',
					headerOption: true
					}),Dic.comboBox('Term', {
						fieldLabel: '学期',
						name: 'termId',
					headerOption: true
					}),Dic.comboBox('Course', {
						fieldLabel: '课程',
						name: 'courseNo',
					headerOption: true
					})]
                },
                columns: [{
                    dataIndex: 'teacherName',
                    text: '任课教师',
                    flex: 1
                },{
                    dataIndex: 'courseNo',
                    text: '课程编号',
                    flex: 1
                },{
                    dataIndex: 'courseName',
                    text: '课程名称',
                    flex: 1
                },{
                    dataIndex: 'className',
                    text: '班级',
                    flex: 1
                },{
                    dataIndex: 'termName',
                    text: '学期',
                    flex: 1
                }]
            }]
        }
    }
})