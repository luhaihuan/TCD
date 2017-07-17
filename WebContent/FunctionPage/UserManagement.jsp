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
					root:'users', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
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
 		        {"dvalue":"��","tvalue":true},
 		        {"dvalue":"��","tvalue":false}
 		    ],
 		    autoLoad : true
 		});	
		
		var grid = Ext.create('Ext.grid.Panel', {
			selModel: {
		        injectCheckbox: 0,
		        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: true     //ֻ��ͨ��checkboxѡ��
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
						text : '�û�����',
						dataIndex : 'user_name'
					}, {
						text : '����',
						dataIndex : 'user_no'
					},{
						text : '�Ƿ����Ա',
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
						text : '�Ƿ�OA�û�',
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
						text : '�Ƿ�����',
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
						xtype:'actioncolumn',//������Ƿ��ð�ť�ĵط�
						width:50,
						text:'����',
						items: [{icon: '<%=basePath%>icons/user_edit.png', // Use a URL in the icon config
								tooltip: '�޸�',
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
	                        		    fieldLabel: '����',
	                        		    store: yn,					                        		    
	                        		    //value: "��",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.actived' ,
                     		            labelAlign: 'right',
                     	                labelWidth: 70,
                     	               allowBlank: false,	 
                     	               editable:false,
                     	              blankText: '��ѡ��',                     	              
              			                selectOnFocus:false
	                        		});
								   
								    var combo1 = Ext.create('Ext.form.field.ComboBox', {
	                        		    fieldLabel: '����Ա',
	                        		    store: yn,					                        		    
	                        		    //value: "��",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.dba' ,
                     		            labelAlign: 'right',
                     	                labelWidth: 70,
                     	               allowBlank: false,	 
                     	               editable:false,
                     	              blankText: '��ѡ��',                     	             
              			                selectOnFocus:false
	                        		});
								   // yn2.reload();
	                        		var combo2 = Ext.create('Ext.form.field.ComboBox', {
	                        		    fieldLabel: 'OA�û�',
	                        		    store: yn,					                        		    
	                        		    //value: "��",
	                        		    queryMode: 'remote',
	                        		    displayField: 'dvalue',
	                        		    valueField: 'tvalue',
	                        		    name: 'user.OA' ,
                     		            labelAlign: 'right',
                     		           allowBlank: false,	 
                     	                labelWidth: 70,
                     	               editable:false,
                     	              blankText: '��ѡ��',                     	             
              			                selectOnFocus:false
	                        		});	                        		
	                        		                        		
	                        		
	                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
	                     		    height: 330,
	                     		    width: 310,
	                     		    bodyPadding: 10,
	                     		    defaultType: 'textfield',
	                     		    items: [
	                     		        {
	                     		            fieldLabel: '�û�',
	                     		            name: 'user.user_name' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70,
	                     	                allowBlank: false,	                
	                     	                blankText: '�������û���'
	                     		        },combo2,
	                     		        {
	                     		            fieldLabel: '����',
	                     		            name: 'user.user_pwd' , 
	                     		            inputType: 'password',
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70
	                     	                //disabled:true
	                     		        },combo1,{
	                     		            fieldLabel: '����',
	                     		            name: 'user.user_no' ,
	                     		            labelAlign: 'right',
	                     	                labelWidth: 70
	                     	                //disabled:true
	                     		        },combo5
	                     		    ]
	                     		});
	                     		
	                     		var win = Ext.create('Ext.window.Window', {
	                     		    title: '�޸��û�',
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
	                     		    	text:'�� ��',
	                     		    	handler: function(){
	                     		    		if (form.getForm().isValid()) {
	                     		    			form.getForm().doAction('submit',{  
		                     	              		 url:'ModUser',                		
		                     	              		 method:'post',  
		                     	              		params:{
		                                                 
		                                               username:id
		                                            },
		                     	               	success:function(form,action){ 
		                     	               	
		                     	               		Ext.Msg.alert("��Ϣ","�޸��û��ɹ�!");
		                     	               		win.close();
		                     	               		stores.reload();
		                     	               		
		                     	               	},  
		                     	               	failure:function(form,action){  
		                     	                   	Ext.Msg.alert("��Ϣ",action.result.message);  
		                     	               	}  
		                     	           		});
	                     		    		}
	                     		    		
	                     		    	}
	                     		    },{
	                     		    	xtype: 'button',					                     		    	
	                     		    	text:'ȡ ��',
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
					                         text:  '����û�',  
					                         icon:'<%=basePath%>icons/user_add.png',  
					                         handler : function(){                    	 				                        	 
					                        	 //yn3.reload();
					                        		// Create the combo box, attached to the states data store
					                        		var combo5 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: '����',
					                        		    store: yn,					                        		    
					                        		    
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.actived' ,
				                     		            labelAlign: 'right',
				                     	                labelWidth: 70,
				                     	               allowBlank: false,				                     	               
				                     	              blankText: '��ѡ��',                     	              
				              			                selectOnFocus:false
					                        		});
					                        		
					                        		var combo1 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: '����Ա',
					                        		    store: yn,					                        		    
					                        		    //value: "��",
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.dba' ,
				                     		            labelAlign: 'right',
				                     	                labelWidth: 70,
				                     	               allowBlank: false,				                     	              
				                     	              blankText: '��ѡ��',				                     	             
				              			                selectOnFocus:false
					                        		});
					                        		//yn4.reload();
					                        		var combo2 = Ext.create('Ext.form.field.ComboBox', {
					                        		    fieldLabel: 'OA�û�',
					                        		    store: yn,					                        		    
					                        		    //value: "��",
					                        		    queryMode: 'remote',
					                        		    displayField: 'dvalue',
					                        		    valueField: 'tvalue',
					                        		    name: 'user.OA' ,
				                     		            labelAlign: 'right',
				                     		           allowBlank: false,	 
				                     	                labelWidth: 70,				                     	               
				                     	              blankText: '��ѡ��',				                     	              
				              			                selectOnFocus:false
					                        		});
					                  					                        		
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [
					                     		        {
					                     		            fieldLabel: '�û�',
					                     		            name: 'user.user_name' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70,
					                     	                allowBlank: false,	                
					                     	                blankText: '�������û���'
					                     		        },combo2,
					                     		        {
					                     		            fieldLabel: '����',
					                     		            name: 'user.user_pwd' , 
					                     		            inputType: 'password',
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70
					                     		        },combo1,{
					                     		            fieldLabel: '����',
					                     		            name: 'user.user_no' ,
					                     		            labelAlign: 'right',
					                     	                labelWidth: 70
					                     		        },combo5
					                     		    ]
					                     		});
					                     		
					                     		var win = Ext.create('Ext.window.Window', {
					                     		    title: '����û�',
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
					                     		    	text:'�� ��',
					                     		    	handler: function(){
					                     		    		if (form.getForm().isValid()) {
					                     		    			form.getForm().doAction('submit',{  
						                     	              		 url:'AddUser',                		
						                     	              		 method:'post',  
						                     	               	success:function(form,action){ 
						                     	               	
						                     	               		Ext.Msg.alert("��Ϣ","����û��ɹ�!");
						                     	               		win.close();
						                     	               		stores.reload();
						                     	               		
						                     	               	},  
						                     	               	failure:function(form,action){  
						                     	                   	Ext.Msg.alert("��Ϣ",action.result.message);  
						                     	               	}  
						                     	           		});
					                     		    		}
					                     		    		
					                     		    	}
					                     		    },{
					                     		    	xtype: 'button',					                     		    	
					                     		    	text:'ȡ ��',
					                     		    	handler: function(){
					                     		    		win.close();
					                     		    	}
					                     		    }]
					                     		});
					                     		win.show();
					                     		
					                         }  
					                     } ,{  
					                         xtype: 'button',  
					                         text:  'ɾ���û�',  
					                         icon:'<%=basePath%>icons/user_delete.png',  
					                         handler : function(){ 
					                        	 var sm=grid.getSelection(); // ���grid��SelectionModel
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
				                                                 //�ں�̨����deleteUsersΪҪɾ���Ķ���
				                                               deleteUsers:Ext.encode(userids)
				                                            }, 
				                                            method:'POST',
				                                            success:function (response){
				                                            	var success = Ext.decode(response.responseText).success;
				                                                 if(success){
				                                                   Ext.Array.each(sm,function(record){
				                                                       //ɾ��store�е����ݣ�ҳ��Ч��
				                                                	   var tid = record.get("user_name");
							                                        	 if(tid!="infodba"){
							                                        		 stores.remove(record);
							                                        	 }
				                                                       
				                                                     }
				                                                       
				                                                   )
				                                                 }else{
				                                                   Ext.MessageBox.show(
				                                                       '��ʾ',
				                                                       'ɾ��ʧ��'
				                                                   )
				                                                 }
				                                            },
				                                        	failure:function(response){
				                                        		Ext.MessageBox.show(
					                                                       '��ʾ',
					                                                       'ɾ��ʧ��'
					                                                   )
				                                        	}
				                                            
					                        	 		 });				                                               
					                        	    	
					                        	 	
					                        	 }
					                         }  
					                     } 
					                   ]  
					              },//�·���ҳ������  
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