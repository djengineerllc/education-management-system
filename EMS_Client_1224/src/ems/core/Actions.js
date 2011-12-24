// ----------------------
// Author: Chiknin
// ----------------------

Ext.define('ems.core.Actions', {
	
	requires: [
		'Ext.direct.Event',
		'Ext.direct.ExceptionEvent',
		'Ext.direct.JsonProvider',
		'Ext.direct.Manager',
		'Ext.direct.PollingProvider',
		'Ext.direct.Provider',
		'Ext.direct.RemotingEvent',
		'Ext.direct.RemotingMethod',
		'Ext.direct.RemotingProvider',
		'Ext.direct.Transaction'
	],
	
	remotingApiUrl: null,
	remotingApi: null,
	
	apiNamespace: null,
	actionsNamespace: null,
	
	mixinRemoteAction: true,
	
	loadRemotingApiOnInit: true,
	
	constructor: function() {
		var me = this;
		me.callParent(arguments);
		me.init();
	},
	
	init: function() {
		var me = this;
		if (me.loadRemotingApiOnInit) {
			me._loadRemotingApi();
		}
	},
	destroy: function() {
		var me = this,
			providerManaged = Ext.direct.Manager.removeProvider(me.getProvider());
			
		providerManaged.un('beforecall', me._beforecall, me);
	},
	
	_loadRemotingApi: function() {
		var me = this;
		Ext.Loader.loadScriptFile(
			me.getRemotingApiUrl(), // url
			me._onLoadRemotingApi, // onLoad
			function(msg, synchronous) { // onError
				alert(msg);
			},
			me, // scope
			true // synchronous
		);
	},
	_onLoadRemotingApi: function() {
		var me = this,
			provider = me.getProvider(),
			providerManaged = Ext.direct.Manager.addProvider(provider);
		
//		Djn.RemoteCallSupport.addCallValidation(provider);
//		Djn.RemoteCallSupport.validateCalls = true;
		
		var api = me.remotingApi = provider,
			ans = me.actionsNamespace = api['namespace'] || undefined,
			actions = api['actions'] || {},
			action,
			methods;
		for (var an in actions) {
			action = ans ? ans[an] : eval(an);
			methods = {};
			Ext.each(actions[an] || [], function(m) {
				var mn = m.name;
				methods[mn] = action[mn];
			}, me);
			
			if (me.mixinRemoteAction == true) {
				Ext.apply(me, methods);
//				break; // // single remote action
			} else {
				me[an] = methods;
			}
		}
		
		providerManaged.on('beforecall', me._beforecall, me);
	},
	
	getBaseRemotingApiUrl: function() {
		return Ems.config.serverUrl + Ems.config.serverApiPath;
	},
	
	getRemotingApiUrl: function() {
		return (this.remotingApiUrl || (this.getBaseRemotingApiUrl() + '/' + this.$className.replace(/\./g, '/') + ".js"));
	},
	
	getApiNamespace: function() {
		return (this.apiNamespace || this.$className);
	},
	
	getProviderName: function() {
		return this.getApiNamespace() + '.REMOTING_API';
	},
	
	getProviderBaseUrl: function() {
		return eval(this.getApiNamespace() + '.PROVIDER_BASE_URL');
	},
	
	getProvider: function() {
		return eval(this.getProviderName());
	},
	
	_beforecall: function(provider, transaction, method) {
		transaction.async = transaction.args[method.len + 2] === false ? false : true;
		return true;
	}
})