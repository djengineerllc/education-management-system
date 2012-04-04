Ext.define('ems.biz.syllabus.syllabusbycourse.SyllabusByCourseUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
	enableCreateAction: false,
	enableUpdateAction: false,
	enableDeleteAction: false,
	enableReadAction: false,
	enableBatchImport: false,
	enablePrint: false,
	enableExportExcel: false,
	
    uiConfig: function() {
        var me = this;
        return {
			layout: 'fit',
            items: [{
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadList'),
                searchForm: {
                    items: [
               		Dic.comboBox('Team', {
                		fieldLabel: '学期',
                		labelWidth: 30,
                		itemId: 'termComboBox',
                		name: 'term',
                		valueKey: '2012B'
                	}), {
                		fieldLabel: '课程名',
                		name: 'subjectName'
                	}]
                },
                columns: [{
                    dataIndex: 'subjectId',
                    text: '课程ID',
                    hidden: true,
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