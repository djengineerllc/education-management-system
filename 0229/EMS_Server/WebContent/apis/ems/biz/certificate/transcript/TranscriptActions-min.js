Ext.namespace("ems.biz.certificate.transcript.TranscriptActions");
ems.biz.certificate.transcript.TranscriptActions.PROVIDER_BASE_URL=window.location.protocol+"//"+window.location.host+"/EMS_Server/djn/directprovider";
ems.biz.certificate.transcript.TranscriptActions.POLLING_URLS={};
ems.biz.certificate.transcript.TranscriptActions.REMOTING_API={url:ems.biz.certificate.transcript.TranscriptActions.PROVIDER_BASE_URL,type:"remoting",actions:{ems_biz_certificate_transcript_TranscriptActions_TranscriptAction:[{name:"printCert",len:2,formHandler:false},{name:"loadList",len:1,formHandler:false}]}};