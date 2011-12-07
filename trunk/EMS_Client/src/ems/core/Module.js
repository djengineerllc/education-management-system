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
	actionsClassSuffix: "Actions",
	
	uiEl: null,
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
			var actionsClassName = (me.$className + me.actionsClassSuffix);
			me.actions = Ext.create(actionsClassName);
		}
	},
	
	activate: function(params) {
		
	},
	
	destroy: function() {
		var me = this;
		me.actions && me.actions.destroy();
		if (me.uiEl) {
			Ext.destroy(me.uiEl);
			me.uiEl = null;
		}
	},
	
	MR: function(requests) {
		Ems.MR(this.id, requests);
	},
	
	MRA: function(event, request) {
		return Ems.MRA(event, this.id, request);
	},
	
	A: function(require) {
		Ems.A(this.id, require);
	},
	
	DF: function(method, action) {
		return Ems.DF(this.id, method, action);
	},
	
	action: function(actionRequest, moduleRequest) { // caller -> handleRequest()
		var me = this,
			ar = actionRequest,
			a = ar.action ? me.actions[ar.action] : null,
			m = a ? a[ar.m] : me.actions[ar.m],
			p = (Ext.isArray(ar.p) ? ar.p : [ar.p]) || [];
		
		if (ar.cb) {
			var s = ar.s || me;
//			p = p.concat(Ext.bind(ar.cb, s));
			p = p.concat(ar.cb).concat(s);
		}
		
		m.apply(me.actions, p); // Ext.pass(m, params.p, a)();
	},
	
	createUI: function(config) {
		var me = this;
		if (me.silent == false && me.segmentUI == true) {
			var uiClassName = (me.$className + me.uiClassSuffix);
			config = Ext.merge({renderTo: me.renderTo}, me._moduleUIConfig(), config);
			me.uiEl = Ext.create(uiClassName, config);
		}
	},
	
	renderView: function(viewId, viewConfig, position) {
		var me = this,
			viewConfig = Ext.apply({}, me._moduleUIConfig(), viewConfig),
			position = position || 'place',
			renderTo = (viewConfig.renderTo || me.uiEl.id);
		delete viewConfig.renderTo;
		
		if (viewId.indexOf('.') == -1) {
			viewId = (me.getModuleDir() + '.view.' + viewId);
		}
		
		Ext.apply(viewConfig, {
			id: viewId
		});
		
		var refCmp = Ext.getCmp(renderTo);
		if (position == 'place') {
			refCmp.removeAll(true);
		} else {
			Ext.destroy(Ext.getCmp(viewId));
		}
		
		var view = Ext.create(viewId, viewConfig);
		refCmp.add(view);
		
		return view;
	},
	
	_moduleUIConfig: function() {
		var me = this;
		return {
			moduleId: me.id,
			idp: me.uiIdPrefix,
			locVal: Ext.bind(me.locVal, me),
			renderView: Ext.bind(me.renderView, me),
			DF: Ext.bind(me.DF, me),
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
		if (me.fireEvent("beforeHandlerRequest", requests) !== false) {
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
		}
		
		if (params) {
			msg = String.format(msg, params);
		}
		
		return msg;
	},
	
	getModuleDir: function() {
		var me = this,
			cn = me.$className;
			
		return cn.substring(0, cn.lastIndexOf('.'));
	},
	
	queryEl: function(selector, root, multiRet) {
		var me = this;
		root = root || me.uiEl.dom;

		var rets = Ext.query(selector, root);
		
		return (multiRet === true ? rets : rets[0]);
	},
	
	getEl: function(id) {
		var me = this;
		if (Ext.isString(id)) {
			id = (me.uiIdPrefix + id);
		}
		
		return Ext.get(id);
	}
});