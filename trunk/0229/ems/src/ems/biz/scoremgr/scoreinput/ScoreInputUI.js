Ext.define('ems.biz.scoremgr.scoreinput.ScoreInputUI', {
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
//                paging: true,
                loadDF: me.DF('loadList'),
                searchForm: {
                    items: [{
						xtype: 'fieldcontainer',
						labelCls: Ext.baseCSSPrefix + 'form-item-label field-container-label',
		                fieldLabel: '班级',
		                combineErrors: true,
		                layout: {
							type: 'hbox',
							align: 'middle'
						},
		                defaults: {
		                    hideLabel: true
		                },
						width: 300,
		                items: [
						Dic.comboBox('Grade', {
	                        fieldLabel: '年级',
	                        name: 'stuGrade',
							hideLabel: true,
							emptyText: '年级',
							value: '',
							listeners: {
								change: function(comp, newValue, oldValue, eOpts) {
									var data = newValue ? Dic.getDicData({type: 'Class', group: newValue}) : [],
										stuClassCombo = me.down('#classComboBox');
										
									stuClassCombo.setValue(null);
									stuClassCombo.emptyText = data.length > 0 ? '班级' : '请选择年级';
									stuClassCombo.applyEmptyText();
									stuClassCombo.store.loadData(data);
								}
							},
							width: 80
						}), 
						Dic.localComboBox({
							itemId: 'classComboBox',
	                        name: 'stuClass',
							emptyText: '请选择年级',
							width: 150
						})]
					}, {
						fieldLabel: '学号',
                        name: 'stuNo',
						labelWidth: 60
					}]
                },
                columns: [{
                	dataIndex: 'id',
                    text: 'ID',
                    hidden: true
                }, {
                    dataIndex: 'gradeId',
                    text: '年级',
                    renderer: Dic.renderer('Grade'),
                    flex: 1
                }, {
                    dataIndex: 'classId',
                    text: '班级',
                    renderer: Dic.renderer('Class'),
                    flex: 1
                }, {
                    dataIndex: 'stuNo',
                    text: '学号',
                    flex: 1
                }, {
                    dataIndex: 'stuName',
                    text: '姓名',
                    flex: 1
                }, {
                    dataIndex: 'sex',
                    text: '性别',
                    renderer: Dic.renderer('Sex'),
                    flex: 1
                }, {
                    dataIndex: 'projectId',
                    text: '项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                }, {
                    dataIndex: 'professId',
                    text: '专业',
                    renderer: Dic.renderer('Professional'),
                    flex: 1
                }, {
                    dataIndex: 'status',
                    text: '状态',
                    renderer: Dic.renderer('StudentStatus'),
                    flex: 1
                }],
				tbarButtons: [{
					text: '成绩录入',
					iconCls: 'icon-edit',
					listeners: me.MRA('click', 'onScoreEdit')
				}]
            }]
        }
    }
});