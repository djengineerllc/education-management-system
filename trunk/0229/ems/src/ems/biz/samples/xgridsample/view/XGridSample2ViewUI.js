Ext.define('ems.biz.samples.xgridsample.view.XGridSample2ViewUI', {
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
				paging: true,
				editingMode: 'rowediting',
				loadDF: me.DF('getXGridSample1ViewData'),
				storeConfig: {
					autoLoad: true
				},
				columns: [{
					dataIndex: 'userName',
					text: '用户名',
					editor: {
		                xtype: 'textfield'
		            },
					flex: 1
				}, {
					dataIndex: 'sex',
					text: '性别',
					flex: 1,
					renderer: Dic.renderer('Sex'),
					field: Dic.comboBox('Sex')
				}, {
					dataIndex: 'birthday',
					text: '生日',
					editor: {
		                xtype: 'datefield'
		            },
					flex: 1
				}, {
					dataIndex: 'email',
					text: '电子邮件',
					flex: 1
				}],
//				buttonAlign: 'center',
				buttons: [{
					text: '保存'
				}]
			}]
		}
	}
});