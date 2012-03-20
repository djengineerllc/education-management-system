Ext.define('ems.biz.syllabus.syllabusplan.view.NormalPlanUI', {
	extend: 'ems.core.UI',
	
	requires: [
		'Ext.tree.Panel',
		'ems.biz.syllabus.data.store.SyllabusStore',
		'Ext.ux.grid.RowspanView'
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
				height: 100,
				border: false,
				xtype: 'tabpanel',
			    activeTab: 0,
			    tabPosition: 'bottom',
			    defaults: {
			    	border: false
			    },
			    items: [{
		            title: '课程',
		            plugins: Ext.create('Ext.ux.PanelFieldDragZone'),
		            items: [{
			            xtype: 'textfield',
			            value: '大学语文',
			            ddSource: 'course'
			        },{
			            xtype: 'textfield',
			            value: '高数',
			            ddSource: 'course'
			        }]
		        }, {
		            title: '教师',
		            plugins: Ext.create('Ext.ux.PanelFieldDragZone'),
		            items: [{
			            xtype: 'textfield',
			            value: '小红老师',
			            ddSource: 'teacher'
			        },{
			            xtype: 'textfield',
			            value: '小白老师',
			            ddSource: 'teacher'
			        }]
		        }, {
		            title: '教室',
		            plugins: Ext.create('Ext.ux.PanelFieldDragZone'),
		            items: [{
			            xtype: 'textfield',
			            value: '大教室',
			            ddSource: 'room'
			        },{
			            xtype: 'textfield',
			            value: '小教室',
			            ddSource: 'room'
			        }]
		        }]
			}, {
				region: 'center',
				border: false,
				xtype: 'xgrid',
				rowNumberer: false,
				stripeRows: true,
				sortableColumns: false,
				cls: 'rowspan-grid',
				viewType: 'gridrowspanview',
				store: Ext.create('ems.biz.syllabus.data.store.SyllabusStore', {
					autoLoad: true
				}),
				plugins: [
					Ext.create('Ext.ux.CellFieldDropZone')
				],
				columns: [{
		            text: '课时',
		            dataIndex: 'lesson',
		            width: 50,
		            rowspan: 4
		        },{
		        	text: '班级',
		            dataIndex: 'classCode',
		            width: 70,
		            rowspan: 2
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
						ddTarget: 'course',
						renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
							if (value) {
								
							}
							return value + ' x';
						}
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'monTeacher',
						ddTarget: 'teacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'monRoom',
						ddTarget: 'room'
		            }]	
				},{
					text: '周二',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'tueCourse',
						ddTarget: 'course'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'tueTeacher',
						ddTarget: 'teacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'tueRoom',
						ddTarget: 'room'
		            }]	
				},{
					text: '周三',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'webCourse',
						ddTarget: 'course'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'webTeacher',
						ddTarget: 'teacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'webRoom',
						ddTarget: 'room'
		            }]	
				},{
					text: '周四',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'thuCourse',
						ddTarget: 'course'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'thuTeacher',
						ddTarget: 'teacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'thuRoom',
						ddTarget: 'room'
		            }]	
				},{
					text: '周五',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'friCourse',
						ddTarget: 'course'
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'friTeacher',
						ddTarget: 'teacher'
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'friRoom',
						ddTarget: 'room'
		            }]	
				}]
			}]
		}
	}
});