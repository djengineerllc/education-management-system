Ext.namespace("ems.biz.samples.formsample.FormSampleActions");
ems.biz.samples.formsample.FormSampleActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/EMS_Server/djn/directprovider";
ems.biz.samples.formsample.FormSampleActions.POLLING_URLS={};
ems.biz.samples.formsample.FormSampleActions.REMOTING_API={url:ems.biz.samples.formsample.FormSampleActions.PROVIDER_BASE_URL,type:"remoting",actions:{ems_biz_samples_formsample_FormSampleActions_FormSampleAction:[{name:"getFormData",len:1,formHandler:false},{name:"submitFormData",len:1,formHandler:true}]}};