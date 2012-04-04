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
                selMode: 'single',
//                paging: true,
                loadDF: me.DF('loadList'),
                storeConfig: {
                	autoLoad: true
                },
//                searchForm: {
//                    items: [
//                    	Dic.comboBox('Team', {
//                    		fieldLabel: '学期',
//                    		labelWidth: 30,
//                    		itemId: 'termComboBox',
//                    		name: 'term',
//                    		valueKey: '2012B'
//                    	})
//                   ]
//                },
                columns: [{
                    dataIndex: 'id',
                    text: '学期ID',
                    hidden: true,
                    flex: 1
                }, {
                    dataIndex: 'termName',
                    text: '学期名',
                    flex: 1
                }, {
                    dataIndex: 'isCurrentTerm',
                    text: '是否当前学期',
                    renderer: Dic.renderer('Indicator'),
                    flex: 1
                }, {
                    dataIndex: 'syllabusStatus',
                    text: '是否已排课',
                    renderer: Dic.renderer('Indicator'),
                    flex: 1
                }],
				tbarButtons: [{
					text: '手动排课',
					iconCls: 'icon-plan',
					listeners: me.MRA('click', 'onNormalPlan')
				}, {
					text: '查看一览表',
					iconCls: 'icon-view',
					listeners: me.MRA('click', 'onViewSyllabusPlan')
				}]
            }]
        }
    }
});