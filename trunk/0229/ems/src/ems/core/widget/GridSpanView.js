Ext.define('ems.core.widget.TableSpanChunker', {
    extend: 'Ext.view.TableChunker.self',
    singleton: true,
	metaRowTpl: [
		'<tr class="' + Ext.baseCSSPrefix + 'grid-row {addlSelector} {[this.embedRowCls()]}" {[this.embedRowAttr()]}>',
			'<tpl for="columns">',
				'{[this.openRowspan(values)]}',
				'<td class="{cls} ' + Ext.baseCSSPrefix + 'grid-cell ' + Ext.baseCSSPrefix + 'grid-cell-{columnId} {{id}-modified} {{id}-tdCls} {[this.firstOrLastCls(xindex, xcount)]}" {{id}-tdAttr} {[this.embedExtendTdAttr(values)]}><div unselectable="on" class="' + Ext.baseCSSPrefix + 'grid-cell-inner ' + Ext.baseCSSPrefix + 'unselectable" style="{{id}-style}; text-align: {align};">{{id}}</div></td>',
				'{[this.closeRowspan()]}',
     		'</tpl>',
     	'</tr>'
     ],
     
     getTableTpl: function(cfg, textOnly) {
		var tpl,
			tableTplMemberFns = {
				openRows: this.openRows,
				closeRows: this.closeRows,
				embedFeature: this.embedFeature,
				embedFullWidth: this.embedFullWidth,
				openTableWrap: this.openTableWrap,
				closeTableWrap: this.closeTableWrap
			},
			tplMemberFns = {},
			features = cfg.features || [],
			ln = features.length,
			i  = 0,
			memberFns = {
				embedRowCls: this.embedRowCls,
				embedRowAttr: this.embedRowAttr,
				firstOrLastCls: this.firstOrLastCls,
				openRowspan: this.openRowspan,
         		closeRowspan: this.closeRowspan,
         		embedExtendTdAttr: this.embedExtendTdAttr
             },
             // copy the default
             metaRowTpl = Array.prototype.slice.call(this.metaRowTpl, 0),
             metaTableTpl;
		
		for (; i < ln; i++) {
			if (!features[i].disabled) {
				features[i].mutateMetaRowTpl(metaRowTpl);
				Ext.apply(memberFns, features[i].getMetaRowTplFragments());
				Ext.apply(tplMemberFns, features[i].getFragmentTpl());
				Ext.apply(tableTplMemberFns, features[i].getTableFragments());
			}
		}
		
		metaRowTpl = Ext.create('Ext.XTemplate', metaRowTpl.join(''), memberFns);
		cfg.row = metaRowTpl.applyTemplate(cfg);
		
		metaTableTpl = Ext.create('Ext.XTemplate', this.metaTableTpl.join(''), tableTplMemberFns);
		
		tpl = metaTableTpl.applyTemplate(cfg);
		
		// TODO: Investigate eliminating.
		if (!textOnly) {
			tpl = Ext.create('Ext.XTemplate', tpl, tplMemberFns);
		}
		return tpl;
     },
     openRowspan: function(values) {
		var rowspan = values.id.replace(/-/g, '') + 'RowSpan';
		return '<tpl if="(_rowIndex % ' + rowspan + ') == 0">'; // {[xindex]}  _rowIndex  (xindex - 1)
	},
	closeRowspan: function() {
		return '</tpl>';
	},
	embedExtendTdAttr: function(values) {
		var rowspan = values.id.replace(/-/g, '') + 'RowSpan';
		return 'rowSpan="{' + rowspan + '}"';
	}
});

Ext.define('ems.core.widget.GridSpanView', {
    extend: 'Ext.grid.View',
    alias: 'widget.gridspanview',
    
    chunker: ems.core.widget.TableSpanChunker
});