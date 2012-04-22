Ext.define('ems.biz.scoremgr.scorequery.ScoreQueryActions', {
	extend: 'ems.biz.scoremgr.scoreinput.ScoreInputActions', //ems.core.Actions',
	
	constructor: function() {
		Ext.apply(this, {
			remotingApiUrl: (this.getBaseRemotingApiUrl() + '/ems/biz/scoremgr/scoreinput/ScoreInputActions.js'),
			apiNamespace: 'ems.biz.scoremgr.scoreinput.ScoreInputActions'
		});
		
		this.callParent(arguments);
	}
});