Ext.define('ems.biz.samples.xgridsample.XGridSample', {
    extend: 'ems.core.Module',
    
    init: function(){
        var me = this;
        me.callParent(arguments);
    },
    
    activate: function(params){
        var me = this;
        me.callParent(arguments);
    },
    
    sample1: function(params, request) {
        var me = this, 
			samplePanel = me.down('#sample-panel');
        
        me.RV('ems.biz.samples.xgridsample.view.XGridSample1ViewUI', {
            renderTo: samplePanel
        });
    },
    
    sample2: function(params, request) {
        var me = this, 
			samplePanel = me.down('#sample-panel');
        
        me.RV('XGridSample2ViewUI', {
            renderTo: samplePanel
        });
    },
    
    onCreate: function(params, request) {
		var me = this,
		win = me.SW('XGridSample1ViewEditUI', {
			bizAction: 'create'
		}, {
			title: '新增用户',
			modal: true,
			resizable: false,
			buttons: [{
				text: '提交',
				handler: function() {
					win.down('form').getForm().submit();
				}
			}, {
				text: '关闭',
				handler: function() {
					win.close();
				}
			}]
		});
    },
	
    onModify: function(params, request) {
		var me = this;
		
		me.SW('XGridSample1ViewEditUI', {
			bizAction: 'modify',
			reqParams: {
				userId: '1'
			}
		}, {
			title: '修改用户',
			modal: true,
			resizable: false,
			buttons: [{
				text: '提交'
			}, {
				text: '关闭'
			}]
		});
    },
	
    onDelete: function(params, request){
		var me = this;
		
		me.SW('XGridSample1ViewReadUI', {
			inputArgs: "abc"
		}, {
			title: '删除用户',
			modal: true,
			resizable: false,
			buttons: [{
				text: '提交'
			}, {
				text: '关闭'
			}]
		});
    }
});