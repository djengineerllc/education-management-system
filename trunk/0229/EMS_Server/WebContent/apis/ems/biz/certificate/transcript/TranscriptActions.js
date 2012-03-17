/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.certificate.transcript.TranscriptActions');

ems.biz.certificate.transcript.TranscriptActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/ES/' + 'djn/directprovider';

ems.biz.certificate.transcript.TranscriptActions.POLLING_URLS = {
}

ems.biz.certificate.transcript.TranscriptActions.REMOTING_API = {
  url: ems.biz.certificate.transcript.TranscriptActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    TranscriptAction: [
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

