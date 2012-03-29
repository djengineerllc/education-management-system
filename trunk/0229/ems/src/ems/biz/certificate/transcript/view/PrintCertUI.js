Ext.define('ems.biz.certificate.transcript.view.PrintCertUI', {
	extend: 'ems.core.UI',
	
	initData: function() {
		var me = this,
			sltStudents = me.reqParams,
			title,
			termComboData = [],
			termComboBox = me.down('#termComboBox'),
			stuComboData = [],
			stuComboBox = me.down('#stuComboBox'),
			apprResultComboBox = me.down('#apprResultComboBox'),
			queryInfoForm = me.down('#queryInfoForm').getForm(),
			initData;
		
		/* 初始化store */
		Ext.each(sltStudents, function(sltStudent) {
			stuComboData.push({
				value: sltStudent.stuNo,
				name: Ext.String.format("{0}({1})", sltStudent.stuName, sltStudent.stuNo)
			});
		});
		stuComboBox.getStore().loadData(stuComboData); // 学生
		
		/* 初始化页面信息 */
		initData = sltStudents[0];
		title = Ext.String.format('成绩证明 - 年级: {0}  班级: {1}', 
			Dic.renderer('Grade')(initData.stuGrade),
			Dic.renderer('Class')(initData.stuClass)
		);
		me.up().setTitle(title); // 标题
		
		if (sltStudents.length <= 1) {
			var prevBtn = me.down('#prevBtn'),
				nextBtn = me.down('#nextBtn'),
				batchPrintBtn = me.down('#batchPrintBtn');
				
			prevBtn.hide();
			nextBtn.hide();
			batchPrintBtn.hide();
		}
		
		queryInfoForm.setValues({
			term: Session.getLoginInfo().currTerm || '',
			stuNo: initData.stuNo
		});
		
		termComboBox.on('change', me.updateCertInfo, me);
		apprResultComboBox.on('change', me.updateCertInfo, me);
	},
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'border',
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				height: 65,
				xtype: 'form',
				itemId: 'queryInfoForm',
				border: false,
				bodyPadding: 3,
				layout: {
					type: 'hbox',
					pack: 'start',
					align: 'middle'
				},
				fieldDefaults: {
					labelWidth: 35,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'displayfield',
					anchor: '100%',
					width: 100
				},
				items: [
					Dic.comboBox('Term', {
						fieldLabel: '学期',
						itemId: 'termComboBox',
                        name: 'term',
                        headerOption: true,
						width: 160
					}),
					Dic.localComboBox({
						fieldLabel: '学生',
						itemId: 'stuComboBox',
                        name: 'stuNo',
						width: 160,
						listeners: {
							change: {
								fn: me.updateCertInfo,
								scope: me
							}
						}
					}),
					Dic.comboBox('AchApprResult', {
						fieldLabel: '成绩评估结果',
						labelWidth: 80,
						itemId: 'apprResultComboBox',
                        name: 'apprResult',
                        headerOption: true,
						width: 160
					})
	            ],
				bbar: [{
					text: '上个学生',
					itemId: 'prevBtn',
					iconCls: 'icon-arrow-up',
					listeners: {
						click: {
							fn: function() {
								me.offsetStudent(-1)
							},
							scope: me
						}
					}
				},{
					text: '上下学生',
					itemId: 'nextBtn',
					iconCls: 'icon-arrow-down',
					listeners: {
						click: {
							fn: function() {
								me.offsetStudent(1)
							},
							scope: me
						}
					}
				}, {
					text: '打印',
					iconCls: 'icon-print',
					listeners: {
						click: {
							fn: me.execPrint,
							scope: me
						}
					}
				},'->',{
					text: '批量打印',
					itemId: 'batchPrintBtn',
					iconCls: 'icon-print',
					listeners: {
						click: {
							fn: me.execBatchPrint,
							scope: me
						}
					}
				}]
			}, {
				region: 'center',
				itemId: 'certContentPanel',
				margins: '2 2 2 2',
				autoScroll: true,
				border: false,
				style: {
					overflowY: 'auto'
				}
			}]
		}
	},
	
	updateCertInfo: function() {
		var me = this,
			stuComboBox = me.down('#stuComboBox'),
			stuComboSltIdx = stuComboBox.getSelectedIndex(),
			prevBtn = me.down('#prevBtn'),
			nextBtn = me.down('#nextBtn'),
			queryInfoForm = me.down('#queryInfoForm').getForm();
		
		if (stuComboBox.getStore().getCount() > 1) {
			prevBtn.setDisabled(stuComboSltIdx > 0 ? false : true);
			nextBtn.setDisabled(stuComboSltIdx < (stuComboBox.getStore().getCount() - 1) ? false : true);
		}
		
		me.down('#certContentPanel').el.load({
			url: Ems.getDirectStreamRequestUrl(me.moduleId, 'printCert'),
			loadMask: true,
			scripts: true,
			params: queryInfoForm.getValues()
		});
	},
	
	offsetStudent: function(offset) {
		var me = this,
			stuComboBox = me.down('#stuComboBox'),
			stuComboSltIdx = stuComboBox.getSelectedIndex(),
			r = stuComboBox.getStore().getRange(stuComboSltIdx + offset)[0];
		
		if (r) {
			stuComboBox.setValue(r.data[stuComboBox.valueField]);
		}	
	},
	
	execPrint: function() {
		var me = this,
			certContentPanel = me.down('#certContentPanel');
		EU.printHtml(certContentPanel.el.dom.innerHTML);
		
		return false;
	},
	
	execBatchPrint: function() {
		var me = this,
			sltStudents = me.reqParams,
			termComboBox = me.down('#termComboBox'),
			printer = EU.printer(sltStudents, function(sltStudent, addPageFn) {
				Ems.Ajax({
					url: Ems.getDirectStreamRequestUrl(me.moduleId, 'printCert'),
					async: false,
					params: {
						term: termComboBox.getValue(),
						stuNo: sltStudent.stuNo
					},
					success: function(response) {
						var text = response.responseText;
						Ext.callback(addPageFn, this, [text]);
					}
				});
			});
			
		printer.print();
	}
});