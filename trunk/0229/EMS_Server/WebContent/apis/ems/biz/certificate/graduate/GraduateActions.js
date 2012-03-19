/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.certificate.graduate.GraduateActions');

ems.biz.certificate.graduate.GraduateActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.biz.certificate.graduate.GraduateActions.POLLING_URLS = {
}

ems.biz.certificate.graduate.GraduateActions.REMOTING_API = {
  url: ems.biz.certificate.graduate.GraduateActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_biz_certificate_graduate_GraduateActions_GraduateAction: [
      {
        name: 'printCert'/*(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) => void */,
        len: 2,
        formHandler: false
      },
      {
        name: 'loadList'/*(com.google.gson.JsonArray) => com.ems.system.client.vo.ExtPagingVO */,
        len: 1,
        formHandler: false
      }
    ]
  }
}

