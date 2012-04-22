Ext.define('ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacherUI', {
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
                		fieldLabel: '教师名',
                		name: 'teacherName'
                	}]
                },
                columns: [{
                	dataIndex: 'id',
                    text: '教师ID',
                    flex: 1
                }, {
                	dataIndex: 'userName',
                    text: '教师名',
                    flex: 1
                }, {
                	dataIndex: 'contact',
                    text: '联系电话',
                    flex: 1
                }, {
                	dataIndex: 'email',
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