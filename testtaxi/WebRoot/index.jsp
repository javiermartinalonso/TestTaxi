<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>autoescueladeltaxi.com</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <style type="text/css"> 
html, body, div, iframe { 
margin:0; 
padding:0; 
height:100%; 
} 
iframe { 
display:block; 
width:100%; 
border:none; } 
</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <iframe src="http://www.autoescueladeltaxi.com/">
      Estamos redireccionando su página. Espere unos instantes.<br>
	<%
		/*response.sendRedirect("xhtml/test/test.xhtml");*/
		response.sendRedirect("xhtml/home.xhtml");
	%>  
  </iframe>

  </body>
</html>
