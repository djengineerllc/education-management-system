Ext.define('ems.biz.base.crud.CrudModule', {
    extend: 'ems.core.Module',
    
    submitButton: null,
    cancelButton: null,
    
    bizActionText: {
        c: '新增',
        u: '修改',
        d: '删除',
        r: '查看'
    },
    
    editViewId: null, // require
    readViewId: null, // require
	
    init: function(){
        var me = this;
        me.callParent(arguments);
		
        if (!me.submitButton) {
            me.submitButton = {
                text: '提交',
                handler: function(){
                    var win = this.up('window'), 
					viewPanel = win.down('panel'), 
					form = viewPanel.down('form').getForm();
                    if (!form.isValid()) {
                        return;
                    };
                    
                    viewPanel.submitFormData({
                        success: function(form, action){
                            EU.showSuccessDialog({
                                msg: Ext.String.format('{0}成功', me.bizActionText[viewPanel.bizAction])
                            });
                            
                            win.close();
							me.down('xgrid').store.load();
                        },
                        failure: function(form, action){
                        }
                    });
                }
            };
        }
		if (!me.cancelButton) {
			me.cancelButton = {
				text: '关闭',
				handler: function(){
					this.up('window').close();
				}
			};
		}
    },
    
    getReqParams: function(bizAction){
        var me = this,
			sm = me.down('xgrid').getSelectionModel(), 
			slts = sm.getSelection();
        
        if (bizAction != 'c') {
            if (slts.length == 0) {
                EU.showInfoDialog({
                    msg: '请您选中记录后再进行此操作'
                });
                return false;
            }
            
            return slts[0].data;
        }
    },
    
    onCreate: function(params, request){
        var me = this, 
			bizAction = 'c', 
			reqParams = me.getReqParams(bizAction);
        if (reqParams == false) {
            return;
        }
        
        me.SW(me.editViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.submitButton, me.cancelButton]
        });
    },
    
    onRead: function(params, request){
        var me = this, 
			bizAction = 'r', 
			reqParams = me.getReqParams(bizAction);
        if (reqParams == false) {
            return;
        }
        
        me.SW(me.readViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.cancelButton]
        });
    },
    
    onUpdate: function(params, request){
        var me = this, 
			bizAction = 'u', 
			reqParams = me.getReqParams(bizAction);
        if (reqParams == false) {
            return;
        }
        
        me.SW(me.editViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.submitButton, me.cancelButton]
        });
    },
    
    onDelete: function(params, request){
        var me = this, 
			bizAction = 'd', 
			reqParams = me.getReqParams(bizAction);
        if (reqParams == false) {
            return;
        }
        
        me.SW(me.readViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.submitButton, me.cancelButton]
        });
    }
});
