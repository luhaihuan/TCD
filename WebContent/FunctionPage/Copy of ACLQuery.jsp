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
								root:'users', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
								root:'sites', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
								root:'departs', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
								root:'users', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
 		        {"dvalue":"��","tvalue":true},
 		        {"dvalue":"��","tvalue":false}
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
				root:'models', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
	  	                //����  	  	               
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
    					fieldLabel: 'վ��',
						store: sites_store,
						queryMode: 'remote',
                     	displayField: 'site_name',
                     	valueField: 'site_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '��ѡ��վ��',
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
    					fieldLabel: '����',
						store: departs_store,
						queryMode: 'remote',
                     	displayField: 'department_name',
                     	valueField: 'department_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '��ѡ����',
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
    					fieldLabel: '�û�',
						store: users_store,
						queryMode: 'remote',
                     	displayField: 'user_name',
                     	valueField: 'user_name',                     
                 		labelAlign: 'right',						                     		            	 
                 	    labelWidth: 40,						                     	               
                 	    blankText: '��ѡ���û�'  ,
                 	    id:"UserCombo"
					},{
						xtype: 'button',
						text: '�鿴Ȩ�޹���',
						handler:function(){
										var site = Ext.getCmp("SiteCombo").getValue();
								var group = Ext.getCmp("GroupCombo").getValue();
								var user = Ext.getCmp("UserCombo").getValue();
	  	        	//alert(ftcombo.getValue());
	  	            var params = {  
	  	                //����  
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
						text : 'վ��',
						dataIndex : 'siteStr',
						hideable: false
					},{
						text : '����',
						dataIndex : 'groupStr',
						hideable: false
					},{
						text : '�û�����',
						dataIndex : 'username',
						hideable: false
					},{
						text : '�ļ�����',
						dataIndex : 'typeStr',
						hideable: false
					},{
						text : 'Ԥ��Word',
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
						text : '��ӡWord',
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
						text : '����Word',
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
						text : 'Ԥ��Excel',
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
						text : '��ӡExcel',
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
						text : '����Excel',
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
						text : 'Ԥ��PDF',
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
						text : '��ӡPDF',
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
						text : '����PDF',
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