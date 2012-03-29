/**********************************************************************
 * 
 * Code generated automatically by DirectJNgine
 * Copyright (c) 2009, Pedro Agull¨® Soliveres
 * 
 * DO NOT MODIFY MANUALLY!!
 * 
 **********************************************************************/

Ext.namespace( 'ems.biz.syllabus.syllabusbyclass.SyllabusByClassActions');

ems.biz.syllabus.syllabusbyclass.SyllabusByClassActions.PROVIDER_BASE_URL=window.location.protocol + '//' + window.location.host + '/EMS_Server/' + 'djn/directprovider';

ems.biz.syllabus.syllabusbyclass.SyllabusByClassActions.POLLING_URLS = {
}

ems.biz.syllabus.syllabusbyclass.SyllabusByClassActions.REMOTING_API = {
  url: ems.biz.syllabus.syllabusbyclass.SyllabusByClassActions.PROVIDER_BASE_URL,
  type: 'remoting',
  actions: {
    ems_biz_syllabus_syllabusbyclass_SyllabusByClassActions_SyllabusByClassAction: [
      {
        name: 'printSyllbusPlan'/*(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) => void */,
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

