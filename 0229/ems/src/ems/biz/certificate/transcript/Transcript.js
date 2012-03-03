Ext.define('ems.biz.certificate.transcript.Transcript', {
    extend: 'ems.biz.base.crud.CrudModule',
	
	bizActionText: {
        printCert: '打印证明'
    },
	
	printCertViewId: 'PrintCertUI',
	
    init: function() {
        var me = this;
        me.callParent(arguments);
    },
    
    activate: function(params) {
        var me = this;
        me.callParent(arguments);
    },
	
	onPrintCert: function(params, request) {
		var me = this, 
			eo = request.eventSource,
			bizAction = 'printCert',
			reqParams = me.getReqParams(bizAction, eo);
        if (reqParams === false) {
            return;
        }
		
        me.SW(me.printCertViewId, {
            bizAction: bizAction,
            reqParams: reqParams
        }, {
			title: '成绩证明',
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