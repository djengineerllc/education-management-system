Ext.define('ems.biz.basicInfo.roomManager.RoomUI', {
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
			layout: 'fit',
            items: [{
//				title: '教室信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadRoom'),
                searchForm: {
                    items: [Dic.comboBox('Term', {
					fieldLabel: '学期',
					name: 'termId',
					value:-1
				}),{
                        fieldLabel: '教室',
                        name: 'roomName'
                    }]
                },
                columns: [{
                    dataIndex: 'termName',
                    text: '学期',
                    flex: 1
                }, {
                    dataIndex: 'roomName',
                    text: '教室',
                    flex: 1
                },{
                    dataIndex: 'roomSize',
                    text: '教室人数',
                    flex: 1
                },{
                    dataIndex: 'roomStatus',
                    text: '教室使用情况',
                    flex: 1
                },{
                    dataIndex: 'roomComment',
                    text: '教室备注',
                    flex: 1
                }]
            }]
        }
    }
})