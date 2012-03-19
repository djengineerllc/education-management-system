/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull�� Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.basicInfo.classManager.ClassActions');

ems.biz.basicInfo.classManager.ClassActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.biz.basicInfo.classManager.ClassActions.POLLING_URLS = {
}

ems.biz.basicInfo.classManager.ClassActions.REMOTING_API = {
  url: ems.biz.basicInfo.classManager.ClassActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_biz_basicInfo_classManager_ClassActions_ClassAction: [
      {
        name: 'update'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      },
      {
        name: 'exportExcel'/*(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) => void */,
        len: 2,
        formHandler: false
      },
      {
        name: 'delete'/*(Integer[]) => com.ems.system.client.vo.ExtFormVO */,
        len: 1,
        formHandler: false
      },
      {
        name: 'read'/*(Integer) => com.ems.system.client.vo.ExtFormVO */,
        len: 1,
        formHandler: false
      },
      {
        name: 'loadClass'/*(com.google.gson.JsonArray) => com.ems.system.client.vo.ExtPagingVO */,
        len: 1,
        formHandler: false
      },
      {
        name: 'create'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      },
      {
        name: 'batchImport'/*() => com.ems.system.client.vo.ExtFormVO -- FORM HANDLER */,
        len: 1,
        formHandler: true
      },
      {
        name: 'downloadExcelTemplate'/*(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) => void */,
        len: 2,
        formHandler: false
      }
    ]
  }
}

