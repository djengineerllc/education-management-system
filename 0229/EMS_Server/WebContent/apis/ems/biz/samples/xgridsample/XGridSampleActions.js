/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.samples.xgridsample.XGridSampleActions');

ems.biz.samples.xgridsample.XGridSampleActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.biz.samples.xgridsample.XGridSampleActions.POLLING_URLS = {
}

ems.biz.samples.xgridsample.XGridSampleActions.REMOTING_API = {
  url: ems.biz.samples.xgridsample.XGridSampleActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_biz_samples_xgridsample_XGridSampleActions_XGridSampleAction: [
      {
        name: 'getFormData'/*(String) => com.ems.system.client.vo.ExtFormVO */,
        len: 1,
        formHandler: false
      },
      {
        name: 'submitFormData'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      },
      {
        name: 'getXGridSample1ViewData'/*(com.google.gson.JsonArray) => com.ems.system.client.vo.ExtPagingVO */,
        len: 1,
        formHandler: false
      }
    ]
  }
}

