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
<title>物料反查</title>

<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">
       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>

<script type="text/javascript">

	Ext.onReady(function () {
        
		
		Ext.QuickTips.init();
		
	var item_name = getQueryString("item_name");
	var item_id = getQueryString("item_id");
	var item_revision = getQueryString("item_revision");
			
		
		var treeModel = Ext.define("TreeModel", { // 定义树节点数据模型
            extend : "Ext.data.Model",
            fields : [
                {name : "text",type : "string"},
                {name : "iconCls",type : "string"},
                {name : "leaf",type : "boolean"},
                {name : 'item_id',type:"string"},
                {name : 'item_rev_id',type:"string"}]
        });
		
		var treeStore = Ext.create("Ext.data.TreeStore", {
			model:treeModel,
			
			proxy : {
	 			type : 'ajax',
	 			url : 'GetParentMaterial',
	 			reader : {
	 					type : 'json',
	 					root:'attrs'  
	 			},	 			
                extraParams : {  
                    item_id : '' ,
                    item_rev_id : ''
                } 
	 		},
	 		
	 		root : {//定义根节点，此配置是必须的
	 		  id : 'root',
              text : item_id+'/'+item_revision+';'+item_name,
              item_id:item_id,
              item_rev_id:item_revision,
               iconCls:'item_rev_icon',
              expanded : true
          	},
          	listeners : {  
                'nodebeforeexpand' : function(node,eOpts){  
            //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
                    
                    this.proxy.extraParams.item_id = node.data.item_id;  
                    this.proxy.extraParams.item_rev_id = node.data.item_rev_id; 
                    //alert(node.data.text);  
                  //node.childNodes.sort(function (a, b) { return a.data.text.localeCompare(b.data.text); });
                } 
          	  
            } ,
            autoLoad : false
          	
		});
			
		var treePanel = Ext.create("Ext.tree.Panel", {
	        store:treeStore,
	        rootVisible:true,
	        lines : true,
	        
	        listeners:[{
	        	 itemclick: {
	        		fn: function(view, record, item, index, e,obj){
	        			
	        		
	        		 
	        		 },
	        		 scope: this
	        	 }
	        	 
	        }]
	    });
		
		var west_panel = Ext.create('Ext.panel.Panel',{
			layout:'fit',
			items:[treePanel]
		});
		
		

	/* 
		var center_panel = Ext.create('Ext.panel.Panel',{
			layout:'fit',
			items:[site_grid,depart_grid,user_grid]
		}); */
	
		
		var mainvp = Ext.create('Ext.container.Viewport', {
			layout : 'border',			
			items : [{
						defaultType:'container',
						layout:'fit',
						region : 'center',
						collapsible : false,
						split : true,					
						items:[west_panel]
					}/* ,{
						region : 'center',						
						xtype : 'container',
						layout: 'fit',
						items : [center_panel]
					} */]
		});
	});
	
	
	  function getQueryString(key){//解决中文乱码
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return decodeURIComponent(result?decodeURIComponent(result[2]):null);
      }	
	
</script>	
	

</head>
<body>

</body>
</html>