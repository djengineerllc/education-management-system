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
                	})]
                },
                columns: [{
                    dataIndex: 'stuGrade',
                    text: '班级',
                    renderer: Dic.renderer('Class'),
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