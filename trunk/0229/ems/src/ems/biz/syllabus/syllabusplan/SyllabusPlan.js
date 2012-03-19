Ext.define('ems.biz.syllabus.syllabusplan.SyllabusPlan', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        normalPlan: '手动排课',
        autoPlan: '自动排课'
    },
	
	normalPlanUIViewId: 'NormalPlanUI',
	autoPlanUIViewId: 'AutoPlanUI',
	
    init: function() {
        var me = this;
        me.callParent(arguments);
    },
    
    activate: function(params) {
        var me = this;
        me.callParent(arguments);
    },
	
	onNormalPlan: function(params, request) {
		var me = this, 
			eo = request.eventSource,
			bizAction = 'normalPlan',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
		
        me.SW(me.printCertViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
			title: '手动排课',
			width: 850,
			minWidth: 600,
			height: 500,
			minHeight: 200,
			resizable: true,
			maximizable: true,
            buttons: [me.cancelButton],
			animateTarget: eo.el
        });
	},
	
	onAutoPlan: function(params, request) {
		var me = this, 
			eo = request.eventSource,
			bizAction = 'autoPlan',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
	}
});