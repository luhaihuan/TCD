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
	
	var type1 = Ext.create('Ext.data.Store', {
		storeId : 'typeStore',
		fields : [ 'name'],
		model : 'CModel',
		proxy : {
			type : 'ajax',
			url : 'GetTypeByUser',
			reader : {
					type : 'json',
					root:'types', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
		            success:'flag',  
		            total: 'total' ,
		            start: 'start',
		            limit: 'limit'
			}
		},
		autoLoad : true

	});
	
	var type1 = Ext.create('Ext.data.Store', {
	    fields: [ 'name'],
	    data : [
	        {"name":"ECO�ļ�"},    
		        {"name":"GCO�ļ�"},    
		        {"name":"PCO�ļ�"},
		        {"name":"SCO�ļ�"},
		        {"name":"���ͼ(������������ͼ)"},
		        { "name":"���װ��ͼ"},
		        { "name":"��ͼ"},
		        { "name":"�������ͼ"},
		        { "name":"����װ��ͼ"},
		        { "name":"������װ"},
		        { "name":"��ҵ��׼"},	        
		        { "name":"��Ʒ�ͺŲ�����"},
		        { "name":"����ά���ֲ���㲿���ֲ�"},		       
		        { "name":"ID��WDͼ��"},
		        { "name":"P&Iͼ��"},
		        { "name":"���̹���"},		       
		        { "name":"�������ϵͳͼ"} ,
		        { "name":"class=13��EX"} 
	    ]
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
                store: type1,
                queryMode: 'remote',
        	    displayField: 'name',
        	    valueField: 'name',
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
	
	Ext.define('ACLTree', {
		extend : 'Ext.data.Model',
		proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },
		fields : [ 
					{name : 'username', type : 'string' },
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

	var users_store = Ext.create('Ext.data.Store', {
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
 		fields : [ 'username','readWord','printWord','downloadWord','readExcel','printExcel','downloadExcel','readPDF','printPDF','downloadPDF' ],
 		model : 'ACLTree',
 		pageSize: 9,
 		proxy : {
 			type : 'ajax',
 			url : 'GetACLbyType', 			
 			reader : {
 				type : 'json',
				root:'models', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
	            success:'flag',  
	            total: 'total' ,
	            start: 'start',
	            limit: 'limit'
 			}
 		},
 		autoLoad : true,
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
        	username :'',
        	readWord :false,
        	printWord:false,
        	downloadWord:false,
        	readExcel:false,
        	printExcel:false,
        	downloadExcel:false,
        	readPDF:false,
        	printPDF:false,
        	downloadPDF:false
            
            
        });

        storts.insert(0, r);
        cellEditing.startEdit(0, 0);
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
		  var un = storts.getAt(i).get("username");
		  if(un!=null&&un!=""){
			  aclAddModels.push(storts.getAt(i).data);
		  }
		  
		}
		//alert("2");
		//var sm = Ext.getCmp('user_grid').getSelection()

        	var ftcombo = form.getForm().findField("aclType").getValue();
    		
    		Ext.Ajax.request({
     			url:'SaveACLbyType',
                params:{                
                   addModels:Ext.encode(aclAddModels),
                   ft:encodeURIComponent(ftcombo)
                }, 
                method:'POST',
                success:function (response){
                	var success = Ext.decode(response.responseText).success;
                     if(success){
                    	 Ext.Msg.alert("��ʾ","����ɹ�!");
                    	 
                    	 storts.reload();
                     }else{
                    	 Ext.Msg.alert("��ʾ","����ʧ��!");
                       
                     }
                },
            	failure:function(response){
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
					columns : [{
						text : '�û�����',
						dataIndex : 'username',
						hideable: false,
						editor: {
							xtype: "combo",
							store: users_store,					            
				            displayField:'name',
				            valueField:'name',
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
				            			   var total = storts.getCount();//��������
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//ÿ��records����
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//��Ӧÿ��dataIndexΪabc��ֵ
				            				  var un = storts.getAt(i).get("username");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            				if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("��ʾ","�û������ظ�!");
				            				}
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	}
						}
					},{
						text : 'Ԥ��Word',
						dataIndex : 'readWord',
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
					},{
						text : 'Ԥ��Excel',
						dataIndex : 'readExcel',
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
					},{
						text : 'Ԥ��PDF',
						dataIndex : 'readPDF',
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