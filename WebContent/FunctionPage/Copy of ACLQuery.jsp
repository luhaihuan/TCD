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
	
	Ext.define('CModel', {  
			         extend: 'Ext.data.Model',  
			         proxy: {  
			             type: 'ajax',  
			             reader: 'json'  
			         },  
			         fields: [  
			             {name: 'name',type: 'string'}
			         ]  
			     });
				
				/* var users_store = Ext.create('Ext.data.Store', {
			 		storeId : 'usersStore',
			 		fields : [ 'name'],
			 		model : 'CModel',
			 		proxy : {
			 			type : 'ajax',
			 			url : 'GetActivedUser',
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
			
			 	}); */
				Ext.define('siteModel', {  
			        extend: 'Ext.data.Model',  
			        proxy: {  
			            type: 'ajax',  
			            reader: 'json'  
			        },  
			        fields: [  
			            {name: 'uuid',type: 'string'},
			            {name: 'site_name',type: 'string'}
			        ]  
			    });
				
				Ext.define('departModel', {  
			        extend: 'Ext.data.Model',  
			        proxy: {  
			            type: 'ajax',  
			            reader: 'json'  
			        },  
			        fields: [  
			            {name: 'uuid',type: 'string'},
			            {name: 'department_name',type: 'string'}
			        ]  
			    });
				
					Ext.define('userModel', {  
			        extend: 'Ext.data.Model',  
			        proxy: {  
			            type: 'ajax',  
			            reader: 'json'  
			        },  
			        fields: [  
			            {name: 'uuid',type: 'string'},
			            {name: 'user_name',type: 'string'}
			        ]  
			    });
	
			
				
				var sites_store = Ext.create('Ext.data.Store', {
			 		storeId : 'siteStore',
			 		fields : [ 'uuid','site_name'],
			 		model : 'siteModel',
			 		proxy : {
			 			type : 'ajax',
			 			url : 'GetMyAllSite',
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
				
				var departs_store = Ext.create('Ext.data.Store', {
			 		storeId : 'departStore',
			 		fields : [ 'uuid','department_name'],
			 		model : 'departModel',
			 		proxy : {
			 			type : 'ajax',
			 			url : 'GetMyDepartBySiteId',
			 			reader : {
			 					type : 'json',
								root:'departs', //对应action中需要被现实的字段，一般为list  
					            success:'flag',  
					            total: 'total' ,
					            start: 'start',
					            limit: 'limit'
			 			},
			 			 extraParams : {  
			                    siteId : ''  
			                    
			                } 
			 		},
			 		autoLoad : false
			
			 	});
				
					var users_store = Ext.create('Ext.data.Store', {
			 		storeId : 'userStore',
			 		fields : [ 'uuid','user_name'],
			 		model : 'userModel',
			 		proxy : {
			 			type : 'ajax',
			 			url : 'GetMyUserByDepartId',
			 			reader : {
			 					type : 'json',
								root:'users', //对应action中需要被现实的字段，一般为list  
					            success:'flag',  
					            total: 'total' ,
					            start: 'start',
					            limit: 'limit'
			 			},
					    extraParams : {  
			                    departId : ''  
			                    
			             } 
			 		},
			 		autoLoad : false
			
			 	});

	
	
	Ext.define('ACLTree', {
		extend : 'Ext.data.Model',
		proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },
		fields : [ 
		          	{name : 'siteStr', type : 'string' },
		          	{name : 'groupStr', type : 'string' },
					{name : 'username', type : 'string' },
					{name : 'typeStr', type : 'string' },
					{name: 'readWord',      type: 'boolean'},
					{name: 'printWord',      type: 'boolean'},
					{name: 'downloadWord',      type: 'boolean'},
					{name: 'readExcel',      type: 'boolean'},
					{name: 'printExcel',      type: 'boolean'},
					{name: 'downloadExcel',      type: 'boolean'},
					{name: 'readPDF',      type: 'boolean'},
					{name: 'printPDF',      type: 'boolean'},
					{name: 'downloadPDF',      type: 'boolean'}]
	});
	
	
	
	Ext.define('ynModel', {  
        extend: 'Ext.data.Model',  
        proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },  
        fields: [  
            {name: 'dvalue',type: 'string'},
            {name: 'tvalue',type: 'boolean'}
        ]  
    });
	
	var yn = Ext.create('Ext.data.Store', {
 		    fields: ['dvalue','tvalue'],
 		    data : [
 		        {"dvalue":"是","tvalue":true},
 		        {"dvalue":"否","tvalue":false}
 		    ],
 		    autoLoad : true
 	});
	
 	var storts=	 Ext.create('Ext.data.Store', {
 		storeId : 'aclStore',
 		fields : [ 'siteStr','groupStr','username','typeStr','readWord','printWord','downloadWord','readExcel','printExcel','downloadExcel','readPDF','printPDF','downloadPDF' ],
 		model : 'ACLTree',
 		pageSize: 10,
 		proxy : {
 			type : 'ajax',
 			url : 'GetACLbyAttrs', 			
 			reader : {
 				type : 'json',
				root:'models', //对应action中需要被现实的字段，一般为list  
	            success:'flag',  
	            total: 'total' ,
	            start: 'start',
	            limit: 'limit'
 			}
 		},
 		autoLoad : false,
 		 listeners: {  
	  	       
	  	        'load': function( store, records, successful ){
	  	        									
					
	  	            var params = {  
	  	                //参数  	  	               
	  	           		flag:false
	  	         		
	  	            };  
	  	            Ext.apply(store.proxy.extraParams, params);   
	  	        }  
	  	    }

 	});
	

	
	Ext.create('Ext.container.Viewport', {
		layout : 'border',			
		items : [ {
					region : 'north',
					collapsible : false,
					split : false,				
					// could use a TreePanel or AccordionLayout for navigational
					border : true,	
					xtype: 'panel',
					layout: {
        					type: 'hbox',       // Arrange child items vertically
        					align: 'stretch',    // Each takes up full width
        					padding: 5
    				},
					items : [{
						xtype: 'combo',
    					fieldLabel: '站点',
						store: sites_store,
						queryMode: 'remote',
                     	displayField: 'site_name',
                     	valueField: 'site_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '请选择站点',
                 	    id:"SiteCombo",
                 	     listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  				            						            						            			 
				            			  departs_store.proxy.extraParams.siteId = record.data.uuid;
				            			  departs_store.reload();				            			
				            			   				            			
				            	   		}
			     				},
					        	 scope: this
				            	   
				           	}
						
					},{
						xtype: 'combo',
    					fieldLabel: '部门',
						store: departs_store,
						queryMode: 'remote',
                     	displayField: 'department_name',
                     	valueField: 'department_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '请选择部门',
                 	    id:"GroupCombo",
                 	     listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  				            						            						            			 
				            			  users_store.proxy.extraParams.departId = record.data.uuid;
				            			  users_store.reload();				            			
				            			   				            			
				            	   		}
			     				},
					        	 scope: this
				            	   
				           	}
					},{
						xtype: 'combo',
    					fieldLabel: '用户',
						store: users_store,
						queryMode: 'remote',
                     	displayField: 'user_name',
                     	valueField: 'user_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '请选择用户'  ,
                 	    id:"UserCombo"
					},{
						xtype: 'button',
						text: '查看权限规则',
						handler:function(){
										var site = Ext.getCmp("SiteCombo").getValue();
								var group = Ext.getCmp("GroupCombo").getValue();
								var user = Ext.getCmp("UserCombo").getValue();
	  	        	//alert(ftcombo.getValue());
	  	            var params = {  
	  	                //参数  
	  	               site:encodeURIComponent(site),
	  	             	group:encodeURIComponent(group),
	  	           		user:encodeURIComponent(user),
	  	           		flag:true
	  	         		
	  	            };  
	  	            Ext.apply(storts.proxy.extraParams, params); 						
								storts.reload();
							}
					}]
				}, {
					region : 'center',						
					collapsible : false,
					split : false,
					xtype: 'grid',
					id:'user_grid',
					store: storts, 
					
					columns : [{
						text : '站点',
						dataIndex : 'siteStr',
						hideable: false
					},{
						text : '部门',
						dataIndex : 'groupStr',
						hideable: false
					},{
						text : '用户名称',
						dataIndex : 'username',
						hideable: false
					},{
						text : '文件类型',
						dataIndex : 'typeStr',
						hideable: false
					},{
						text : '预览Word',
						dataIndex : 'readWord',
						hideable: false,
						sortable: false,
						width: 75,
					
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '打印Word',
						dataIndex : 'printWord',
						hideable: false,
							sortable: false,
							width: 75,
							
				            renderer: function(value,metadata,record){  
				                var index = yn.find('tvalue',value);  
				                if(index!=-1){  
				                    return yn.getAt(index).data.dvalue;  
				                }  
				                return value;  
				            }
					},{
						text : '下载Word',
						dataIndex : 'downloadWord',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '预览Excel',
						dataIndex : 'readExcel',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '打印Excel',
						dataIndex : 'printExcel',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '下载Excel',
						dataIndex : 'downloadExcel',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '预览PDF',
						dataIndex : 'readPDF',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '打印PDF',
						dataIndex : 'printPDF',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '下载PDF',
						dataIndex : 'downloadPDF',
						hideable: false,
						sortable: false,
						width: 75,
						
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					}],
	        	bbar: { xtype: "pagingtoolbar",store: storts,   displayInfo: true }
				}]
	});
	
	
});
	
</script>
</head>
<body>

</body>
</html>