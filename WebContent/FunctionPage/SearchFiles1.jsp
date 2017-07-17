<%@page import="com.sulliar.ypq.model.User"%>
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
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">

       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
       
       <!--  <script type="text/ecmascript" src="<%=basePath%>js/md5.js"></script> -->
       
       <script type="text/javascript" src="<%=basePath%>js/base64.js"></script>

<script type="text/javascript">
	Ext.onReady(function () {
		
		//Ext.Msg.wait('提示','正在处理数据，请稍候');
		Ext.QuickTips.init();
		
		Ext.define('Image', {
    		extend: 'Ext.data.Model',
    		fields: [
        				{ name:'src', type:'string' },
        				{ name:'caption', type:'string' }
    				]
		});

		var imagesStore = Ext.create('Ext.data.Store', {
    			
			storeId : 'imagesStore',
	 		fields : [ 'src' ,'caption'],
	 		model : 'Image',
	 		proxy : {
	 			type : 'ajax',
	 			url : 'GetWaterMark',
	 			reader : {
	 					type : 'json',
						root:'images', //对应action中需要被现实的字段，一般为list  
			            success:'flag',  
			            total: 'total' ,
			            start: 'start',
			            limit: 'limit'
	 			}
	 		},
	 		autoLoad : true
		});

		Ext.define('Printer', {  
	        extend: 'Ext.data.Model',  
	        proxy: {  
	            type: 'ajax',  
	            reader: 'json'  
	        },  
	        fields: [  
	            {name: 'name',type: 'string'}	            
	        ]  
	    });
		
		var printer_store = Ext.create('Ext.data.Store', {
	 		storeId : 'printer_store',
	 		fields : [ 'name' ],
	 		model : 'Printer',
	 		proxy : {
	 			type : 'ajax',
	 			url : 'GetAllPrinter',
	 			reader : {
	 					type : 'json',
						root:'pms', //对应action中需要被现实的字段，一般为list  
			            success:'flag',  
			            total: 'total' ,
			            start: 'start',
			            limit: 'limit'
	 			}
	 		},
	 		autoLoad : false
		});
		
		var ymstore = Ext.create('Ext.data.Store', {
 		    fields: ['name'],
 		    data : [
 		        {"name":"A3"},
 		        {"name":"A4"}
 		    ],
 		    autoLoad : true
 		});
		
		var wpstore = Ext.create('Ext.data.Store', {
 		    fields: ['name'],
 		    data : [
 		        {"name":"左上"},
 		        {"name":"左下"}
 		    ],
 		    autoLoad : true
 		});
		
		var orstore = Ext.create('Ext.data.Store', {
 		    fields: ['name'],
 		    data : [
 		        {"name":"横向"},
 		        {"name":"纵向"}
 		    ],
 		    autoLoad : true
 		});
		
		Ext.define('Item', {  
	        extend: 'Ext.data.Model',  
	        proxy: {  
	            type: 'ajax',  
	            reader: 'json'  
	        },  
	        fields: [
	                 {name: 'searchType',type: 'string'},    
	            {name: 'item_id',type: 'string'},
	            {name: 'item_name',type: 'string'},
	            {name: 'item_revision',type: 'string'},
	            {name: 'dataset_name',type: 'string'},
	            {name: 'dataset_type',type: 'string'},
	            {name: 'child_id',type: 'string'},
	            {name: 'child_rev_id',type: 'string'}
	        ]  
	    });
		
		var item_store = Ext.create('Ext.data.Store', {
	 		storeId : 'itemStore',
	 		fields : [ 'searchType','item_id' , 'item_name','item_revision','dataset_name','dataset_type','child_id','child_rev_id'],
	 		model : 'Item',
	 		proxy : {	 			
	 			type : 'ajax',
	 			
	 			timeout:6000000,
	 			url : 'GetSearchItem',
	 			reader : {
	 					type : 'json',
						root:'items', //对应action中需要被现实的字段，一般为list  
			            success:'flag',  
			            total: 'total' ,
			            start: 'start',
			            limit: 'limit'
	 			}
	 		},
	 		autoLoad : false,
	 		 listeners: {  
	  	        'beforeload': function (store, op, options) { 
	  	        	var form = Ext.getCmp("search_form");
	  	        	var item_id = form.getForm().findField("item_id").getValue();
	  	        	var item_name = form.getForm().findField("item_name").getValue();
	  	        	var item_revision = form.getForm().findField("item_revision").getValue();
	  	        	var query_type = form.getForm().findField("query_type").getValue();
	  	        	//alert(ftcombo.getValue());
	  	            var params = {  
	  	                //参数  
	  	               item_id:encodeURIComponent(item_id),
	  	             	item_name:encodeURIComponent(item_name),
	  	           		item_revision:encodeURIComponent(item_revision),
	  	         		query_type:encodeURIComponent(query_type)
	  	            };  
	  	            Ext.apply(store.proxy.extraParams, params);//将属性复制   
	  	        }  
	  	    }

	 	});
		
		//alert(cpld);
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
		
		var btnsearch = function(){
			var form = Ext.getCmp("search_form");
			
			var v = form.getForm().findField("item_id").getValue();
			
			if(v==null||v==""){
				msg = Ext.Msg.alert("提示","请填写ITEM ID！");
				return;
			}
			
			if(form.getForm().isValid()){
				//alert(form.getForm().getValues());
				//item_store.baseParams= form.getForm().getValues();
				//Ext.apply(item_store.baseParams, {item_id:'123'});
				item_store.reload({
					callback:function(r,o,s){
						if(s){
							if(item_store.getCount()==0){
								
								Ext.Msg.alert("提示","没有对应记录！");
							}	
						}
						
							
					}
				}					
				);
				
				
			}
			
		};
			
		var btncancel = function(){
			var ps = item_store.getProxy();
			
			ps.abort();
		};
		
		var  bgroup ='';											
		
		Ext.create('Ext.container.Viewport', {
			layout : 'border',			
			items : [ {
						region : 'west',
						collapsible : false,
						split : true,						
						width : 265 ,
						// could use a TreePanel or AccordionLayout for navigational
						id:'search_form',
						xtype: 'form',
						bodyPadding: 10, 
						defaultType: 'textfield',
					    items: [{
				            	fieldLabel: 'Item ID(*)',
				            	name: 'item_id' ,
				            	labelAlign: 'right',
			                	labelWidth: 70,
			                	allowBlank: false,	                
				                blankText: '请填写ITEM ID',
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
	                        			if (e.getKey() == Ext.EventObject.ENTER) {  
	                          
	                        				btnsearch();

	                        		}  
	                    			} // end of specialkey  
	                   				} // end of listeners
				        	},
					        {
					            fieldLabel: 'Item名称',
					            name: 'item_name' ,
					            labelAlign: 'right',
				                labelWidth: 70,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
	                        			if (e.getKey() == Ext.EventObject.ENTER) { //如果点击enter执行搜索 
	                          
	                        				btnsearch();

	                        		}  
	                    			} // end of specialkey  
	                   				} // end of listeners
					        },
					        {
					        	xtype:'combo',
					            fieldLabel: '查找类型',
					            name: 'query_type' , 		            
					            labelAlign: 'right',
				                labelWidth: 70,
				                store: type1,
				                queryMode: 'remote',
				        	    displayField: 'name',
				        	    valueField: 'name',				        	    
				        	    selectOnFocus:true,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
	                        			if (e.getKey() == Ext.EventObject.ENTER) {  
	                          
	                        				btnsearch();

	                        		}  
	                    			} // end of specialkey  
	                   				} // end of listeners
					        },
					        {
					            fieldLabel: '版本',
					            name: 'item_revision' , 		            
					            labelAlign: 'right',
				                labelWidth: 70,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
	                        			if (e.getKey() == Ext.EventObject.ENTER) {  
	                          
	                        				btnsearch();

	                        		}  
	                    			} // end of specialkey  
	                   				} // end of listeners
					        }
					    ],
					    buttons: [{
					    	xtype: 'button',
					    	name:'search_file',
					    	text:'搜索',
					    	handler: btnsearch
					    },{
					    	xtype: 'button',
					    	name:'search_cancel',
					    	text:'取消',
					    	handler: btncancel
					    }] 
					}, {
						region : 'center',						
						collapsible : false,
						split : true,
						xtype: 'grid',
						id:'item_grid',
						store:item_store,
						
						selModel: {
					        injectCheckbox: 0,
					        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
					        checkOnly: true     //只能通过checkbox选择
					    },
					    selType: "checkboxmodel",

						columns : [{ xtype: "rownumberer" },
						           {
							xtype:'actioncolumn',//这里就是放置按钮的地方
							width:150,
							text:'action',									
							items: [{ // Use a URL in the icon config
									tooltip: '预览',											
									getClass:function(v,m,r,rIndex,cIndex,store){ 
										var preview = store.getAt(rIndex).get("canRead");
										
								        if (preview){  
								            return 'preview';  
								        }else{  
								            return 'unpreview'  
								        }  
								    },
									handler:function(grid,rindex,cindex){
																					
										var record=grid.getStore().getAt(rindex);
										
										var preview = record.get("canRead");
										var cp = record.get("canPrint");
										var cd = record.get("canDownload");
										
										if(preview){
											Ext.Msg.wait('提示','正在处理数据，请稍候'); 
											
											var nodePath=record.get("nodePath");
											var filePath=record.get("filePath");
											var dataset_type = record.get("dataset_type");
											
											var item_id=record.get("item_id");
											var item_name=record.get("item_name");
											var item_rev=record.get("item_revision");
											
											var dsName = record.get("dataset_name");
											
											Ext.Ajax.request({
												timeout: 600000,
								     			url:'PreviewFile',
								                params:{                
								                	nodePath:encodeURIComponent(nodePath),
								                	filePath:encodeURIComponent(filePath),
								                	dataset_type:encodeURIComponent(dataset_type),
								                	item_id:encodeURIComponent(item_id),
								                	item_name:encodeURIComponent(item_name),
								                	item_rev:encodeURIComponent(item_rev)
								                }, 
								                method:'POST',
								                success:function (response){
								                	Ext.Msg.hide();
								                	
								                	var success = Ext.decode(response.responseText).flag;
								                	var resultPath = Ext.decode(response.responseText).resultPath;
								                    
								                	var b = new Base64();
								                	var str1 = b.encode(resultPath);
								                	var str2 = b.encode(item_id);
								                	var str3 = b.encode(item_name);
								                	var str4 = b.encode(item_rev);
								                	var str5 = b.encode(dataset_type);
								                	var str6 = b.encode(filePath);
								                	var str7 = b.encode(dsName);
								                	
								                
								                	if(success){
								                    	 window.open('<%=basePath%>FlexPaperSWF/SWF1.jsp?rp='+str1+'&id='+str2+'&name='+str3+'&rev='+str4+'&dst='+str5+'&fp='+str6+'&dsn='+str7+'&cp='+cp+'&cd='+cd);
								                     }
								                }
								                
								     		 });
											
											
											
											 
										}
										
										 
										}
									},{  
						                   iconCls : ''  
						                                        
						              },
							        { getClass:function(v,m,r,rIndex,cIndex,store){ 
										var preview = store.getAt(rIndex).get("canPrint");
										
								        if (preview){  
								            return 'print';  
								        }else{  
								            return 'unprint'  
								        }  
								    },// Use a URL in the icon config
									tooltip: '打印'	,
										handler:function(grid,rindex,cindex){
											
											var record=grid.getStore().getAt(rindex);
											var print = record.get("canPrint");
											
											if(print){
												var nodePath=record.get("nodePath");
												var filePath=record.get("filePath");
												var dataset_type = record.get("dataset_type");
												var item_id=record.get("item_id");
											var item_name=record.get("item_name");
											var item_rev=record.get("item_revision");
												
											
											if(bgroup=="文控中心"){
												//alert(bgroup);
												var form = Ext.create('Ext.form.Panel', {  
													bodyPadding: 10,
												    defaultType: 'combobox',
												    
												    items: [
												        {
												            fieldLabel: '打印机',
												            name: 'printer_name' ,
												            labelAlign: 'right',
											                labelWidth: 110,
											                store: printer_store,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'选择打印机...',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '纸张大小',
												            name: 'page_size' ,
												            labelAlign: 'right',
											                labelWidth: 110,
											                store: ymstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'Please choose page size...',
												            value:'A4',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '页面方向',
												            name: 'page_orientation' ,
												            labelAlign: 'right',
											                labelWidth: 110,
											                store: orstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'Please choose page orientation...',
												            value:'横向',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '水印位置',
												            name: 'watermark_pos' ,
												            labelAlign: 'right',
											                labelWidth: 110,
											                store: wpstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',
											        	    value:'左上',
											        	    forceSelection: true,				            
												            emptyText:'Please choose watermark position...',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												        	xtype:'panel',
												        	layout: 'fit',    						
															items: [{
																	xtype:'label',
																	text:'选择水印'
																	},{
																	xtype:'dataview',
																	id:'images-view',
																	store: imagesStore,
																	tpl: new Ext.XTemplate(
	  
	 																			'<tpl for=".">',
																				'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																				'<div class="thumb"><img src="<%=basePath%>{src}" title="{caption}"></div>',
																				'<span class="x-editable">{caption}</span></div>',
																			'</tpl>',
																			'<div class="x-clear"></div>'
	 	
																	), 
																	autoScroll : true,
																	border: 3,
																	style: {
																		borderColor: '#76ba53',
																		borderStyle: 'solid'
																		},			
																	trackOver: true,
 																	itemSelector: 'div.thumb-wrap',
																	emptyText: 'No images available'
																	}]
												        }
												    ]
												});
												
												var win = Ext.create('Ext.window.Window', {
												    title: '打印配置',
												    height: 440,
												    width: 360,				   
												    buttonAlign: 'center',		    
												    modal: true,
												    layout:'fit',
												    closable: true,													    
												    items: form,
												    
												    buttons: [{
												    	xtype: 'button',
												    	name:'ok',
												    	text:'打印',
												    	handler:function(){
												    		if(form.getForm().isValid()){
												    			//win.hide();
												    			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
																				    			
												    		
												    		var printer_name = form.getForm().findField("printer_name").getValue();
												    		var ps = form.getForm().findField("page_size").getValue();
												    		var po = form.getForm().findField("page_orientation").getValue();
												    		var wp = form.getForm().findField("watermark_pos").getValue();
												    		
												    		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
			                        	 					if(sm!=null&&sm.length>0){
			                        	 						//alert(sm[0].get("caption"));
			                        	 						
			                        	 						form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'PrintFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		printer_name:encodeURIComponent(printer_name),
												                		waterm_name:encodeURIComponent(sm[0].get("caption")),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						ps:encodeURIComponent(ps),
								                						po:encodeURIComponent(po),
								                						wp:encodeURIComponent(wp)
												                		
												                	}
																});

			                        	 						
			                        	 					}else{
			                        	 						
			                        	 						if(imagesStore.getCount()>0){
			                        	 							//alert(imagesStore.getAt(0).data['caption']);
			                        	 							
			                        	 							form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'PrintFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		printer_name:encodeURIComponent(printer_name),
												                		waterm_name:encodeURIComponent(imagesStore.getAt(0).data['caption']),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						ps:encodeURIComponent(ps),
								                						po:encodeURIComponent(po),
								                						wp:encodeURIComponent(wp)
												                	}
																});					                        	 							

			                        	 						}
			                        	 						
			                        	 					}
												    		
												    		//Ext.Msg.hide();
												    		//win.close();
												    		
												    		}
												    		
												    		
												    	}
												    },{
												    	xtype: 'button',
												    	name:'cancel',
												    	text:'取消',
												    	handler:function(){
												    		win.close();
												    	}
												    }]
												});
												win.show();
											}else{
												//alert(bgroup);
												var form = Ext.create('Ext.form.Panel', {  
													bodyPadding: 10,
												    defaultType: 'combobox',
												    
												    items: [
												        {
												            fieldLabel: '打印机',
												            name: 'printer_name' ,
												            labelAlign: 'right',
											                labelWidth: 90,
											                store: printer_store,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'选择打印机...',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '纸张大小',
												            name: 'page_size' ,
												            labelAlign: 'right',
											                labelWidth: 90,
											                store: ymstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'Please choose page size...',
												            value:'A4',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '页面方向',
												            name: 'page_orientation' ,
												            labelAlign: 'right',
											                labelWidth: 90,
											                store: orstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',												        	    
											        	    forceSelection: true,				            
												            emptyText:'Please choose page orientation...',
												            value:'横向',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												            fieldLabel: '普通用户方章水印位置',
												            name: 'watermark_pos' ,
												            labelAlign: 'right',
											                labelWidth: 90,
											                store: wpstore,
											                queryMode: 'remote',
											        	    displayField: 'name',
											        	    valueField: 'name',
											        	    value:'左上',
											        	    forceSelection: true,				            
												            emptyText:'Please choose watermark position...',
												            selectOnFocus:true,
												            allowBlank: false
												        },{
												        	xtype:'panel',
												        	layout: 'fit',    						
															items: [{
																	xtype:'label',
																	text:'参考和作废章可选(默认在中间)'
																	},{
																	xtype:'dataview',
																	id:'images-view',
																	store: imagesStore,
																	tpl: new Ext.XTemplate(
	  
	 																			'<tpl for=".">',
																				'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																				'<div class="thumb"><img src="<%=basePath%>{src}" title="{caption}"></div>',
																				'<span class="x-editable">{caption}</span></div>',
																			'</tpl>',
																			'<div class="x-clear"></div>'
	 	
																	), 
																	autoScroll : true,
																	border: 3,
																	style: {
																		borderColor: '#76ba53',
																		borderStyle: 'solid'
																		},			
																	trackOver: true,
 																	itemSelector: 'div.thumb-wrap',
																	emptyText: 'No images available'
																	}]
												        }
												    ]
												});
												
												var win = Ext.create('Ext.window.Window', {
												    title: '打印配置',
												    height: 440,
												    width: 360,				   
												    buttonAlign: 'center',		    
												    modal: true,
												    layout:'fit',
												    closable: true,													    
												    items: form,
												    
												    buttons: [{
												    	xtype: 'button',
												    	name:'ok',
												    	text:'打印',
												    	handler:function(){
												    		if(form.getForm().isValid()){
												    			//win.hide();
												    			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
																				    			
												    		
												    		var printer_name = form.getForm().findField("printer_name").getValue();
												    		var ps = form.getForm().findField("page_size").getValue();
												    		var po = form.getForm().findField("page_orientation").getValue();
												    		var wp = form.getForm().findField("watermark_pos").getValue();
												    		
												    		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
			                        	 					if(sm!=null&&sm.length>0){
			                        	 						//alert(sm[0].get("caption"));
			                        	 						
			                        	 						form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'PrintFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		printer_name:encodeURIComponent(printer_name),
												                		waterm_name:encodeURIComponent(sm[0].get("caption")),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						ps:encodeURIComponent(ps),
								                						po:encodeURIComponent(po),
								                						wp:encodeURIComponent(wp)
												                		
												                	}
																});

			                        	 						
			                        	 					}else{
			                        	 						
			                        	 						//if(imagesStore.getCount()>0){
			                        	 							//alert(imagesStore.getAt(0).data['caption']);
			                        	 							
			                        	 							form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'PrintFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		printer_name:encodeURIComponent(printer_name),
												                		waterm_name:encodeURIComponent("null"),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						ps:encodeURIComponent(ps),
								                						po:encodeURIComponent(po),
								                						wp:encodeURIComponent(wp)
												                	}
																});					                        	 							

			                        	 						//}
			                        	 						
			                        	 					}
												    		
												    		//Ext.Msg.hide();
												    		//win.close();
												    		
												    		}
												    		
												    		
												    	}
												    },{
												    	xtype: 'button',
												    	name:'cancel',
												    	text:'取消',
												    	handler:function(){
												    		win.close();
												    	}
												    }]
												});
												win.show();
											}
											
												
												
												
												
											}							
											
											
											
										}
							        },{  
						                   iconCls : ''  
						                                       
						              },
							        { getClass:function(v,m,r,rIndex,cIndex,store){ 
										var preview = store.getAt(rIndex).get("canDownload");
										
								        if (preview){  
								            return 'download';  
								        }else{  
								            return 'undownload'  ;
								        }  
								    },// Use a URL in the icon config
										tooltip: '下载',
										handler:function(grid,rindex,cindex){
											
											
											var record=grid.getStore().getAt(rindex);
											var download = record.get("canDownload");
											var dsName = record.get("dataset_name");
											
											if(download){														
												 
												var nodePath=record.get("nodePath");
												var filePath=record.get("filePath");
												var dataset_type = record.get("dataset_type");	
												var item_id=record.get("item_id");
											var item_name=record.get("item_name");
											var item_rev=record.get("item_revision");
												
											
											if(bgroup=="文控中心"){
												var form = Ext.create('Ext.form.Panel', {  
													bodyPadding: 10,
													defaultType: 'combobox',													    
												    items: [{
												    	
											            fieldLabel: '水印位置',
											            name: 'watermark_pos' ,
											            labelAlign: 'right',
										                labelWidth: 60,
										                store: wpstore,
										                queryMode: 'remote',
										        	    displayField: 'name',
										        	    valueField: 'name',												        	    
										        	    forceSelection: true,				            
											            emptyText:'Please choose watermark position...',
											            value:'左上',
											            selectOnFocus:true,
											            allowBlank: false
											        },
												        {
												        	xtype:'panel',
												        	layout: 'fit',    						
															items: [{
																	xtype:'label',
																	text:'选择水印'
																	},{
																	xtype:'dataview',
																	id:'images-view',
																	store: imagesStore,
																	tpl: new Ext.XTemplate(
	  //支持高级功能的模板类，如自动数组输出、条件判断、子模板、基本数学运行、特殊内建的模板变量，直接执行代码和更多的功能。XTemplate有些特殊的标签和内建的操作运算符，是模板创建时生成的
	 																			'<tpl for=".">',
																				'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																				'<div class="thumb"><img src="<%=basePath%>{src}" title="{caption}"></div>',
																				'<span class="x-editable">{caption}</span></div>',
																			'</tpl>',
																			'<div class="x-clear"></div>'
	 	
																	), 
																	autoScroll : true,
																	border: 3,
																	style: {
																		borderColor: '#76ba53',
																		borderStyle: 'solid'
																		},			
																	trackOver: true,
 																	itemSelector: 'div.thumb-wrap',
																	emptyText: 'No images available'
																	}]
												        }
												    ]
												});
												
												var win = Ext.create('Ext.window.Window', {
												    title: '下载配置',
												    height: 350,
												    width: 360,				   
												    buttonAlign: 'center',		    
												    modal: true,
												    layout:'fit',
												    closable: true,													    
												    items: form,
												    
												    buttons: [{
												    	xtype: 'button',
												    	name:'ok',
												    	text:'下载',
												    	handler:function(){
												    		if(form.getForm().isValid()){
												    			//win.hide();
												    			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
												    			
												    			//var myMask = new Ext.LoadMask(Ext.getBody(),{msg:"请稍等,正在导入..."}); 
																
												    			//myMask.show();	
												    			var wp = form.getForm().findField("watermark_pos").getValue();
												    		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
			                        	 					if(sm!=null&&sm.length>0){
			                        	 						//alert(sm[0].get("caption"));
			                        	 						 
			                        	 						form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'SaveFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		
												                		waterm_name:encodeURIComponent(sm[0].get("caption")),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						
												           					//var obj =Ext.decode(res.responseText);
												           					//alert(action.result.path);
												           					
												           					var overDownload = action.result.overDownload;
												           					if(overDownload){
												           						Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
												           					}
												           					
												 		   					window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   

												            
												      					}              	
        	
																});		
			                        	 						
			       					                        	 						
			                        	 					}else{
			                        	 						 
			                        	 						if(imagesStore.getCount()>0){
			                        	 							//alert(imagesStore.getAt(0).data['caption']);
			                        	 							form.getForm().doAction('submit',{
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'SaveFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		
												                		waterm_name:encodeURIComponent(imagesStore.getAt(0).data['caption']),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						//alert("1");
												           					//var obj =Ext.decode(res.responseText);
												           					//alert(action.result.path);
												           					var overDownload = action.result.overDownload;
												           					if(overDownload){
												           						Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
												           					}
												 		   					window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   

												            
												      					}
																}); 
			                        	 							
			                        	 							
			                        	 						}
			                        	 						
			                        	 					}
												    		
												    		//Ext.Msg.hide();
												    		//win.close();
												    		//myMask.hide();
												    		}
												    		
												    		
												    	}
												    },{
												    	xtype: 'button',
												    	name:'cancel',
												    	text:'取消',
												    	handler:function(){
												    		win.close();
												    	}
												    }]
												});
												win.show();	
											}else{
												var form = Ext.create('Ext.form.Panel', {  
													bodyPadding: 10,
													defaultType: 'combobox',													    
												    items: [{
												    	
											            fieldLabel: '普通用户方章水印位置',
											            name: 'watermark_pos' ,
											            labelAlign: 'right',
										                labelWidth: 90,
										                store: wpstore,
										                queryMode: 'remote',
										        	    displayField: 'name',
										        	    valueField: 'name',												        	    
										        	    forceSelection: true,				            
											            emptyText:'Please choose watermark position...',
											            value:'左上',
											            selectOnFocus:true,
											            allowBlank: false
											        },
												        {
												        	xtype:'panel',
												        	layout: 'fit',    						
															items: [{
																	xtype:'label',
																	text:'参考和作废章可选(默认在中间)'
																	},{
																	xtype:'dataview',
																	id:'images-view',
																	store: imagesStore,
																	tpl: new Ext.XTemplate(
	  
	 																			'<tpl for=".">',
																				'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																				'<div class="thumb"><img src="<%=basePath%>{src}" title="{caption}"></div>',
																				'<span class="x-editable">{caption}</span></div>',
																			'</tpl>',
																			'<div class="x-clear"></div>'
	 	
																	), 
																	autoScroll : true,
																	border: 3,
																	style: {
																		borderColor: '#76ba53',
																		borderStyle: 'solid'
																		},			
																	trackOver: true,
 																	itemSelector: 'div.thumb-wrap',
																	emptyText: 'No images available'
																	}]
												        }
												    ]
												});
												
												var win = Ext.create('Ext.window.Window', {
												    title: '下载配置',
												    height: 350,
												    width: 360,				   
												    buttonAlign: 'center',		    
												    modal: true,
												    layout:'fit',
												    closable: true,													    
												    items: form,
												    
												    buttons: [{
												    	xtype: 'button',
												    	name:'ok',
												    	text:'下载',
												    	handler:function(){
												    		if(form.getForm().isValid()){
												    			//win.hide();
												    			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
												    			
												    			//var myMask = new Ext.LoadMask(Ext.getBody(),{msg:"请稍等,正在导入..."}); 
																
												    			//myMask.show();	
												    			var wp = form.getForm().findField("watermark_pos").getValue();
												    		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
			                        	 					if(sm!=null&&sm.length>0){
			                        	 						//alert(sm[0].get("caption"));
			                        	 						 
			                        	 						form.getForm().submit({
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'SaveFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		
												                		waterm_name:encodeURIComponent(sm[0].get("caption")),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						
												           					//var obj =Ext.decode(res.responseText);
												           					//alert(action.result.path);
												           					
												           					var overDownload = action.result.overDownload;
												           					if(overDownload){
												           						Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
												           					}
												           					
												 		   					window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   

												            
												      					}              	
        	
																});		
			                        	 						
			       					                        	 						
			                        	 					}else{
			                        	 						 
			                        	 						//if(imagesStore.getCount()>0){
			                        	 							//alert(imagesStore.getAt(0).data['caption']);
			                        	 							form.getForm().doAction('submit',{
              														waitMsg:'正在保存数据，请稍候。',
              														waitTitle:'提示',
             														method:'POST',
             														timeout: 600000,
              														url:'SaveFile',
              														params:{                
												                		nodePath:encodeURIComponent(nodePath),
												                		filePath:encodeURIComponent(filePath),
												                		dataset_type:encodeURIComponent(dataset_type),
												                		
												                		waterm_name:encodeURIComponent("null"),
								                						item_id:encodeURIComponent(item_id),
								                						item_name:encodeURIComponent(item_name),
								                						item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						//alert("1");
												           					//var obj =Ext.decode(res.responseText);
												           					//alert(action.result.path);
												           					var overDownload = action.result.overDownload;
												           					if(overDownload){
												           						Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
												           					}
												 		   					window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   

												            
												      					}
																}); 
			                        	 							
			                        	 							
			                        	 						//}
			                        	 						
			                        	 					}
												    		
												    		//Ext.Msg.hide();
												    		//win.close();
												    		//myMask.hide();
												    		}
												    		
												    		
												    	}
												    },{
												    	xtype: 'button',
												    	name:'cancel',
												    	text:'取消',
												    	handler:function(){
												    		win.close();
												    	}
												    }]
												});
												win.show();	
											}
																								
												
												
												
											}
											
										}
										
										
										}						
										]
						},{
							text : 'Search Type',
							dataIndex : 'searchType',
							width:160
						}, {
									text : 'Item ID',
									dataIndex : 'item_id',
									width:200
								}, {
									text : 'Item Name',
									dataIndex : 'item_name',
									width:200
								}, {
									text : 'Revision ID',
									dataIndex : 'item_revision'
								},{
									text : 'Dataset Name',
									dataIndex : 'dataset_name',
									width:200
								},{
									text : 'Dataset Type',
									dataIndex : 'dataset_type'
								},{
									text : 'Child ID',
									dataIndex : 'child_id',
									width:200
								},{
									text : 'Child Revision ID',
									dataIndex : 'child_rev_id'
								}],
								
						autoHeight:true ,
						autoWidth:true ,
						tbar:[{ 
							xtype:'button',
							id:'pldl',
				            text : '批量下载' ,
				            handler:function(){
				            	var item_grid = Ext.getCmp("item_grid");
				            	var sm1=item_grid.getSelection(); // 获得grid的SelectionModel
				            	
				            	if(sm1!=null&&sm1.length>0){	
				            		
				            		var items=[];
				            		 Ext.Array.each(
					                       sm1,
					                      function(record){
					                          
					                           items.push(record.data);
          	 
					                      }
					                  );
				            		
				            		
				            		var form = Ext.create('Ext.form.Panel', {  
															bodyPadding: 10,
															defaultType: 'combobox',													    
														    items: [{
													            fieldLabel: '水印位置',
													            name: 'watermark_pos' ,
													            labelAlign: 'right',
												                labelWidth: 60,
												                store: wpstore,
												                queryMode: 'remote',
												        	    displayField: 'name',
												        	    valueField: 'name',
												        	    value:'左上',
												        	    forceSelection: true,				            
													            emptyText:'Please choose watermark position...',
													            selectOnFocus:true,
													            allowBlank: false
													        },
														        {
														        	xtype:'panel',
														        	layout: 'fit',    						
    																items: [{
    																		xtype:'label',
    																		text:'选择水印'
    																		},{
    																		xtype:'dataview',
    																		id:'images-view',
    																		store: imagesStore,
    																		tpl: new Ext.XTemplate(
    		  
   		 																			'<tpl for=".">',
																						'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																						'<div class="thumb"><img src="<%=basePath%>{src}" title="{caption}"></div>',
																						'<span class="x-editable">{caption}</span></div>',
																					'</tpl>',
																					'<div class="x-clear"></div>'
   		 	
																			), 
    																		autoScroll : true,
    																		border: 3,
																			style: {
    																			borderColor: '#76ba53',
    																			borderStyle: 'solid'
																				},			
																			trackOver: true,
         																	itemSelector: 'div.thumb-wrap',
    																		emptyText: 'No images available'
    																		}]
														        }
														    ]
														});
														
														var win = Ext.create('Ext.window.Window', {
														    title: '下载配置',
														    height: 300,
														    width: 360,				   
														    buttonAlign: 'center',		    
														    modal: false,
														    layout:'fit',
														    closable: true,													    
														    items: form,
														    
														    buttons: [{
														    	xtype: 'button',
														    	name:'ok',
														    	text:'下载',
														    	handler:function(){
														    		if(form.getForm().isValid()){
														    			//win.hide();
														    			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
														    			
														    			//var myMask = new Ext.LoadMask(Ext.getBody(),{msg:"请稍等,正在导入..."}); 
																		
														    			//myMask.show();	
														    			var wp = form.getForm().findField("watermark_pos").getValue();
														    		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
					                        	 					if(sm!=null&&sm.length>0){
					                        	 						//alert(sm[0].get("caption"));
					                        	 						 
					                        	 						form.getForm().submit({
                      														waitMsg:'正在保存数据，请稍候。',
                      														waitTitle:'提示',
                     														method:'POST',
                     														timeout: 600000,
                      														url:'BatchDownLoadFiles',
                      														params:{                
                      															wp:encodeURIComponent(wp),
														                		waterm_name:encodeURIComponent(sm[0].get("caption")),
										                						items:Ext.encode(items)
														                	},
														      					success:function(form,action){
														      						
														           					//var obj =Ext.decode(res.responseText);
														           					//alert(action.result.path);
														           					var overDownload = action.result.overDownload;
														           					if(overDownload){
														           						Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
														           					}
														 		   					window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   

														            
														      					} ,
														      					failure:function(form,action){
														      						//alert("1");
														           					//var obj =Ext.decode(res.responseText);
														           					//alert(action.result.path);
														 		   					//window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   
														      						Ext.Msg.alert("提示",action.result.message);
														            
														      					}             	
                	
																		});		
					                        	 						
					       					                        	 						
					                        	 					}else{
					                        	 						 
					                        	 						if(imagesStore.getCount()>0){
					                        	 							//alert(imagesStore.getAt(0).data['caption']);
					                        	 							form.getForm().doAction('submit',{
                      														waitMsg:'正在保存数据，请稍候。',
                      														waitTitle:'提示',
                     														method:'POST',
                     														timeout: 600000,
                      														url:'BatchDownLoadFiles',
                      														params:{                
														                		
                      															wp:encodeURIComponent(wp),
														                		waterm_name:encodeURIComponent(imagesStore.getAt(0).data['caption']),
										                						items:Ext.encode(items)
														                	},
														      					success:function(form,action){
														      						//alert("1");
														           					//var obj =Ext.decode(res.responseText);
														           					//alert(action.result.path);
														 		   					window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   

														            
														      					},
														      					failure:function(form,action){
														      						//alert("1");
														           					//var obj =Ext.decode(res.responseText);
														           					//alert(action.result.path);
														 		   					//window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   
														      						Ext.Msg.alert("提示",action.result.message);
														            
														      					}
																		}); 
					                        	 							
					                        	 							
					                        	 						}
					                        	 						
					                        	 					}
														    		
														    		//Ext.Msg.hide();
														    		//win.close();
														    		//myMask.hide();
														    		}
														    		
														    		
														    	}
														    },{
														    	xtype: 'button',
														    	name:'cancel',
														    	text:'取消',
														    	handler:function(){
														    		win.close();
														    	}
														    }]
														});
														win.show();
				            					            		 
				            		
				            	}
				            }
				        }]
						
					}]
		});	
		
	//Ext.Msg.hide();	
	});
</script>	
	

</head>
<body>

</body>
</html>