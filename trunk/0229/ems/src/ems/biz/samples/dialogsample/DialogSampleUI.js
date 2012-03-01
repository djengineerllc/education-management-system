Ext.define('ems.biz.samples.dialogsample.DialogSampleUI', {
    extend: 'ems.core.UI',
    
    uiConfig: function(){
        var me = this;
        return {
			defaultType: 'button',
			defaults: {
				margin:'5'
			},
			items: [{
				text: 'showInfoDialog',
				listeners: {
					click: function() {
						EU.showInfoDialog({
							msg: 'msg-showInfoDialg'
						})
					}
				}
			}, {
				text: 'showWarningDialog',
				listeners: {
					click: function() {
						EU.showWarningDialog({
							msg: 'msg-showWarningDialog'
						})
					}
				}
			}, {
				text: 'showConfirmDialog',
				listeners: {
					click: function() {
						EU.showConfirmDialog({
							msg: 'msg-showConfirmDialog'
						})
					}
				}
			}, {
				text: 'showErrorDialog',
				listeners: {
					click: function() {
						EU.showErrorDialog({
							msg: 'msg-showErrorDialog'
						})
					}
				}
			}, {
				text: 'showSuccessDialog',
				listeners: {
					click: function() {
						EU.showSuccessDialog({
							msg: 'msg-showSuccessDialog'
						})
					}
				}
			}]
        };
    }
});