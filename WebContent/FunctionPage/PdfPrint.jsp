<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB18030"%>
    <%

String path =request.getContextPath();

String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin User</title>

<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">

       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>
       
       <body>
<!-- <object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" name="PDF1" >
        </object> -->
    
      
        <script  language="JavaScript" type="text/javascript" > 
/*  function getQueryString(name) {//输入参数名称
 var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); //根据参数格式，正则表达式解析参数
var r = window.location.search.substr(1).match(reg); 
if (r != null) return unescape(r[2]); return null; //返回参数值 
}  */
   function getQueryString(key){//解决中文乱码
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
         return decodeURIComponent(result?decodeURIComponent(result[2]):null);
      }
		
				
    	var a=getQueryString("filename");
    	//var a=getQueryString("inputPath");
      var d=getQueryString("dsname"); 
	 Ext.onReady(function () {
		// window.location.href = "print?filename="+encodeURIComponent(a)+"&inputPath="+encodeURIComponent(a)+"&dsname="+encodeURIComponent(d); 
			//alert("print?filename="+encodeURI(encodeURI(a))+"&inputPath="+encodeURI(encodeURI(a))+"&dsname="+encodeURI(encodeURI(d)));
			 window.location.href = "print?filename="+encodeURI(encodeURI(a))+"&inputPath="+encodeURI(encodeURI(a))+"&dsname="+encodeURI(encodeURI(d)); 
		 toolbar=0;
		
	 });
     



</script> 

      


</body> 