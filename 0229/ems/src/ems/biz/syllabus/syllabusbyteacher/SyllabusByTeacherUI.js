Ext.define('ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacherUI', {
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
               		Dic.comboBox('Term', {
                		fieldLabel: '学期',
                		labelWidth: 30,
                		itemId: 'termComboBox',
                		name: 'term',
                		value: Session.getLoginInfo().currTerm || ''
                	}), {
                		fieldLabel: '教师名',
                		name: 'teacherName'
                	}]
                },
                columns: [{
                	dataIndex: 'teacherName',
                    text: '教师名',
                    flex: 1
                }, {
                	dataIndex: 'teacherTel',
                    text: '电话',
                    flex: 1
                }, {
                	dataIndex: 'teacherEmail',
                    text: 'E-Mail',
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