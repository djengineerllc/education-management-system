Ext.define('ems.biz.scoremgr.scorequery.ScoreQuery', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        scoreQuery: '成绩查询'
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
			bizAction = 'scoreQuery',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
        
        me.SW('ScoreQueryUI', {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
			title: '成绩查询',
			width: 850,
			minWidth: 600,
			height: 500,
			minHeight: 200,
			resizable: true,
			maximizable: true,
            buttons: [me.cancelButton],
			animateTarget: eo.el
        });
	}
});