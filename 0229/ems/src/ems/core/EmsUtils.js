// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.EmsUtils', {
	singleton: true,
	alternateClassName: 'EU',
	
	requires: [
		'Ext.window.MessageBox'
//		,'Ext.window.Window'
	],
	
	showDialog: function(config) {
		if (config && Ext.isIE) {
			delete config.animateTarget;
		}
		
		return Ext.MessageBox.show(config || {
			width: 360
		}); 
//		Ext.apply(config || {}, {
//			autoHeight: true,
//			autoWidth: true,
//			autoScroll: true
//		})).doLayout();
	},
	
	showProgress: function(config) {
		return this.showDialog(Ext.applyIf(config || {}, {
			title: Ems.config.waitMsg,
            progress: true,
            closable: false
		}));
	},
	updateProgress: function(currValue, totalValue) {
        if (arguments.length == 0) {
            Ext.MessageBox.hide();
        } else {
            var i = currValue / totalValue;
            Ext.MessageBox.updateProgress(i, '完成 ' + Math.round(100*i)+'%');
        }
	},
	
	showInfoDialog: function(config) {
		config = Ext.apply({
			title: "信息",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.INFO
		}, config);
		return this.showDialog(config);
	},
	showWarningDialog: function(config) {
		config = Ext.apply({
			title: "警告",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.WARNING
		}, config);
		return this.showDialog(config);
	},
	showConfirmDialog: function(config) {
		config = Ext.apply({
			title: "确认",
			buttons: Ext.MessageBox.YESNO,
			icon: Ext.MessageBox.QUESTION
		}, config);
		return this.showDialog(config);
	},
	showErrorDialog: function(config) {
		config = Ext.apply({
			title: "错误",
			buttons: Ext.MessageBox.OK,
			icon: Ext.MessageBox.ERROR
		}, config);
		return this.showDialog(config);
	},
	showSuccessDialog: function(config) {
		config = Ext.apply({
			title: "成功",
			buttons: Ext.MessageBox.OK,
			icon: 'ext-mb-success'
		}, config);
		return this.showDialog(config);
	},
	
	showMsg: function(title, format){
		var me = this;
        if(!me.msgCt){
            me.msgCt = Ext.DomHelper.insertFirst(document.body, {'class': 'global-message'}, true);
			me.msgTpl = [
				'<div class="msg">',
					'<h3>{0}</h3>',
					'<p>{1}</p>'
			].join('');
        }
		
        var text = Ext.String.format.apply(String, Array.prototype.slice.call(arguments, 1)),
			msgHtml = Ext.String.format(me.msgTpl, title, text),
			msg = Ext.DomHelper.append(me.msgCt, msgHtml, true);
        msg.hide();
        msg.slideIn('t').ghost("t", { delay: 3000, remove: true});
    },
	
//	,showWindow: function(config) {
//		return Ext.create('Ext.window.Window', config).show();
//	},
    
    exception: function(message) {
    	if (Ext.isString(message)) {
    		var msgJson = (/{(.*)}/.exec(message) || '')[0]; // TODO regex
    		if (msgJson) {
    			message = Ext.decode(msgJson);
    		}
    	}
    	
    	if (Ext.isObject(message)) {
    		var errorCode = message.errorCode;
    		if (errorCode == 'AccessDenied') {
    			EU.showMsg('', message.errorMsg || errorCode);
    			Ems.requestViewportModule();
    		}
    	} else {
    		this.showErrorDialog({
				title: '系统异常',
				msg: message//Ext.String.format('Call to {0}.{1} failed with message:<xmp>{2}</xmp>', tx.action, tx.method, e.message)
			});
    	}
	},

	PRINT_FRAME_ID: '__print_frame__',
	print: function(content) {
		var me = this, win, doc;
		if (Ext.isIE) {
			win = window.open('', '__print_window__', 'toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		} else {
			var printFrameDom = (Ext.fly(me.PRINT_FRAME_ID) || {}).dom;
			if (!printFrameDom) {
				printFrameDom = Ext.DomHelper.append(Ext.getBody().dom, {
					tag: 'iframe',
					id: me.PRINT_FRAME_ID,
					frameBorder: 0,
					style: 'width:1px;height:1px;position:absolute;right:0;bottom:0;border:none;overflow:hidden;visibility:hidden'
				});
			}
			win = printFrameDom.contentWindow;
		}
		
		doc = win.document;
		doc.open();
		doc.write(content);
		doc.close();
		win.focus();
		win.print();
		win.close();
	},
	
	printHtmlTpl: Ext.create('Ext.Template', [
		'<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">',
		'<html xmlns=\"http://www.w3.org/1999/xhtml\">',
			'<head><title>{title}</title>{head}</head>',
			'<body class=\"ems\">{body}</body>',
		'</html>'
	]),
	printHtml: function(content, title) {
		var me = this,
			html = me.printHtmlTpl.apply({
				title: title,
				head: '<style type="text/css">' + 
						'*{color: #000 !important;}' + 
						'a{text-decoration: none !important;}' + 
						'body{color:black;font-size:9px;font-family:tahoma, arial, verdana, sans-serif}' +
					  '</style>',
				body: content
			});
		me.print(html);
	},
	
	printer: function(pageArgs, pageFn) {
		return {
			PAGE_SEPARATOR: '<div style="page-break-after: always;"></div>',
			pages: [],
			
			addPage: function(page) {
				this.pages.push(page);
			},
			
			print: function() {
				EU.showProgress();
				Ext.each(pageArgs, function(pageArg, index) {
					Ext.callback(pageFn, this, [pageArg, Ext.bind(this.addPage, this)]);
					EU.updateProgress(index + 1, pageArgs.length);
				}, this);
				EU.updateProgress();
				
				EU.printHtml(this.pages.join(this.PAGE_SEPARATOR));
			}
		};
	}
});