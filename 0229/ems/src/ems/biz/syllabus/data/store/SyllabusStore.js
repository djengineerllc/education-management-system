Ext.define('ems.biz.syllabus.data.store.SyllabusStore', {
	extend: 'Ext.data.Store', //'Ext.data.TreeStore',
    requires: ['ems.biz.syllabus.data.model.SyllabusModel'],
    model: 'ems.biz.syllabus.data.model.SyllabusModel',
    autoDestroy: true,
    
    constructor: function(){
        var me = this;
//        me.proxy = {
////            type: 'direct',
////            directFn: Ems.DF('ems.main.Main', 'getMenu'),
////            paramOrder: ['node'],
////            extraParams: {}
//        	type: 'ajax',
//        	url: 'test_syllabus_data.json'
//        }
        
        me.proxy = {
        	type: 'memory',
        	reader: {
	        	type: 'json'
	        }
        }
        
        this.callParent(arguments);
    },
    
    fields: [
     		'lesson',
     		'classCode',
     		'oeInd',
     		'monCourse',
     		'monTeacher',
     		'monRoom',
     		'tueCourse',
     		'tueTeacher',
     		'tueRoom',
     		'webCourse',
     		'webTeacher',
     		'webRoom',
     		'thuCourse',
     		'thuTeacher',
     		'thuRoom',
     		'friCourse',
     		'friTeacher',
     		'friRoom'
         ],
    
   data: [{
			lesson : '12节',
			classCode : '09BA',
			oeInd : '单',
			monCourse : '',
			monTeacher : '',
			monRoom : '',
			tueCourse : '',
			tueTeacher : '',
			tueRoom : '',
			webCourse : '',
			webTeacher : '',
			webRoom : '',
			thuCourse : '',
			thuTeacher : '',
			thuRoom : '',
			friCourse : '',
			friTeacher : '',
			friRoom : ''
		},{
			lesson : '12节',
			classCode : '09BA',
			oeInd : '双',
			monCourse : '',
			monTeacher : '',
			monRoom : '',
			tueCourse : '',
			tueTeacher : '',
			tueRoom : '',
			webCourse : '',
			webTeacher : '',
			webRoom : '',
			thuCourse : '',
			thuTeacher : '',
			thuRoom : '',
			friCourse : '',
			friTeacher : '',
			friRoom : ''
		},{
			lesson : '12节',
			classCode : '09DE',
			oeInd : '单',
			monCourse : '',
			monTeacher : '',
			monRoom : '',
			tueCourse : '',
			tueTeacher : '',
			tueRoom : '',
			webCourse : '',
			webTeacher : '',
			webRoom : '',
			thuCourse : '',
			thuTeacher : '',
			thuRoom : '',
			friCourse : '',
			friTeacher : '',
			friRoom : ''
		},{
			lesson : '12节',
			classCode : '09DE',
			oeInd : '双',
			monCourse : '',
			monTeacher : '',
			monRoom : '',
			tueCourse : '',
			tueTeacher : '',
			tueRoom : '',
			webCourse : '',
			webTeacher : '',
			webRoom : '',
			thuCourse : '',
			thuTeacher : '',
			thuRoom : '',
			friCourse : '',
			friTeacher : '',
			friRoom : ''
		}]
});