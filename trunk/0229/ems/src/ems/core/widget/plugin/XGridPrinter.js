// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.widget.plugin.XGridPrinter', {
	alias: 'plugin.xgridprinter',
	
	init: function(grid) {
		var me = this;
		grid.print = function() {
			me.print(grid);
		};
	},
	
	print: function(grid) {
		
	},
	
	
	
	
	
	columns:null,
	headerCtl:null,
	maxrow:0,
	headings:'',
	
	init: function(grid) {
		var me = this;
		grid.print = function() {
			me.print(grid);
		};
	},
	
	bodyTpl: [
		'<tr>',
			'<tpl for=".">',
				'<td>\{{dataIndex}\}</td>',
			'</tpl>',
		'</tr>'
	],
	
	print: function(grid) {
		var me=this;
		me.columns = [];
		me.headerCtl = [];
		me.maxrow = 0;
		me.headings = '';
		
		me.initColumns(grid.columns);
		var columns = this.columns;
		me.headings=me.initHeader(grid.columns);
		var data = [];
		grid.store.data.each(function(item) {
			var convertedData = [];
			for (var key in item.data) {
				var value = item.data[key];

				Ext.each(columns, function(column) {
					if (column.dataIndex == key) {
						convertedData[key] = column.renderer ? column.renderer(value) : value;
					}
				}, this);
			}

			data.push(convertedData);
		});
		var body     = Ext.create('Ext.XTemplate', this.bodyTpl).apply(columns);
		
		var htmlMarkup = [
		    '<table class="print-table">'+this.headings+
		      '<tpl for=".">',
		        body,
		      '</tpl>',
		    '</table>'
		];
		var html = Ext.create('Ext.XTemplate', htmlMarkup).apply(data);
		EU.printHtml(html);
	},
	
	initColumns:function(columns){
		var me=this;
		Ext.each(columns,function(column){
			if(column.dataIndex){
				me.columns.push(column);
			}
			else{
				me.initColumns(column.items.items);
			}
		});
	},
		
	initHeaderCtl:function(columns,index,maxrow){
		var me=this;
		var html='<tr align=center>';
		var colspan=0;
		Ext.each(columns,function(column){
			if(column.dataIndex){
				if(maxrow-index>1)html+='<th rowspan='+eval(maxrow-index)+'>'+column.text+'</th>';
				else html+='<th>'+column.text+'</th>';
				colspan++;
			}
			else{
				var temp=me.initHeaderCtl(column.items.items,index+1,maxrow);
				html+='<th colspan='+temp+'>'+column.text+'</th>';
				colspan+=temp;
			}
			
		});
		html+='</tr>';
		var temp=this.headerCtl[index];
		if(temp!=null && temp!=''){
			html=temp+html;
		}
		this.headerCtl[index]=html;
		return colspan;
	},
	
	initHeader:function(columns){
		var me=this;
		me.getMaxRows(columns,1);
		me.initHeaderCtl(columns,0,me.maxrow);
		var headerCtls=me.headerCtl;
		var headings='';
		Ext.each(headerCtls,function(headerCtl){
			headings+=headerCtl;
		});
		return headings;
	},
	getMaxRows:function(columns,rowIndex){
		var me=this;
		Ext.each(columns,function(column){
			if(column.dataIndex){
				var temp=me.getMaxRows(column.items.items,rowIndex+1);	
			}
		});
		if(rowIndex>me.maxrow){
			me.maxrow=rowIndex;
		}
		
		return me.maxrow;
	}
});