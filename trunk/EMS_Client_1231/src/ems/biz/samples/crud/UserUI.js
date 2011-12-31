Ext.define('ems.biz.samples.crud.UserUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
//	enableCreateAction: true,
//	enableUpdateAction: true,
//	enableDeleteAction: true,
//	enableReadAction: true,
    
    uiConfig: function(){
        var me = this;
        return {
            layout: 'anchor',
            anchor: '100% 100%',
            items: [{
                anchor: '100% 100%',
                xtype: 'xgrid',
                selMode: 'single',
                paging: true,
                loadDF: me.DF('loadList'),
                searchForm: {
                    items: [{
                        fieldLabel: '用户名',
                        name: 'userName'
                    }, 
					Dic.comboBox('Sex', {
                        fieldLabel: '性别',
                        name: 'sex',			
                        valueKey: 'S2'
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
                }]
            }]
        }
    }
})
