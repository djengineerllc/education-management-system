Ext.define('ems.biz.basicInfo.roomManager.view.RoomBatchImportUI', {
	extend: 'ems.core.UI',
	
	uiConfig: function(){
		var me = this;
		return {
			bodyPadding: 3,
			anchor: 'anchor',
			items: [{
				xtype: 'form',
				itemId: 'uploadForm',
				bodyPadding: 3,
				border: false,
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				api: {
					submit: me.DF('batchImport')
				},
				items: [{
					xtype: 'container',
                    layout: 'hbox',
					items: [{
						xtype: 'filefield',
						itemId: 'fileInput',
						width: 300,
			            emptyText: '请点击"浏览"按钮选择文件',
			            fieldLabel: '上传文件',
						regex: /(\.xls)$/i,
						regexText: '只能上传扩展名[.xls]的Excel文件',
			            buttonText: '浏览',
//						buttonConfig: {
//			                iconCls: 'upload-icon'
//			            },
						allowBlank: false,
						listeners: {
							validitychange: function(comp, isValid, eOpts) {
								me.down('#uploadButton').setDisabled(!isValid);
							}
						}
					}, {
						xtype: 'button',
						text: '上传',
						itemId: 'uploadButton',
						disabled: true,
//						tooltip: '请您先点击"浏览"按钮选择文件',
						margins: '0 0 0 3',
						handler: function() {
							var formPanel = this.up('form'),
								form = formPanel.getForm();
		                    if (!form.isValid()) {
		                        return;
		                    }
							
							formPanel.submit({
		                        success: function(form, action){
									
									var result = action.result,
										successGrid = me.down('#successGrid'),
										successData = result.data.successData,
										failureGrid = me.down('#failureGrid'),
										failureData = result.data.failureData;
									
									EU.showMsg('导入成功', '成功10笔，失败9笔');
									
									successGrid.setTitle(Ext.String.format('{0}笔数据解析成功', successData.totalCount));
									successGrid.store.loadRawData(successData);
									
//									failureGrid.setTitle(Ext.String.format('{0}笔数据解析失败', failureData.totalCount));
//									failureGrid.store.loadRawData(failureData);
		                        }
		                    });
						}
					}, {
						margins: '0 0 0 15',
						xtype: 'button',
						text: '模板下载',
						handler: function() {
							Ems.directStreamRequest(me.moduleId, 'downloadExcelTemplate');
						}
					}]
				}]
			}, {
				xtype: 'xgrid',
				itemId: 'successGrid',
				title: '解析成功列表',
//				collapsible: true,
				sortableColumns: false,
				border: true,
//				hidden: true,
				margin: '0 0 3 0',
				height: 200,
                columns: [{
                    dataIndex: 'gradeId',
                    text: '年级ID',
                    flex: 1
                }, {
                    dataIndex: 'gradeName',
                    text: '年级名',
                    flex: 1
                }]
			}, {
				xtype: 'xgrid',
				itemId: 'failureGrid',
				title: '解析失败列表',
//				collapsible: true,
				sortableColumns: false,
				border: true,
//				hidden: true,
				height: 200,
                columns: [{
                    dataIndex: 'gradeId',
                    text: '年级ID',
                    flex: 1
                }, {
                    dataIndex: 'gradeName',
                    text: '年级名',
                    flex: 1
                }, {
					dataIndex: 'failInfo',
                    text: '失败信息',
                    flex: 1
				}]
			}]
		};
	}
})
