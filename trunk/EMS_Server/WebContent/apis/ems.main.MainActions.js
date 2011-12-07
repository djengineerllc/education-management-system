/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.main.MainActions');

ems.main.MainActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/ES/' + 'djn/directprovider';

ems.main.MainActions.POLLING_URLS = {
}

ems.main.MainActions.REMOTING_API = {
  url: ems.main.MainActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    MainAction: [
      {
        name: 'getMenu'/*(String) => java.util.List */,
        len: 1,
        formHandler: false
      }
    ]
  }
}

