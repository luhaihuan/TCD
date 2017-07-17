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
			             {name: 'user_name',type: 'string'}
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
				
				var users_store = Ext.create('Ext.data.Store', {
			 		storeId : 'usersStore',
			 		fields : [ 'uuid','user_name'],
			 		model : 'userModel',
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
			
			 	});
				
				var departStore =	 Ext.create('Ext.data.Store', {
					storeId : 'departStore',
					fields : [ 'uuid','department_name'],
					model : 'departModel',
					proxy : {
						type : 'ajax',
						url : 'GetAllGroup',
						reader : {
							type : 'json',
							root:'groups', //对应action中需要被现实的字段，一般为list  
				            success:'flag',  
				            total: 'total' ,
				            start: 'start',
				            limit: 'limit'
						}
					},
					autoLoad : true
		
				});
				
				Ext.define('Log', {  
	        		extend: 'Ext.data.Model',  
	        		proxy: {  
	            		type: 'ajax',  
	            		reader: 'json'  
	        		},  
	        		fields: [  
	            		{name: 'user_name',type: 'string'},
	            		{name: 'action_type',type: 'string'},
	            		{name: 'action_date',type: 'string'},
	            		{name: 'item_id',type: 'string'},
	            		{name: 'item_name',type: 'string'},
	            		{name: 'item_rev',type: 'string'},
	            		{name: 'user_group',type: 'string'}
	            		
	        		]  
	    		});
				
				var log_store = Ext.create('Ext.data.Store', {
	 				storeId : 'log_store',
	 				fields : [ 'user_name','action_type','action_date','item_id','item_name','item_rev','user_group' ],
	 				model : 'Log',
	 				pageSize: 10,
	 				proxy : {
	 					type : 'ajax',
	 					url : 'GetAllLogs',
	 				reader : {
	 					
						root:'logs', //对应action中需要被现实的字段，一般为list  
			            
			            total: 'total' 
	 					}
	 				},
	 				autoLoad : true
				});				
				
				
				var mainviewport = Ext.create('Ext.container.Viewport', {
					layout : 'border',			
					items : [{
						region : 'north',						
						border : true,	
						xtype: 'panel',
						layout: {
        					type: 'hbox',       // Arrange child items vertically
        					align: 'stretch',    // Each takes up full width
        					padding: 10
    					},
						items : [{
							xtype: 'numberfield',
							fieldLabel: '单日下载ITEM最大数量设置:',
							labelWidth : 200,
							labelAlign : 'right',							
							id:'downloadPerDay'
						},{
							xtype: 'button',
							text: '确定',
							handler:function(){
								
								var day = Ext.getCmp("downloadPerDay").getValue();
								
								Ext.Ajax.request({
				   					url:'ModLogSetting',                                          
			       					method:'POST',
			       					params:{
		                                      day:day
		                            },
			       					success:function (response){
			          					var success = Ext.decode(response.responseText).success;
			          					if(success){
			          						Ext.Msg.alert("信息","修改设置成功!");
			          					}else{
			          						Ext.Msg.alert("信息","修改设置失败!");
			          					}
			          					
			       					},
			       					failure:function(response){
			       						Ext.Msg.alert("信息","修改设置失败!");
			       					}
			                                            
				   					});
							}
						}]
						},{
						region : 'center',						
						border : true,	
						xtype: 'grid',
						store:log_store,
						columns :[{
							text : '用户名',
							dataIndex : 'user_name'
						},{
							text : '操作类型',
							dataIndex : 'action_type'
						},{
							text : '操作时间',
							dataIndex : 'action_date'
						},{
							text : 'ITEM号',
							dataIndex : 'item_id'
						},{
							text : 'ITEM名',
							dataIndex : 'item_name'
						},{
							text : 'ITEM版本',
							dataIndex : 'item_rev'
						},{
							text : '所属部门',
							dataIndex : 'user_group'
						}],
						tbar:[{ 
							xtype:'button',							
				            text : '输出审计报表' ,
				            handler:function(){
				            
				            	var tabpanel = Ext.create('Ext.tab.Panel', {
    												width: 300,
    												height: 173,
    								
    												layout:'fit',
    												items: [{
        												title: '按部门',
        												items: {
        													xtype:'form',
        													bodyPadding: 10,    								
    														url: 'ExportLogByGroup',
    														buttonAlign: 'center',	
															height:173,//173
    														
    														items: [{
    															xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期从',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'from_date',
        														format : 'Y年m月d日'
        														
    														},{
        														xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期到',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'to_date',
        														format : 'Y年m月d日'
    														},{
    															xtype: 'combo',
    															fieldLabel: '部门',
							                        		    store: departStore,
							                        		    queryMode: 'remote',
							                        		    displayField: 'department_name',
							                        		    valueField: 'department_name',
							                        		    name: 'bgroup' ,
						                     		            labelAlign: 'right',						                     		            	 
						                     	                labelWidth: 60,						                     	               
						                     	                blankText: '请选择部门'
    														}],

    														buttons: [{
        														text: '确定',
        														handler: function() {
        															Ext.Msg.wait('提示','正在处理数据，请稍候');
        															
            														var form = this.up('form').getForm();
						            								if (form.isValid()) {
						               							 	form.submit({
						                    							success: function(form, action) {
						                    								
						                    							Ext.Msg.hide();
						                    							var filepath = action.result.filename;
						                       							//Ext.Msg.alert('Success', 'Success');
						                       							window.location.href = "downloadXlsx?filename="+encodeURI(encodeURI(filepath))+"&inputPath="+encodeURI(encodeURI(filepath))+"&dsname="+encodeURI(encodeURI(filepath));   
																		
						                    						},
						                    						failure: function(form, action) {
						                    							Ext.Msg.hide();
						                        						//Ext.Msg.alert('Failed', action.result.msg);
						                    						}
						                							});
						            								}
						        								}
						    								},{
        														text: '重置',
        														handler: function() {
            														this.up('form').getForm().reset();
        														}
    														}]
        												}
    												},{
        												title: '按用户',
        												items: {
        													xtype:'form',
        													bodyPadding: 10,   
        													buttonAlign: 'center',	
    														url: 'ExportLogByUser',
															height:173,
    														
    														items: [{
    															xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期从',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'from_date',
        														format : 'Y年m月d日'
        														
    														},{
        														xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期到',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'to_date',
        														format : 'Y年m月d日'
    														},{
    															xtype: 'combo',
    															fieldLabel: '用户名',
							                        		    store: users_store,
							                        		    queryMode: 'remote',
							                        		    displayField: 'user_name',
							                        		    valueField: 'user_name',
							                        		    name: 'username' ,
						                     		            labelAlign: 'right',						                     		            	 
						                     	                labelWidth: 60,						                     	               
						                     	                blankText: '请选择用户'
    														}],

    														buttons: [ {
        														text: '确定',
        														handler: function() {
        															Ext.Msg.wait('提示','正在处理数据，请稍候');
            														var form = this.up('form').getForm();
						            								if (form.isValid()) {
						               							 	form.submit({
						                    							success: function(form, action) {
						                       							Ext.Msg.hide();
						                       							var filepath = action.result.filename;
						                       							//Ext.Msg.alert('Success', 'Success');
						                       							window.location.href = "downloadXlsx?filename="+encodeURI(encodeURI(filepath))+"&inputPath="+encodeURI(encodeURI(filepath))+"&dsname="+filepath;   
																		
						                    						},
						                    						failure: function(form, action) {
						                        						Ext.Msg.hide();
						                    						}
						                							});
						            								}
						        								}
						    								},{
        														text: '重置',
        														handler: function() {
            														this.up('form').getForm().reset();
        														}
    														}]
        												}
    												},{
        												title: '按ITEM号',
        												items: {
        													xtype:'form',
        													bodyPadding: 10, 
        													buttonAlign: 'center',	
    														url: 'ExportLogByItem',
															height:173,
    														
    														items: [{
    															xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期从',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'from_date',
        														format : 'Y年m月d日'
        														
    														},{
        														xtype: 'datefield',
        														anchor: '100%',
        														fieldLabel: '日期到',
        														labelAlign: 'right',
	                											labelWidth: 60,
        														name: 'to_date',
        														format : 'Y年m月d日'
    														},{
    															xtype: 'textfield',
    															fieldLabel: 'ITEM号',							                        		    
							                        		    name: 'itemid' ,
						                     		            labelAlign: 'right',						                     		            	 
						                     	                labelWidth: 60
    														}],

    														buttons: [ {
        														text: '确定',
        														handler: function() {
        															Ext.Msg.wait('提示','正在处理数据，请稍候');
            														var form = this.up('form').getForm();
						            								if (form.isValid()) {
						               							 	form.submit({
						                    							success: function(form, action) {
						                       							Ext.Msg.hide();
						                       							var filepath = action.result.filename;
						                       							//Ext.Msg.alert('Success', 'Success');
						                       							window.location.href = "downloadXlsx?filename="+encodeURI(encodeURI(filepath))+"&inputPath="+encodeURI(encodeURI(filepath))+"&dsname="+encodeURI(encodeURI(filepath));   
																		
						                    						},
						                    						failure: function(form, action) {
						                        						Ext.Msg.hide();
						                    						}
						                							});
						            								}
						        								}
						    								},{
        														text: '重置',
        														handler: function() {
            														this.up('form').getForm().reset();
        														}
    														}]
        												}
    												}]
								});    	
				            	
				            	
				            	var win = Ext.create('Ext.window.Window', {
				            		title: '输出审计报表',
				            		height: 250,
									width: 300,
									modal: false,
									resizable : false,
									layout:'fit',
									closable: true,													    
									items: tabpanel
				            		
				            	});
				            	win.show();
				            }
				        }],
						bbar: { xtype: "pagingtoolbar", store:log_store,displayInfo: true }
					}]
				});
				
			Ext.Ajax.request({
				   url:'GetLogSetting',                                          
			       method:'POST',
			       success:function (response){
			          var day = Ext.decode(response.responseText).day;
			          
			          Ext.getCmp("downloadPerDay").setValue( day ) ;
			          
			       },
			       failure:function(response){ }
			                                            
				   });
				
			});
		</script>
</head>
<body>

</body>
</html>