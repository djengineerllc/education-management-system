Ext.define('ems.main.data.model.MenuModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'id', type: 'int'}, 
    	'text', 
    	'textKey', 
    	'moduleId', 
    	'moduleName', 
    	'moduleNameKey', 
    	'moduleConfig'
    ]
});
