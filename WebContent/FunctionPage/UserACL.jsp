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
	
	Ext.define('typeModel', {  
        extend: 'Ext.data.Model',  
        proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },  
        fields: [  
            {name: 'type_id',type: 'string'},
            {name: 'type_name',type: 'string'}
        ]  
    });
	
	var type_store = Ext.create('Ext.data.Store', {
		storeId : 'type_store',
		fields : [ 'type_id','type_name'],
		model : 'typeModel',
		proxy : {
			type : 'ajax',
			url : 'GetAllType',
			reader : {
					type : 'json',
					root:'types', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
			}
		},
		autoLoad : true,
		 listeners: {  
 	        'refresh': function (store, options) { 
 	        	//var ftcombo = form.getForm().findField("aclType").getValue();
 	        	var ftcombo = form.getForm().findField("aclType");
 	        	ftcombo.setValue(store.getAt(0).get('type_id'));
 	            storts.reload();
 	           
 	        }  
 	    }  

	});
	
	
	var form = Ext.create('Ext.form.Panel', {  
		bodyPadding: 20,
	    defaultType: 'combobox',
	    items: [
	        {
	            fieldLabel: '����',
	            name: 'aclType' ,
	            labelAlign: 'right',
                labelWidth: 100,
                store: type_store,
                queryMode: 'remote',
        	    displayField: 'type_name',
        	    valueField: 'type_id',
        	    value:'ECO�ļ�',
        	    forceSelection: true,				            
	            emptyText:'ѡ���ļ�����...',
	            selectOnFocus:true,
	            listeners:{ 
	            	   select:{
	            		   fn:function(combo,record,opts) {  
	            	     		 
	            			   storts.reload();
	            	   		}
     				},
		        		 scope: this
	            	   
	           	}
        	    
	        }
	    ]
	});
