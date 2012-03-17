Ext.namespace("ems.biz.certificate.studentship.StudentshipActions");
ems.biz.certificate.studentship.StudentshipActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.certificate.studentship.StudentshipActions.POLLING_URLS={};
ems.biz.certificate.studentship.StudentshipActions.REMOTING_API={url:ems.biz.certificate.studentship.StudentshipActions.PROVIDER_BASE_URL,type:"remoting",actions:{StudentshipAction:[{name:"printCert",len:2,formHandler:false},{name:"loadList",len:1,formHandler:false}]}};