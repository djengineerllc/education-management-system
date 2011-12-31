Ext.define('ems.biz.demo.widgetdemo.WidgetDemoUI', {
    extend: 'ems.core.UI',
	
	requires: [
		'ems.core.widget.XGrid'
	],
	
    uiConfig: function(){
        var me = this;
		return {
			border: false,
			frame: false,
			layout: 'anchor',
			anchor: '100% 100%',
			items: [
				{
					id: me.idp + 'demo-dialog-panel'
				},
//				Ext.create('ems.biz.demo.widgetdemo.view.DialogDemoView'),
				me.xgridDemo(),
				me.xgridDemo1()

			]
		}
	},
	
	xgridDemo: function() {
		var me = this;
		return Ext.createWidget('xgrid', {
			anchor: '100% 100%',
			selMode: 'multi',
			paging: false,
			loadDF: me.DF('getUserInfo'),//Ems.DF('ems.biz.test.Test','getGrid'),
			searchForm: {
				items: [{
		            fieldLabel: '用户名',
		            name: 'userName'
		        }
				,
				Dic.comboBox('Sex', {
					fieldLabel: '性别',
					name: 'sex'
//					,value: 2
					,valueKey: 'S2'
				})
//					{
//					xtype: 'combo',
//					queryMode: 'local',
//		            fieldLabel: '性别',
//		            name: 'sex',
//					displayField: 'name',
//					valueField: 'value',
//					value: 'm',
//					store: 
//					Dic.getStore('Sex')
////					Ext.create('Ext.data.Store', {
////						fields : ['name', 'value'],
////						data   : [
////							{name : '男', value: 'm'},
////							{name : '女',  value: 'f'}
////						]
////					})
//		        }
				, {
					xtype: 'datefield',
		            fieldLabel: '生日',
		            name: 'birthday'
		        }]
			},
			columns: [{
		        dataIndex: 'userName',
		        text: '用户名',
				flex: 1
		    }
			, {
		        dataIndex: 'sex',
		        text: '性别',
				flex: 1
				, renderer: Dic.renderer('Sex')
		    }
			,{
				dataIndex: 'birthday',
		        text: '生日',
				flex: 1
			}, {
				dataIndex: 'email',
		        text: '电子邮件',
				flex: 1
			}],
//			buttonAlign: 'center',
			buttons: [{
				text: 'New',
				handler: me.onNew
			}, {
				text: 'Modify'
			}, {
				text: 'Delete'
			}]
		})
	},
	
	xgridDemo1: function() {
		var me = this;
		var grid1 = Ext.createWidget('xgrid', {
			anchor: '100% 100%',
			rowNumberer: false,
			loadDF: me.DF('getUserInfo'),//Ems.DF('ems.biz.test.Test','getGrid'),
			editingMode: 'rowediting',
			columns: [{
		        dataIndex: 'a',
		        text: '姓名',
				flex: 1,
				editor: {
	                xtype: 'textfield'
	            }
		    }, {
		        dataIndex: 'b',
		        text: '亲属关系',
				flex: 1
//				,
//				renderer: {
//				}
		    }, {
				dataIndex: 'c',
		        text: '工作单位',
				flex: 3,
				editor: {
	                xtype: 'textfield'
	            }
			}, {
				dataIndex: 'd',
		        text: '职务',
				flex: 1,
				editor: {
	                xtype: 'textfield'
	            }
			}, {
				dataIndex: 'e',
		        text: '联系电话',
				flex: 1,
				editor: {
	                xtype: 'textfield'
	            }
			}]
		})
		
		grid1.store.load();
		
		return grid1;
	},
	
	onNew: function() {
		 Ext.create('Ext.window.Window', {
		 	title: 'Hello',
			height: 200,
			width: 400,
			layout: 'fit',
			items: {  // Let's put an empty grid in just to illustrate fit layout
				xtype: 'grid',
				border: false,
				columns: [{header: 'World'}],                 // One header just for show. There's no data,
				store: Ext.create('Ext.data.ArrayStore', {}) // A dummy empty data store
		}}).show();
	},
	
	dialogDemo: function() {
		
	}
});