/* 	var ftcombo = form.getForm().findField("aclType");
 	ftcombo.setValue(type1.getAt(0).get('name')); */
	Ext.define('ACLTree', {
		extend : 'Ext.data.Model',
		proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },
		fields : [ //  {name : 'siteName', type : 'string' },
					{name : 'userName', type : 'string' }, 
					
					{name: 'printWord',      type: 'boolean'},
					{name: 'downloadWord',      type: 'boolean'},
					
					{name: 'printExcel',      type: 'boolean'},
					{name: 'downloadExcel',      type: 'boolean'},
					
					{name: 'printPDF',      type: 'boolean'},
					{name: 'downloadPDF',      type: 'boolean'},
					
					
					{name: 'bomSearch',      type: 'boolean'},
					{name: 'materialSearch',      type: 'boolean'},
					{name: 'parentSearch',      type: 'boolean'}]
	});

	var users_store = Ext.create('Ext.data.Store', {
 		storeId : 'userStore',
 		fields : [ 'uuid','user_name'],
 		model : 'userModel',
 		proxy : {
 			type : 'ajax',
 			url : 'GetMyAllUser',
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
 		fields : [ 'userId','userName','printWord','downloadWord','printExcel','downloadExcel','printPDF','downloadPDF','bomSearch','materialSearch','parentSearch'  ],
 		model : 'ACLTree',
 		pageSize: 9,
 		proxy : {
 			type : 'ajax',
 			url : 'GetUserACLbyType', 			
 			reader : {
 				type : 'json',
				root:'models', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
	            success:'flag',  
	            total: 'total',
	            start: 'start',
	            limit: 'limit'
 			}
 		},
 		autoLoad : false,
 	    listeners: {  
 	        'beforeload': function (store, op, options) { 
 	        	var ftcombo = form.getForm().findField("aclType").getValue();
 	        	//alert(ftcombo.getValue());
 	            var params = {  
 	                //����  
 	               ft:encodeURIComponent(ftcombo)
 	            };  
 	            Ext.apply(store.proxy.extraParams, params);   
 	        }  
 	    }  

 	});
	
	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit: 1
	});
	
	var btnaddclick = function(){	
		
		cellEditing.cancelEdit();
		
        // Create a model instance
        var r = Ext.create('ACLTree', {  
        	userId :'',
        	userName :'',
        	printWord:true,
        	downloadWord:true,
        	printExcel:true,
        	downloadExcel:true,
        	printPDF:true,
        	downloadPDF:true,
        	bomSearch:true,
        	materialSearch:true,
        	parentSearch:true
        	
            
            
        });

        storts.insert(0, r);
        cellEditing.startEdit(0, 0);
       var sm = Ext.getCmp('user_grid').getSelectionModel();
        sm.select(0);
	};
	
	var btndelclick = function(){
		var sm = Ext.getCmp('user_grid').getSelectionModel();
		cellEditing.cancelEdit();        
		storts.remove(Ext.getCmp('user_grid').getSelection());
        if (storts.getCount() > 0) {
            sm.select(0);
        }
	};
	
	var btnsaveclick = function(){
		cellEditing.cancelEdit();
		var aclAddModels=[]; 
		//alert("1");
		var total = storts.getCount();//��������
		for(var i=0;i<total;i++){
		  //alert(storts.getAt(i))//ÿ��records����
		  //alert(mygrid.getStrore().getAt(i).data['abc'])//��Ӧÿ��dataIndexΪabc��ֵ
		  var un = storts.getAt(i).get("userName");
		  if(un!=null&&un!=""){
			  aclAddModels.push(storts.getAt(i).data);
		  }
		  
		}
		//alert("2");
		//var sm = Ext.getCmp('depart_grid').getSelection()

        	var ftcombo = form.getForm().findField("aclType").getValue();
    		 Ext.Msg.wait("���Եȣ����ڱ�����...", "��ʾ");
    		Ext.Ajax.request({
     			url:'SaveUsersACLbyType',
                params:{                
                   addModels:Ext.encode(aclAddModels),
                   ft:encodeURIComponent(ftcombo)
                }, 
                method:'POST',
                success:function (response){
                	 Ext.Msg.hide();
                	var success = Ext.decode(response.responseText).success;
                     if(success){
                    	 Ext.Msg.alert("��ʾ","����ɹ�!");
                    	 
                    	 storts.reload();
                     }else{
                    	 Ext.Msg.alert("��ʾ","����ʧ��!");
                       
                     }
                },
            	failure:function(response){
            		 Ext.Msg.hide();
            		Ext.Msg.alert("��ʾ","����ʧ��!");
            	}
                
     		 });
   	 	
		
		
	};
	
	Ext.create('Ext.container.Viewport', {
		layout : 'border',			
		items : [ {
					region : 'north',
					collapsible : false,
					split : false,				
					// could use a TreePanel or AccordionLayout for navigational
					items : [form]
				}, {
					region : 'center',						
					collapsible : false,
					split : false,
					xtype: 'grid',
					id:'user_grid',
					store: storts, 				
					selModel: {
					    selection: "rowmodel",
					    mode: "MULTI"
					},
					plugins: [cellEditing],
					columns : [
					           {
						text : '�û�ID',
						dataIndex : 'userId',
						hidden: true,
						
					},{
						text : '�û�����',
						dataIndex : 'userName',
						hideable: false,
						
						editor: {
						/* 	xtype: "combo",
							store: users_store,					            
				            displayField:'user_name',
				            valueField:'user_name',
				            typeAhead: true,
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'ѡ���û�...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            	     		//alert(combo.value);
				            			   //storts.reload();
				            			     storts.getAt(0).set("userId",record.data.uuid);  
				            			   var total = storts.getCount();//��������
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//ÿ��records����
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//��Ӧÿ��dataIndexΪabc��ֵ
				            				  var un = storts.getAt(i).get("userName");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            			
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	} */
				           	
				           	xtype: "textfield",	
				           	id:'select_user_name',
				            emptyText:'ѡ���û�...',
				            selectOnFocus:true,
				            listeners:{ 				            	   
			     				render: function(p) {   
								     p.getEl().on('click', function(p){   
								    	  
								    	 	 var userField = Ext.getCmp('select_user_name');
								    	 	 var tmp_user_name;
								    	 	// alert(userField.getValue());
								    	 	 //userField.setValue('hello');
					                        	 var form = Ext.create('Ext.form.Panel', {					                     		    		    
					                     		    height: 330,
					                     		    width: 310,
					                     		    bodyPadding: 10,
					                     		    defaultType: 'textfield',
					                     		    items: [       
					                     		        {
					                     		        	collapsible : false,
															split : true,
																
															xtype: 'grid',
															itemId:'ruleGridId',
															id:'select_user_grid',
															store: users_store, 
															height: 240,
															autoScroll : true,
														selModel: {
													        injectCheckbox: 0,
													        mode: "SINGLE",     //"SINGLE"/"SIMPLE"/"MULTI"
													        checkOnly: true     //ֻ��ͨ��checkboxѡ��
													    },
													    selType: "checkboxmodel",
														columns : [{  xtype: "rownumberer",
													        	 text:'���',
													        	 align:'left',
													        	 width:50
													        	 },{
																text : '�û���',
																dataIndex : 'user_name',
																flex: 1
																
															}],
													    dockedItems: [{
												                xtype: 'toolbar',
												                dock: 'bottom',
												                items: [{
												                    xtype: 'textfield',
												                    emptyText: '���û�������',
												                    width: '100%',
												                   // fieldLabel: '����',
												                    labelAlign: 'left',
                                                                   // labelWidth: 50,
												                    enableKeyEvents: true,
												                    itemId: 'queryFieldId',
												                    triggers: {
												                        bar: {
												                            cls: 'x-form-clear-trigger',//������ʱ����߳���һ�����X��ť
												                            handler: function() {//�����հ�ť��textfield���ã�����������������
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
																		            filterField = field,//��ѯ������ؼ�
																		            filters = ruleGrid.store.getFilters();//��ǰ����filter
																		        if(filterField.value){//������ֵ,���filter
																		            this.nameFilter = filters.add({
																		                id:'nameField',
																		                property:'user_name',//ͨ��name���Թ���
																		                value:filterField.value,//ֵΪ�����������ֵ
																		                anyMatch:true,//ģ��ƥ��
																		                caseSensitive:false
																		            });
																		        }else if(this.nameFilter){//δ���룬���Ƴ�filter
																		            filters.remove(this.nameFilter);
																		            this.nameFilter = null;
																		        }
           
												                            }
												                        },
												                        
												                        buffer: 250
												                    }
												                }]
												            }]
																									    
																									    
																									    
																									    
													    
													    
													    
													    
													    
					                     		        }
					                     		    ]
					                     		});
					                        	 
 													ruleGrid = Ext.getCmp('select_user_grid'),					            
													 filters = ruleGrid.store.getFilters();//��ǰ����filter
					                        	     filters.removeAll();
					                     		
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
					                     		    		
					                     		    	
					                     		    	  var select_user_grid = Ext.getCmp('select_user_grid');
														  var sm = select_user_grid.getSelection(); // ���grid��SelectionModel	
														 
														    var tmp_user_uuid;
					                        	 if(sm!=null && sm.length == 1){					                        	     
					                        	      tmp_user_name = sm[0].get("user_name");
					                        	      tmp_user_uuid = sm[0].get("uuid");
					                                  //alert(sm[0].data.user_name)
					                        	     // var textField = Ext.getCmp('select_user_name');
					                        	      
					                     		     // userField.setValue(tmp_user_name);  
					                     		      win.close();
					                     		      var user_grid = Ext.getCmp('user_grid');
					                     		      var user_model = user_grid.getSelection(); 
					                     		     // user_model[0].data.userName = tmp_user_name;
					                     		      //user_model[0].set('userName',tmp_user_name);
					                     		      //storts.reload();
					                     		     // userField.focus(); 
					                     		    // store.getAt().set('name','value')
					                     		   // alert(storts.getAt(0).get('userName'));
					                     		   //   textField.setValue(tmp_user_name);
					                     		     // alert('hello');
					                     		     
					                     		  
														//var store = user_grid.getStore();
														//var selectData = user_model[0];
														//var dataIndex = store.indexOf(selectData);
														//store.getAt(dataIndex).set("userName",tmp_user_name);
														 user_model[0].set('userId',tmp_user_uuid);
														 user_model[0].set('userName',tmp_user_name);
														 
														//user_model[0].data.userName = tmp_user_name;	        
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
					                     		//textField.setValue('lhhkk'); 
					                     		win.show();
					                     		//userField.setValue('lhhkk');
								           //alert("lhh")
								     });   
								},
					        		 scope: this
				            	   
				           	} 
				           	
				           	
						}
					},{
						text : '��ӡWord',
						dataIndex : 'printWord',
						hideable: false,
							sortable: false,
							width: 75,
							editor: {
								xtype: "combo",
								store: yn,					            
					            displayField:'dvalue',
					            valueField:'tvalue',				            
					            queryMode: 'remote',
					            forceSelection: true,				            
					            emptyText:'��ѡ��...',
					            selectOnFocus:true
							} ,
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
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',	
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},
					{
						text : '��ӡExcel',
						dataIndex : 'printExcel',
						hideable: false,
						sortable: false,
						width: 75,
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
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
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',	
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},
					{
						text : '��ӡPDF',
						dataIndex : 'printPDF',
						hideable: false,
						sortable: false,
						width: 75,
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',	
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
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
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : 'BOM��ѯ',
						dataIndex : 'bomSearch',
						hideable: false,
						sortable: false,
						width: 75,
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '�������Բ�ѯ',
						dataIndex : 'materialSearch',
						hideable: false,
						sortable: false,
						width: 75,
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					},{
						text : '�㲿������',
						dataIndex : 'parentSearch',
						hideable: false,
						sortable: false,
						width: 75,
						editor: {
							xtype: "combo",
							store: yn,					            
				            displayField:'dvalue',
				            valueField:'tvalue',				            
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'��ѡ��...',
				            selectOnFocus:true
						} ,
			            renderer: function(value,metadata,record){  
			                var index = yn.find('tvalue',value);  
			                if(index!=-1){  
			                    return yn.getAt(index).data.dvalue;  
			                }  
			                return value;  
			            }
					}],
			
				tbar:[{  
	            	text : '���' ,
	            	handler: btnaddclick
	        	},{  
	            	text : '�Ƴ�' ,
	            	handler: btndelclick
	        	},{  
	            	text : '����' ,
	            	handler: btnsaveclick
	        	}]
				}]
	});
	
	
});
	
</script>
</head>
<body>

</body>
</html>