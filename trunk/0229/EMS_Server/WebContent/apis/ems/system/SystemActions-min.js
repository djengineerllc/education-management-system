Ext.namespace("ems.system.SystemActions");
ems.system.SystemActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/EMS_Server/djn/directprovider";
ems.system.SystemActions.POLLING_URLS={};
ems.system.SystemActions.REMOTING_API={url:ems.system.SystemActions.PROVIDER_BASE_URL,type:"remoting",actions:{ems_system_SystemActions_SystemAction:[{name:"getDicData",len:1,formHandler:false},{name:"getSystemTime",len:0,formHandler:false},{name:"getLoginInfo",len:0,formHandler:false}]}};