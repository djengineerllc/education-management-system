Ext.define('ems.biz.basicInfo.roomManager.view.RoomReadUI', {
    extend: 'ems.biz.base.crud.CrudUI',
	
//	getApis: function(){
//		var me = this;
//		return {
//			load: me.DF('loadUser'),
//			d: {
//				submit: me.DF('submitUser')
//			}
//		}
//	},
	
	uiConfig: function() {
		var me = this;
		return {
			width: 300,
			height: 200,
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['roomId'],
				fieldDefaults: {
					labelWidth: 60,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'displayfield',
					anchor: '100%'
				},
				items: [{
					fieldLabel: '教室ID',
	                name: 'roomId'
				},{
	                fieldLabel: '教室名',
	                name: 'roomName'
	            },{
	                fieldLabel: '学期',
	                name: 'termName'
	            },{
	                fieldLabel: '教室人数',
	                name: 'roomSize'
	            },{
	                fieldLabel: '教室使用情况',
	                name: 'roomStatus'
	            },{
	                fieldLabel: '教室备注',
	                name: 'roomComment'
	            }]
			}]
		};
	}
});