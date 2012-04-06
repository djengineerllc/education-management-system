Ext.define('ems.biz.syllabus.syllabusbycourse.view.ViewSyllabusUI', {
	extend: 'ems.core.UI',
	
	initData: function() {
		var me = this,
			termId = me.reqParams['termId'],
			sltCourses = me.reqParams['sltCourses'],
			sltCourseNos = [],
			contentPanel = me.down('#contentPanel');
		
		Ext.each(sltCourses, function(sltCourse) {
			sltCourseNos.push(sltCourse.courseNo);
		});
		
		contentPanel.el.load({
			url: Ems.getDirectStreamRequestUrl(me.moduleId, 'printSyllbusPlan'),
			loadMask: true,
			scripts: true,
			params: {
				termId: termId,
				courseNo: sltCourseNos
			}
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
			certContentPanel = me.down('#contentPanel');
		EU.printHtml(certContentPanel.el.dom.innerHTML);
		
		return false;
	}
});