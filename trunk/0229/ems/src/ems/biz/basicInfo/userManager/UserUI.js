Ext.define('ems.biz.basicInfo.userManager.UserUI', {
    extend: 'ems.biz.base.crud.CrudUI',
    
    requires: ['ems.core.widget.XGrid'],
	
	isListUI: true,
    
    uiConfig: function(){
        var me = this;
        return {
			layout: 'fit',
            items: [{
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadUser'),
                searchForm: {
                	fieldColumns:4,
                    items: [{
                        fieldLabel: '帐号',
                        name: 'loginName'
                    },{
                        fieldLabel: '姓名',
                        name: 'userName'
                    },Dic.comboBox('Role', {
					fieldLabel: '角色',
					name: 'roleId',
					value:-1
				})]
                },
                columns: [{
                    dataIndex: 'loginName',
                    text: '帐号',
                    flex: 1
                }, {
                    dataIndex: 'userName',
                    text: '姓名',
                    flex: 1
                },{
                    dataIndex: 'email',
                    text: 'E-Mail',
                    flex: 1
                },{
                    dataIndex: 'contact',
                    text: '联系电话',
                    flex: 1
                }]
            }]
        }
    }
})