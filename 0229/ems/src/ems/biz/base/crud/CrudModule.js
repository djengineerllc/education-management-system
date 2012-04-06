Ext.define('ems.biz.base.crud.CrudModule', {
    extend: 'ems.core.Module',
    
    submitButton: null, // create | update
    cancelButton: null,
    
    bizActionText: {
        c: '新增',
        u: '修改',
        d: '删除',
        r: '查看'
    },
    
    addViewId: null, // require
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
                    }
					
//					win.el.mask('请求处理中，请稍候...');
                    viewPanel.submitFormData({
//						waitMsg: '请求处理中，请稍候...',
//						waitMsgTarget: true,
                        success: function(form, action){
//							win.el.unmask();
							me._onSuccess(viewPanel.bizAction);
							win.close();
							me.down('xgrid').store.load();
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
	
	activate: function(params){
//		this.down('xgrid').store.load();
	},
    
    getReqParams: function(bizAction, eventSource){
        var me = this,
			sm = me.down('xgrid').getSelectionModel(), 
			slts = sm.getSelection();
		
		if (bizAction != 'c') {
			if (slts.length == 0) {
				EU.showInfoDialog({
					msg: Ext.String.format('请您选中记录后再进行{0}操作。', me.bizActionText[bizAction]),
					animateTarget: eventSource.el
				});
//				EU.showMsg(Ext.String.format('请您选中记录后再进行{0}操作。', me.bizActionText[bizAction]), "");
				
				return false;
			}
			
			if (bizAction == 'u' || bizAction == 'r') {
				if (slts.length > 1) {
					EU.showInfoDialog({
						msg: Ext.String.format('请您只选中要进行{0}操作的一条记录。', me.bizActionText[bizAction]),
						animateTarget: eventSource.el
					});
					return false;
				} else {
					return slts[0].raw;
				}
			}
			if (bizAction == 'd') {
				var ids = [];
				Ext.each(slts, function(slt) {
					ids.push(slt.raw[slt.store.idProperty]);
				});
				
				return ids;
			}
        }
		//TODO slt.data 可以获取更新后的数据
		var sltRaws = [];
		Ext.each(slts, function(slt) {
			sltRaws.push(slt.raw);
		});
		return sltRaws;
    },
    
    onCreate: function(params, request){
        var me = this, 
			eo = request.eventSource,
			bizAction = 'c', 
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
        
        me.SW(me.addViewId || me.editViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.submitButton, me.cancelButton],
			animateTarget: eo.el
        });
    },
    
    onRead: function(params, request){
        var me = this, 
			eo = request.eventSource,
			bizAction = 'r', 
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
		
        me.SW(me.readViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.cancelButton],
			animateTarget: eo.el
//			,modal: false
        });
    },
    
    onUpdate: function(params, request){
        var me = this, 
			eo = request.eventSource,
			bizAction = 'u', 
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
        
        me.SW(me.editViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
            title: me.bizActionText[bizAction],
            buttons: [me.submitButton, me.cancelButton],
			animateTarget: eo.el
        });
    },
    
    onDelete: function(params, request){
        var me = this, 
			eo = request.eventSource,
			bizAction = 'd',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
			
		var dlg = EU.showConfirmDialog({
			animateTarget: eo.el,
			msg: Ext.String.format('您确认删除选中的记录({0}条)？', reqParams.length),
			callback: function(btnId, value) {
				if (btnId == 'yes') {
					me.A({
						m: 'delete',
						p: [reqParams],
						cb: function(result, e) {
							if (result.success) {
								me._onSuccess(bizAction);
								me.down('xgrid').store.load();
							} else {
								alert('删除失败'); // TODO 
							}
						}
					})
				}
			}
		});
    },
	
	onBatchImport: function(params, request){
		var me = this,
			eo = request.eventSource;
		
		me.SW('UserBatchImportUI', {
//            bizAction: bizAction,
//            reqParams: reqParams
        }, {
            title: '批量导入',
			width: 750,
			minWidth: 600,
//			height: 300,
//			minHeigh: 150,
			autoScroll: true,
			resizable: true,
			maximizable: true,
//			constrain: me.ui.getTargetEl(),
//			constrainTo: me.ui.getTargetEl(),
            buttons: [me.cancelButton],
			animateTarget: eo.el
        });
	},
	
	onPrint: function(params, request){
		return false;
	},
	
	onExportExcel: function(params, request){
		Ems.download(this.id, 'exportExcel');
	},
	
	_onSuccess: function(bizAction) {
		EU.showMsg('操作成功', Ext.String.format('{0}成功', this.bizActionText[bizAction]));
//		EU.showSuccessDialog({
//			msg: Ext.String.format('{0}成功', this.bizActionText[bizAction])
//		});
	},
	
	_onFailure: function() {
		
	}
});
