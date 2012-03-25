Ext.define('ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacher', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        viewSyllabus: '查看'
    },
	
	viewSyllabus: 'ViewSyllabusUI',
	
    init: function() {
        var me = this;
        me.callParent(arguments);
    },
    
    activate: function(params) {
        var me = this;
        me.callParent(arguments);
    },
	
	onViewSyllabus: function(params, request) {
		var me = this, 
			eo = request.eventSource,
			bizAction = 'viewSyllabus',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
		
        me.SW(me.viewSyllabusPlanUIViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
			title: '教师课表',
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