Ext.define('ems.biz.syllabus.syllabusbyclass.SyllabusByClassUI', {
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
                		name: 'term',
                		value: Session.getLoginInfo().currTerm || ''
                	}), 
                	Dic.comboBox('Grade', {
                		fieldLabel: '年级',
                		name: 'gradeId',
                		headerOption: true
                	}), {
                		fieldLabel: '班级名',
                		name: 'className'
                	}]
                },
                columns: [{
                    dataIndex: 'gradeId',
                    text: '年级',
                    renderer: Dic.renderer('Grade'),
                    flex: 1
                },{
                    dataIndex: 'id',
                    text: '班级名',
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