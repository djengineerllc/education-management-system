Ext.namespace("ems.biz.certificate.graduate.GraduateActions");
ems.biz.certificate.graduate.GraduateActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.certificate.graduate.GraduateActions.POLLING_URLS={};
ems.biz.certificate.graduate.GraduateActions.REMOTING_API={url:ems.biz.certificate.graduate.GraduateActions.PROVIDER_BASE_URL,type:"remoting",actions:{GraduateAction:[{name:"printCert",len:2,formHandler:false},{name:"loadList",len:1,formHandler:false}]}};