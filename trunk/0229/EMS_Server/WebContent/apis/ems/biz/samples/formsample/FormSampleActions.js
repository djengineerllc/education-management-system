/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.samples.formsample.FormSampleActions');

ems.biz.samples.formsample.FormSampleActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.biz.samples.formsample.FormSampleActions.POLLING_URLS = {
}

ems.biz.samples.formsample.FormSampleActions.REMOTING_API = {
  url: ems.biz.samples.formsample.FormSampleActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_biz_samples_formsample_FormSampleActions_FormSampleAction: [
      {
        name: 'getFormData'/*(String) => com.ems.system.client.vo.ExtFormVO */,
        len: 1,
        formHandler: false
      },
      {
        name: 'submitFormData'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      }
    ]
  }
}

