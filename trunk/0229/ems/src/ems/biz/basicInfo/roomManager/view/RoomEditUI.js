Ext.define('ems.biz.basicInfo.roomManager.view.RoomEditUI', {
	extend: 'ems.biz.base.crud.CrudUI',
	
//	getApis: function(){
//		var me = this;
//		return {
//			load: me.DF('loadUser'),
//			submit:  me.DF('submitUser')
////			c: {
////				submit: 'submitUser'
////			},
////			u: {
////				submit: 'submitUser'
////			}
//		}
//	},
	
	uiConfig: function() {
		var me = this;
		return {
			layout: 'fit',
			items: [{
				xtype: 'form',
				border: false,
				bodyPadding: 10,
				paramOrder: ['id'],
				fieldDefaults: {
					labelWidth: 80,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'textfield',
					anchor: '100%'
				},
				items: [{
					xtype: 'hidden',
					name: 'id'
				},{
	                fieldLabel: '教室名',
	                name: 'roomName'
	            },Dic.comboBox('Term', {
					fieldLabel: '学期',
					name: 'termId',
					value: Session.getLoginInfo().currTerm || ''
				}),{
					xtype: 'numberfield',
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