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
		var welcomeText,
			loginInfo = this.getLoginInfo();
		
		if (loginInfo.roleCd == 'student') {
			welcomeText = Ext.String.format(
				'您好 {0}, 角色: {1} | 学号: {2} | 年级: {3} | 班级: {4} | 项目: {5} | 专业: {6}', 
				loginInfo.userName || '', 
				loginInfo.roleName || '',
				loginInfo.stuNo || '',
				loginInfo.stuGradeName || '',
				loginInfo.stuClassName || '',
				loginInfo.stuProjectName || '',
				loginInfo.stuProfessName || ''
			);
		} else {
			welcomeText = Ext.String.format(
				'您好 {0}，角色: {1}', 
				loginInfo.userName || '',
				loginInfo.roleName || ''
			);
		}
		
		return welcomeText;
	}
});