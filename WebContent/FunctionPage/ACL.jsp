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
					root:'types', //对应action中需要被现实的字段，一般为list  
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
	        {"name":"ECO文件"},    
		        {"name":"GCO文件"},    
		        {"name":"PCO文件"},
		        {"name":"SCO文件"},
		        {"name":"零件图(含主机零件表格图)"},
		        { "name":"组件装配图"},
		        { "name":"总图"},
		        { "name":"主机零件图"},
		        { "name":"主机装配图"},
		        { "name":"主机工装"},
		        { "name":"企业标准"},	        
		        { "name":"产品型号参数表"},
		        { "name":"操作维护手册和零部件手册"},		       
		        { "name":"ID，WD图样"},
		        { "name":"P&I图样"},
		        { "name":"工程公告"},		       
		        { "name":"随机资料系统图"} ,
		        { "name":"class=13或EX"} 
	    ]
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
                store: type1,
                queryMode: 'remote',
        	    displayField: 'name',
        	    valueField: 'name',
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
					root:'users', //对应action中需要被现实的字段，一般为list  
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
 		fields : [ 'username','readWord','printWord','downloadWord','readExcel','printExcel','downloadExcel','readPDF','printPDF','downloadPDF' ],
 		model : 'ACLTree',
 		pageSize: 9,
 		proxy : {
 			type : 'ajax',
 			url : 'GetACLbyType', 			
 			reader : {
 				type : 'json',
				root:'models', //对应action中需要被现实的字段，一般为list  
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
		var total = storts.getCount();//数据行数
		for(var i=0;i<total;i++){
		  //alert(storts.getAt(i))//每行records对象
		  //alert(mygrid.getStrore().getAt(i).data['abc'])//对应每行dataIndex为abc的值
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
                    	 Ext.Msg.alert("提示","保存成功!");
                    	 
                    	 storts.reload();
                     }else{
                    	 Ext.Msg.alert("提示","保存失败!");
                       
                     }
                },
            	failure:function(response){
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
					id:'user_grid',
					store: storts, 
					selModel: {
					    selection: "rowmodel",
					    mode: "MULTI"
					},
					plugins: [cellEditing],
					columns : [{
						text : '用户名称',
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
				            emptyText:'选择用户...',
				            selectOnFocus:true,
				            listeners:{ 
				            	   select:{
				            		   fn:function(combo,record,opts) {  
				            	     		//alert(combo.value);
				            			   //storts.reload();
				            			   var total = storts.getCount();//数据行数
				            			   var flag = 0;
				            				for(var i=0;i<total;i++){
				            				  //alert(storts.getAt(i))//每行records对象
				            				  //alert(mygrid.getStrore().getAt(i).data['abc'])//对应每行dataIndex为abc的值
				            				  var un = storts.getAt(i).get("username");			            				
				            				 
				            				  if(un==combo.value){
				            					  flag++;
				            				  }
				            				  
				            				}
				            				if(flag>0){
				            					combo.setValue("");
				            					Ext.Msg.alert("提示","用户不能重复!");
				            				}
				            	   		}
			     				},
					        		 scope: this
				            	   
				           	}
						}
					},{
						text : '预览Word',
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
					},{
						text : '预览Excel',
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
					},{
						text : '预览PDF',
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