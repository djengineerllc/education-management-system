Ext.namespace("ems.biz.test.TestActions");
ems.biz.test.TestActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.biz.test.TestActions.POLLING_URLS={};
ems.biz.test.TestActions.REMOTING_API={url:ems.biz.test.TestActions.PROVIDER_BASE_URL,type:"remoting",actions:{TestAction:[{name:"getLocationInfo",len:1,formHandler:false},{name:"getPhoneInfo",len:1,formHandler:false},{name:"getBasicInfo",len:2,formHandler:false},{name:"getGrid",len:1,formHandler:false},{name:"updateBasicInfo",len:1,formHandler:true}]}};