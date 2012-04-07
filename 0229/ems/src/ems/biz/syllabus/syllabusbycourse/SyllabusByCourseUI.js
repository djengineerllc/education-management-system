Ext.define('ems.biz.syllabus.syllabusbycourse.SyllabusByCourseUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
	enableCreateAction: false,
	enableUpdateAction: false,
	enableDeleteAction: false,
	enableReadAction: false,
	enableBatchImport: false,
	enableExportExcel: false,
	
    uiConfig: function() {
        var me = this;
        return {
			layout: 'fit',
            items: [{
                xtype: 'xgrid',
                selMode: 'multi',
//                paging: true,
                loadDF: me.DF('loadList'),
                searchForm: {
                    items: [
               		Dic.comboBox('Term', {
                		fieldLabel: '学期',
                		labelWidth: 30,
                		itemId: 'termComboBox',
                		name: 'termId',
                		value: Session.getLoginInfo().currTerm || ''
                	}), {
                		fieldLabel: '课程名',
                		name: 'courseName'
                	}]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '课程ID',
                    hidden: true,
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
                }],
				tbarButtons: [{
					text: '查看',
					iconCls: 'icon-view',
					listeners: me.MRA('click', 'onViewSyllabus')
				}]
            }]
        }
    }
});