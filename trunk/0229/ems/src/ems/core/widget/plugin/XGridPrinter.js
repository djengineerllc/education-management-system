// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.widget.plugin.XGridPrinter', {
	alias: 'plugin.xgridprinter',
	
	tableTpl: Ext.create('Ext.XTemplate', [
		'<table border="1" cellpadding="0" cellspacing="0" class="print-table">',
			'<thead>',
				'{head}',
			'</thead>',
			'<tbody>',
				'{body}',
			'</tbody>',
		'</table>'
	]),
	trTpl: Ext.create('Ext.XTemplate', [
		'<tr>',
			'<tpl for=".">',
				'<td>{text}</td>',
			'</tpl>',
		'</tr>'
	]),
	
	init: function(grid) {
		var me = this;
		grid.print = function() {
			me.print(grid);
		};
	},
	
	print: function(g) { // rowspan / colspan not support
		var me = this,
			cols = me.getVisibleColumn(g),
			headHtml = me.trTpl.apply(cols), 
			bodyHtml = [],
			tableHtml;
			
		Ext.each(g.getStore().getRange(), function(r) {
			var trData = [];
			Ext.each(cols, function(col) {
				trData.push({
					text: (col.renderer ? col.renderer(r.data[col.dataIndex], null, r) : r.data[col.dataIndex])
				});
			});

			bodyHtml.push(me.trTpl.apply(trData));
		});
		bodyHtml = bodyHtml.join('');
		
		tableHtml = me.tableTpl.apply({
			head: headHtml,
			body: bodyHtml
		});
		
		EU.printHtml(tableHtml);
	},
	
	getVisibleColumn: function(g) {
		var cols = [];
		Ext.each(g.columns, function(column) {
			if (column.dataIndex && column.hidden != true) {
				cols.push(column);
			}
		});
		
		return cols;
	}
});