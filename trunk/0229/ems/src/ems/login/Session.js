Ext.define('ems.login.Session', {
	extend: 'Ext.util.MixedCollection',
	singleton: true,
	alternateClassName: 'Session',
	
	KEY_LOGIN_INFO: 'loginInfo',
	
	setLoginInfo: function(loginInfo) {
		if (Ext.isEmpty(loginInfo)) {
			this.removeAtKey(this.KEY_LOGIN_INFO);
		} else {
			this.add(this.KEY_LOGIN_INFO, loginInfo);
		}
	},
	
	getLoginInfo: function() {
		return this.get(this.KEY_LOGIN_INFO);
	},
	
	getWelcomeText: function() {
		var loginInfo = this.getLoginInfo();
		
		
		return '您好 admin 同学， 学号 xxxxx | 年级 xxx | 班级 xxx | 项目 xxx | 专业 xxxx';
	}
});