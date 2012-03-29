Ext.define('ems.biz.syllabus.syllabusplan.view.NormalPlanUI', {
	extend: 'ems.core.UI',
	
	requires: [
		'Ext.tree.Panel',
		'ems.core.widget.GridSpanView',
		'ems.core.widget.IconBrowser'
	],
	
	initData: function() {
		var me = this,
			sltTeam = me.reqParams[0],
			syllabusPlanGrid = me.down('#syllabusPlanGrid');
		
		syllabusPlanGrid.store.load({
			params: sltTeam
		});
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
				cls: 'icon-browser-tab-panel',
			    activeTab: 0,
//			    tabPosition: 'bottom',
			    defaults: {
			    	border: false,
			    	autoScroll: true
			    },
			    listeners: {
			    	afterrender: function(comp) {
			    		var filterInput = Ext.createWidget('textfield', {
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
			    		comp.tabBar.add(filterInput);
			    	},
			    	tabchange: function(comp, card, preCard) {
			    		var filterInput = comp.down('#filterInput');
			    		
			    		preCard.filterInputValue = filterInput.getValue();
			    		filterInput.setValue(card.filterInputValue || '');
//			    		filterInput.focus(false, 10);
			    	}
			    },
			    items: [{
		            title: '课程',
		            items: {
			    		xtype: 'iconbrowser',
			    		border: false,
			    		ddSource: 'course',
			    		dataIndex: 'value',
			    		dataFilter: 'name',
			    		store: Dic.getStore('Course'),
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
			    		border: false,
			    		ddSource: 'teacher',
			    		dataIndex: 'value',
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
		            title: '教室',
		            items: {
			    		xtype: 'iconbrowser',
			    		border: false,
			    		ddSource: 'room',
			    		dataIndex: 'value',
			    		dataFilter: 'name',
			    		store: Dic.getStore('Room'),
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
				itemId: 'syllabusPlanGrid',
				rowNumberer: false,
				stripeRows: true,
				sortableColumns: false,
				viewType: 'gridspanview',
				loadDF: me.DF('loadSyllabusPlanDetail'),
				plugins: [
					Ext.create('ems.core.widget.CellFieldDropZone')
				],
				columns: [{
					text: 'ID',
		            dataIndex: 'id',
		            hidden: true
				},{
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
		            renderer: Dic.renderer('WeekOeInd'),
		            width: 50
				},{
					text: '周一',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'monCourse',
						ddTarget: 'course',
						renderer: Dic.renderer('Course')
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'monTeacher',
						ddTarget: 'teacher',
						renderer: Dic.renderer('Teacher')
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'monRoom',
						ddTarget: 'room',
						renderer: Dic.renderer('Room')
		            }]
				},{
					text: '周二',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'tueCourse',
						ddTarget: 'course',
						renderer: Dic.renderer('Course')
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'tueTeacher',
						ddTarget: 'teacher',
						renderer: Dic.renderer('Teacher')
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'tueRoom',
						ddTarget: 'room',
						renderer: Dic.renderer('Room')
		            }]	
				},{
					text: '周三',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'webCourse',
						ddTarget: 'course',
						renderer: Dic.renderer('Course')
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'webTeacher',
						ddTarget: 'teacher',
						renderer: Dic.renderer('Teacher')
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'webRoom',
						ddTarget: 'room',
						renderer: Dic.renderer('Room')
		            }]	
				},{
					text: '周四',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'thuCourse',
						ddTarget: 'course',
						renderer: Dic.renderer('Course')
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'thuTeacher',
						ddTarget: 'teacher',
						renderer: Dic.renderer('Teacher')
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'thuRoom',
						ddTarget: 'room',
						renderer: Dic.renderer('Room')
		            }]	
				},{
					text: '周五',
		            columns: [{
		                text: '课程',
		                width: 120,
						dataIndex: 'friCourse',
						ddTarget: 'course',
						renderer: Dic.renderer('Course')
		            }, {
		                text: '教师',
		                width: 70,
						dataIndex: 'friTeacher',
						ddTarget: 'teacher',
						renderer: Dic.renderer('Teacher')
		            }, {
		                text: '教室',
		                width: 70,
						dataIndex: 'friRoom',
						ddTarget: 'room',
						renderer: Dic.renderer('Room')
		            }]	
				}]
			}]
		}
	}
});