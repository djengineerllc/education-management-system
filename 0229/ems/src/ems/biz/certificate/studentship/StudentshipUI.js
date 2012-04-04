Ext.define('ems.biz.certificate.studentship.StudentshipUI', {
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
                    items: [{
						xtype: 'fieldcontainer',
		                fieldLabel: '班级',
		                labelCls: Ext.baseCSSPrefix + 'form-item-label field-container-label',
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
                    dataIndex: 'stuGrade',
                    text: '年级',
                    renderer: Dic.renderer('Grade'),
                    flex: 1
                }, {
                    dataIndex: 'stuClass',
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
                    dataIndex: 'stuSex',
                    text: '性别',
                    renderer: Dic.renderer('Sex'),
                    flex: 1
                }, {
                    dataIndex: 'stuProj',
                    text: '项目',
                    renderer: Dic.renderer('Project'),
                    flex: 1
                }, {
                    dataIndex: 'stuProf',
                    text: '专业',
                    renderer: Dic.renderer('Professional'),
                    flex: 1
                }, {
                    dataIndex: 'stuStatus',
                    text: '状态',
                    renderer: Dic.renderer('StudentStatus'),
                    flex: 1
                }],
				tbarButtons: [{
					text: '打印证明',
					iconCls: 'icon-stamp',
					listeners: me.MRA('click', 'onPrintCert')
				}]
            }]
        }
    }
});