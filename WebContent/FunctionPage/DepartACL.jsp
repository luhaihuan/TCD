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
					root:'types', //对应action中需要被现实的字段，一般为list  
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
	            fieldLabel: '类型',
	            name: 'aclType' ,
	            labelAlign: 'right',
                labelWidth: 100,
                store: type_store,
                queryMode: 'remote',
        	    displayField: 'type_name',
        	    valueField: 'type_id',
        	    value:'ECO文件',
        	    forceSelection: true,				            
	            emptyText:'选择文件类型...',
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
 		fields : [ 'siteId','departId','siteName','departName','printWord','downloadWord','printExcel','downloadExcel','printPDF','downloadPDF','bomSearch','materialSearch','parentSearch'  ],
 		model : 'ACLTree',
 		pageSize: 9,
 		proxy : {
 			type : 'ajax',
 			url : 'GetDepartACLbyType', 			
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
 	        'beforeload': function (store, op, options) { 
 	        	var ftcombo = form.getForm().findField("aclType").getValue();
 	        	//alert(ftcombo.getValue());
 	            var params = {  
 	                //参数  
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
		var total = storts.getCount();//数据行数
		for(var i=0;i<total;i++){
		  //alert(storts.getAt(i))//每行records对象
		  //alert(mygrid.getStrore().getAt(i).data['abc'])//对应每行dataIndex为abc的值
		  var un = storts.getAt(i).get("departName");
		  if(un!=null&&un!=""){
			  aclAddModels.push(storts.getAt(i).data);
		  }
		  
		}
		//alert("2");
		//var sm = Ext.getCmp('depart_grid').getSelection()

        	var ftcombo = form.getForm().findField("aclType").getValue();
    		//Ext.getBody().mask("请稍等，正在保存中...","x-mask-loading");
    		 Ext.Msg.wait("请稍等，正在保存中...", "提示");
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
                    	 Ext.Msg.alert("提示","保存成功!");
                    	 
                    	 storts.reload();
                     }else{
                    	 Ext.Msg.alert("提示","保存失败!");
                       
                     }
                },
            	failure:function(response){
            		//Ext.getBody().unmask();
            		 Ext.Msg.hide();
            		Ext.Msg.alert("提示","保存失败!");
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
						text : '站点ID',
						dataIndex : 'siteId',
						hidden: true
						
					},
					{
						text : '部门ID',
						dataIndex : 'departId',
						hidden: true
						
					},
					{
						text : '站点名称',
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
				            emptyText:'选择站点...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            		
				            			  storts.getAt(0).set("siteId",record.data.uuid);  
				            			 
				            			  departs_store.proxy.extraParams.siteId = record.data.uuid;
				            			  departs_store.reload();
				            			
				            			   var total = storts.getCount();//数据行数
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//每行records对象
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//对应每行dataIndex为abc的值
				            				  var un = storts.getAt(i).get("siteName");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            			/* 	if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("提示","站点不能重复!");
				            				} */
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	}
						}
					}, {
						text : '部门名称',
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
				            emptyText:'选择部门...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            	     		//alert(combo.value);
				            			   //storts.reload();
				            			     storts.getAt(0).set("departId",record.data.uuid); 
				            			   var total = storts.getCount();//数据行数
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//每行records对象
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//对应每行dataIndex为abc的值
				            				  var un = storts.getAt(i).get("departName");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            			/* 	if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("提示","部门不能重复!");
				            				} */
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	}
						}
					},{
						text : '打印Word',
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
					            emptyText:'请选择...',
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
						text : '下载Word',
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
				            emptyText:'请选择...',
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
						text : '打印Excel',
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
				            emptyText:'请选择...',
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
						text : '下载Excel',
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
				            emptyText:'请选择...',
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
						text : '打印PDF',
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
				            emptyText:'请选择...',
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
						text : '下载PDF',
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
				            emptyText:'请选择...',
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
						text : 'BOM查询',
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
				            emptyText:'请选择...',
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
						text : '物料属性查询',
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
				            emptyText:'请选择...',
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
						text : '零部件反查',
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
				            emptyText:'请选择...',
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
	            	text : '添加' ,
	            	handler: btnaddclick
	        	},{  
	            	text : '移除' ,
	            	handler: btndelclick
	        	},{  
	            	text : '保存' ,
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