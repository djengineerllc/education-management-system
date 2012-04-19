Ext.define('ems.biz.syllabus.syllabusplan.view.ViewSyllabusPlanUI', {
	extend: 'ems.core.UI',
	
	initData: function() {
		var me = this,
			sltTerm = me.reqParams[0],
			contentPanel = me.down('#contentPanel');
		
		contentPanel.el.load({
			url: Ems.getDirectStreamRequestUrl(me.moduleId, 'printSyllbusPlan'),
			loadMask: true,
			scripts: true,
			params: sltTerm
		});
	},
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'fit',
			frame: false,
			tbar: [{
				text: '打印',
				iconCls: 'icon-print',
				listeners: {
					click: {
						fn: me.execPrint,
						scope: me
					}
				}
			}],
			items: [{
				itemId: 'contentPanel',
				margins: '2 2 2 2',
				autoScroll: true,
				border: false,
				frame: false,
				style: {
					overflowY: 'auto'
				}
			}]
		}
	},
	
	execPrint: function() {
		var me = this,
			contentPanel = me.down('#contentPanel');
		EU.printHtml(contentPanel.el.dom.innerHTML);
		
		return false;
	}
});