Ext.define('ems.biz.certificate.studentship.view.PrintCertUI', {
	extend: 'ems.core.UI',
	
	init: function() {
		var me = this,
			sltStudents = me.reqParams,
			title,
			termComboData = [],
			termComboBox = me.down('#termComboBox'),
			stuComboData = [],
			stuComboBox = me.down('#stuComboBox'),
			queryInfoForm = me.down('#queryInfoForm').getForm(),
			initData;
		
		/* 初始化store */
		termComboData = [{
			value: '',
			name: '全学期'
		}, {
			value: '2011A',
			name: '2011年第一学期'
		}, {
			value: '2011B',
			name: '2011年第二学期'
		}];
		termComboBox.store.loadData(termComboData); // 学期
		
		Ext.each(sltStudents, function(sltStudent) {
			stuComboData.push({
				value: sltStudent.stuNo,
				name: Ext.String.format("{0}({1})", sltStudent.stuName, sltStudent.stuNo)
			});
		});
		stuComboBox.store.loadData(stuComboData); // 学生
		
		/* 初始化页面信息 */
		initData = sltStudents[0];
		title = Ext.String.format('在学证明 - 年级: {0}  班级: {1}', 
			Dic.renderer('Sex')(initData.stuGrade),
			Dic.renderer('Sex')(initData.stuClass)
		);
		me.up().setTitle(title); // 标题
		
		queryInfoForm.setValues({
			term: '2011B',
			stuNo: initData.stuNo
		});
	},
	
	afterRender: function() {
		var me = this;
		me.callParent(arguments);
		me.init();
	},
	
	uiConfig: function(){
		var me = this;
		return {
			items: [{
				xtype: 'form',
				itemId: 'queryInfoForm',
				border: false,
				bodyPadding: 3,
				layout: {
					type: 'hbox',
					pack: 'start',
					align: 'middle'
				},
				fieldDefaults: {
					labelWidth: 35,
					labelAlign: 'right'
				},
				defaults: {
					xtype: 'displayfield',
					anchor: '100%',
					width: 100
				},
				items: [
					Dic.localComboBox({
						fieldLabel: '学期',
						itemId: 'termComboBox',
                        name: 'term',
						width: 160
					}),
					Dic.localComboBox({
						fieldLabel: '学生',
						itemId: 'stuComboBox',
                        name: 'stuNo',
						width: 160
					})
	            ],
				bbar: [{
					text: '上个学生'
				},{
					text: '上下学生'
				}, {
					text: '打印'
				}]
			}, {
				html: '打印模板...'
			}]
		}
	}
});