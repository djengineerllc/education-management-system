Ext.define('ems.biz.syllabus.syllabusplan.SyllabusPlanUI', {
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
                    		itemId: 'termComboBox',
                    		name: 'term',
                    		valueKey: '2012B'
                    	})
                   ]
                },
                columns: [{
                    dataIndex: 'teamName',
                    text: '学期名称',
                    flex: 1
                },{
                    dataIndex: 'syllabusState',
                    text: '是否排课',
                    flex: 1,
                    renderer: Dic.renderer('Indicator')
                }],
				tbarButtons: [{
					text: '手动排课',
					iconCls: 'icon-plan',
					listeners: me.MRA('click', 'onNormalPlan')
				}]
            }]
        }
    }
});