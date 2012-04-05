Ext.define('ems.biz.syllabus.syllabusbyclass.SyllabusByClass', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        viewSyllabus: '查看'
    },
	
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
		
        me.SW('ViewSyllabusUI', {
            bizAction: bizAction,
            reqParams: {
            	termId: me.down('#termComboBox').getValue(),
            	sltClasses: reqParams
            }
        }, {
			title: '班级课表',
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