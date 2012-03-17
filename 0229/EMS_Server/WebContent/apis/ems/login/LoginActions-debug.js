/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.login.LoginActions');

ems.login.LoginActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/ES/' + 'djn/directprovider';

ems.login.LoginActions.POLLING_URLS = {
}

ems.login.LoginActions.REMOTING_API = {
  url: ems.login.LoginActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    LoginAction: [
      {
        name: 'selectRole'/*(String, String) => com.ems.system.client.vo.ExtFormVO */,
        len: 2,
        formHandler: false
      },
      {
        name: 'logout'/*() => void */,
        len: 0,
        formHandler: false
      },
      {
        name: 'login'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      }
    ]
  }
}

