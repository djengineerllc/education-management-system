Ext.namespace("ems.main.MainActions");
ems.main.MainActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/EMS_Server/djn/directprovider";
ems.main.MainActions.POLLING_URLS={};
ems.main.MainActions.REMOTING_API={url:ems.main.MainActions.PROVIDER_BASE_URL,type:"remoting",actions:{ems_main_MainActions_MainAction:[{name:"getMenu",len:1,formHandler:false}]}};