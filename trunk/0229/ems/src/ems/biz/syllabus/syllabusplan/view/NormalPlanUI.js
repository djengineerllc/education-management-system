Ext.define('ems.biz.syllabus.syllabusplan.view.NormalPlanUI', {
	extend: 'ems.core.UI',
	
	requires: [
		'Ext.tree.Panel',
		'ems.biz.syllabus.data.store.SyllabusStore'
	],
	
	init: function() {
		var me = this,
			sltTeams = me.reqParams;
	},
	
	afterRender: function() {
		var me = this;
		me.callParent(arguments);
		me.init();
	},
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'border',
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				height: 65,
				border: false
			}, {
				region: 'center',
				border: false,
				xtype: 'treepanel',
		        useArrows: true,
		        rootVisible: false,
//		        plugins: [
//		        	Ext.create('Ext.grid.plugin.CellEditing')
//		        ],
		        store: Ext.create('ems.biz.syllabus.data.store.SyllabusStore', {
					autoLoad: true
				}),
		        multiSelect: true,
		        singleExpand: false,
		        columns: [{
		            xtype: 'treecolumn',
		            text: '课时',
		            dataIndex: 'lesson'
		        },{
		           text: '班级',
		            dataIndex: 'classCode',
		            width: 50
		        },{
					text: '单双',
		            dataIndex: 'oeInd',
		            width: 50
				},{
					text: '周一',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'monCourse',
						editor: {
			                xtype: 'textfield'
			            }
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'monTeacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'monRoom'
		            }]	
				},{
					text: '周二',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'tueCourse'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'tueTeacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'tueRoom'
		            }]	
				},{
					text: '周三',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'webCourse'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'webTeacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'webRoom'
		            }]	
				},{
					text: '周四',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'thuCourse'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'thuTeacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'thuRoom'
		            }]	
				},{
					text: '周五',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'friCourse'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'friTeacher'	
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'friRoom'
		            }]	
				}]
			}]
		}
	}
});