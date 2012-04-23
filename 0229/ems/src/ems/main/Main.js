Ext.define('ems.main.Main', {
	extend: 'ems.core.Module',
	requires: [
		'Ext.layout.container.Border',
		'Ext.tab.Panel',
		'Ext.form.field.ComboBox'
	],
	uses: [
		'ems.main.MainActions',
		'ems.main.data.store.MenuStore',
		'ems.main.MainUI'
	],
	
	currBulletinIndex: 0,
	bulletins: [{
		id: 1,
		publishDate: '2012-1-1 12:12:00',
		title: '中午食堂免费大餐',
		content: ''
	}, {
		id: 2,
		publishDate: '2012-2-2 22:22:00',
		title: '五一放假九天',
		content: ''
	}],
	
	constructor: function() {
		var me = this;
		me.overrideExtClassess();
		me.callParent(arguments);
	},
	
	init: function() {
		var me = this;
		me.callParent(arguments);
		
		Ext.require([
			'ems.biz.base.Requires'
		]);
		
		Ext.defer(function() {
			me.RV('HomeUI', {
	            renderTo: me.down('#homeWorkItem')
	        });
		}, 100);
		
		me.updateBulletinTask = {
			run: me.updateBulletinTaskRun,
			args: [Ext.getCmp('bulletinBoard')],
			scope: me,
			interval: 5000
		};
		Ext.TaskManager.start(me.updateBulletinTask);
	},
	destroy: function() {
		var me = this;
		Ext.TaskManager.stop(me.updateBulletinTask);
		me.callParent(arguments);
	},
	
	updateBulletinTaskRun: function(bulletinBoard) {
		var me = this,
			bulletin = me.bulletins[me.currBulletinIndex];
		bulletinBoard.setStatus({
			text: Ext.String.format('<a href="#{0}">{1} [{2}]</a>', bulletin.id, bulletin.title, bulletin.publishDate)
		});
		if (++me.currBulletinIndex == me.bulletins.length) {
			me.currBulletinIndex = 0;
		}
	},
	
	loadMenu: function(params, request) {
		var me = this,
			r = request,
			p = r.p,
			pc = p.panelConfig || {},
			eo = r.eventSource,
			menuId = eo.menuId || -1;
		
		me.A({
			m: 'getMenu',
			p: menuId,
			cb: function(result, event) {
				Ext.each(result, function(item) {
					var treePanel = Ext.merge({}, pc, {
						store: Ext.create('ems.main.data.store.MenuStore', {
							defaultRootId: item.id,
							autoLoad: false
						})
					});
					item.items = [treePanel];
					
					var menuItem = eo.add(me._toMenuItem(item));
//					menuItem.on
				}, me);
			},
			s: me
		});
	},
	
	getWorkspace: function() {
		return Ext.getCmp('workspace');
	},
	
	addWorkItem: function(params, request) {
		var me = this,
			ea = request.eventArgs,
			record = ea ? ea[1] : {},
			workItemConfig = Ext.apply({}, params),
			moduleId = workItemConfig.moduleId = (workItemConfig.moduleId || record.get('moduleId'));
		if (!moduleId) {
			return;
		}
		
		var workspace = me.getWorkspace(),
			workItem = workspace.getChildByElement(moduleId);
		if (workItem) {
			me.activeWorkItem(moduleId, request);
			return;
		}
		
		Ext.applyIf(workItemConfig, {
			moduleConfig: (record.get ? (Ext.decode(record.get('moduleConfig') || '{}')) : null),
			id: moduleId,
			title: record.get ? (record.get('moduleName') ? record.get('moduleName') : record.get('text')) : '',
			autoScroll: true
//			,
//			tabConfig: {
//				minWidth: 90
//			}
		});
//		Ems.RM(moduleId, workItemConfig.moduleConfig, function(module) {
//			workItemConfig.items = [module.ui];
//			workItem = workspace.add(workItemConfig); // add workItem(tab)
//			workItem.on('beforedestroy', me._doDestroyWorkItem, this);
//			
//			me.activeWorkItem(workItem, request);
//		}, me);
		
		workItemConfig.items = [];
		workItem = workspace.add(workItemConfig); // add workItem(tab)
		workItem.on('beforedestroy', me._doDestroyWorkItem, me);
		
		Ems.mask();
		Ext.defer(function() {
			Ems.RM(moduleId, workItemConfig.moduleConfig, function(module) {
				workItem.add(module.ui);
				me.activeWorkItem(workItem, request);
				Ems.unmask();
			}, me);
		}, 10, me); // 120
	},
	_doDestroyWorkItem: function(workItem) {
		var moduleId = workItem.moduleId;
		Ems.destroyModule(moduleId);
	},
	
	onWorkItemChange: function(params, request) {
		var me = this,
			ea = request.eventArgs, 
			tabPanel = ea[0], 
			workItem = ea[1], 
			prevWorkItem = ea[2],
			moduleId = workItem.moduleId;
		
		if (moduleId) {
			Ems.MR(moduleId, {
				m: 'activate',
				p: request
			});
		}
	},
	getActiveWorkItem: function() {
		return this.getWorkspace().getActiveTab();
	},
	
	activeWorkItem: function(moduleId, request) {
		var me = this,
			moduleId = moduleId,
			workspace = me.getWorkspace(),
			workItem;
		
		if (Ext.isString(moduleId)) {
			workItem = workspace.getChildByElement(moduleId);
		} else {
			moduleId = moduleId.moduleId;
			workItem = moduleId;
		}
		
		if (workItem) {
			workspace.setActiveTab(workItem);
			Ems.MR(moduleId, {
				m: 'activate',
				p: request
			});
		}
	},
	
	_toMenuItem: function(item, listeners) {
		var me = this,
			ls = listeners || {};
		item.text = (item.textKey ? me.locVal(item.textKey) : item.text) || '',
		item.title = (item.titleKey ? me.locVal(item.titleKey) : item.title) || item.text;
		item.listeners = ls;
		
		return item;
	},
	
	onLogout: function (params, request) {
		var me = this,
			eo = request.eventSource;
		
		EU.showConfirmDialog({
			animateTarget: eo.el,
			msg: '您确认退出系统？',
			callback: function(btnId, value) {
				if (btnId == 'yes') {
					Ems.MR('ems.login.Login', {
						m: 'logout'
					});
				}
			}
		});
    },
	
	onSwitchRole: function(params, request) {
		alert('切换角色'); // TODO 
	},
	
	overrideExtClassess: function() {
		Ext.form.field.ComboBox.override({
			getSelectedIndex: function() {
				var value = this.getValue(),
					record = this.findRecord(this.valueField || this.displayField, value);
				return this.store.indexOf(record);
			}
		});
		
		Ext.grid.header.Container.override({
			/**
		     * Maps the record data to base it on the header id's.
		     * This correlates to the markup/template generated by
		     * TableChunker.
		     */
		    prepareData: function(data, rowIdx, record, view, panel) {
		        var obj       = {},
		            headers   = this.gridDataColumns || this.getGridColumns(),
		            headersLn = headers.length,
		            colIdx    = 0,
		            header,
		            headerId,
		            renderer,
		            value,
		            metaData,
		            store = panel.store;

		        for (; colIdx < headersLn; colIdx++) {
		            metaData = {
		                tdCls: '',
		                style: ''
		            };
		            header = headers[colIdx];
		            headerId = header.id;
		            renderer = header.renderer;
		            value = data[header.dataIndex];

		            // When specifying a renderer as a string, it always resolves
		            // to Ext.util.Format
		            if (typeof renderer === "string") {
		                header.renderer = renderer = Ext.util.Format[renderer];
		            }

		            if (typeof renderer === "function") {
		                value = renderer.call(
		                    header.scope || this.ownerCt,
		                    value,
		                    // metadata per cell passing an obj by reference so that
		                    // it can be manipulated inside the renderer
		                    metaData,
		                    record,
		                    rowIdx,
		                    colIdx,
		                    store,
		                    view
		                );
		            }

		            // <debug>
		            if (metaData.css) {
		                // This warning attribute is used by the compat layer
		                obj.cssWarning = true;
		                metaData.tdCls = metaData.css;
		                delete metaData.css;
		            }
		            // </debug>
		            
		            // START: add by chiknin
		            obj['_rowIndex'] = rowIdx;
		            obj[headerId.replace(/-/g, '')+'RowSpan'] = header.rowSpan || 1; // TODO header.rowSpan Array
		            obj[headerId+'-tdCls'] = metaData.tdCls + (header.rowSpan ? Ext.baseCSSPrefix + 'grid-cell-rowspan grid-cell-rowspan-' + header.rowSpan : '');
		            // END: add by chiknin

		            obj[headerId+'-modified'] = record.isModified(header.dataIndex) ? Ext.baseCSSPrefix + 'grid-dirty-cell' : '';
//		            obj[headerId+'-tdCls'] = metaData.tdCls; // remove by chiknin
		            obj[headerId+'-tdAttr'] = metaData.tdAttr;
		            obj[headerId+'-style'] = metaData.style;
		            if (value === undefined || value === null || value === '') {
		                value = '&#160;';
		            }
		            obj[headerId] = value;
		        }
		        return obj;
		    }
		});
		
		Ext.panel.Table.override({
			getColumn: function(columnIndex, rowIndex) { // TODO array
				var me = this, i = 0, column, col, col_i, subCol, subCol_i, colIndex = (columnIndex + 1);
	        	outer: for (col_i in me.columns) {
	        		col = me.columns[col_i];
	        		
	        		if (rowIndex != undefined && col.rowSpan > 1) {
	        			if (rowIndex % col.rowSpan != 1) {
	        				colIndex++;
	        			}
	        		}
	        		
	        		if (col.headerCounter > 0) {
	        			for (subCol_i in col.items.items) {
	        				subCol = col.items.items[subCol_i];
	        				if (++i == colIndex) {
	        					column = subCol;
	        					break outer;
	        				}
	        			}
	        		} else {
	        			if (++i == colIndex) {
	        				column = col;
	    					break;
	    				}
	        		}
	        	}
	        	
	        	return column;
			},
			
			getUpdatedData: function() {
				var me = this,
					s = me.store,
					data = [], r;
					
				Ext.each(s.getUpdatedRecords(), function(record) {
					r = {};
					for (var r_f in record.data) {
						if (!Ext.isEmpty(record.data[r_f])) {
							r[r_f] = record.data[r_f];
						}
					}
					data.push(r);
				});
				
				return data;
			},
			
			getData: function() {
				var me = this,
					s = me.store,
					data = [], r, hasData;
					
				Ext.each(s.getRange(), function(record) {
					r = {};
					hasData = false
					for (var r_f in record.data) {
						if (!Ext.isEmpty(record.data[r_f])) {
							r[r_f] = record.data[r_f];
							hasData = true;
						}
					}
					
					if (hasData) {
						data.push(r);
					}
				});
				
				return data;
			}
		});
	}
});