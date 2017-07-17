<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB18030"%>
<%

String path =request.getContextPath();

String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>物料属性</title>
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
//alert(getQueryString("normal_attrs"));
Ext.onReady(function () {
	//var normal_attrs = Ext.decode(getQueryString("normal_attrs"));
	//alert(attrs[0].title)
	var searchType = getQueryString("searchType");
	var item_id = getQueryString("item_id");
	var item_revision = getQueryString("item_revision");
	var item_name = getQueryString("item_name");
	
 	Ext.define('AttrModel', {  
        extend: 'Ext.data.Model',  
        proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },  
        fields: [  
            {name: 'title',type: 'string'},
            {name: 'content',type: 'string'}
        ]  
    }); 
 	var normal_attrs;
 	var class_attrs;
	 Ext.Ajax.request({
			url:'GetMaterialAttribute',
			async:false,
			params:{                
			// addModels:Ext.encode(aclAddModels),
			searchType:encodeURIComponent(searchType),
			item_id:item_id,					
			item_revision:item_revision
			}, 
			 method:'POST',
			success:function (response){
			//Ext.getBody().unmask();
			//alert(response.responseText);
			normal_attrs = Ext.decode(response.responseText).normal_attrs;
			class_attrs = Ext.decode(response.responseText).class_attrs;
			var success = Ext.decode(response.responseText).success;
			//alert(normal_attrs[0].title);
			//window.open('<%=basePath%>FunctionPage/MaterialAttrs.jsp?item_id='+item_id+'&item_revision='+item_revision+'&normal_attrs='+Ext.encode(normal_attrs)+'&searchType='+searchType);
			if(!success) {
				Ext.Msg.alert("提示","获取信息失败!");
			}
			},
			failure:function(response){
			//Ext.getBody().unmask();
			Ext.Msg.alert("提示","获取信息失败!");
			}
		});	
	 
 	 var normal_attrs_store = Ext.create('Ext.data.Store', {
		storeId : 'normal_attrs_store',
		fields : [ 'title','content'],
		model : 'AttrModel',
		data : normal_attrs,
	 	/*  proxy : {
			type : 'ajax',
			url : 'GetMaterialAttribute',
			reader : {
					type : 'json',
					root:'normal_attrs', //对应action中需要被现实的字段，一般为list  
		            success:'success',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
			},
			extraParams : {  
                   searchType:encodeURIComponent(searchType),
			       item_id:item_id,					
			       item_revision:item_revision
                }
		},   */
		autoLoad : true
	 

	});  
 	 
 	 var class_attrs_store = Ext.create('Ext.data.Store', {
		storeId : 'class_attrs_store',
		fields : [ 'title','content'],
		model : 'AttrModel',
		data : class_attrs,
	 	/*  proxy : {
			type : 'ajax',
			url : 'GetMaterialAttribute',
			reader : {
					type : 'json',
					root:'normal_attrs', //对应action中需要被现实的字段，一般为list  
		            success:'success',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
			},
			extraParams : {  
                   searchType:encodeURIComponent(searchType),
			       item_id:item_id,					
			       item_revision:item_revision
                }
		},   */
		autoLoad : true
	 

	});
	
	/* 	var attrs_store = Ext.create('Ext.data.Store', {
		storeId : 'attrs_store',
		fields : [ 'title','content'],
		model : 'AttrModel',
		//data :getQueryString('attrs'),
		autoLoad : true,
		 listeners: {  
 	        'refresh': function (store, options) { 
 	        	//var ftcombo = form.getForm().findField("aclType").getValue();
 	        	//var ftcombo = form.getForm().findField("aclType");
 	        	//ftcombo.setValue(store.getAt(0).get('type_id'));
 	           // storts.reload();
 	           
 	        }  
 	    }  

	});
	 */
	
 	        	
	

	




	


	
	
	Ext.create('Ext.container.Viewport', {
		layout : 'border',			
		items : [ {region : 'north',						
					collapsible : false,
					split : false,
					xtype: 'button',
					style: {
					/* borderColor: '#ff0000',
					borderStyle: 'solid', */
					bodyStyle:'font-size:100px'
					
					},
					text : item_id+'/'+item_revision+';'+item_name
					} ,{
					region : 'center',
					flex: 1,
					title:'通用属性',
					collapsible : false,
					split : false,
					xtype: 'grid',
					id:'normal_attr_grid',
					store: normal_attrs_store, 				
				
			
					columns : [{
						text : '属性',
						dataIndex : 'title',
						flex: 1
						
						
					},{
						text : '属性值',
						dataIndex : 'content',
						flex: 2
						
					}]
			
				
				},{
					region : 'east',	
					title:'分类属性',
					flex: 1,
					collapsible : false,
					split : true,
					xtype: 'grid',
					id:'class_attr_grid',
					store: class_attrs_store, 				
				
			
					columns : [{
						text : '属性',
						dataIndex : 'title',
						flex: 1
						
						
					},{
						text : '属性值',
						dataIndex : 'content',
						flex: 2
						
					}]
			
				
				} ]
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