// ----------------------
// Author: Chiknin
// ----------------------

Ext.ns('ems.core');

Ems = ems.core.Ems = {
	
	name: 'EMS - Education Management System',
	
	appFolder: 'src/ems',
//	appFolder: 'build/build/src/ems',
	
	isStarted: false,
	
//	systemTime: new Date(),
	
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
			appVersion: '1.0',
			serverUrl: 'http://localhost:8080/EMS_Server',
			serverApiPath: '/apis',
			ajaxTimeout: 60000,
			enableQuickTips: true,
			enableLocale: true,
			enableBuffer: 10,
//			stateProvider: 'Ext.state.CookieProvider',
			lang: 'zh_CN',
			rootEl: Ext.getBody(),
			viewportModuleId: 'ems.login.Login', //'ems.main.Main',
			viewportModuleConfig: {},
			extLoaderConfig: {
				enabled: true,
				disableCaching: Bootstrap.config.productMode == false,
				disableCachingParam: '_dc',
		        paths: {
		            'Ext': 'lib/ext/src',
					'Ext.ux': 'lib/ext/src/ux',
					'ems': me.appFolder
		        }
			},
			requires: [
				'ems.core.Requires'
			],
			requireModules: [
				'ems.system.System'
			],
			waitMsg: '请求处理中, 请稍候...'
		});
		
		Ext.Loader.setConfig(config.extLoaderConfig);
		
		var requires = [].concat(
			config.requires
//			,config.viewportModuleId
		);
		Ext.require(requires);
		
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
		me.eventBus = Ext.create('ems.core.EventBus');
		
		me.overrideExtClassess();
		
		if (cfg.lang) {
			Ext.Loader.loadScriptFile('src/ems/core/locale/ext-lang-' + cfg.lang + '.js', Ext.emptyFn, Ext.emptyFn, this, true);
			this.updateLocale();
		}
		if (cfg.appVersion) {
			Ext.Ajax.defaultHeaders = {
				appVersion: cfg.appVersion
			};
		}
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
		
		if (cfg.requireModules) {
			Ext.each(me.config.requireModules, function(moduleId) {
				me.RM(moduleId);
			}, me);
		}
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
		this.requestViewportModule();
	},
	
	shutdown: function() {
		var me = this;
		
		if (me.isStarted === false) {
			return;
		}
		this.destroyAllModule();
//		Ext.destroy(Ext.getBody());

		me.config = null;
		me.init = null;
		me.plugins = null;
		me.privileges = null;
		
//		Ext.EventManager.onWindowUnload();
		
		me.isStarted = false;
	},
	
	updateLocale: function() {
		if (this.config.lang) {
			Ext.defer(Ems.Locale.update, 10);
		}
	},
	
	// -----------------------------------------------
	// START: Global Mask
	// -----------------------------------------------
	mask: function(msg) {
		Ext.getBody().mask(msg || this.config.waitMsg);
//		Ext.MessageBox.wait('请求处理中...', '请稍候');
	},
	
	unmask: function() {
		Ext.getBody().unmask();
//		Ext.MessageBox.updateProgress(1);
//        Ext.MessageBox.hide();
	},
	// -----------------------------------------------
	// END: Global Mask
	// -----------------------------------------------
	
	// -----------------------------------------------
	// START: Module Management 
	// -----------------------------------------------
	requestModule: function(id, config, cb, scope) {
		var me = this,
			module = me.getModule(id);
		
		if (!module) {
			module = me.loadModule(id, config);
			module.init();
		}
		
		if (module) {
			if (cb) {
				scope = scope || module;
				cb && cb.call(scope, module);
			}
		} else {
			Ext.Error.raise({
				sourceClass: 'Ems',
				sourceMethod: 'requestModule',
				msg: 'module not found: ' + id
            });
		}
	},
	RM: function(id, config, cb, scope) {
		this.requestModule(id, config, cb, scope);
	},
	
	/**
	 * requests: [{m/method, p/params, cb/callback, s/scope}]
	 */
	makeRequest: function(id, requests) {
		var me = this;
		me.requestModule(id, null, function(module) {
			module.handleRequest(requests);
		}, me);
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
	A: function(id, request) { // action
		this.makeRequest(id, {
			m: 'action',
			p: request
		});
	},
	
	DF: function(id, method, action) { // directFn // }, async) {
		var me = this,
			module = me.getModule(id),
			remoteDirectFn = (action ? module.actions[action][method] : module.actions[method]);
		
		var directFn = function() {
			var args = Array.prototype.slice.call(arguments, 0), arg, p = [], cb, s, i;
			for (i = 0; i < args.length; i++) {
				arg = args[i];
				if (Ext.isFunction(arg)) {
					cb = arg;
					if ((i + 1) < args.length) {
						s = args[i + 1];
					};
					break;
				} else {
					p.push(arg);
				}
			}
			
			Ems.A(id, {
				action: action,
				m: method,
				p: p,
				cb: cb,
				s: s
//				,async: async
			});
		};
		directFn.directCfg = remoteDirectFn.directCfg;
		
		return directFn;
	},
	
	loadModule : function(id, config) {
		var module = Ext.create(id, config);
		this.registerModule(module);
		
		this.updateLocale();
		
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
	destroyAllModule: function(filterRequireModules) {
		var me = this;
		if (me.modules) {
			for (var moduleId in me.modules) {
				if (filterRequireModules === true && Ext.Array.indexOf(me.config.requireModules, moduleId) != -1) {
					continue;
				}
				
				me.destroyModule(moduleId);
			}
		}
		if (me.rootEl) {
			Ext.destroy(me.rootEl);
			me.rootEl = null;
		};
	},
	
	getModule: function(id) {
		return this.modules[id];
	},
	
	requestViewportModule: function() {
		var me = this,
			cfg = me.config,
			rootEl = cfg.rootEl,
			viewportModuleId = cfg.viewportModuleId,
			viewportModuleConfig = cfg.viewportModuleConfig;
		
		me.destroyAllModule(true);
		
		me.rootEl = rootEl ? Ext.get(rootEl) : Ext.getBody();
		me.requestModule(viewportModuleId, viewportModuleConfig, function(module) {
			// TODO ...
		}, me);
	},
	// -----------------------------------------------
	// END: Module Management 
	// -----------------------------------------------
	
	getSDRTransId: (function() {
		var sdrTransId = 100000000;
		return function() {
			return sdrTransId++;
		}
	})(),
	
	syncDirectRequest: function(moduleId, method, data, action) { // TODO need suppot http get
		var me = this,
			module = me.getModule(moduleId),
			request = {
				url: module.actions.getProviderBaseUrl(),
				async: false,
				jsonData: {
					tid: me.getSDRTransId(),
					type: 'rpc',
					action: action || module.actions.mixinRemoteActionName,
					method: method,
					data: data
				}
			};
		
		var response = Ext.Ajax.request(request);
		if (response.status != 200) {
			alert(response.statusText);
			return {};
		}
		
		return Ext.decode(response.responseText);
	},
	
	download: function(moduleId, method, data, action) {
		var url = Ems.getDirectStreamRequestUrl(moduleId, method, data, action);
		
//		var dd = Ext.DomHelper.append(module.ui.el.dom, {
//            tag : 'iframe',  
//            style : "width:0px;height:0px;position:absolute;right:0;bottom:0;border:none;overflow:hidden"
//			,src : url
//        });
		window.open(url);//, null, "status=no,toolbar=no,menubar=no,location=no"); // "height=0,width=0,status=no,toolbar=no,menubar=no,location=no");
	},
	getDirectStreamRequestUrl: function(moduleId, method, data, action) {
		var me = this,
			module = me.getModule(moduleId),
			streamUrl = module.actions.getProviderBaseUrl() + '/stream/',
			url =  streamUrl + '?' + Ext.urlEncode(Ext.applyIf({
					action: action || module.actions.mixinRemoteActionName,
					method: method
				}, data || {}));
		
		return url;
	},
	
	Ajax: function(options) {
		return Ext.Ajax.request(Ext.applyIf(options || {}, {
			loadMask: true,
			scripts: true,
			failure: function(response, opts) {
				EU.showErrorDialog({
					title: '系统异常',
					msg: response.responseText //Ext.String.format('Call to {0}.{1} failed with message:<xmp>{2}</xmp>', tx.action, tx.method, e.message)
				});
			}
		}));
	},
	
	overrideExtClassess: function() {
		
//		Ext.Ajax.self.override({
//			request: function(options){
//				var t = options.transaction;
//				if (t) {
//					t = Ext.isArray(t) ? t : [t];
//					Ext.each(t, function(ti) {
//						if (options.async === false || ti.async === false) {
//							options.async = false;
//						}
//					});
//				}
//				return this.callOverridden([options]);
//			}
//		});
		
		Ext.AbstractComponent.override({
			destroy : function() {
		        var me = this;
		
		        if (!me.isDestroyed) {
		            if (me.fireEvent('beforedestroy', me) !== false) {
		                me.destroying = true;
		                me.beforeDestroy();
		
		                if (me.floating) {
		                    delete me.floatParent;
		                    // A zIndexManager is stamped into a *floating* Component when it is added to a Container.
		                    // If it has no zIndexManager at render time, it is assigned to the global Ext.WindowManager instance.
		                    if (me.zIndexManager) {
		                        me.zIndexManager.unregister(me);
		                    }
		                } else if (me.ownerCt && me.ownerCt.remove) {
		                    me.ownerCt.remove(me, false);
		                }
		
		                me.onDestroy();
		
		                // Attempt to destroy all plugins
		                Ext.destroy(me.plugins);
		
		                if (me.rendered) {
							// START: add by chiknin
							Ext.each(me.childEls, function(childEl) {
								var c;
								if (Ext.isObject(childEl)) {
									c = me[childEl.name];
								} else {
									c = me[childEl];
								}
								c && c.remove();
							});
							// END: add by chiknin
							
		                    me.el.remove();
		                }
		
		                me.fireEvent('destroy', me);
		                Ext.ComponentManager.unregister(me);
		
		                me.mixins.state.destroy.call(me);
		
		                me.clearListeners();
		                // make sure we clean up the element references after removing all events
		                me.cleanElementRefs();
		                me.destroying = false;
		                me.isDestroyed = true;
		            }
		        }
		    }
		});
		
//		Ext.ElementLoader.override({
//			
//		});
		
		Ext.form.Basic.override({
//			waitTitle: '请稍候',
			waitMsgTarget: Ext.getBody(),
			beforeAction: function(action) {
				if (action.type == 'directsubmit') {
					action.waitMsg = action.waitMsg ? action.waitMsg : Ems.config.waitMsg;
				}
				return this.callOverridden([action]);
		    }
		});
	}
};