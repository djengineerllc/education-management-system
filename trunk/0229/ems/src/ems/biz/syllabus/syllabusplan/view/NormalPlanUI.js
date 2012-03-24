Ext.define('ems.biz.syllabus.syllabusplan.view.NormalPlanUI', {
	extend: 'ems.core.UI',
	
	requires: [
		'Ext.tree.Panel',
		'ems.core.widget.GridSpanView',
		'ems.core.widget.IconBrowser',
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
			border: false,
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				height: 85,
				border: true,
				xtype: 'tabpanel',
			    activeTab: 0,
//			    tabPosition: 'bottom',
			    defaults: {
			    	border: false,
			    	autoScroll: true
			    },
			    listeners: {
			    	afterrender: function(comp) {
			    		var filterEl = Ext.createWidget('textfield', {
			    			itemId: 'filterInput',
			    			cls: 'input-filter',
			    			emptyText: '关键字',
			    			height: 18,
			    			width: 100,
			    			listeners: {
			    				change: function(input) {
			    					var iconBrowser = comp.getActiveTab().down('iconbrowser');
			    					iconBrowser.filter(iconBrowser.dataFilter, this.getValue());
			    				}
			    			}
			    		});
			    		comp.tabBar.add(filterEl);
			    	},
			    	tabchange: function(comp, card, preCard) {
			    		var filterInput = comp.down('#filterInput');
			    		
			    		preCard.filterInputValue = filterInput.getValue();
			    		filterInput.setValue(card.filterInputValue || '');
			    		filterInput.getEl().focus();
			    	}
			    },
			    items: [{
		            title: '课程',
		            items: {
			    		xtype: 'iconbrowser',
			    		ddSource: 'course',
			    		dataIndex: 'name',
			    		dataFilter: 'name',
			    		store: Dic.getStore('Sex'),
			    		prepareData: function(data) {
			                Ext.apply(data, {
			                    url: 'src/ems/core/resources/css/images/icons/users_16.png'
			                });
			                return data;
			            }
			    	}
		        }, {
		            title: '教师',
		            items: {
			    		xtype: 'iconbrowser',
			    		ddSource: 'teacher',
			    		dataIndex: 'name',
			    		dataFilter: 'name',
			    		store: Dic.getStore('Class'),
			    		prepareData: function(data) {
			                Ext.apply(data, {
			                    url: 'src/ems/core/resources/css/images/icons/users_16.png'
			                });
			                return data;
			            }
			    	}
		        }, {
		            title: '教室',
		            items: {
			    		xtype: 'iconbrowser',
			    		ddSource: 'room',
			    		dataIndex: 'name',
			    		dataFilter: 'name',
			    		store: Dic.getStore('Grade'),
			    		prepareData: function(data) {
			                Ext.apply(data, {
			                    url: 'src/ems/core/resources/css/images/icons/users_16.png'
			                });
			                return data;
			            }
			    	}
		        }]
			}, {
				region: 'center',
				border: false,
				xtype: 'xgrid',
				rowNumberer: false,
				stripeRows: true,
				sortableColumns: false,
				cls: 'rowspan-grid',
				viewType: 'gridspanview',
				store: Ext.create('ems.biz.syllabus.data.store.SyllabusStore', {
					autoLoad: true
				}),
				plugins: [
					Ext.create('ems.core.widget.CellFieldDropZone')
				],
				columns: [{
		            text: '课时',
		            dataIndex: 'lesson',
		            width: 50,
		            rowSpan: 4
		        },{
		        	text: '班级',
		            dataIndex: 'classCode',
		            width: 70,
		            rowSpan: 2
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
						ddTarget: 'course'
//						,renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
//							if (value) {
//								
//							}
//							return value + ' x';
//						}
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