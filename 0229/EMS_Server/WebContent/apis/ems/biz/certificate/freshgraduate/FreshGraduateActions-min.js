Ext.namespace("ems.biz.certificate.freshgraduate.FreshGraduateActions");
ems.biz.certificate.freshgraduate.FreshGraduateActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.certificate.freshgraduate.FreshGraduateActions.POLLING_URLS={};
ems.biz.certificate.freshgraduate.FreshGraduateActions.REMOTING_API={url:ems.biz.certificate.freshgraduate.FreshGraduateActions.PROVIDER_BASE_URL,type:"remoting",actions:{FreshGraduateAction:[{name:"printCert",len:2,formHandler:false},{name:"loadList",len:1,formHandler:false}]}};