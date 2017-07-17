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
		
		Ext.QuickTips.init();
		
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
		
		Ext.define('User', {  
	         extend: 'Ext.data.Model',  
	         proxy: {  
	             type: 'ajax',  
	             reader: 'json'  
	         },  
	         fields: [  
	             {name: 'uuid',type: 'string'},  
	             {name: 'user_name',type: 'string'},  
	             {name: 'user_no', type: 'string'},  
	             {name: 'dba',      type: 'boolean'},  
	             {name: 'OA',    type: 'boolean'},             
	             {name: 'actived', type: 'boolean'} 
	           
	         ]  
	     });
		
		var stores = Ext.create('Ext.data.Store', {
			storeId : 'usersStore',
			fields : [ 'uuid','user_name', 'user_no','dba','OA','actived'],
			model : 'User',			
			pageSize: 12,
			proxy : {
				type : 'ajax',
				url : 'GetAllUser',
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
		
		 var yn = Ext.create('Ext.data.Store', {
 		    fields: ['dvalue','tvalue'],
 		    data : [
 		        {"dvalue":"是","tvalue":true},
 		        {"dvalue":"否","tvalue":false}
 		    ],
 		    autoLoad : true
 		});	
		
		var grid = Ext.create('Ext.grid.Panel', {
			selModel: {
		        injectCheckbox: 0,
		        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: true     //只能通过checkbox选择
		    },
		    selType: "checkboxmodel",		
			store : stores,
			columns : [
					{ xtype: "rownumberer" },
					 {
						text : 'uuid',
						dataIndex : 'uuid',
						hidden:true
					},
			           {
						text : '用户名称',
						dataIndex : 'user_name'
					}, {
						text : '工号',
						dataIndex : 'user_no'
					},{
						text : '是否管理员',
						dataIndex : 'dba',						
						editor: new Ext.form.field.ComboBox({
			                typeAhead:true,
			                readOnly:true,
			                queryMode:'remote',			                
			                triggerAction:'All',
			                valueField:'tvalue',
			                displayField:'dvalue',
			                store:yn,
			                lazyRender:true
			            }) ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  			                	
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
						
					},{
						text : '是否OA用户',
						dataIndex : 'OA',
						editor: new Ext.form.field.ComboBox({
			                typeAhead:true,
			                readOnly:true,
			                queryMode:'remote',			                
			                triggerAction:'All',
			                valueField:'tvalue',
			                displayField:'dvalue',
			                store:yn,
			                lazyRender:true
			            }) ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '是否启用',
						dataIndex : 'actived',
						editor: new Ext.form.field.ComboBox({
			                typeAhead:true,
			                readOnly:true,
			                queryMode:'remote',			                
			                triggerAction:'All',
			                valueField:'tvalue',
			                displayField:'dvalue',
			                store:yn,
			                lazyRender:true
			            }) ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					}, {
						xtype:'actioncolumn',//这里就是放置按钮的地方
						width:50,
						text:'操作',
						items: [{icon: '<%=basePath%>icons/user_edit.png', // Use a URL in the icon config
								tooltip: '修改',
								handler:function(grid,rindex,cindex){
									var record = grid.getStore().getAt(rindex);
								     
								    var id=record.get("user_name");
								    var pwd = record.get("user_pwd");
								    var user_no = record.get("user_no");
								    var b1 = record.get("dba");
								    var b2 = record.get("OA");
								    var b3 = record.get("actived"); 						    
								    
								    //var b3 = record.get("canDownload");
								   // yn1.reload();
								   var combo5 = Ext.create('Ext.form.field.ComboBox', {
	                        		    fieldLabel: '启用',
	                        		    store: yn,					                        		    
	                        		    //value: "否",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.actived' ,
                     		            labelAlign: 'right',
                     	                labelWidth: 70,
                     	               allowBlank: false,	 
                     	               editable:false,
                     	              blankText: '请选择',                     	              
              			                selectOnFocus:false
	                        		});
								   
								    var combo1 = Ext.create('Ext.form.field.ComboBox', {
	                        		    fieldLabel: '管理员',
	                        		    store: yn,					                        		    
	                        		    //value: "否",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.dba' ,
                     		            labelAlign: 'right',
                     	                labelWidth: 70,
                     	               allowBlank: false,	 
                     	               editable:false,
                     	              blankText: '请选择',                     	             
              			                selectOnFocus:false
	                        		});
								   // yn2.reload();
	                        		var combo2 = Ext.create('Ext.form.field.ComboBox', {
	                        		    fieldLabel: 'OA用户',
	                        		    store: yn,					                        		    
	                        		    //value: "是",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.OA' ,
                     		            labelAlign: 'right',
                     		           allowBlank: false,	 
                     	                labelWidth: 70,
                     	               editable:false,
                     	              blankText: '请选择',                     	             
              			                selectOnFocus:false
	                        		});	                        		
	                        		                        		
	                        		
	                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
	                     		    height: 330,
	                     		    width: 310,
	                     		    bodyPadding: 10,
	                     		    defaultType: 'textfield',
	                     		    items: [
	                     		        {
	                     		            fieldLabel: '用户',
	                     		            name: 'user.user_name' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70,
	                     	                allowBlank: false,	                
	                     	                blankText: '请输入用户名'
	                     		        },combo2,
	                     		        {
	                     		            fieldLabel: '密码',
	                     		            name: 'user.user_pwd' , 
	                     		            inputType: 'password',
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70
	                     	                //disabled:true
	                     		        },combo1,{
	                     		            fieldLabel: '工号',
	                     		            name: 'user.user_no' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70
	                     	                //disabled:true
	                     		        },combo5
	                     		    ]
	                     		});
	                     		
	                     		var win = Ext.create('Ext.window.Window', {
	                     		    title: '修改用户',
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
		                     	              		 url:'ModUser',                		
		                     	              		 method:'post',  
		                     	              		params:{
		                                                 
		                                               username:id
		                                            },
		                     	               	success:function(form,action){ 
		                     	               	
		                     	               		Ext.Msg.alert("信息","修改用户成功!");
		                     	               		win.close();
		                     	               		stores.reload();
		                     	               		
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
	                     		var un = form.getForm().findField("user.user_name");	                     		
	                     		un.setValue(id);
	                     		
	                     		combo1.select(b1);
	                     		combo2.select(b2);		                     		
	                     		
	                     		combo5.select(b3);
	                     		
	                     		var ue = form.getForm().findField("user.user_no");
	                     		ue.setValue(user_no);	                     		
	                     		
	                     		var up = form.getForm().findField("user.user_pwd");
	                     		up.setValue(pwd);
	                     		
	                     		
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
					                         text:  '添加用户',  
					                         icon:'<%=basePath%>icons/user_add.png',  
					                         handler : function(){                    	 				                        	 
					                        	 //yn3.reload();
					                        		// Create the combo box, attached to the states data store
					                        		var combo5 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: '启用',
					                        		    store: yn,					                        		    
					                        		    
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.actived' ,
				                     		            labelAlign: 'right',
				                     	                labelWidth: 70,
				                     	               allowBlank: false,				                     	               
				                     	              blankText: '请选择',                     	              
				              			                selectOnFocus:false
					                        		});
					                        		
					                        		var combo1 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: '管理员',
					                        		    store: yn,					                        		    
					                        		    //value: "否",
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.dba' ,
				                     		            labelAlign: 'right',
				                     	                labelWidth: 70,
				                     	               allowBlank: false,				                     	              
				                     	              blankText: '请选择',				                     	             
				              			                selectOnFocus:false
					                        		});
					                        		//yn4.reload();
					                        		var combo2 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: 'OA用户',
					                        		    store: yn,					                        		    
					                        		    //value: "是",
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.OA' ,
				                     		            labelAlign: 'right',
				                     		           allowBlank: false,	 
				                     	                labelWidth: 70,				                     	               
				                     	              blankText: '请选择',				                     	              
				              			                selectOnFocus:false
					                        		});
					                  					                        		
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [
					                     		        {
					                     		            fieldLabel: '用户',
					                     		            name: 'user.user_name' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	                
					                     	                blankText: '请输入用户名'
					                     		        },combo2,
					                     		        {
					                     		            fieldLabel: '密码',
					                     		            name: 'user.user_pwd' , 
					                     		            inputType: 'password',
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70
					                     		        },combo1,{
					                     		            fieldLabel: '工号',
					                     		            name: 'user.user_no' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70
					                     		        },combo5
					                     		    ]
					                     		});
					                     		
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
					                     		    		if (form.getForm().isValid()) {
					                     		    			form.getForm().doAction('submit',{  
						                     	              		 url:'AddUser',                		
						                     	              		 method:'post',  
						                     	               	success:function(form,action){ 
						                     	               	
						                     	               		Ext.Msg.alert("信息","添加用户成功!");
						                     	               		win.close();
						                     	               		stores.reload();
						                     	               		
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
					                         text:  '删除用户',  
					                         icon:'<%=basePath%>icons/user_delete.png',  
					                         handler : function(){ 
					                        	 var sm=grid.getSelection(); // 获得grid的SelectionModel
					                        	 if(sm!=null&&sm.length>0){					                        	     
					                        	     var userids=[]; 
					                        	    
					                                     Ext.Array.each(
					                                         sm,
					                                         function(record){
					                                        	 var tid = record.get("user_name");
					                                        	 if(tid!="infodba"){
					                                        		 userids.push(record.data);
					                                        	 }
					                                        	 
					                                         }
					                                     );
					                        	     
					                        	 		 Ext.Ajax.request({
					                        	 			url:'DelUsers',
				                                            params:{
				                                                 //在后台接收deleteUsers为要删除的对象
				                                               deleteUsers:Ext.encode(userids)
				                                            }, 
				                                            method:'POST',
				                                            success:function (response){
				                                            	var success = Ext.decode(response.responseText).success;
				                                                 if(success){
				                                                   Ext.Array.each(sm,function(record){
				                                                       //删除store中的数据，页面效果
				                                                	   var tid = record.get("user_name");
							                                        	 if(tid!="infodba"){
							                                        		 stores.remove(record);
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
					                  store: stores,  
					                  dock: 'bottom',  
					                  displayInfo: true  
					              }  
					            ]
		});
		
		var mainvp = Ext.create('Ext.container.Viewport', {
			layout : 'fit',			
			items : [grid]
		});
	});
	
</script>	
	

</head>
<body>

</body>
</html>