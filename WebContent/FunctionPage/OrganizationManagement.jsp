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
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">
       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>

<script type="text/javascript">

	Ext.onReady(function () {
        
		var targetNode;
		Ext.QuickTips.init();		
		
		var model = Ext.define("TreeModel", { // 定义树节点数据模型
            extend : "Ext.data.Model",
            fields : [{name : "id",type : "string"},
                {name : "text",type : "string"},
                {name : "iconCls",type : "string"},
                {name : "leaf",type : "boolean"},
                {name : 'nodeType',type:"string"}]
        });
		
		var store = Ext.create("Ext.data.TreeStore", {
			model:model,
			
			proxy : {
	 			type : 'ajax',
	 			url : 'GetAllOrganization',
	 			reader : {
	 					type : 'json',
	 					root:'models'  
	 			},	 			
                extraParams : {  
                    id : ''  ,
                    nodeType : ''
                } 
	 		},
	 		autoLoad : false,
	 		root : {//定义根节点，此配置是必须的
	 		  id : 'root',
              text : '组织架构',
              iconCls:'site_icon',
              expanded : true
          	},
          	listeners : {  
                'nodebeforeexpand' : function(node,eOpts){  
            //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
                    
                    this.proxy.extraParams.id = node.data.id;  
                    this.proxy.extraParams.nodeType = node.data.nodeType; 
                    //alert(node.data.text);  
                  //node.childNodes.sort(function (a, b) { return a.data.text.localeCompare(b.data.text); });
                },  
          	  
            } 
          	
		});
		//targetNode = store.getNodeById("root");
		targetNode = store.getRoot();
		//treeReload(targetNode);
			function delTreeReload(data) {
				 Ext.Array.each(data,function(record){
							                     // alert(record.uuid+record.site_name)
							                          targetNode.removeChild(store.getNodeById(record.uuid));
							                                        	 
							                          }
							      );
			}
			
			function modTreeReload(id,data) {
				
			 	 var node = store.getNodeById(id);
			 	 node.data.text = data;
			 	 targetNode.collapse();
			 	 targetNode.expand();
				/* var idStr = node.data.id;									
				 var textStr = data;
				 var iconClsStr = node.data.iconCls;
				var leafStr = node.data.leaf;
				 var nodeTypeStr = node.data.nodeType; */
				//node.data.text = data;
				//store.reload();
				//alert(node.data.text+"-->"+data)
				
				
			}
		
		function addTreeReload(tm) { 
				
		 	/* if(targetNode.data.leaf == false) {
				targetNode.removeAll();
				
				
				
			} 
		 	
		 	    targetNode.data.leaf = false;
		 	    var parentNode = targetNode.parentNode;
				parentNode.collapse();
				parentNode.expand(); */
				//targetNode.expand();
				
			//alert(tm.text+"-->"+tm.id)
			var isLeaf = targetNode.data.leaf;
			 	targetNode.appendChild([{
											id:tm.id,  
											text:tm.text,
											iconCls:tm.iconCls,
											leaf:tm.leaf,
											nodeType:tm.nodeType
										}]);  
			if(isLeaf == true) {//刷新第一次添加
				targetNode.parentNode.collapse();
			    targetNode.parentNode.expand();
			    targetNode.collapse();
			    targetNode.expand();
			}
			
			 	 
		//	targetNode.childNodes.sort(function (a, b) { return a.data.text.localeCompare(b.data.text); });
		//	targetNode.collapse();
		//	targetNode.expand();
			//targetNode.appendChild(tm);
			/* Ext.Ajax.request({
									timeout: 600000,
									url:'GetAllOrganization',
									method:'POST',
									params:{                
											  id : tempNode.data.id  ,
                                              nodeType : tempNode.data.nodeType					
											},
									success:function (response){ 
										//Ext.Msg.alert("info",response.responseText);
									//Ext.Msg.alert("info",Ext.decode(response.responseText).dateList[0]);
									//Ext.log(Ext.decode(response.responseText));
                                   tempNode.removeAll();  
                                
								    Ext.each(Ext.decode(response.responseText).models,
									 
									function(model){
										 var idStr = model.id;									
									     var textStr = model.text;
									     var iconClsStr = model.iconCls;
									     var leafStr = model.leaf;
									     var nodeTypeStr = model.nodeType;
								
								
									
									
									tempNode.appendChild([{
											id:idStr,  
											text:textStr,
											iconCls:iconClsStr,
											leaf:leafStr,
											nodeType:nodeTypeStr
										}]);
										
																
											  
											});  
										
										
									},
									failure:function (response){
										 Ext.Msg.alert("提示","获取树数据失败!");
									}
	        					});
	        			tempNode.expand(); */
			 
 			  
			   
			/* 	store.proxy.extraParams.id = targetNode.id;  
                store.proxy.extraParams.nodeType = targetNode.nodeType;  */
               // targetNode.collapse();
               // targetNode.expand();
                //if(!targetNode.isRoot)
                //store.getNodeById(targetNode.id).removeAll();
                //targetNode.expanded = true;
                //if(!targetNode.isRoot)
                //  store.getNodeById(targetNode.id).reload();
               //  alert(globalAddAction+"-->"+targetNode.leaf+"-->"+treeNode.data.text);	
               // store.proxy.extraParams.id = targetNode.data.id;
              //  store.proxy.extraParams.nodeType = targetNode.data.nodeType;
              /*   if(targetNode.data.id != "root") {
                	targetNode.removeAll();
                	// alert(targetNode.data.text+"-->"+targetNode.data.nodeType);	
                }
                 */
              
             //   alert(targetNode.data.text+"-->"+targetNode.data.nodeType);		
			//	store.reload();
			
			
			
			
				
		}
	
		
		var treePanel = Ext.create("Ext.tree.Panel", {
	        store:store,
	        rootVisible:true,
	        lines : true,
	        
	        listeners:[{
	        	 itemclick: {
	        		fn: function(view, record, item, index, e,obj){
	        			
	        			  
	        			 targetNode = store.getNodeById(record.data.id);
	        			
	        			 //targetNode.data.leaf = false;
	        		
	        			 
		                
	        			
	        			if(record.data.text == "组织架构") {
						 	site_grid.show();
						 	depart_grid.hide();
						 	user_grid.hide();
						 
						 	site_stores.reload();
						// 	alert(record.data.text+"-->"+center_panel.items.length);	
						
	        			}	        				        			
	        			if(record.data.nodeType == 'user'){
	        				//alert("user-->"+record.data.text);
	        				site_grid.hide();
						 	depart_grid.hide();
						 	user_grid.hide();
						 	Ext.Msg.alert("提示","请点击有效选项");
	        			}else if(record.data.nodeType == 'department'){
	        			
	        			    site_grid.hide();
						 	depart_grid.hide();
						 	user_grid.show();	
						 	user_stores.proxy.extraParams.id =  record.data.id;
				            user_stores.proxy.extraParams.nodeType =  record.data.nodeType;
				            user_stores.reload();
				           
					//	alert(record.data.text+"-->"+center_panel.items.length);	        				
	        			}else if(record.data.nodeType == 'site'){	        		
                            site_grid.hide();
						 	depart_grid.show();
						 	user_grid.hide();				
						
							depart_stores.proxy.extraParams.id =  record.data.id;
					        depart_stores.proxy.extraParams.nodeType =  record.data.nodeType;
					        depart_stores.reload();
					        
	        			}else{
	        				
	        			}
	        		 
	        		 },
	        		 scope: this
	        	 }
	        	 
	        }]
	    });
		
		var west_panel = Ext.create('Ext.panel.Panel',{
			layout:'fit',
			items:[treePanel]
		});
		
		Ext.define('Site', {  
	         extend: 'Ext.data.Model',  
	         proxy: {  
	             type: 'ajax',  
	             reader: 'json'  
	         },  
	         fields: [
	             {name: 'uuid' , type: 'string'},
	             {name: 'site_name',type: 'string'}
	         ]  
	     });
		
		var site_stores = Ext.create('Ext.data.Store', {
			storeId : 'siteStore',
			fields : [ 'uuid','site_name'],
			model : 'Site',	
			pageSize: 12,
			proxy : {
				type : 'ajax',
				url : 'GetSite',
				reader : {
					type : 'json',
					root:'sites', //对应action中需要被现实的字段，一般为list  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
				}
			},
			autoLoad : true

		});
		
		
		
		var site_grid = Ext.create('Ext.grid.Panel', {
			id:'site_grid',
			selModel: {
		        injectCheckbox: 0,
		        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: true     //只能通过checkbox选择
		    },
		    selType: "checkboxmodel",		
			store : site_stores,
			columns : [
					{ xtype: "rownumberer" },
			        {
						text : '站点名称',
						dataIndex : 'site_name'
					},{
						dataIndex : 'uuid',
						hidden: true
					}, {
						xtype:'actioncolumn',//这里就是放置按钮的地方
						width:50,
						text:'操作',
						items: [{icon: '<%=basePath%>icons/user_edit.png', // Use a URL in the icon config
								tooltip: '修改',
								handler:function(grid,rindex,cindex){
									var record = grid.getStore().getAt(rindex);
								     
								    var site_name = record.get("site_name");
								    var uuid = record.get("uuid");							    
	                        		
	                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
	                     		    height: 330,
	                     		    width: 310,
	                     		    bodyPadding: 10,
	                     		    defaultType: 'textfield',
	                     		    items: [
	                     		        {
	                     		        	id:'siteName',
	                     		            fieldLabel: '站点',
	                     		            name: 'site.site_name' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70,
	                     	                allowBlank: false,	                
	                     	                blankText: '请输入站点名称',
	                     	                value: site_name
	                     		        }
	                     		    ]
	                     		});
	                     		
	                     		var win = Ext.create('Ext.window.Window', {
	                     		    title: '修改站点',
	                     		    height: 330,
	                     		    width: 310,				   
	                     		    buttonAlign: 'center',		    
	                     		    modal: true,
	                     		    resizable:false,
	                     		   	draggable:false,
	                     		    layout: "fit",
	                     		    closable: true,				                     		    
	                     		    items: form,
	                     		    buttons: [{
	                     		    	xtype: 'button',					                     		    	
	                     		    	text:'保 存',
	                     		    	handler: function(){
	                     		    		if (form.getForm().isValid()) {
	                     		    				var tempData = form.getForm().findField("siteName").getValue();
	                     		    			form.getForm().doAction('submit',{  
		                     	              		 url:'ModSite',                		
		                     	              		 method:'post',  
		                     	              		params:{
		                                                 
		                                               uuid:uuid
		                                            },
		                     	               	success:function(form,action){ 
		                     	               	    
		                     	               		Ext.Msg.alert("信息","修改站点成功!");
		                     	               		win.close();
		                     	               		site_stores.reload();
		                     	               		modTreeReload(uuid,tempData);
		                     	               		
		                     	               		
		                     	               	},  
		                     	               	failure:function(form,action){  
		                     	                   	Ext.Msg.alert("信息",action.result.message);  
		                     	               	}  
		                     	           		});
	                     		    		}
	                     		    		
	                     		    	}
	                     		    },{
	                     		    	xtype: 'button',					                     		    	
	                     		    	text:'取 消',
	                     		    	handler: function(){
	                     		    		win.close();
	                     		    	}
	                     		    }]
	                     		});
	                     		win.show();                  		
	                     		
								
	                     		
								}
						}]
					}],
						
					dockedItems: [  
					              {  
					                  xtype: 'toolbar',  
					                  dock: 'top',  
					                  items: [  
					                     {  
					                         xtype: 'button',  
					                         text:  '添加站点',  
					                         icon:'<%=basePath%>icons/user_add.png',  
					                         handler : function(){                    	 				                        	 
					                        	 
					                        							                        		
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [
					                     		        {
					                     		        	id:'siteName',
					                     		            fieldLabel: '站点',
					                     		            name: 'site.site_name' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	                
					                     	                blankText: '请输入站点名'
					                     		        }
					                     		    ]
					                     		});
					                     		
					                     		var win = Ext.create('Ext.window.Window', {
					                     		    title: '添加站点',
					                     		    height: 330,
					                     		    width: 310,				   
					                     		    buttonAlign: 'center',		    
					                     		    modal: true,
					                     		    resizable:false,
					                     		   	draggable:false,
					                     		    layout: "fit",
					                     		    closable: true,				                     		    
					                     		    items: form,
					                     		    buttons: [{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'保 存',
					                     		    	handler: function(){
					                     		    		if (form.getForm().isValid()) {
					                     		    			
					                     		    			form.getForm().doAction('submit',{  
						                     	              		 url:'AddSite',                		
						                     	              		 method:'post',  
						                     	               	success:function(form,action){ 
						                     	               	
						                     	               		Ext.Msg.alert("信息",action.result.message);
						                     	               		addTreeReload(action.result.tm);
						                     	               		win.close();
						                     	               		site_stores.reload();
						                     	               		
						                     	               		
						                     	               		
						                     	               	},  
						                     	               	failure:function(form,action){  
						                     	                   	Ext.Msg.alert("信息",action.result.message);  
						                     	               	}  
						                     	           		});
					                     		    		}
					                     		    		
					                     		    	}
					                     		    },{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'取 消',
					                     		    	handler: function(){
					                     		    		win.close();
					                     		    	}
					                     		    }]
					                     		});
					                     		win.show();
					                     		
					                         }  
					                     } ,{  
					                         xtype: 'button',  
					                         text:  '删除站点',  
					                         icon:'<%=basePath%>icons/user_delete.png',  
					                         handler : function(){ 
					                        	 var sm = site_grid.getSelection(); // 获得grid的SelectionModel
					                        	 if(sm!=null&&sm.length>0){					                        	     
					                        	     var siteIds=[]; 
					                        	    
					                                     Ext.Array.each(
					                                         sm,
					                                         function(record){
					                                        	 var uuid = record.get("uuid");
					                                        	 var site_name = record.get("site_name");
					                                        	 if(site_name!="dba"){
					                                        		 siteIds.push(record.data);
					                                        	 }
					                                        	 
					                                         }
					                                     );
					                        	     
					                        	 		 Ext.Ajax.request({
					                        	 			url:'DelSite',
				                                            params:{
				                                                 //在后台接收deleteUsers为要删除的对象
				                                               deleteSites:Ext.encode(siteIds)
				                                            }, 
				                                            method:'post',
				                                            success:function (response){
				                                            	var success = Ext.decode(response.responseText).success;
				                                            	delTreeReload(siteIds);
				                                                 if(success){
				                                                   Ext.Array.each(sm,function(record){
				                                                       //删除store中的数据，页面效果
				                                                	   var site_name = record.get("site_name");
							                                        	 if(site_name!="dba"){
							                                        		 site_stores.remove(record);
							                                        	 }
				                                                       
				                                                     }
				                                                       
				                                                   )
				                                                 }else{
				                                                   Ext.MessageBox.show(
				                                                       '提示',
				                                                       '删除失败'
				                                                   )
				                                                 }
				                                            },
				                                        	failure:function(response){
				                                        		Ext.MessageBox.show(
					                                                       '提示',
					                                                       '删除失败'
					                                                   )
				                                        	}
				                                            
					                        	 		 });				                                               
					                        	    	
					                        	 	
					                        	 }
					                         }  
					                     } 
					                   ]  
					              },//下方分页工具条  
					              {  
					                  xtype: 'pagingtoolbar',  
					                  store: site_stores,  
					                  dock: 'bottom',  
					                  displayInfo: true  
					              }  
					            ]
		});
		
		
		
		
	
		
		
		Ext.define('Depart', {  
	         extend: 'Ext.data.Model',  
	         proxy: {  
	             type: 'ajax',  
	             reader: 'json'  
	         },  
	         fields: [
	             {name: 'uuid' , type: 'string'},
	             {name: 'department_name',type: 'string'}
	         ]  
	     });
			
		
		var depart_stores = Ext.create('Ext.data.Store', {
			storeId : 'departStore',
			fields : [ 'uuid','department_name'],
			model : 'Depart',			
			pageSize: 12,
			proxy : {
				extraParams : {  
                    id : ''  ,
                    nodeType : ''
                }, 
				type : 'ajax',
				url : 'GetDepartBySiteId',
				reader : {
					type : 'json',
					root:'departs', //对应action中需要被现实的字段，一般为list  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
				}
			},
			autoLoad : false

		});
		
		
		var depart_grid = Ext.create('Ext.grid.Panel', {
			id:'depart_grid',
			selModel: {
		        injectCheckbox: 0,
		        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: true     //只能通过checkbox选择
		    },
		    selType: "checkboxmodel",		
			store : depart_stores,
			columns : [
					{ xtype: "rownumberer" },
			        {
						text : '部门名称',
						dataIndex : 'department_name'
					},{
						dataIndex : 'uuid',
						hidden: true
					}, {
						xtype:'actioncolumn',//这里就是放置按钮的地方
						width:50,
						text:'操作',
						items: [{icon: '<%=basePath%>icons/user_edit.png', // Use a URL in the icon config
								tooltip: '修改',
								handler:function(grid,rindex,cindex){
									var record = grid.getStore().getAt(rindex);
								     
								    var depart_name = record.get("department_name");
								    var uuid = record.get("uuid");							    
	                        		
	                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
	                     		    height: 330,
	                     		    width: 310,
	                     		    bodyPadding: 10,
	                     		    defaultType: 'textfield',
	                     		    items: [
	                     		        {
	                     		        	id:'departName',
	                     		            fieldLabel: '部门',
	                     		            name: 'depart.department_name' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70,
	                     	                allowBlank: false,	                
	                     	                blankText: '请输入部门名称',
	                     	                value: depart_name
	                     		        }
	                     		    ]
	                     		});
	                     			
	                     		var win = Ext.create('Ext.window.Window', {
	                     		    title: '修改部门',
	                     		    height: 330,
	                     		    width: 310,				   
	                     		    buttonAlign: 'center',		    
	                     		    modal: true,
	                     		    resizable:false,
	                     		   	draggable:false,
	                     		    layout: "fit",
	                     		    closable: true,				                     		    
	                     		    items: form,
	                     		    buttons: [{
	                     		    	xtype: 'button',					                     		    	
	                     		    	text:'保 存',
	                     		    	handler: function(){
	                     		    		if (form.getForm().isValid()) {
	                     		    			var tempData = form.getForm().findField("departName").getValue();
	                     		    			form.getForm().doAction('submit',{  
		                     	              		 url:'ModDepart',                		
		                     	              		 method:'post',  
		                     	              		params:{
		                                                 
		                                               uuid:uuid,
		                                               depart_name:depart_name
		                                            },
		                     	               	success:function(form,action){ 
		                     	               	
		                     	               		Ext.Msg.alert("信息","修改部门成功!");
		                     	               		win.close();
		                     	               		depart_stores.reload();
		                     	               		modTreeReload(uuid,tempData);
		                     	               	
		                     	               		
		                     	               	},  
		                     	               	failure:function(form,action){  
		                     	                   	Ext.Msg.alert("信息",action.result.message);  
		                     	               	}  
		                     	           		});
	                     		    		}
	                     		    		
	                     		    	}
	                     		    },{
	                     		    	xtype: 'button',					                     		    	
	                     		    	text:'取 消',
	                     		    	handler: function(){
	                     		    		win.close();
	                     		    	}
	                     		    }]
	                     		});
	                     		win.show();                  		
	                     		
								
	                     		
								}
						}]
					}],
						
					dockedItems: [  
					              {  
					                  xtype: 'toolbar',  
					                  dock: 'top',  
					                  items: [  
					                     {  
					                         xtype: 'button',  
					                         text:  '添加部门',  
					                         icon:'<%=basePath%>icons/user_add.png',  
					                         handler : function(){                    	 				                        	 
					                        	 
					                        							                        		
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [
					                     		        {
					                     		            fieldLabel: '部门',
					                     		            id:'departName',
					                     		            name: 'depart.department_name' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	                
					                     	                blankText: '请输入部门名称'
					                     		        },
					                     		        {
					                     		        	xtype:'hidden',
					                     		            fieldLabel: '站点id',
					                     		            name: 'depart.site.uuid' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	
					                     	                value:depart_stores.proxy.extraParams.id
					                     	               
					                     		        }
					                     		    ]
					                     		});
					                     		
					                     		var win = Ext.create('Ext.window.Window', {
					                     		    title: '添加部门',
					                     		    height: 330,
					                     		    width: 310,				   
					                     		    buttonAlign: 'center',		    
					                     		    modal: true,
					                     		    resizable:false,
					                     		   	draggable:false,
					                     		    layout: "fit",
					                     		    closable: true,				                     		    
					                     		    items: form,
					                     		    buttons: [{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'保 存',
					                     		    	handler: function(){
					                     		    		if (form.getForm().isValid()) {
					                     		    		
					                     		    			form.getForm().doAction('submit',{  
						                     	              		 url:'AddDepart',                		
						                     	              		 method:'post',  
						                     	               	success:function(form,action){ 
						                     	               	
						                     	               		Ext.Msg.alert("信息",action.result.message); 
						                     	               		addTreeReload(action.result.tm);
						                     	               		win.close();
						                     	               		depart_stores.reload();
						                     	               		
						                     	               	
						                     	               		
						                     	               	},  
						                     	               	failure:function(form,action){  
						                     	                   	Ext.Msg.alert("信息",action.result.message);  
						                     	               	}  
						                     	           		});
					                     		    		}
					                     		    		
					                     		    	}
					                     		    },{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'取 消',
					                     		    	handler: function(){
					                     		    		win.close();
					                     		    	}
					                     		    }]
					                     		});
					                     		win.show();
					                     		
					                         }  
					                     } ,{  
					                         xtype: 'button',  
					                         text:  '删除部门',  
					                         icon:'<%=basePath%>icons/user_delete.png',  
					                         handler : function(){ 
					                        	 var sm = depart_grid.getSelection(); // 获得grid的SelectionModel
					                        	 if(sm!=null&&sm.length>0){					                        	     
					                        	     var departIds=[]; 
					                        	    
					                                     Ext.Array.each(
					                                         sm,
					                                         function(record){
					                                        	 var uuid = record.get("uuid");
					                                        	 var depart_name = record.get("department_name");
					                                        	 if(depart_name!="dba"){
					                                        		 departIds.push(record.data);
					                                        	 }
					                                        	 
					                                         }
					                                     );
					                        	     
					                        	 		 Ext.Ajax.request({
					                        	 			url:'DelDepart',
				                                            params:{
				                                                 //在后台接收deleteUsers为要删除的对象
				                                              
				                                               deleteDeparts:Ext.encode(departIds)
				                                            }, 
				                                            method:'post',
				                                            success:function (response){
				                                            	var success = Ext.decode(response.responseText).success;
				                                            	delTreeReload(departIds);
				                                                 if(success){
				                                                   Ext.Array.each(sm,function(record){
				                                                       //删除store中的数据，页面效果
				                                                	   var depart_name = record.get("department_name");
							                                        	 if(depart_name!="dba"){
							                                        		 depart_stores.remove(record);
							                                        	 }
				                                                       
				                                                     }
				                                                       
				                                                   )
				                                                 }else{
				                                                   Ext.MessageBox.show(
				                                                       '提示',
				                                                       '删除失败'
				                                                   )
				                                                 }
				                                            },
				                                        	failure:function(response){
				                                        		Ext.MessageBox.show(
					                                                       '提示',
					                                                       '删除失败'
					                                                   )
				                                        	}
				                                            
					                        	 		 });				                                               
					                        	    	
					                        	 	
					                        	 }
					                         }  
					                     } 
					                   ]  
					              },//下方分页工具条  
					              {  
					                  xtype: 'pagingtoolbar',  
					                  store: depart_stores,  
					                  dock: 'bottom',  
					                  displayInfo: true  
					              }  
					            ]
		});
		
		Ext.define('User', {  
	         extend: 'Ext.data.Model',  
	         proxy: {  
	             type: 'ajax',  
	             reader: 'json'  
	         },  
	         fields: [
	             {name: 'uuid' , type: 'string'},
	             {name: 'user_name',type: 'string'}
	         ]  
	     });
		
		var user_stores = Ext.create('Ext.data.Store', {
			storeId : 'userStore',
			fields : [ 'uuid','user_name'],
			model : 'User',			
			pageSize: 12,
			proxy : {
				extraParams : {  
                    id : ''  ,
                    nodeType : ''
                }, 
				type : 'ajax',
				url : 'GetUserByDepartId',
				reader : {
					type : 'json',
					root:'users', //对应action中需要被现实的字段，一般为list  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
				}
			},
			autoLoad : false

		});
		
		Ext.define('CModel', {  
        extend: 'Ext.data.Model',  
        proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },  
        fields: [  
            {name: 'user_name',type: 'string'},
              {name: 'uuid',type: 'string'}
        ]  
     });
 	 
 		
 		var select_user_store = Ext.create('Ext.data.Store', {
 		storeId : 'select_user_store',
 		fields : [ 'uuid','user_name'],
 		model : 'User',
 		proxy : {
 			type : 'ajax',
 			url : 'GetAllFWUser',
 			reader : {
 					type : 'json',
					root:'users', //对应action中需要被现实的字段，一般为list  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
 			}
 		},
 		autoLoad : true

 	}); 
 		
 		

 		
 		
		var user_grid = Ext.create('Ext.grid.Panel', {
			id:'user_grid',
			selModel: {
		        injectCheckbox: 0,
		        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: true     //只能通过checkbox选择
		    },
		    selType: "checkboxmodel",		
			store : user_stores,
			columns : [
					{ xtype: "rownumberer" },
			        {
						text : '用户名称',
						dataIndex : 'user_name'
					},{
						dataIndex : 'uuid',
						hidden: true
					}],
						
					dockedItems: [  
					              {  
					                  xtype: 'toolbar',  
					                  dock: 'top',  
					                  items: [  
					                     {  
					                         xtype: 'button',  
					                         text:  '添加用户',  
					                         icon:'<%=basePath%>icons/user_add.png',  
					                         handler : function(){                    	 				                        	 
					                        	 
					                        							                        		
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [
					                     		       /*   {
					                     		        	 xtype:'combo',
					                     		        	id:'userName',
					                     		            fieldLabel: '用户',
					                     		            store:select_user_store,
					                     		            displayField:'user_name',
				                                            valueField:'uuid',
					                     		            name: 'fwUser.uuid' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,
					                     	                
					                     	                //editable: false,
					                     	                emptyText: '请选择用户名称',
					                     	                queryMode : 'local' 

 				                     	              

		                     	       
					                     		        
					                     		        
					                     		        
					                     		        
													        
					                     		        },  */
					                    
					                     		        {
					                     		        	collapsible : false,
															split : true,
																
															xtype: 'grid',
															itemId:'ruleGridId',
															id:'select_user_grid',
															store: select_user_store, 
															height: 240,
															autoScroll : true,
														selModel: {
													        injectCheckbox: 0,
													        mode: "SINGLE",     //"SINGLE"/"SIMPLE"/"MULTI"
													        checkOnly: true     //只能通过checkbox选择
													    },
													    selType: "checkboxmodel",
														columns : [{  xtype: "rownumberer",
													        	 text:'序号',
													        	 align:'left',
													        	 width:50
													        	 },/* {
																text : '用户ID',
																dataIndex : 'uuid',
																hidden:true,
																flex: 1
																
																
															}, */{
																text : '用户名',
																dataIndex : 'user_name',
																flex: 1
																
															}],
													    dockedItems: [{
												                xtype: 'toolbar',
												                dock: 'bottom',
												                items: [{
												                    xtype: 'textfield',
												                    emptyText: '按用户名查找',
												                    width: '100%',
												                   // fieldLabel: '过滤',
												                    labelAlign: 'left',
                                                                   // labelWidth: 50,
												                    enableKeyEvents: true,
												                    itemId: 'queryFieldId',
												                    triggers: {
												                        bar: {
												                            cls: 'x-form-clear-trigger',//有输入时，后边出现一个清空X按钮
												                            handler: function() {//点击清空按钮，textfield重置，即清空已输入的数据
												                                this.reset();
												                               // me.getController().filterRule();
												                      
												                               
												                               //end function
												                            }
												                        }
												                    },
												                    listeners: {
												
												                        change: {
												                            fn: function(field , newValue , oldValue , eOpts) {
																		            ruleGrid = Ext.getCmp('select_user_grid'),
																		            filterField = field,//查询的输入控件
																		            filters = ruleGrid.store.getFilters();//当前表格的filter
																		        if(filterField.value){//有输入值,添加filter
																		            this.nameFilter = filters.add({
																		                id:'nameField',
																		                property:'user_name',//通过name属性过滤
																		                value:filterField.value,//值为搜索框输入的值
																		                anyMatch:true,//模糊匹配
																		                caseSensitive:false
																		            });
																		        }else if(this.nameFilter){//未输入，则移除filter
																		            filters.remove(this.nameFilter);
																		            this.nameFilter = null;
																		        }
           
												                            }
												                        },
												                        
												                        buffer: 250
												                    }
												                }/* , '->', {
												                    xtype: 'button',
												                    text: '添 加',
												                    handler: function() {
												                        me.getController().updateRule('add', null);
												                    }
												                }, {
												                    xtype: 'button',
												                    text: '关 闭',
												                    handler: function() {
												                        this.up("window").close();
												                    }
												                } */]
												            }]
																									    
																									    
																									    
																									    
													    
													    
													    
													    
													    
					                     		        }, 
					                     		         {
					                     		        	xtype:'hidden',
					                     		            fieldLabel: '部门id',
					                     		            name: 'depart.uuid' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	
					                     	                value:user_stores.proxy.extraParams.id
					                     	               
					                     		        },{
					                     		        	xtype:'hidden',
					                     		        	id:'userName',
					                     		            fieldLabel: '用户id',
					                     		            name: 'fwUser.uuid' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false	
					                     	               
					                     	               
					                     		        }
					                     		    ]
					                     		});
					                        	 
 													ruleGrid = Ext.getCmp('select_user_grid'),					            
													 filters = ruleGrid.store.getFilters();//当前表格的filter
					                        	     filters.removeAll();
					                     		
					                     		var win = Ext.create('Ext.window.Window', {
					                     		    title: '添加用户',
					                     		    height: 330,
					                     		    width: 310,				   
					                     		    buttonAlign: 'center',		    
					                     		    modal: true,
					                     		    resizable:false,
					                     		   	draggable:false,
					                     		    layout: "fit",
					                     		    closable: true,				                     		    
					                     		    items: form,
					                     		    buttons: [{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'保 存',
					                     		    	handler: function(){
					                     		    		
					                     		    	
					                     		    		var select_user_grid = Ext.getCmp('select_user_grid');
														    var sm = select_user_grid.getSelection(); // 获得grid的SelectionModel	
														    var tmp_user_name;
														    var tmp_user_uuid;
						                        	 if(sm!=null&&sm.length == 1){					                        	     
						                        	     tmp_user_name = sm[0].get("user_name");
						                        	      tmp_user_uuid = sm[0].get("uuid");
						                        	   
						                        	    Ext.getCmp('userName').setValue(tmp_user_uuid);
						                        	    	 if (form.getForm().isValid()) {
						                     		    		
					                     		    			form.getForm().doAction('submit',{  
						                     	              		 url:'BindUser',                		
						                     	              		 method:'post',
						                     	              		 waitMsg:'正在保存数据，请稍候。',
																	 waitTitle:'提示',
						                     	               	success:function(form,action){ 
						                     	               	
						                     	               		Ext.Msg.alert("信息",action.result.message); 
						                     	               		addTreeReload(action.result.tm);
						                     	               		win.close();
						                     	               		user_stores.reload();
						                     	               		
						                     	               		
						                     	               		
						                     	               	},  
						                     	               	failure:function(form,action){  
						                     	                   	Ext.Msg.alert("信息",action.result.message);  
						                     	               	}  
						                     	           		});
					                     		    		} 
					                        	 } else {
					                        		// alert('not select');
					                        	 }
								  
								  
					                     		    		
					                     		    	
					                     		    		
					                     		    	}
					                     		    },{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'取 消',
					                     		    	handler: function(){
					                     		    		win.close();
					                     		    	}
					                     		    }]
					                     		});
					                     		win.show();
					                     		
					                         }  
					                     } ,{  
					                         xtype: 'button',  
					                         text:  '删除用户',  
					                         icon:'<%=basePath%>icons/user_delete.png',  
					                         handler : function(){ 
					                        	 var sm = user_grid.getSelection(); // 获得grid的SelectionModel
					                        	 if(sm!=null&&sm.length>0){					                        	     
					                        	     var userIds=[]; 
					                        	    
					                                     Ext.Array.each(
					                                         sm,
					                                         function(record){
					                                        	 var uuid = record.get("uuid");
					                                        	 var user_name = record.get("user_name");
					                                        	 if(user_name!="dba"){
					                                        		 userIds.push(record.data);
					                                        	 }
					                                        	 
					                                         }
					                                     );
					                        	     
					                        	 		 Ext.Ajax.request({
					                        	 			url:'UnBindUser',
				                                            params:{
				                                                 //在后台接收deleteUsers为要删除的对象
				                                             
				                                               departId:user_stores.proxy.extraParams.id,
				                                               deleteUsers:Ext.encode(userIds)
				                                            }, 
				                                            method:'post',
				                                            success:function (response){
				                                            	delTreeReload(userIds);
				                                            	var success = Ext.decode(response.responseText).success;
				                                            		//user_stores.reload();
						                     	               		//store.reload();
				                                                 if(success){
				                                                   Ext.Array.each(sm,function(record){
				                                                       //删除store中的数据，页面效果
				                                                	   var user_name = record.get("user_name");
							                                        	 if(user_name!="dba"){
							                                        		 user_stores.remove(record);
							                                        	 }
				                                                       
				                                                     }
				                                                       
				                                                   )
				                                                 }else{
				                                                   Ext.MessageBox.show(
				                                                       '提示',
				                                                       '删除失败'
				                                                   )
				                                                 }
				                                            },
				                                        	failure:function(response){
				                                        		Ext.MessageBox.show(
					                                                       '提示',
					                                                       '删除失败'
					                                                   )
				                                        	}
				                                            
					                        	 		 });				                                               
					                        	    	
					                        	 	
					                        	 }
					                         }  
					                     } 
					                   ]  
					              },//下方分页工具条  
					              {  
					            	  
					                  xtype: 'pagingtoolbar',  
					                  store: user_stores,  
					                  dock: 'bottom',  
					                  displayInfo: true  
					              }  
					            ]
		});
		
		var center_panel = Ext.create('Ext.panel.Panel',{
			layout:'fit',
			items:[site_grid,depart_grid,user_grid]
		});
		//center_panel.add(site_grid);
		
		var mainvp = Ext.create('Ext.container.Viewport', {
			layout : 'border',			
			items : [{
						defaultType:'container',
						layout:'fit',
						region : 'west',
						collapsible : false,
						split : true,					
						width : 200 ,
						items:[west_panel]
					},{
						region : 'center',						
						xtype : 'container',
						layout: 'fit',
						items : [center_panel]
					}]
		});
	});
	
</script>	
	

</head>
<body>

</body>
</html>