<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css">
  	<link rel="stylesheet" type="text/css" href="extjs/examples/shared/examples.css" />
  	<script type="text/javascript" src="extjs/adapter/ext/ext-base-debug.js"></script>
 	<script type="text/javascript" src="extjs/ext-all-debug.js"></script>
<!--以下为DirectJNgine的基础JS文件，放在djn目录下，直接引用就可以。-->
  <script type="text/javascript" src="djn/djn-remote-call-support.js"></script>
  <script type="text/javascript" src="ejn/ejn-assert.js"></script>
  <!--以上为DirectJNgine的基础JS文件.-->
  <script type="text/javascript">
  Ext.onReady(function(){
		var out = new Ext.form.DisplayField({
			cls: 'x-form-text',
			id: 'out',
			renderTo: 'outdiv',
			autoScroll: true
			});//输出打印信息的DisplayField
		var multiply = new Ext.Button({
				text:'调用后台方法',
				handler:function (){
					try{
						alert('test....');
						//此处通过命名空间，添加初始化远程调用API
						ems.main.MainActions.REMOTING_API.enableBuffer = 0;
						Ext.Direct.addProvider(ems.main.MainActions.REMOTING_API);
						alert('test....');
						//根据类名MyDemo调用它的方法doShow,此函数没有传入参数。 回调函数的参数result表示返回的结果。参数e表示当前调用状态。
						TestAction.doEcho(function(result,e){
								var t = e.getTransaction();
								if(e.status){
									out.append(String.format('<p><b>Successful call to {0}.{1} with response:</b><xmp>{2}</xmp></p>',
											t.action, t.method, Ext.encode(result)));
									}else{
										out.append(String.format('<p><b>Call to {0}.{1} failed with message:</b><xmp>{2}</xmp></p>',
												t.action, t.method, e.message));
										}
								out.el.scroll('b', 100000, true);
							});
						//doEcho函数，此函数有参数。
						var parm = txtparm.value;  //要传入后台的参数
						TestAction.doShow(parm,function(result,e){
							var t = e.getTransaction();
							if(e.status){
								out.append(String.format('<p><b>Successful call to {0}.{1} with response:</b><xmp>{2}</xmp></p>',
										t.action, t.method, Ext.encode(result)));
								}else{
									out.append(String.format('<p><b>Call to {0}.{1} failed with message:</b><xmp>{2}</xmp></p>',
											t.action, t.method, e.message));
									}
							out.el.scroll('b', 100000, true);
							});
						}catch(err)
						{
							alert(err.description);
						}
			}
			});
		var txtField = new Ext.form.TextField({
			id: 'txtparm',
			name: 'txtparm',
			emptyText :'请输入参数',
			renderTo: 'txtFieldDiv'
		});
		var p = new Ext.Panel({
			title: '远程调用方法输出日志',
			//frame:true,
			width: 600,
			height: 300,
			layout:'fit',
			items: [out],
			bbar: [multiply]
		}).render(Ext.getBody());
	  });
  </script>
  </head>
  
  <body>
    <h1>Ext Direct 使用directjngine项目，调用后台Java方法</h1>
	<div id="outdiv" ></div>
	<div id="txtFieldDiv"></div><br/>
  </body>
</html>
