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
               		Dic.comboBox('Team', {
                		fieldLabel: '学期',
                		labelWidth: 30,
                		itemId: 'termComboBox',
                		name: 'term',
                		valueKey: '2012B'
                	}), {
						xtype: 'fieldcontainer',
		                fieldLabel: '班级',
		                combineErrors: true,
		                layout: {
							type: 'hbox',
							align: 'middle'
						},
		                defaults: {
		                    hideLabel: true
		                },
						width: 290,
		                items: [
						Dic.comboBox('Grade', {
	                        fieldLabel: '年级',
	                        name: 'stuGrade',
							hideLabel: true,
							emptyText: '年级',
							value: '',
							listeners: {
								change: function(comp, newValue, oldValue, eOpts) {
									var data = newValue ? Ems.syncDirectRequest('ems.system.System', 'getDicData', [{type: 'Class', group: newValue}]).result : [],
										stuClassCombo = me.down('#stuClassId');
										
									stuClassCombo.setValue(null);
									stuClassCombo.emptyText = data.length > 0 ? '班级' : '请选择年级';
									stuClassCombo.applyEmptyText();
									stuClassCombo.store.loadData(data);
								}
							},
							width: 80
						}), 
						Dic.localComboBox({
							itemId: 'stuClassId',
	                        name: 'stuClass',
							emptyText: '请选择年级',
							width: 100
						})]
					}]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '班级ID',
                    hidden: true,
                    flex: 1
                },{
                    dataIndex: 'gradeName',
                    text: '年级',
                    flex: 1
                },{
                    dataIndex: 'className',
                    text: '班级名',
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