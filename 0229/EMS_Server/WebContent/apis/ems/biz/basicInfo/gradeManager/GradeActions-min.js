Ext.namespace("ems.biz.basicInfo.gradeManager.GradeActions");
ems.biz.basicInfo.gradeManager.GradeActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.basicInfo.gradeManager.GradeActions.POLLING_URLS={};
ems.biz.basicInfo.gradeManager.GradeActions.REMOTING_API={url:ems.biz.basicInfo.gradeManager.GradeActions.PROVIDER_BASE_URL,type:"remoting",actions:{GradeAction:[{name:"update",len:1,formHandler:true},{name:"exportExcel",len:2,formHandler:false},{name:"delete",len:1,formHandler:false},{name:"read",len:1,formHandler:false},{name:"loadGrade",len:1,formHandler:false},{name:"create",len:1,formHandler:true},{name:"batchImport",len:1,formHandler:true},{name:"downloadExcelTemplate",len:2,formHandler:false}]}};