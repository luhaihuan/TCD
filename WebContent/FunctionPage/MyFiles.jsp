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
<title>Insert title here</title>
<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">

       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>
       
<script type="text/javascript">
	Ext.onReady(function () {
		//只给leaf为true的节点加data，否则会与父节点展开事件发生冲突
	    var store = Ext.create("Ext.data.TreeStore", {
	        root:{
	        	text:"Home",
	            expanded:true,
	            children:[ {
	                text:"文件夹1",
	                expanded:false,
	                children:[ {
	                    text:"Item1",
	                    id:"865700",
	                    leaf:true
	                }, {
	                    text:"Item2",
	                    id:"865701",
	                    leaf:true
	                } ]
	            }, {
	                text:"文件夹2",
	                expanded:false,
	                children:[ {
	                    text:"Item3",	
	                    id:"865702",
	                    leaf:true
	                }, {
	                    text:"Item4",
	                    id:"865703",
	                    leaf:true
	                },{
	                	text:"Item5",	
	                	id:"865704",
	                    leaf:true
	                } ]
	            } ]
	        }
	    });
		
	    var treePanel = Ext.create("Ext.tree.Panel", {
	        store:store,
	        rootVisible:true,	
	        listeners:{
	        	 itemclick: {
	        		fn: function(view, record, item, index, e,obj){	

	        		  //alert(record.data.text);
	        		  if(record.data.leaf){
	        			  document.getElementById("fileResult").src = "/SulliarPaperlessSystem/FileResultPage/SingeFile.jsp";
	        		  }else{
	        			  document.getElementById("fileResult").src = "/SulliarPaperlessSystem/FileResultPage/MultiFiles.jsp";
	        		  }        		  
	        		  
	        		  e.stopEvent;
	        		 },
	        		 scope: this
	        	 }
	        	 
	        },
	        renderTo:Ext.getBody()
	    });
	    
	    Ext.create('Ext.container.Viewport', {
			layout : 'border',			
			items : [ {
						region : 'west',
						collapsible : false,
						split : true,						
						width : 170 ,
						// could use a TreePanel or AccordionLayout for navigational
						items : treePanel
					}, {
						region : 'center',						
						collapsible : false,
						split : true,						
						id:"mainPanel",
						html: '<iframe id ="fileResult" width="100%" height="100%" frameborder="no" border="0" ></iframe>',
						
					}]
		});
	
	});
	
</script>

</head>
<body>

</body>
</html>