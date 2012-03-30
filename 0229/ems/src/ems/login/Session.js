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
				'您好 {0} 同学，学号: {1} | 年级: {2} | 班级: {3} | 项目: {4} | 专业: {5}', 
				loginInfo.userName || '', 
				loginInfo.stuNo || '',
				loginInfo.stuGradeName || '',
				loginInfo.stuClassName || '',
				loginInfo.stuProjectName || '',
				loginInfo.stuProfessName || ''
			);
		} else if (loginInfo.roleCd == 'teacher') {
			welcomeText = Ext.String.format(
				'您好 {0} 老师', 
				loginInfo.userName || ''
			);
		} else if (loginInfo.roleCd == 'admin') {
			welcomeText = '您好 系统管理员';
		} else {
			welcomeText = Ext.String.format(
				'您好 {0} {1}', 
				loginInfo.userName || '',
				loginInfo.roleName || ''
			);
		}
		
		return welcomeText;
	}
});