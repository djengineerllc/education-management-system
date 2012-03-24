Ext.define('ems.core.widget.IconBrowser', {
	extend: 'Ext.view.View',
	alias: 'widget.iconbrowser',
	
	cls: 'iconbrowser-view',
	
	singleSelect: true,
	trackOver: true,
	overItemCls: 'x-view-over',
	itemSelector: 'div.icon-browser-wrap',
	tpl: [
		'<tpl for=".">',
			'<div class="icon-browser-wrap">',
				'<div class="icon-browser">',
				(!Ext.isIE6? '<img src="{url}" />' : '<div style="width:16px;height:16px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\'{url}\')"></div>'),
				'</div>',
				'<span>{name}</span>',
			'</div>',
		'</tpl>'
    ],
	
	ddSource: null,
	dataIndex: 'id',
	
	listeners: {
		render: function(comp) {
			comp.dragZone = Ext.create('Ext.dd.DragZone', comp.getEl(), {
				hasOuterHandles: true,
				getDragData: function(e) {
					var sourceEl = e.getTarget(comp.itemSelector, comp.getEl()), d, data;
					if (sourceEl) {
						d = sourceEl.cloneNode(true);
						d.id = Ext.id();
						
						data = comp.getRecord(sourceEl).data;
						
						return comp.dragData = {
							sourceEl: sourceEl,
							repairXY: Ext.fly(sourceEl).getXY(),
							ddel: d,
							data: data,
							ddSource: comp.ddSource,
							value: data[comp.dataIndex]
						};
					}
				},
				
				getRepairXY: function() {
					return this.dragData.repairXY;
				}
			});
		},
		
		beforedestroy: function() {
			this.store.clearFilter();
		}
 	},
	
	filter: function(field, newValue) {
		var me = this,
			s = me.store;
		
		s.suspendEvents();
		s.clearFilter();
		me.getSelectionModel().clearSelections();
		s.resumeEvents();
		s.filter({
			property: field,
			anyMatch: true,
			value: newValue
		});
	}
});