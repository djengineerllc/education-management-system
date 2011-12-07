// ----------------------
// Author: Chiknin
// ----------------------

Ext.ns("ems.core");

Ems = ems.core.Ems = {
	
	name: "EMS - Education Management System",
	
	appFolder: 'src/ems',
	
	isStarted: false,
	
	config: null,
	
	eventBus: null,
	
	modules: null,
	
	plugins: null,
	
	privileges: null, // 登陆后设置 用户权限
	
	rootEl: null, // TODO 保留
	
	init: Ext.emptyFn,
	
	startup: function(config) {
		var me = this;
		me.config = Ext.applyIf(config || {}, {
			serverUrl: 'http://localhost:8080/EMS_Server',
			serverApiPath: '/apis',
			ajaxTimeout: 60000,
			enableQuickTips: true,
			enableLocale: true,
			enableBuffer: 10,
//			stateProvider: 'Ext.state.CookieProvider',
			locale: 'zh_CN',
			rootEl: Ext.getBody(),
			viewportModuleId: 'ems.main.Main',
			viewportModuleConfig: {},
			extLoaderConfig: {
				enabled: true,
				disableCaching: PRODUCT_MODE == false,
				disableCachingParam: '_dc',
		        paths: {
		            'Ext': 'lib/ext/src',
					'Ext.ux': 'lib/ext/src/ux',
					'ems': me.appFolder
		        }
			},
			require: [
				'Ext.Ajax',
				'Ext.tip.QuickTipManager',
				'Ext.util.KeyMap',
				
				'ems.core.Module',
				'ems.core.Actions',
				'ems.core.EventBus'
			]
		});
		
		Ext.Loader.setConfig(config.extLoaderConfig);
		
		var require = [].concat(
			config.require,
			config.viewportModuleId
		);
		Ext.require(config.require);
		
		Ext.onReady(function() {
			me._initConfig();
			
			me._initApp();
			
			me.isStarted = true;
			
			me.config.onStarted && me.config.onStarted.apply(me);
		});
	},
	
	_initConfig: function() {
		var me = this,
			cfg = me.config;
		
		me.modules = {};
		me.plugins = [];
		me.privileges = {};
		me.isStarted = false;
		me.rootEl = null;
		
		if (cfg.ajaxTimeout) {
			Ext.Ajax.timeout = cfg.ajaxTimeout;
		}
		if (cfg.enableQuickTips) {
			Ext.tip.QuickTipManager.init();
		}
		if (cfg.enableBuffer) {
			Ext.direct.RemotingProvider.enableBuffer = cfg.enableBuffer;
		}
		if (cfg.stateProvider) {
			Ext.state.Manager.setProvider(Ext.create(cfg.stateProvider));
		}
		
		this.eventBus = Ext.create('ems.core.EventBus');
	},
	
	_initApp: function() {
		var me = this;
		
		me.init();
		
		me._preventBackspace();
		
		me._initPlugin();
		
		me._initUI();
	},
	_preventBackspace: function() {
		var map = new Ext.util.KeyMap(document, [{
			key: Ext.EventObject.BACKSPACE,
			stopEvent: false,
			fn: function(key, e){
				var t = e.target.tagName;
				if(t != 'INPUT' && t != 'TEXTAREA'){
					e.stopEvent();
				}
			}
		}]);
	},
	_initPlugin: function() {
		
	},
	_initUI: function() {
		var me = this,
			cfg = me.config,
			rootEl = cfg.rootEl,
			viewportModuleId = cfg.viewportModuleId,
			viewportModuleConfig = cfg.viewportModuleConfig;
			
		me.rootEl = rootEl ? Ext.get(rootEl) : Ext.getBody();
		me.requestModule(viewportModuleId, viewportModuleConfig, function(module) {
			// TODO ...
		}, me);
	},
	
	shutdown: function() {
		var me = this;
		
		if (me.isStarted === false) {
			return;
		}
		
		if (me.modules) {
			for (var moduleId in me.modules) {
				me.destroyModule(moduleId);
			}
		}
		
		if (me.rootEl) {
			Ext.destroy(me.rootEl);
			me.rootEl = null;
		}
//		Ext.destroy(Ext.getBody());

		me.config = null;
		me.init = null;
		me.plugins = null;
		me.privileges = null;
		
		me.isStarted = false;
	},
	
	// -----------------------------------------------
	// START: Module Management 
	// -----------------------------------------------
	requestModule: function(id, config, cb, scope) {
		var me = this,
			module = me.getModule(id);
		if (!module) {
			module = me.loadModule(id, config);
			module.init();
			if (module.silent === false) {
				module.createUI();
			}
		}
		if (cb) {
			scope = scope || module;
			cb && cb.call(scope, module);
		}
	},
	RM: function(id, config, cb, scope) {
		this.requestModule(id, config, cb, scope);
	},
	
	/**
	 * requests: [{m/method, p/params, cb/callback, s/scope}]
	 */
	makeRequest: function(id, requests) {
		this.requestModule(id, null, function(module) {
			module.handleRequest(requests);
		}, this);
	},
	MR: function(id, requests) {
		this.makeRequest(id, requests);
	},
	
	MRA: function(event, id, request) { // makeRequestAction
		var action = {};
		action[event] = {
			fn: function() {
				request.eventArgs = arguments;
				request.eventSource = this;
				
				Ems.makeRequest(id, request);
			}
		};
		
		return action;
	},
	
	/**
	 * @param {Object} id
	 * @param {Object} require
	 * sample:
	 * Ems.A('ems.main.Main', {
	 *     action: 'TestAction',
	 *     m: 'getTree',
	 *     p: {
	 *         
	 *     }
	 * })
	 */
	A: function(id, require) { // action
		this.makeRequest(id, {
			m: 'action',
			p: require
		});
	},
	
	DF: function(id, method, action) { // directFn
		var me = this;
		var module = me.getModule(id);
		var remoteDirectFn = (action ? module.actions[action][method] : module.actions[method]);
		
		var directFn = function() {
			var p = Array.prototype.slice.call(arguments, 0);
			Ems.A(id, {
				action: action,
				m: method,
				p: p
			});
		};
		directFn.directCfg = remoteDirectFn.directCfg;
		
		return directFn;
	},
	
	loadModule : function(id, config) {
		var module = Ext.create(id, config);
		this.registerModule(module);
		
		return module;
    },
	
	registerModule: function(module) {
		var me = this;
		
		if (!module.id) {
			module.id = module.$className;
		}
		module.app = me;
		
		me.modules[module.id] = module;
	},
	
	destroyModule: function(id) {
		var me = this,
			module = me.modules[id];
		if (module) {
			module.destroy();
			delete me.modules[id];
		}
	},
	
	getModule: function(id) {
		return this.modules[id];
	}
	// -----------------------------------------------
	// END: Module Management 
	// -----------------------------------------------
};