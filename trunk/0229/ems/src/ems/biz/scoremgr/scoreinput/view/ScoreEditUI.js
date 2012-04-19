Ext.define('ems.biz.scoremgr.scoreinput.view.ScoreEditUI', {
	extend: 'ems.core.UI',
	
	initData: function() {
		var me = this,
			sltStudents = me.reqParams,
			termComboBox = me.down('#termComboBox'),
			courseComboBox = me.down('#courseComboBox'),
			queryInfoForm = me.down('#queryInfoForm').getForm(),
			initData,
			title;
		
		/* 初始化页面信息 */
		initData = sltStudents[0];
		title = Ext.String.format('成绩录入 - 年级: {0}  班级: {1}', 
			Dic.renderer('Grade')(initData.gradeId),
			Dic.renderer('Class')(initData.classId)
		);
		me.up().setTitle(title); // 标题
		
		queryInfoForm.setValues({
			termId: Session.getLoginInfo().currTerm || ''
		});
		
		me.updateScoreDataGrid();
		termComboBox.on('change', me.updateScoreDataGrid, me);
		courseComboBox.on('change', me.updateScoreDataGrid, me);
	},
	
	uiConfig: function(){
		var me = this;
		return {
			layout: 'border',
			defaults: {
				padding: 1
			},
			items: [{
				region: 'north',
				height: 65,
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
					Dic.comboBox('Term', {
						fieldLabel: '学期',
						itemId: 'termComboBox',
                        name: 'termId',
						width: 180
					}),
					Dic.comboBox('Course', {
						fieldLabel: '课程',
						itemId: 'courseComboBox',
                        name: 'courseNo',
						width: 180
					})
	            ],
				bbar: ['->', {
					text: '评级',
					tooltip: '选中记录批量评级',
					iconCls: 'icon-star',
					menu: [{
						text: '自动',
						iconCls: 'icon-star'
					}, {
						text: 'A',
						iconCls: 'icon-star'
					}, {
						text: 'B',
						iconCls: 'icon-star'
					}, {
						text: 'C',
						iconCls: 'icon-star'
					}]
				}]
			}, {
				region: 'center',
				xtype: 'xgrid',
				itemId: 'scoreDataGrid',
				selMode: 'multi',
				editingMode: 'cellediting',
				loadDF: me.DF('loadScoreDefaultList'),
                columns: [{
                    dataIndex: 'id',
                    text: '成绩ID',
                    hidden: true,
                    flex: 1
                }, {
                    dataIndex: 'stuNo',
                    text: '学号',
                    flex: 1
                }, {
                    dataIndex: 'stuName',
                    text: '姓名',
                    flex: 1
                }, {
                    dataIndex: 'score',
                    text: '成绩',
                    editor: {
                    	xtype: 'textfield'
                    },
                    flex: 1
                }, 
                Dic.column('ScoreLevel', {
                	dataIndex: 'level',
                    text: '评级',
                    flex: 1
                })]
			}]
		}
	},
	
	updateScoreDataGrid: function() {
		var me = this,
			sltStudents = me.reqParams;
			queryInfoForm = me.down('#queryInfoForm').getForm();
			scoreDataGrid = me.down('#scoreDataGrid'),
			stuIds = Ext.Array.map(sltStudents, function(sltStudent) {
				return sltStudent['id'];
			}),
			loadParams = Ext.apply({
				classId: sltStudents[0].classId,
				stuIds: stuIds
			}, queryInfoForm.getValues());
		
		scoreDataGrid.store.load({
			params: loadParams
		});
	}
});