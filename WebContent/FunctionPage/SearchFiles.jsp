<%@page import="com.fuwa.ypq.model.Department"%>
<%@page import="com.fuwa.ypq.model.FWUser"%>
<%@page import="java.net.URLDecoder"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB18030"%>
<%

String path =request.getContextPath();

String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//out.print("item_id:"+request.getParameter("item_id"));

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
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>

<script type="text/javascript">
	Ext.onReady(function () {
		
		//Ext.Msg.wait('提示','正在处理数据，请稍候');
		Ext.QuickTips.init();
		
	

	
		
		var have_old_code = 0;
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
 		        {"name":"左下"},
 		        {"name":"右上"},
 		          {"name":"右下"}
 		    ],
 		    autoLoad : true
 		});
		var wpstoree = Ext.create('Ext.data.Store', {
 		    fields: ['name'],
 		    data : [  
 		        {"name":"左上"},
 		        {"name":"左下"},
 		         {"name":"右上"},
 		          {"name":"右下"}
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
				{name: 'item_id_new',type: 'string'},
				{name: 'item_id_old',type: 'string'},
	            {name: 'item_name',type: 'string'},
				{name: 'object_code',type: 'string'},
	            {name: 'item_revision',type: 'string'},
	            {name: 'remark',type: 'string'},
	            {name: 'dataset_name',type: 'string'},
	            {name: 'dataset_type',type: 'string'},
	            {name: 'child_id',type: 'string'},
	            {name: 'child_rev_id',type: 'string'}
	        ]  
	    });
		
		var item_store = Ext.create('Ext.data.Store', {
	 		storeId : 'itemStore',
	 		fields : [ 'searchType','item_id' , 'item_id_new','item_id_old','item_name','object_code','item_revision','remark','dataset_name','dataset_type','child_id','child_rev_id'],
	 		model : 'Item',
	 		proxy : {	 			
	 			type : 'ajax',
	 			
	 			timeout:6000000,
	 			url : '"GetMySearchItem"',
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
					var item_id_new = form.getForm().findField("item_id_new").getValue();
					var item_id_old = form.getForm().findField("item_id_old").getValue();
	  	        	var item_name = form.getForm().findField("item_name").getValue();
					var object_code = form.getForm().findField("object_code").getValue();
	  	        	var item_revision = form.getForm().findField("item_revision").getValue();
	  	        	var query_type = form.getForm().findField("query_type").getValue();
	  	        	//alert(ftcombo.getValue());
	  	            var params = {  
	  	                //参数  
						have_old_code:encodeURIComponent(have_old_code),
	  	                item_id:encodeURIComponent(item_id),
					    item_id_new:encodeURIComponent(item_id_new),
						item_id_old:encodeURIComponent(item_id_old),
	  	             	item_name:encodeURIComponent(item_name),
						object_code:encodeURIComponent(object_code),
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
		
		
	  Ext.define('typeModel', {  
        extend: 'Ext.data.Model',  
        proxy: {  
            type: 'ajax',  
            reader: 'json'  
        },  
        fields: [  
            {name: 'type_id',type: 'string'},
             {name: 'type_name',type: 'string'},
              {name: 'have_old_code',type: 'boolean'},
              
              
              {name: 'bom_search',type: 'boolean'},
             {name: 'material_search',type: 'boolean'},
              {name: 'parent_search',type: 'boolean'}
        ]  
    });
	
	var type_store = Ext.create('Ext.data.Store', {
		storeId : 'type_store',
		fields : [ 'type_id','type_name','have_old_code','bom_search','material_search','parent_search'],
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
		listeners : { 
			datachanged:function ( store , eOpts )  {
				
				if(getQueryString("flag")=="1") {
					//alert("find");
					getRequest();
					
				}
				
			}
		},
		autoLoad : true

	});
	
	
		
		
		var btnSearch = function(){
			var form = Ext.getCmp("search_form");
			
			var item_id = form.getForm().findField("item_id").getValue();
			var item_id_new = form.getForm().findField("item_id_new").getValue();
			var item_id_old = form.getForm().findField("item_id_old").getValue();
			
			if(have_old_code ==0){
				msg = Ext.Msg.alert("提示","请选择文件类型");
				return;
			}
			
			if(have_old_code ==1&&item_id_new==""&&item_id_old==""){
				msg = Ext.Msg.alert("提示","请填写图号或旧图号");
				return;
			}
			
			if(have_old_code ==1&&item_id_new!=""&&item_id_old!=""){
				msg = Ext.Msg.alert("提示","图号和旧图号不能同时填写");
				return;
			}
			
			
			if(have_old_code==2&&item_id==""){
				msg = Ext.Msg.alert("提示","请填写文件名！");
				return;
			}
			
			if(form.getForm().isValid()){
				//alert(form.getForm().getValues());
				//item_store.baseParams= form.getForm().getValues();
				//Ext.apply(item_store.baseParams, {item_id:'123'});
				item_store.reload({
					callback:function(r,o,s){
						if(s){
							if(getQueryString("flag")!="1")
							window.parent.loadRecord();
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
		
			

       var item_grid = Ext.create('Ext.grid.Panel', {
		                id:'item_grid',
						store:item_store,
						
						selModel: {
					        injectCheckbox: 0,
					        mode: "MULTI",     //"SINGLE"/"SIMPLE"/"MULTI"
					        checkOnly: true     //只能通过checkbox选择
					    },
					    selType: "checkboxmodel",
					    split : true,

						columns : [
						
						          {  xtype: "rownumberer",
						        	 text:'序号',
						        	 align:'left',
						        	 width:50
						        	 },{
										xtype:'actioncolumn',//这里就是放置按钮的地方
										width:150,
										text:'功能',									
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
														
														var nodePath=record.get("nodePath");
														var filePath=record.get("filePath");
														//alert("filePath-->"+filePath);
														var dataset_type = record.get("dataset_type");
												        var searchType=record.get("searchType");
														var item_id=record.get("item_id");
														var item_id_new=record.get("item_id_new");
														var item_name=record.get("item_name");
														var item_rev=record.get("item_revision");
														var dsName = record.get("dataset_name");
														//alert(dataset_type);
														/*  if(dataset_type=="MSExcel"||dataset_type=="MSExcelX") {
																 // alert("preparing");
																  getExcel("SaveFileto",nodePath,filePath,dataset_type,"nowatermark",searchType,item_id,item_name,item_rev,"左上","");  
																  return;
														}  */
														Ext.Msg.wait('提示','正在处理数据，请稍候'); 
							
														
														
														//alert(searchType+item_name);
														Ext.Ajax.request({
															
															timeout: 600000,
															url:'PreviewFile',
															params:{                
																nodePath:encodeURIComponent(nodePath),
																filePath:encodeURIComponent(filePath),
																dataset_type:encodeURIComponent(dataset_type),
																searchType:encodeURIComponent(searchType),
																item_id:encodeURIComponent(item_id),
																item_id_new:encodeURIComponent(item_id_new),
																item_name:encodeURIComponent(item_name),
																item_rev:encodeURIComponent(item_rev)
															}, 
															method:'POST',
															success:function (response){
																Ext.Msg.hide();
																
																var success = Ext.decode(response.responseText).flag;
																var resultPath = Ext.decode(response.responseText).resultPath;
																//alert(success+"--->"+resultPath);
																var b = new Base64();
																var str1 = b.encode(resultPath);
																var str2 = b.encode(item_id);
																var str3 = b.encode(item_name);
																var str4 = b.encode(item_rev);
																var str5 = b.encode(dataset_type);
																var str6 = b.encode(filePath);
																var str7 = b.encode(dsName);
																var str8 = b.encode(searchType);
															
															 
																
																//alert(success+"-->"+'<%=basePath%>FlexPaperSWF/SWF1.jsp?rp='+str1+'&id='+str2+'&name='+str3+'&rev='+str4+'&dst='+str5+'&fp='+str6+'&dsn='+str7+'&cp='+cp+'&cd='+cd);
															
																if(success){
																	 window.open('<%=basePath%>FlexPaperSWF/PreviewFrame.jsp?'+
																			 'rp='+str1+
																			 '&id='+str2+
																			 '&name='+str3+
																			 '&rev='+str4+
																			 '&dst='+str5+
																			 '&fp='+str6+
																			 '&dsn='+str7+
																			 '&cp='+cp+
																			 '&cd='+cd+
																			 '&type='+str8);
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
															//alert(filePath);
															var dataset_type = record.get("dataset_type");
															var item_id=record.get("item_id");
															 var searchType=record.get("searchType");
															var item_id_new=record.get("item_id_new");
														    var item_name=record.get("item_name");
														    var item_rev=record.get("item_revision");
															var dsName = record.get("dataset_name");
																												
															//////////////////////////////////////////////////user
															//alert(imagesStore.getAt(0).get("src"));
															//alert("hello");
															/*   if(dataset_type=="MSExcel"||dataset_type=="MSExcelX") {
																 // alert("preparing");
																  getExcel("SaveFileto",nodePath,filePath,dataset_type,"nowatermark",searchType,item_id,item_name,item_rev,"左上","");  
																  return;
															  }  */
																  
															
															
															var form = Ext.create('Ext.form.Panel', {  
																bodyPadding: 10,
																defaultType: 'combobox',
																
																items: [
																 {
																		fieldLabel: '水印位置',
																		name: 'watermark_pos' ,
																		labelAlign: 'right',
																		labelWidth: 90,
																		store: wpstoree,
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
																				xtype:'dataview',
																				id:'images-view',
																				
																				
																			
																				store: imagesStore,
																				tpl: new Ext.XTemplate(
				  //class="thumb-wrap"                                                      
																							'<tpl for=".">',
																							'<div style="margin-bottom: 20px;" class="thumb-wrap" >',
																							'<div class="thumb"><img src="<%=basePath%>{src}"  title="{caption}"></div>',
																							'<span class="x-editable">{caption}</span></div>',
																						'</tpl>',
																						'<div class="x-clear"></div>'
																						
																						
					
																				), 
																				height: 280,
															         
																				autoScroll : true,
																				border: 3,
																				style: {
																					/* borderColor: '#76ba53', */
																					borderColor: '#ff0000',
																					borderStyle: 'solid'
																					},			
																				trackOver: true,
																				itemSelector: 'div.thumb-wrap',
																				selectedItemCls :'imgselect',
																				multiSelect:true,
																				//itemSelector : 'thumb-wrap',
		
																				//overItemCls : 'print',
																				//autoScroll : true,
																				emptyText: 'No images available'
																				
																				}]
																	}
																]
															});
															 //  Ext.getCmp("images-view").getSelectionModel().selectAll();  
															
															var win = Ext.create('Ext.window.Window', {
																title: '打印配置',
																height: 400,
																width: 360,				   
																buttonAlign: 'center',		    
																modal: true,
																layout:'fit',
																closable: true,													    
																items: form,
																autoScroll : true,
																
																buttons: [{
																	xtype: 'button',
																	name:'ok',
																	text:'预览或打印',
																	handler:function(){
																		if(form.getForm().isValid()){
																			//win.hide();
																			//Ext.Msg.wait('提示','正在处理数据，请稍候'); 
																											
																		
																		/* var printer_name = form.getForm().findField("printer_name").getValue();
																		var ps = form.getForm().findField("page_size").getValue();
																		var po = form.getForm().findField("page_orientation").getValue(); */
																		var wp = form.getForm().findField("watermark_pos").getValue();
																		
																		
																		var sm=Ext.getCmp("images-view").getSelection(); // 获得grid的SelectionModel
																		
																		
																		//Ext.getCmp("images-view").getSelectionModel().selectAll();  
																		
																		var tmpStr = "";
																		var tmpArray = [];

																		for(var i =0; i< sm.length;i++) {
																			tmpStr += sm[i].get("caption")+",";
																			tmpArray.push(sm[i].get("caption"));
																		}
																		//alert(tmpStr);
																		
																		//return;
																		if(sm!=null&&sm.length>0){
																			//alert(sm[0].get("caption"));
																			
																			form.getForm().submit({
																				waitMsg:'正在保存数据，请稍候。',
																				waitTitle:'提示',
																				method:'POST',
																				timeout: 600000,
																				url:'SaveFileto',
																				params:{                
																					nodePath:encodeURIComponent(nodePath),
																					filePath:encodeURIComponent(filePath),
																					dataset_type:encodeURIComponent(dataset_type),
																					/* printer_name:encodeURIComponent(printer_name), */
																					waterm_name:encodeURIComponent(tmpArray),
																					searchType:encodeURIComponent(searchType),
																					item_id:encodeURIComponent(item_id),
																					item_name:encodeURIComponent(item_name),
																					item_rev:encodeURIComponent(item_rev),
																					/* ps:encodeURIComponent(ps),
																					po:encodeURIComponent(po), */
																					wp:encodeURIComponent(wp)
																					
																				},success:function(form,action){
																						
																						//var obj =Ext.decode(res.responseText);
																						//alert(action.result.path);
																						
																					/* 	var overDownload = action.result.overDownload;
																						if(overDownload){
																							Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
																						}
																						 */
																						// Ext.Msg.alert("提示","执行到前一步了！！！");
																						 var a=action.result.path;
																						  var d=dsName+'_'+dataset_type;
																						 // alert("<%=basePath%>HFTempFiles/"+action.result.path);
																						<%--   if(dataset_type=="MSExcel"||dataset_type=="MSExcelX") {
																						
																							StartExcel("<%=basePath%>HFTempFiles/"+action.result.path);
																						 } else { --%>
																							 window.open('<%=basePath%>FunctionPage/PdfPrint.jsp?'+
																									 'filename='+encodeURIComponent(a)+
																									 '&dsname='+encodeURIComponent(d));
																						 //}
																						
																						//alert('<%=basePath%>FunctionPage/PdfPrint.jsp?filename='+a+'&inputPath='+a+'&dsname='+d);
																						  
																					/* 	window.location.href = "downloadprint?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));    */

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
																				url:'SaveFileto',
																				params:{                
																					nodePath:encodeURIComponent(nodePath),
																					filePath:encodeURIComponent(filePath),
																					dataset_type:encodeURIComponent(dataset_type),
																					/* printer_name:encodeURIComponent(printer_name), */
																					waterm_name:encodeURIComponent("null"),
																					searchType:encodeURIComponent(searchType),
																					item_id:encodeURIComponent(item_id),
																					item_name:encodeURIComponent(item_name),
																					item_rev:encodeURIComponent(item_rev),
																				/* 	ps:encodeURIComponent(ps),
																					po:encodeURIComponent(po), */
																					wp:encodeURIComponent(wp)
																				},
																			success:function(form,action){
																						//alert("1");
																						//var obj =Ext.decode(res.responseText);
																						//alert(action.result.path);
																					/* 	var overDownload = action.result.overDownload;
																						if(overDownload){
																							Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
																						} */
																						var a=action.result.path;
																						  var d=dsName+'_'+dataset_type;
																						
																						  window.open('<%=basePath%>FunctionPage/PdfPrint.jsp?filename='+a+'&inputPath='+a+'&dsname='+d);
																						/* window.location.href = "downloadprint?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));    */

																						
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
														var searchType=record.get("searchType");
															
															/*  if(dataset_type=="MSExcel"||dataset_type=="MSExcelX") {
																 // alert("download preparing");
																  getExcel("SaveFile",nodePath,filePath,dataset_type,"nowatermark",searchType,item_id,item_name,item_rev,"左上",dsName);  
																  return;
														      }  */
															var form = Ext.create('Ext.form.Panel', {  
																bodyPadding: 10,
																defaultType: 'combobox',													    
																items: [{
																	
																	fieldLabel: '水印位置',
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
																				height: 280,
																				autoScroll : true,
																				border: 3,
																				selectedItemCls :'imgselect',
																				multiSelect:true,
																				style: {
																					borderColor: '#ff0000',
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
																height: 400,
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
																											var tmpStr = "";
																		var tmpArray = [];

																		for(var i =0; i< sm.length;i++) {
																			tmpStr += sm[i].get("caption")+",";
																			tmpArray.push(sm[i].get("caption"));
																		}
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
																					
																					waterm_name:encodeURIComponent(tmpArray),
																					searchType:encodeURIComponent(searchType),
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
																					searchType:encodeURIComponent(searchType),
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
													]
								 }, {
									itemId: 'item_id',
									text : '文件号',
									dataIndex : 'item_id',
									width:160
								}, {
									itemId: 'item_id_new',
									text : '图号',
									dataIndex : 'item_id_new',
									width:160
								}, {
									itemId: 'item_id_old',
									text : '旧图号',
									dataIndex : 'item_id_old',
									width:160
								},{
									text : '名称',
									dataIndex : 'item_name',
									width:160
								},{
									text : '版本',
									dataIndex : 'item_revision'
								},{
									text : '数据集名称',
									dataIndex : 'dataset_name',
									width:160
								},{
									text : '数据集类型',
									dataIndex : 'dataset_type'
								}, {
									itemId: 'object_code',
									text : '物料编码',
									dataIndex : 'object_code',
									width:160
								},{
									itemId: 'remark',
									text : '描述',
									dataIndex : 'remark',
									width:160
								}, {
									text : '查找类型',
									dataIndex : 'searchType',
									width:160
								}, {
									text : '子级图号',
									dataIndex : 'child_id',
									width:160
								},{
									text : '子级版本',
									dataIndex : 'child_rev_id'
								}],
								
						autoHeight:true ,
						autoWidth:true ,
						tbar:[{ 
							xtype:'button',
							id:'pldl',
				            text : '批量下载' ,
				            icon:'<%=basePath%>icons/select_download_16.png',
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
																			height: 280,
    																		autoScroll : true,
    																		border: 3,
																			style: {
    																			borderColor: '#ff0000',
    																			borderStyle: 'solid'
																				},			
																			trackOver: true,
         																	itemSelector: 'div.thumb-wrap',
         																	selectedItemCls :'imgselect',
																	        multiSelect:true,
    																		emptyText: 'No images available'
    																		}]
														        }
														    ]
														});
														
														var win = Ext.create('Ext.window.Window', {
														    title: '下载配置',
														    height: 400,
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
														    		
														    		var tmpStr = "";
																    var tmpArray = [];

																    for(var i =0; i< sm.length;i++) {
																	   tmpStr += sm[i].get("caption")+",";
																	   tmpArray.push(sm[i].get("caption"));
																    }
																    
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
														                		waterm_name:encodeURIComponent(tmpArray),
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
				        },{
							xtype:'button',
							id:'btnBomSearch',
				            text : 'BOM查询' ,
				            icon:'<%=basePath%>icons/bomview_16.png',  
				            handler:function(){
								  var item_grid = Ext.getCmp("item_grid");
								  var sm = item_grid.getSelection(); // 获得grid的SelectionModel									
					                        	 if(sm!=null&&sm.length == 1){					                        	     
					                        	    var searchType = sm[0].get("searchType");
					                        	    var item_id = sm[0].get("item_id");
					                        	    var item_revision = sm[0].get("item_revision");
					                        	    
					                        		Ext.Msg.wait('提示','正在获取数据，请稍候'); 
					                        	    
					                        	      Ext.Ajax.request({
											     			url:'GetBOM',
											                params:{                
											                  // addModels:Ext.encode(aclAddModels),
											                  // ft:encodeURIComponent(ftcombo)
											                  searchType:searchType,
											                  item_id:item_id,					
											                  item_rev_id:item_revision
											                }, 
											                method:'POST',
											                success:function (response){
											                	 //Ext.getBody().unmask();
											                	 // alert(response.responseText);
											                	 Ext.Msg.hide();
											                	var success = Ext.decode(response.responseText).success;	
											                	var gcqbomModel = Ext.decode(response.responseText).gcqbomModel;
											                	var qdqbomModel = Ext.decode(response.responseText).qdqbomModel;
											                	
											                	if(gcqbomModel !=null ) {
											                		     var item_id = gcqbomModel.item_id;
																	     var item_rev_id = gcqbomModel.item_rev_id;
																	     var item_id_old = gcqbomModel.item_id_old;
																	     var client_object_code = gcqbomModel.client_object_code;
																	     var item_name = gcqbomModel.item_name;
																	     var material = gcqbomModel.material;
																	     var unit = gcqbomModel.unit;
																	     var quantity = gcqbomModel.quantity;
																	     var single_weight = gcqbomModel.single_weight;
																	     var total_weight = gcqbomModel.total_weight;
																	     var part_type = gcqbomModel.part_type;
																	     var remark = gcqbomModel.remark;
											                		     window.open('<%=basePath%>FunctionPage/GCQBOM.jsp?'+
											                				 'item_id='+encodeURIComponent(item_id)+
											                				 '&item_rev_id='+encodeURIComponent(item_rev_id)+
											                				 '&item_id_old='+encodeURIComponent(item_id_old)+
											                				 '&client_object_code='+encodeURIComponent(client_object_code)+
											                				 '&item_name='+encodeURIComponent(item_name)+
											                				 '&material='+encodeURIComponent(material)+
											                				 '&unit='+encodeURIComponent(unit)+
											                				 '&quantity='+encodeURIComponent(quantity)+
											                				 '&single_weight='+encodeURIComponent(single_weight)+
											                				 '&total_weight='+encodeURIComponent(total_weight)+
											                				 '&part_type='+encodeURIComponent(part_type)+
											                				 '&remark='+encodeURIComponent(remark));
											                	}
											                	if(qdqbomModel !=null ) {
											                		 var item_id = qdqbomModel.item_id;
																     var item_rev_id = qdqbomModel.item_rev_id;
																     var axle_type = qdqbomModel.axle_type;
																     var specif = qdqbomModel.specif;
																     var item_name = qdqbomModel.item_name;
																     var material = qdqbomModel.material;
																     var unit = qdqbomModel.unit;
																     var quantity = qdqbomModel.quantity;
																     var weight = qdqbomModel.weight;
																     var class_type = qdqbomModel.class_type;
																     var phase = qdqbomModel.phase;
																	 var client_name = qdqbomModel.client_name;
																	// alert(client_name);
																     var client_no = qdqbomModel.client_no;
																	 var remark = qdqbomModel.remark;
											                		 window.open('<%=basePath%>FunctionPage/QDQBOM.jsp?'+
											                				 'item_id='+encodeURIComponent(item_id)+
											                				 '&item_rev_id='+encodeURIComponent(item_rev_id)+
											                				 '&axle_type='+encodeURIComponent(axle_type)+
											                				 '&specif='+encodeURIComponent(specif)+
											                				 '&item_name='+encodeURIComponent(item_name)+
											                				 '&material='+encodeURIComponent(material)+
											                				 '&unit='+encodeURIComponent(unit)+
											                				 '&quantity='+encodeURIComponent(quantity)+
											                				 '&weight='+encodeURIComponent(weight)+
											                				 '&class_type='+encodeURIComponent(class_type)+
											                				 '&phase='+encodeURIComponent(phase)+
											                				 '&client_name='+encodeURIComponent(client_name)+
											                				 '&client_no='+encodeURIComponent(client_no)+
											                				 '&remark='+encodeURIComponent(remark)
											                				 );
											                	}
											                	
											                	
											                	if(!success) {
											                		Ext.Msg.alert("提示",Ext.decode(response.responseText).message);
											                	}
											                },
											            	failure:function(response){
											            		//Ext.getBody().unmask();
											            		 
											            		Ext.Msg.alert("提示",Ext.decode(response.responseText).message);
											            	}
											                
											     		 }); 
												 } else {
													 Ext.Msg.alert("提示","只能单选");
												 }
							}
						},{
							xtype:'button',
							id:'btnMaterialSearch',
				            text : '物料属性查询' ,
				             icon:'<%=basePath%>icons/detail_16.png', 
				            handler:function(){
								   var item_grid = Ext.getCmp("item_grid");
								  var sm = item_grid.getSelection(); // 获得grid的SelectionModel									
					                        	 if(sm!=null&&sm.length == 1){					                        	     
					                        	    var searchType = sm[0].get("searchType");
					                        	    var item_name = sm[0].get("item_name");
					                        	    var item_id = sm[0].get("item_id");
					                        	    var item_revision = sm[0].get("item_revision");
					                        	    window.open('<%=basePath%>FunctionPage/MaterialAttrs.jsp?'+
					                        	    		'item_id='+encodeURIComponent(item_id)+
					                        	    		'&item_revision='+encodeURIComponent(item_revision)+
					                        	    		'&item_name='+encodeURIComponent(item_name)+
					                        	    		'&searchType='+encodeURIComponent(searchType));
					                        	   
												 } else {
													 Ext.Msg.alert("提示","只能单选");
												 }
							
							}
						},{
							xtype:'button',
							id:'btnParentSearch',
				            text : '零部件反查' ,
				            icon:'<%=basePath%>icons/parent_16.png', 
				            handler:function(){
								    var item_grid = Ext.getCmp("item_grid");
								  var sm = item_grid.getSelection(); // 获得grid的SelectionModel									
					                        	 if(sm!=null&&sm.length == 1){					                        	     
					                        	    var item_name = sm[0].get("item_name");
					                        	    var item_id = sm[0].get("item_id");
					                        	    var item_revision = sm[0].get("item_revision");
					                        	      window.open('<%=basePath%>FunctionPage/ParentMaterial.jsp?'+
					                        	    		  'item_id='+encodeURIComponent(item_id)+
					                        	    		  '&item_revision='+encodeURIComponent(item_revision)+
					                        	    		  '&item_name='+encodeURIComponent(item_name));
												 } else {
													 Ext.Msg.alert("提示","只能单选");
												 }
							
							}
						}]
						
					
		});	
		
		
		
		 
		var center_panel = Ext.create('Ext.panel.Panel',{
			layout:'fit',
			items:[item_grid]
		});
		
		
		
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
					    items: [ {
					        	xtype:'combo',
					            fieldLabel: '查找类型',
					            name: 'query_type' , 		            
					            labelAlign: 'right',
				                labelWidth: 70,
				                store: type_store,
				                queryMode: 'remote',
				        	    displayField: 'type_name',
				        	    valueField: 'type_name',				        	    
				        	    selectOnFocus:true,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        		    }
	        	          
	                    			    },
	                    			     select: {
								            		   fn:function(combo,record,opts) {  
													              item_store.removeAll();
													               Ext.getCmp("item_id").setValue("");
													               Ext.getCmp("item_id_new").setValue("");
			                                                      Ext.getCmp("item_id_old").setValue("");
			                                                      Ext.getCmp("item_name").setValue("");
			                                                     Ext.getCmp("item_revision").setValue("");
			                                                      Ext.getCmp("object_code").setValue("");
																//alert(record.data.have_old_code);
																Ext.getCmp("item_name").show();
																Ext.getCmp("item_revision").show();
															
								            			   if(record.data.bom_search==true) {
								            				   Ext.getCmp("btnBomSearch").show();
								            			   } else {
								            				   Ext.getCmp("btnBomSearch").hide();
								            			   }
								            			   
								            			   if(record.data.material_search==true) {
								            				    Ext.getCmp("btnMaterialSearch").show();
								            			   } else {
								            				    Ext.getCmp("btnMaterialSearch").hide();
								            			   }
								            			     
								            			    if(record.data.parent_search==true) {
								            				   Ext.getCmp("btnParentSearch").show();
								            			   } else {
								            				   Ext.getCmp("btnParentSearch").hide();
								            			   }
																
								            			   //alert(record.data.type_name+"/"+record.data.have_old_code+"/"+record.data.bom_search);
															  if(record.data.have_old_code==true) {
																  have_old_code = 1;
																  Ext.getCmp("item_id").hide();
																	Ext.getCmp("item_id_new").show();
																   Ext.getCmp("item_id_old").show();
																	Ext.getCmp("object_code").show();
																	
																	item_grid.down('#item_id').hide();
																	item_grid.down('#item_id_new').show();
																	item_grid.down('#item_id_old').show();
																	item_grid.down('#object_code').show();
																	
																	
																  
															  } else {
																 have_old_code = 2;
																  Ext.getCmp("item_id_new").hide();
																   Ext.getCmp("item_id_old").hide();
																  Ext.getCmp("object_code").hide();
																   Ext.getCmp("item_id").show();
																   
																   
																   item_grid.down('#item_id_new').hide();
																	item_grid.down('#item_id_old').hide();
																	item_grid.down('#object_code').hide();
																   	item_grid.down('#item_id').show();
																	
																  
																  
															   }
															 
															if(record.data.type_name=="驱动桥零件图（含总图）") {
								            				item_grid.down('#remark').show();
								            				item_grid.down('#item_id_old').hide();
								            				 Ext.getCmp("item_id_old").hide();
								            			   } else {
								            				 item_grid.down('#remark').hide();
								            			   }
								            			   				            			
								            	   		}
			     				         },
			     				          
					        	        scope: this
					        	        
					        	        
	                   			} 
					        },{
				            	fieldLabel: '文件名(*)',
				            	id :'item_id',
				            	name: 'item_id' ,
				            	labelAlign: 'right',
			                	labelWidth: 70,
			                	allowBlank: true,	                
				                blankText: '请填写文件名',
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        			}  
	                    				} // end of specialkey  
	                   			} // end of listeners
				        	},
				        	{
				            	fieldLabel: '图号(*)',
				            	id :'item_id_new',
				            	name: 'item_id_new' ,
				            	labelAlign: 'right',
			                	labelWidth: 70,
			                	allowBlank: true,	                
				                blankText: '请填写图号',
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        			}  
	                    				} // end of specialkey  
	                   			} // end of listeners
				        	},
				        	{
				            	fieldLabel: '旧图号(*)',
				            	id :'item_id_old',
				            	name: 'item_id_old' ,
				            	labelAlign: 'right',
			                	labelWidth: 70,
			                	allowBlank: true,	                
				                blankText: '请填写旧图号',
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        		 	}  
	                    				} // end of specialkey  
	                   			} // end of listeners
				        	},
					        {
					            fieldLabel: '名称',
					            id :'item_name',
					            name: 'item_name' ,
					            labelAlign: 'right',
				                labelWidth: 70,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) { //如果点击enter执行搜索 
		                          
		                        				btnSearch();
	
		                        	   		}  
	                    				} // end of specialkey  
	                   			} // end of listeners
					        },
					       
					        {
					            fieldLabel: '版本',
					             id :'item_revision',
					            name: 'item_revision' , 		            
					            labelAlign: 'right',
				                labelWidth: 70,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        		     }  
	                    				} 
	                   			} 
					        },
					         {
					            fieldLabel: '物料编码',
					             id :'object_code',
					            name: 'object_code' , 		            
					            labelAlign: 'right',
				                labelWidth: 70,
				                listeners : {  
	                    				specialkey : function(field, e) {  

	                             
		                        			if (e.getKey() == Ext.EventObject.ENTER) {  
		                          
		                        				btnSearch();
	
		                        		   }  
	                    		    	} 
	                   			} 
					        }
					    ],
					    buttons: [{
					    	xtype: 'button',
					    	name:'search_file',
					    	text:'搜索',
					    	handler: btnSearch
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
						//xtype: 'grid',
						xtype : 'container',
						layout: 'fit',
						items : [center_panel]
					}]
		});	
		
	//Ext.Msg.hide();

		 
	    Ext.getCmp("item_id").hide();
		Ext.getCmp("item_revision").hide();
		Ext.getCmp("item_name").hide();
		Ext.getCmp("item_id_new").hide();
		Ext.getCmp("item_id_old").hide();
		Ext.getCmp("object_code").hide();  
				
		if(getQueryString("flag")=="1") {
	     var form = Ext.getCmp("search_form");
		 form.hide();
        } else {
        	checkOverDownload();
        }
		
      function getRequest() {	 	
		var item_id = getQueryString("item_id");
		var item_rev = getQueryString("item_rev");
		//alert(item_id+":"+item_rev+":");
		//Ext.log("item_id:"+item_id);
		if(item_id!="null"&&item_id!="") {
			
			var type_name = getQueryString("type_name");
			var form = Ext.getCmp("search_form");
			
			form.getForm().findField("query_type").setValue(type_name);
			var count = type_store.getCount();
			for(var i=0;i<count;i++) {
				if(type_store.getAt(i).get("type_name") == type_name) {
					//alert(type_store.getAt(i).get("have_old_code")=="false");
					
					if(type_store.getAt(i).get("have_old_code") == false) 
					have_old_code = 2;
					else
					have_old_code = 1;	
					break;
					
				}
					
				
					
			}
		Ext.getCmp("item_id").show();
		Ext.getCmp("item_revision").show();
		Ext.getCmp("item_name").show();
		Ext.getCmp("item_id_new").show();
		Ext.getCmp("item_id_old").show();
		Ext.getCmp("object_code").show(); 
			//alert(have_old_code);
		    if(have_old_code == 2) {
		    	form.getForm().findField("item_id").setValue(item_id);
		    	 Ext.getCmp("item_id_new").hide();
		         Ext.getCmp("item_id_old").hide();
		         Ext.getCmp("object_code").hide();
		         item_grid.down('#item_id_old').hide();
		         item_grid.down('#item_id_new').hide();
		         item_grid.down('#object_code').hide();
		    }
			
			 if(have_old_code == 1) {
				 Ext.getCmp("item_id").hide();
				 item_grid.down('#item_id').hide();
				 form.getForm().findField("item_id_new").setValue(item_id);
			 }
			 
			 if(type_name=="驱动桥零件图（含总图）") {
						
					item_grid.down('#remark').show();
					item_grid.down('#item_id_old').hide();
					} else {
					item_grid.down('#remark').hide();
					
					}
			
		    form.getForm().findField("item_revision").setValue(item_rev);
		  // Ext.Msg.alert("info",item_id+"/"+item_rev);
			btnSearch();
			
			Ext.Ajax.request({															
								timeout: 600000,
								url:'CheckItemRevUpdate',
								params:{                
								type_name:encodeURIComponent(type_name),
								item_id:encodeURIComponent(item_id),
								item_rev:encodeURIComponent(item_rev)
																
								}, 
								method:'POST',
								success:function (response){
								Ext.Msg.hide();
																
								var success = Ext.decode(response.responseText).success;
								var message = Ext.decode(response.responseText).message;
								
														
								if(success){
								Ext.Msg.alert("提示",message);
								
								 }
							   },
							   failure:function (response){
								Ext.Msg.hide();
																
								var success = Ext.decode(response.responseText).success;
								var message = Ext.decode(response.responseText).message;
								
														
								
								Ext.Msg.alert("提示","查询最新版本信息失败");
								 
							   }
								
							}); 
			
			
		} 
	 }
   
				function checkOverDownload() {
				var departName = '<%=session.getAttribute("current_depart")==null?"":((Department)session.getAttribute("current_depart")).getDepartment_name()%>';
				
			
				if(departName!="") {
					
					
					if(departName == "dba") {
						//alert(departName);
						Ext.Ajax.request({
																	
										timeout: 600000,
										url:'GetUserOverDownload',
										/* 	params:{                
										nodePath:encodeURIComponent(nodePath),
										filePath:encodeURIComponent(filePath),															
										},  */
										method:'POST',
										success:function (response){
		                                                              var success = Ext.decode(response.responseText).success;
																		var message = Ext.decode(response.responseText).message;
																     //   alert(message);
																   
																     if(success)
																    	 //  Ext.Msg.alert("info","hello<br/>kkk");
																     Ext.Msg.alert("提示",message);
															       }
																	
										}); 
					}
				}
		}
	 function getExcel(actionUrl,nodePath,filePath,dataset_type,waterm_name,searchType,item_id,item_name,item_rev,wp,dsName) {
		 //alert("hello");
		 //Ext.Msg.show("提示","获取中...");
		var ieVersion = IETester();
		if(actionUrl!="SaveFile" && ieVersion==false) {
		Ext.Msg.alert("提示","Excel文件,请用IE浏览器打开");
		//alert("请用IE浏览器打开");
		return;
		} else if(actionUrl!="SaveFile" && ieVersion>9){
		Ext.Msg.alert("提示","Excel文件,IE浏览器版本太高，请用低于IE10版本浏览器");
		//alert("IE浏览器版本太高，请用低于IE10版本浏览器");
		return;
		} 
			
		 Ext.Msg.wait('提示','获取中...'); 
		 Ext.Ajax.request({															
								timeout: 600000,
								url:actionUrl,
								params:{                
								nodePath:encodeURIComponent(nodePath),
								filePath:encodeURIComponent(filePath),
								dataset_type:encodeURIComponent(dataset_type),
								previewExcel:true,												
								waterm_name:encodeURIComponent("无水印"),
								searchType:encodeURIComponent(searchType),
								item_id:encodeURIComponent(item_id),
								item_name:encodeURIComponent(item_name),
								item_rev:encodeURIComponent(item_rev),
								wp:encodeURIComponent(wp)
																
								}, 
								method:'POST',
								success:function (response){
								Ext.Msg.hide();
								if(actionUrl=="SaveFile") {
									//alert("file result");
									var overDownload = Ext.decode(response.responseText).overDownload;
									if(overDownload){
									             Ext.Msg.alert("提示","今日下载量已经超出公司要求！！！");
									}
																						
									window.location.href = "download?filename="+encodeURI(encodeURI(Ext.decode(response.responseText).path))+"&inputPath="+encodeURI(encodeURI(Ext.decode(response.responseText).path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   
								} else	{
									//alert("<%=basePath%>HFTempFiles/"+Ext.decode(response.responseText).path);
									StartExcel("<%=basePath%>HFTempFiles/"+Ext.decode(response.responseText).path);
								}							
								
								 
							   },
							   failure:function (response){
								Ext.Msg.hide();
																
								var success = Ext.decode(response.responseText).success;
								var message = Ext.decode(response.responseText).message;
								
														
								
								Ext.Msg.alert("提示","获取Excel失败");
								 
							   }
								
							}); 
	 }
	 
	 
	 function IETester(userAgent){
			    var UA =  userAgent || navigator.userAgent;
			    if(/msie/i.test(UA)){
			        return UA.match(/msie (\d+\.\d+)/i)[1];
			    }else if(~UA.toLowerCase().indexOf('trident') && ~UA.indexOf('rv')){
			        return UA.match(/rv:(\d+\.\d+)/)[1];
			    }
			    return false;
    }
				
				
	});
		
   function getQueryString(key){//解决中文乱码
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return decodeURIComponent(result?decodeURIComponent(result[2]):null);
      }	
   
   function StartExcel(strFileName) {
			var oExcel;
			var oWorkbook;
			var strAllName;
			oExcel = new ActiveXObject("Excel.Application");
			//if(strFileName=null || strFileName="")
			//{return;
			//}
			strAllName=strFileName;
			oExcel.Workbooks.Open(strAllName);
			//oExcel.Workbooks.Open("file://ep-hgh/Top30销控表/TOP30招商续约销控表-上海.xls");
			oExcel.DisplayAlerts = false;
			oExcel.Visible = true;
			//oExcel.Quit();
			//oExcel = null;
			//防EExcel死进程的关键！！必须！！！downmoon严重声明
			idTmr = window.setInterval("Cleanup();",1000);
			}
			
			function Cleanup() {
			window.clearInterval(idTmr);
			CollectGarbage();
    }
   
	
</script>	
	

</head>
<body>

</body>
</html>