Ext.define('ems.biz.basicInfo.bookManager.BookUI', {
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
//				title: '教材信息列表',
                xtype: 'xgrid',
                selMode: 'multi',
                paging: true,
                loadDF: me.DF('loadBook'),
                searchForm: {
                    items: [{
                        fieldLabel: '教材名',
                        name: 'bookName'
                    },{
                        fieldLabel: '出版社',
                        name: 'publishName'
                    },{
                        fieldLabel: '作者',
                        name: 'author'
                    },{
                        fieldLabel: 'ISBN',
                        name: 'isbnNo'
                    }]
                },
                columns: [{
                    dataIndex: 'id',
                    text: '教材ID',
                    flex: 1
                }, {
                    dataIndex: 'bookName',
                    text: '教材名称',
                    flex: 1
                }, {
                    dataIndex: 'publishName',
                    text: '出版社',
                    flex: 1
                } ,{
                    dataIndex: 'author',
                    text: '作者',
                    flex: 1
                }, {
                    dataIndex: 'isbnNo',
                    text: 'ISBN号',
                    flex: 1
                }]
            }]
        }
    }
})