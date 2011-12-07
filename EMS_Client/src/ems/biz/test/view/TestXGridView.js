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
					selMode: 'multi', // single / simple / multi / null
					
					editingMode: 'cellediting', // cellediting / rowediting / null
					
					expandRowBodyTpl: null,
					
					loadDF: Ems.DF('ems.biz.test.Test','getGrid'),
					
					searchForm: {
						items: [{
				            fieldLabel: 'Name',
				            name: 'name'
				        }, {
				            fieldLabel: 'Email',
				            name: 'email',
							vtype: 'mail'
				        }, {
				            fieldLabel: 'Company',
				            name: 'company',
							colspan: 3
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
			                    alert("Edit " + rec.get('firstname'));
			                }
			            },{
			                icon: 'extjs/examples/restful/images/delete.png',
			                tooltip: 'Delete',
			                handler: function(grid, rowIndex, colIndex) {
			                    var rec = grid.getStore().getAt(rowIndex);
			                    alert("Terminate " + rec.get('firstname'));
			                }
			            }]
			        }],
					
					buttons: [{
						text: 'New'
					}, {
						text: 'Modify'
					}, {
						text: 'Delete'
					}],
				})
			]
		}
	}
});