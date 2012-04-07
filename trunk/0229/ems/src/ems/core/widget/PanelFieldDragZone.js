// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.widget.PanelFieldDragZone', {
	extend: 'Ext.dd.DragZone',
	
	constructor: function(){
	},
	
	init: function(panel) {
		if (panel.nodeType) {
			ems.core.widget.PanelFieldDragZone.superclass.init.apply(this, arguments);
        } else {
            if (panel.rendered) {
                ems.core.widget.PanelFieldDragZone.superclass.constructor.call(this, panel.getEl());
                var i = Ext.fly(panel.getEl()).select('input');
                i.unselectable();
            } else {
                panel.on('afterlayout', this.init, this, {single: true});
            }
        }
    },

    scroll: false,

    getDragData: function(e) {
        var t = e.getTarget('input');
        if (t) {
            e.stopEvent();

            if (Ext.isOpera) {
                Ext.fly(t).on('mousemove', function(e1){
                    t.style.visibility = 'hidden';
                    Ext.defer(function(){
                        t.style.visibility = '';
                    }, 1);
                }, null, {single:true});
            }

            var f = Ext.ComponentQuery.query('field[inputEl]{inputEl.id=="' + t.id + '"}')[0];
            var d = document.createElement('div');
            d.className = 'x-form-text';
            d.appendChild(document.createTextNode(t.value));
            Ext.fly(d).setWidth(f.getEl().getWidth());
            return {
                field: f,
                value: f.getValue(),
                ddSource: f.ddSource,
                ddel: d
            };
        }
    },

    getRepairXY: function() {
        return this.dragData.field.getEl().getXY();
    }
});