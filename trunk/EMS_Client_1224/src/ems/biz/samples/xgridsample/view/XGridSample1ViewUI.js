Ext.define('ems.biz.samples.xgridsample.view.XGridSample1ViewUI', {
    extend: 'ems.core.UI',
	
	requires: [
		'ems.core.widget.XGrid'
	],
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'anchor',
			anchor: '100% 100%',
			items: [{
				anchor: '100% 100%',
				xtype: 'xgrid',
				selMode: 'multi',
				paging: true,
				loadDF: me.DF('getXGridSample1ViewData'),
				searchForm: {
					items: [{
						fieldLabel: '用户名',
						name: 'userName'
					}, Dic.comboBox('Sex', {
						fieldLabel: '性别',
						name: 'sex'
//						,value: 2
						,valueKey: 'S2'
					}), {
						xtype: 'datefield',
						fieldLabel: '生日',
						name: 'birthday'
					}]
				},
				columns: [{
					dataIndex: 'userName',
					text: '用户名',
					flex: 1
				}, {
					dataIndex: 'sex',
					text: '性别',
					flex: 1,
					renderer: Dic.renderer('Sex')
				}, {
					dataIndex: 'birthday',
					text: '生日',
					flex: 1
				}, {
					dataIndex: 'email',
					text: '电子邮件',
					flex: 1
				}
//				,{
//		            xtype:'actioncolumn',
//		            width:50,
//		            items: [{
//		                icon: 'extjs/examples/shared/icons/fam/cog_edit.png',  // Use a URL in the icon config
//		                tooltip: 'Edit',
//		                handler: function(grid, rowIndex, colIndex) {
//		                    var rec = grid.getStore().getAt(rowIndex);
//		                    alert("Edit " + rec.get('name'));
//		                }
//		            },{
//		                icon: 'extjs/examples/restful/images/delete.png',
//		                tooltip: 'Delete',
//		                handler: function(grid, rowIndex, colIndex) {
//		                    var rec = grid.getStore().getAt(rowIndex);
//		                    alert("Terminate " + rec.get('name'));
//		                }
//		            }]
//		        }
				],
//				buttonAlign: 'center',
				tbar: [{
					text: '新增',
					iconCls: 'icon-new'
				}, {
					text: '修改',
					iconCls: 'icon-edit'
				}, {
					text: '删除',
					iconCls: 'icon-delete'
				}]
			}]
		}
	}
});