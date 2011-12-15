Ext.define('ems.biz.test.view.TestXGridView', {
    extend: 'ems.core.UI',
	
	requires: [
		'ems.core.widget.XGrid'
	],
	
	uiConfig: function(){
		var me = this;
		return {
			border: false,
			items: [
				Ext.createWidget('xgrid', {
//					selMode: 'multi', // single / simple / multi / null
					
//					editingMode: 'cellediting', // cellediting / rowediting / null
					
					expandRowBodyTpl: null,
					
					loadDF: Ems.DF('ems.biz.test.Test','getGrid'),
					
					searchForm: {
						defaults: {
							allowBlank: false
						},
						items: [{
				            fieldLabel: 'Name11',
				            name: 'name'
				        }, {
				            fieldLabel: 'Email11',
				            name: 'email',
							vtype: 'email'
				        }, {
				            fieldLabel: 'Company11',
				            name: 'company'
				        }]
					},
					columns: [{
				        dataIndex: 'name',
				        flex: 1,
				        text: 'Name',
						editor: {
				            allowBlank: false
				        }
				    }, {
				        dataIndex: 'turnover',
				        align: 'right',
				        width: 120,
				        text: 'Turnover pa.',
				        renderer: Ext.util.Format.usMoney
				    },{
			            xtype:'actioncolumn',
			            width:50,
			            items: [{
			                icon: 'extjs/examples/shared/icons/fam/cog_edit.png',  // Use a URL in the icon config
			                tooltip: 'Edit',
			                handler: function(grid, rowIndex, colIndex) {
			                    var rec = grid.getStore().getAt(rowIndex);
			                    alert("Edit " + rec.get('name'));
			                }
			            },{
			                icon: 'extjs/examples/restful/images/delete.png',
			                tooltip: 'Delete',
			                handler: function(grid, rowIndex, colIndex) {
			                    var rec = grid.getStore().getAt(rowIndex);
			                    alert("Terminate " + rec.get('name'));
			                }
			            }]
			        }],
					buttons: [{
						text: 'New',
						handler: function() {
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
						}
					}, {
						text: 'Modify'
					}, {
						text: 'Delete'
					}]
				})
			]
		}
	}
});