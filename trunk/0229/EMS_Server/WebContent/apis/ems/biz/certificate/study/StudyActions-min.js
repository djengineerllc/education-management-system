Ext.namespace("ems.biz.certificate.study.StudyActions");
ems.biz.certificate.study.StudyActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.certificate.study.StudyActions.POLLING_URLS={};
ems.biz.certificate.study.StudyActions.REMOTING_API={url:ems.biz.certificate.study.StudyActions.PROVIDER_BASE_URL,type:"remoting",actions:{StudyAction:[{name:"printCert",len:2,formHandler:false},{name:"loadList",len:1,formHandler:false}]}};