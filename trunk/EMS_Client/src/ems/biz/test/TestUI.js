Ext.define('ems.biz.test.TestUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function(){
        var me = this;
		
        var basicInfo = Ext.createWidget('form',
			{
			itemId: '_form',
			xtype: 'form',
            // configs for FormPanel
            title: 'Basic Information',
            border: false,
            bodyPadding: 10,
            // configs for BasicForm
            api: {
                // The server-side method to call for load() requests
                load: me.DF('getBasicInfo'),
                // The server-side must mark the submit handler as a 'formHandler'
                submit: me.DF('updateBasicInfo'), //TestAction.updateBasicInfo
            },
            // specify the order for the passed params
			
            paramOrder: ['uid', 'foo'],
//			paramsAsHash: true,
            dockedItems: [{
                dock: 'bottom',
                xtype: 'toolbar',
                ui: 'footer',
                style: 'margin: 0 5px 5px 0;',
                items: ['->', {
                    text: 'Submit',
                    handler: function(){
                        basicInfo.getForm().submit({
                            params: {
                                foo: 'bar',
                                uid: 34
                            }
                        });
                    }
                }, {
                    text: 'LOAD',
                    handler: function(){
                        this.up().up().getForm().load({
                            // pass 2 arguments to server side getBasicInfo method (len=2)
                            params: {
                                foo: 'bar',
                                uid: 34
                            }
                        });
                    }
                }, {
                    text: 'renderView',
                    handler: function(){
						me.renderView('ViewOne', {
						}
//							renderTo: 'viewoneid'
//						}, 'after'
						);
                    }
                }, {
                    text: 'renderXGirdView',
                    handler: function(){
						me.renderView('TestXGridView');
                    }
                }]
            }],
            defaultType: 'textfield',
            defaults: {
                anchor: '100%'
            },
            items: [{
                fieldLabel: 'Name',
                name: 'name'
            }, {
                fieldLabel: 'Email',
                msgTarget: 'side',
                name: 'email'
            }, {
                fieldLabel: 'Company',
                name: 'company'
            }]
       }); //  };
	   
		
		var grid = Ext.createWidget('grid', 
		{
		//	itemId: '_grid',
			xtype: 'grid',
			loadMask: true,
	        store: Ext.create('ems.biz.test.data.store.TestStore'),
	        columns: [{
	            dataIndex: 'name',
	            flex: 1,
	            text: 'Name'
	        }, {
	            dataIndex: 'turnover',
	            align: 'right',
	            width: 120,
	            text: 'Turnover pa.',
	            renderer: Ext.util.Format.usMoney
	        }],
	        title: 'Company Grid',
			viewConfig: {
	            stripeRows: true,
				forceFit: true
	        }
	   }); //  };
	   
//	   grid.store.load({
//	   	params: {
//			_a:"aa"
//		}
//	   })
//		grid.store.destroy();
        
        return {
            //			height: '100%',
            //			width: '100%',
            border: 0,
            items: [
				basicInfo,
				grid,
				{
					id: "viewoneid"
				}
			]
			
        };
    },
	
	initComponent: function(){
		this.callParent(arguments);
//		alert(Ext.getCmp('_form'))
//		Ext.data.StoreManager.get('gridBookStore').load();
//		alert(this.getComponent("_grid").$className)
//		alert(this.getComponent("_form").down().$className)
//		alert(this.down().$className)
	}
});
