Ext.namespace("ems.login.LoginActions");
ems.login.LoginActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/ES/djn/directprovider";
ems.login.LoginActions.POLLING_URLS={};
ems.login.LoginActions.REMOTING_API={url:ems.login.LoginActions.PROVIDER_BASE_URL,type:"remoting",actions:{LoginAction:[{name:"selectRole",len:2,formHandler:false},{name:"logout",len:0,formHandler:false},{name:"login",len:1,formHandler:true}]}};