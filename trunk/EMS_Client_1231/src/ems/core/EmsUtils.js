Ext.define('ems.core.EmsUtils', {
	singleton: true,
	alternateClassName: 'EU',
	
	requires: [
		'Ext.window.MessageBox'
//		,'Ext.window.Window'
	],
	
	showDialog: function(config) {
		return Ext.MessageBox.show(config); 
//		Ext.apply(config || {}, {
//			autoHeight: true,
//			autoWidth: true,
//			autoScroll: true
//		})).doLayout();
	},
	
	showInfoDialog: function(config) {
		config = Ext.apply({
			title: "信息",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.INFO
		}, config);
		return this.showDialog(config);
	},
	showWarningDialog: function(config) {
		config = Ext.apply({
			title: "警告",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.WARNING
		}, config);
		return this.showDialog(config);
	},
	showConfirmDialog: function(config) {
		config = Ext.apply({
			title: "确认",
			buttons: Ext.MessageBox.YESNO,
			icon: Ext.MessageBox.QUESTION
		}, config);
		return this.showDialog(config);
	},
	showErrorDialog: function(config) {
		config = Ext.apply({
			title: "错误",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.ERROR
		}, config);
		return this.showDialog(config);
	},
	showSuccessDialog: function(config) {
		config = Ext.apply({
			title: "成功",
			buttons: Ext.MessageBox.OK,
			icon: 'ext-mb-success'
		}, config);
		return this.showDialog(config);
	}
	
//	,showWindow: function(config) {
//		return Ext.create('Ext.window.Window', config).show();
//	}
});