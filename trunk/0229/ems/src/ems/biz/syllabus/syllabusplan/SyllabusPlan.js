Ext.define('ems.biz.syllabus.syllabusplan.SyllabusPlan', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        normalPlan: '手动排课',
        autoPlan: '自动排课'
    },
	
	normalPlanUIViewId: 'NormalPlanUI',
	autoPlanUIViewId: 'AutoPlanUI',
	
//	submitButton: {
//        text: '提交',
//        handler: function() {
//        	alert(this.up('grid'))
//        	debugger;
//        }
//    },
	
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
		
        me.SW(me.normalPlanUIViewId, {
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
            buttons: [{
            	xtype: 'textfield',
            	cls: 'help-info-left',
            	width: 350,
            	html: '1. 拖动[课程/教师/教室]到对应表格列    2. 双击单元格清空数据'
            }, {
		        text: '提交',
		        handler: function() {
		        	var g = this.up('window').down('grid'),
		        		s = g.getStore(),
		        		datas = [];
		    		
					Ext.each(s.getUpdatedRecords(), function(record) {
						datas.push(record.data);
					});
					alert(Ext.encode(datas));
		        }
		    }, me.cancelButton],
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