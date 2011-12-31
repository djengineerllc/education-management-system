/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.test.TestActions');

ems.biz.test.TestActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/ES/' + 'djn/directprovider';

ems.biz.test.TestActions.POLLING_URLS = {
}

ems.biz.test.TestActions.REMOTING_API = {
  url: ems.biz.test.TestActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    TestAction: [
      {
        name: 'getLocationInfo'/*(Long) => com.ems.client.action.test.TestAction$LocationInfo */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getPhoneInfo'/*(Long) => com.ems.client.action.test.TestAction$PhoneInfo */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getBasicInfo'/*(Long, String) => com.ems.client.action.test.TestAction$BasicInfo */,
        len: 2,
        formHandler: false
      },
      {
        name: 'getGrid'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'updateBasicInfo'/*() => com.ems.client.action.test.TestAction$SubmitResult -- FORM HANDLER */,
        len: 1,
        formHandler: true
      }
    ]
  }
}

