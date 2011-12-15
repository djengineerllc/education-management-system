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
		var me = this;
		Ext.direct.Manager.removeProvider(me.getProvider());
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
			provider = me.getProvider();
		
		Ext.direct.Manager.addProvider(provider);
		
//		Djn.RemoteCallSupport.addCallValidation(provider);
//		Djn.RemoteCallSupport.validateCalls = true;

		var api = me.remotingApi = provider,
			ans = me.actionsNamespace = api['namespace'] || '',
			actions = api['actions'] || {};
		
		for (var an in actions) {
			var afn = ((ans ? '.' : '') + an),
				methods = {};
			Ext.each(actions[an] || [], function(m) {
				var mn = m.name,
					mfn = (afn + '.' + mn);
				methods[mn] = eval(mfn);
			}, me);
			
			if (me.mixinRemoteAction == true) {
				Ext.apply(me, methods);
//				break; // // single remote action
			} else {
				me[an] = methods;
			}
		}
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
	
	getProvider: function() {
		var me = this;
		return eval(me.getProviderName());
	}
})