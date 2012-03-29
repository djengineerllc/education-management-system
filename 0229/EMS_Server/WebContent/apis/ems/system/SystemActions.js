/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.system.SystemActions');

ems.system.SystemActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.system.SystemActions.POLLING_URLS = {
}

ems.system.SystemActions.REMOTING_API = {
  url: ems.system.SystemActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_system_SystemActions_SystemAction: [
      {
        name: 'getDicData'/*(com.google.gson.JsonArray) => java.util.List */,
        len: 1,
        formHandler: false
      },
      {
        name: 'getSystemTime'/*() => java.util.Date */,
        len: 0,
        formHandler: false
      }
    ]
  }
}

