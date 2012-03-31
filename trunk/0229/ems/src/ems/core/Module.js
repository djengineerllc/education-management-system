// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.Module', { // Controller
	
	extend: 'Ext.util.Observable',
//	mixins: {
//		observable: 'Ext.util.Observable',
//		animate: 'Ext.util.Animate',
//		state: 'Ext.state.Stateful'
//	},
	
	id: null,
	
	app: null,
	
	silent: false,
	
	autoLoadLocale: false,
	locale: null,
	
	autoLoadActions: true,
	actions: null,
	actionsClassSuffix: 'Actions',
	
	ui: null,
	segmentUI: true,
	uiClassSuffix: 'UI',
	uiIdPrefix: null,
	
	autoLoadCss: false,
	
//	depends: ['ems.biz.common.xxx'],

	constructor: function() {
		var me = this;
		me.callParent(arguments);
		
		me.uiIdPrefix = me.$className.substring(0, me.$className.lastIndexOf('.')).replace(/\./g, '-').toLowerCase() + '-';
	},
	
	init: function() {
		var me = this;
		me.addEvents(
			'beforeHandlerRequest'
		);
		
		if (me.autoLoadActions) {
			var actionsClassName = me.getActionsClassName();
			try {
				me.actions = Ext.create(actionsClassName);
			} catch (e) {
				Ext.Error.raise({
					sourceClass: me.$className,
					sourceMethod: 'init',
					msg: 'create action[' + actionsClassName + '] error: ' + e.message
                });
			}
		};
		if (me.silent === false) {
			me.createUI();
		}
	},
	getActionsClassName: function() {
		return (this.$className + this.actionsClassSuffix);
	},
	
	activate: function(params) {
		
	},
	
	destroy: function() {
		var me = this;
		me.actions && me.actions.destroy();
		if (me.ui) {
//			me.ui.el.unmask();
			Ext.destroy(me.ui);
			me.ui = null;
		}
	},
	destroyUI: function() {
		var me = this;
		if (me.ui) {
			Ext.destroy(me.ui);
			me.ui = null;
		}
	},
	
	MR: function(requests) {
		Ems.MR(this.id, requests);
	},
	
	MRA: function(event, request) {
		return Ems.MRA(event, this.id, request);
	},
	
	A: function(request) {
		Ems.A(this.id, request);
	},
	
	DF: function(method, action) {
		return Ems.DF(this.id, method, action);
	},
	
	action: function(actionRequest, moduleRequest) { // caller -> handleRequest()
		var me = this,
			ar = actionRequest,
			a = ar.action ? me.actions[ar.action] : me.actions,
			m = a[ar.m],
			p = ar.p ? (Ext.isArray(ar.p) ? ar.p : [ar.p]) : [],
			cb = ar.cb;
//			async = ar.async;
			
		if (cb) {
			var s = ar.s || me;
			p = p.concat(function(result, e) {
				var tx = e.getTransaction();
				if (!e.status) {
//					EU.showErrorDialog({
//						title: '系统异常',
//						msg: Ext.String.format('Call to {0}.{1} failed with message:<xmp>{2}</xmp>', tx.action, tx.method, e.message)
//					});
					EU.exception(e.message);
					return;
				};
				
				cb.apply(s, [result, e]);
				
//				me.ui && me.ui.el && me.ui.el.unmask();
			}).concat(s);
		};
//		if (async === false) {
//			p.push(false); // 同步
//		}
		
//		me.ui.el.mask('请求处理中，请稍后...');
		m.apply(a, p);
	},
	
	createUI: function(config) {
		var me = this;
		if (me.silent == false && me.segmentUI == true) {
			var uiClassName = me.getUIClassName();
			config = Ext.merge(me.renderTo ? {renderTo: me.renderTo} : {}, me._moduleUIConfig(), config);
			try {
				me.ui = Ext.create(uiClassName, config);
			} catch (e) {
				Ext.Error.raise({
					sourceClass: me.$className,
					sourceMethod: 'createUI',
					msg: 'create ui[' + uiClassName + '] error: ' + e.message
                });
			}
		};
		
		return me.uEl;
	},
	getUIClassName: function() {
		return (this.$className + this.uiClassSuffix);
	},
	
	renderView: function(viewId, viewConfig, position) {
		var me = this,
			viewConfig = Ext.apply({}, me._moduleUIConfig(), viewConfig),
			position = position || 'place',
			renderTo = (viewConfig.renderTo || me.ui.id); // || Ext.getBody()
		delete viewConfig.renderTo;
		
		viewId = me._getViewId(viewId);
		Ext.apply(viewConfig, {
			id: viewId // TODO viewId conflict
		});
		
		var refCmp = renderTo.isComponent ? renderTo : Ext.getCmp(renderTo);
		if (position == 'place') {
			refCmp.removeAll(true);
		} else {
			Ext.destroy(Ext.getCmp(viewId));
		}
		
		var view = Ext.create(viewId, viewConfig);
		refCmp.add(view);
		
		return view;
		
//		Ext.defer(function() {
//			var view = Ext.create(viewId, viewConfig);
//			refCmp.add(view);
//		}, 1, me);
	},
	RV: function(viewId, viewConfig, position) {
		return this.renderView(viewId, viewConfig, position);
	},
	
	showWindow: function(viewId, viewConfig, windowConfig) {
		var me = this,
			viewConfig = Ext.apply({}, me._moduleUIConfig(), viewConfig),
			win = Ext.create('Ext.window.Window', Ext.apply({
				modal: true,
				resizable: false
			}, windowConfig, {
				autoScroll: true,
				layout: 'anchor',
				defaults: {
					layout: 'anchor',
					anchor: '100% 100%'
				},
				items: Ext.create(me._getViewId(viewId), viewConfig)
			}));
		
		return win.show();
	},
	SW: function(viewId, viewConfig, windowConfig) {
		return this.showWindow(viewId, viewConfig, windowConfig);
	},
	
	_getViewId: function(viewId) {
		if (viewId.indexOf('.view.') == -1) {
			viewId = (this.getModulePkg() + '.view.' + viewId);
		}
		
		return viewId;
	},
	_moduleUIConfig: function() {
		var me = this;
		return {
			moduleId: me.id,
			idp: me.uiIdPrefix,
			locVal: Ext.bind(me.locVal, me),
			renderView: Ext.bind(me.renderView, me),
			DF: Ext.bind(me.DF, me),
			MR: function(method, params) {
				return me.MR({
					m: method,
					p: params
				});
			},
			MRA: function(event, method, params) {
				return me.MRA(event, {
					m: method,
					p: params
				});
			}
		}
	},
	
	handleRequest: function(requests) { // AOP logging / auth / exception handle
		var me = this;
		requests = (Ext.isArray(requests) ? requests : [requests]);
		if (me.fireEvent("beforeHandlerRequest", requests) !== false) { // TODO 
			for (var i = 0; i < requests.length; i++) {
			
				var r = requests[i], 
					m = me[r.m], 
					p = r.p, 
					cb = r.cb || Ext.emptyFn, 
					s = r.s || me, 
					ret;
				
				ret = m.apply(me, [p, r]);
				cb.apply(s, [ret, r]);
			}
		}
	},
	
	control: function() {
//		{
//			'selector': {
//				click: ''
//			}
//		}
	},
	
	locVal: function(key, params) {
		var me = this,
			enableLocale = me.app.config.enableLocale,
			msg;
			
		if (enableLocale === true) {
			var keys = key.split('.'),
				msg = me.locale[keys.shift()];
			Ext.each(keys, function(k) {
				msg = msg[k];
			}, me);
		} else {
			msg = key;
		};
		
		if (params) {
			msg = Ext.String.format(msg, params);
		};
		
		return msg;
	},
	
	getModulePkg: function() {
		var me = this,
			cn = me.$className;
			
		return cn.substring(0, cn.lastIndexOf('.'));
	},
	
	query: function(selector, root) {
		var me = this,
			root = root || me.ui;
		
		return root.query(selector);
	},
	up: function(selector) {
		return this.ui.up(selector);
	},
	down: function(selector) {
		return this.ui.down(selector);
	},
	
	getEl: function(id) {
		var me = this;
		if (Ext.isString(id)) {
			id = (me.uiIdPrefix + id);
		};
		
		return Ext.get(id);
	},
	
	getCmp: function(id) {
		var me = this;
		if (Ext.isString(id)) {
			id = (me.uiIdPrefix + id);
		};
		
		return Ext.getCmp(id);
	}
});