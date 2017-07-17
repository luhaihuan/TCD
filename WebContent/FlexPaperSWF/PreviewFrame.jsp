<%@page import="com.sulliar.ypq.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="GB18030"%>
<%

String path =request.getContextPath();

String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>


<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Preview</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/icon.css">
<script type="text/javascript" src="jquery.js"></script>

<script type="text/javascript" src="flexpaper_flash.js"></script>

<script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

<script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

<script type="text/javascript"
	src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

<script type="text/javascript" src="<%=basePath%>js/app.js"></script>

<script type="text/javascript" src="<%=basePath%>js/base64.js"></script>

<script type="text/javascript">
/*        	function getQueryString(name) { 
       		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
       		var r = window.location.search.substr(1).match(reg); 
       		if (r != null) return unescape(r[2]); return null; 
       	}  */
       	  function getQueryString(key){//解决中文乱码
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return result?decodeURIComponent(result[2]):null;
      }
       	var b = new Base64();
       	var id = getQueryString("id");
				
    	var str7 = b.decode(id);
    	//alert(str1);
    	
    	var name = getQueryString("name");
				
    	var str2 = b.decode(name);
    	
    	var rev = getQueryString("rev");				
    	var str3 = b.decode(rev);
    	
    	var fp = getQueryString("fp");
    	var str4 = b.decode(fp);
    	
    	var dst = getQueryString("dst");				
    	var str5 = b.decode(dst);
    	
    	var dsn = getQueryString("dsn");				
    	var str6 = b.decode(dsn);
    	
    	var cp = getQueryString("cp");
    	var cd = getQueryString("cd");
    	
    	var type = getQueryString("type");
    	
    	var searchType = b.decode(type);
    	
    	
    	var rp = getQueryString("rp");
    	var str1 = b.decode(rp);
    	
    	var rp1 = "<%=basePath%>HFTempFiles/"+str1;
       	
    	window.onload=function myalert()
        {
          document.getElementById("targetPdf").src="<%=basePath%>FlexPaperSWF/Preview.jsp?file="+ rp1;
        }
    	
		</script>
</head>



<!-- <frameset cols="22%,78%" > -->
<frameset cols="100%">
	<%-- <frame src="<%=basePath%>FlexPaperSWF/SWF1.jsp" /> --%>
	<%--  <frame id ="targetPdf" src="<%=basePath%>FlexPaperSWF/Preview.jsp?" --%>
	<frame id="targetPdf" src="" />
</frameset>


</html>