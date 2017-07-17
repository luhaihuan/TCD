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
		fields : [  {name : 'siteName', type : 'string' },
					{name : 'departName', type : 'string' },
					
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
 		fields : [ 'siteId','departId','siteName','departName','printWord','downloadWord','printExcel','downloadExcel','printPDF','downloadPDF','bomSearch','materialSearch','parentSearch'  ],
 		model : 'ACLTree',
 		pageSize: 9,
 		proxy : {
 			type : 'ajax',
 			url : 'GetDepartACLbyType', 			
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
	var editRow;
	var btnaddclick = function(){	
		
		cellEditing.cancelEdit();
		
        // Create a model instance
        var r = Ext.create('ACLTree', {
        	siteId :'',
        	departId:'',
        	siteName :'',
        	departName :'',
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
        editRow = r;

        storts.insert(0, r);
        cellEditing.startEdit(0, 0);
	};
	
	var btndelclick = function(){
		var sm = Ext.getCmp('depart_grid').getSelectionModel();
		cellEditing.cancelEdit();        
		storts.remove(Ext.getCmp('depart_grid').getSelection());
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
		  var un = storts.getAt(i).get("departName");
		  if(un!=null&&un!=""){
			  aclAddModels.push(storts.getAt(i).data);
		  }
		  
		}
		//alert("2");
		//var sm = Ext.getCmp('depart_grid').getSelection()

        	var ftcombo = form.getForm().findField("aclType").getValue();
    		//Ext.getBody().mask("���Եȣ����ڱ�����...","x-mask-loading");
    		 Ext.Msg.wait("���Եȣ����ڱ�����...", "��ʾ");
    		Ext.Ajax.request({
     			url:'SaveDepartsACLbyType',
                params:{                
                   addModels:Ext.encode(aclAddModels),
                   ft:encodeURIComponent(ftcombo)
                }, 
                method:'POST',
                success:function (response){
                	 //Ext.getBody().unmask();
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
            		//Ext.getBody().unmask();
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
					id:'depart_grid',
					store: storts, 				
					selModel: {
					    selection: "rowmodel",
					    mode: "MULTI"
					},
					plugins: [cellEditing],
					columns : [{
						text : 'վ��ID',
						dataIndex : 'siteId',
						hidden: true
						
					},
					{
						text : '����ID',
						dataIndex : 'departId',
						hidden: true
						
					},
					{
						text : 'վ������',
						dataIndex : 'siteName',
						hideable: false,
						editor: {
							xtype: "combo",
							store: sites_store,					            
				            displayField:'site_name',
				            valueField:'site_name',
				            typeAhead: true,
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'ѡ��վ��...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            		
				            			  storts.getAt(0).set("siteId",record.data.uuid);  
				            			 
				            			  departs_store.proxy.extraParams.siteId = record.data.uuid;
				            			  departs_store.reload();
				            			
				            			   var total = storts.getCount();//��������
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//ÿ��records����
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//��Ӧÿ��dataIndexΪabc��ֵ
				            				  var un = storts.getAt(i).get("siteName");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            			/* 	if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("��ʾ","վ�㲻���ظ�!");
				            				} */
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	}
						}
					}, {
						text : '��������',
						dataIndex : 'departName',
						hideable: false,
						
						editor: {
							xtype: "combo",
							store: departs_store,					            
				            displayField:'department_name',
				            valueField:'department_name',
				            typeAhead: true,
				            queryMode: 'remote',
				            forceSelection: true,				            
				            emptyText:'ѡ����...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            	     		//alert(combo.value);
				            			   //storts.reload();
				            			     storts.getAt(0).set("departId",record.data.uuid); 
				            			   var total = storts.getCount();//��������
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//ÿ��records����
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//��Ӧÿ��dataIndexΪabc��ֵ
				            				  var un = storts.getAt(i).get("departName");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            			/* 	if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("��ʾ","���Ų����ظ�!");
				            				} */
				            	   		}
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