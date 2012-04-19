Ext.define('ems.biz.scoremgr.scoreinput.ScoreInput', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        scoreEdit: '成绩录入'
    },
	
    init: function() {
        var me = this;
        me.callParent(arguments);
    },
    
    activate: function(params) {
        var me = this;
        me.callParent(arguments);
    },
	
	onScoreEdit: function(params, request) {
		var me = this, 
			eo = request.eventSource,
			bizAction = 'scoreEdit',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
        
        me.SW('ScoreEditUI', {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
			title: '成绩录入',
			width: 850,
			minWidth: 600,
			height: 500,
			minHeight: 200,
			resizable: true,
			maximizable: true,
            buttons: [{
            	xtype: 'textfield',
            	cls: 'help-info-left',
            	width: 400,
            	html: '双击成绩/评级单元格进行编辑'
            }, {
		        text: '保存',
		        handler: function() {
			    	var win = this.up('window'), 
			    		g = win.down('grid'),
			    		data = g.getUpdatedData();
			    	
					me.A({
						m: 'submitScoreInputDetail',
						p: {
							termId: reqParams[0].id,
							submitData: data
						}, 
						cb: function(result, e) {
							if (result.errors) {
								EU.showInfoDialog({
									msg: result.errors
								})
							} else {
								me._onSuccess(bizAction);
								win.close();
								me.down('grid').store.load();
							}
						}
					})
			    }
		    }, me.cancelButton],
			animateTarget: eo.el
        });
	}
});