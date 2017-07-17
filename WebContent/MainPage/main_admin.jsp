<%@page import="com.fuwa.ypq.model.FWUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%

String path =request.getContextPath();

String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="ie-comp"> 
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" /> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin User</title>
<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">

       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
<script type="text/javascript">

     
     var loadRecord;
	function checkFile(o){  
    //��֤ͼƬ�ļ�������   
	    var img_reg = /\.([xX][lL][sS][xX]){1}/;  
	    if(!img_reg.test(o.value)){  
	        Ext.Msg.alert('��ʾ','�ļ����ʹ���,��ѡ����������ģ��Excel�ļ�(xlsx)');  
	        o.setRawValue('');  
	    }  
	    //ȡ�ؼ�DOM����  
	    /*
	    var field = document.getElementById('id_fileField');  
	    //ȡ�ؼ��е�inputԪ��   
	    var inputs = field.getElementsByTagName('input');  
	    var fileInput = null;  
	    var il = inputs.length;  
	    //ȡ��input ����Ϊfile��Ԫ��   
	    for(var i = 0; i < il; i ++){  
	        if(inputs[i].type == 'file'){  
	            fileInput = inputs[i];  
	            break;  
	        }  
	    }  
	    if(fileInput != null){  
	        var fileSize = getFileSize(fileInput);  
	        //�����ϴ�������1M���ļ� 
	  
	        if(fileSize > 1000){  
	            Ext.Msg.alert('��ʾ','�ļ�̫����ѡ��С��1M��ͼƬ�ļ���');  
	            picItem.setRawValue('');  
	        }  
	    } */ 
	} 

	Ext.onReady(function () {	
		
		
		var wpstore = Ext.create('Ext.data.Store', {
 		    fields: ['name'],
 		    data : [
 		        {"name":"����"},
 		        {"name":"����"},
 		        {"name":"����"},
 		        {"name":"����"}
 		    ],
 		    autoLoad : true
 		});
		
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
	 			url : 'GetWaterMark4WKZX',
	 			reader : {
	 					type : 'json',
						root:'images', //��Ӧaction����Ҫ����ʵ���ֶΣ�һ��Ϊlist  
			            success:'flag',  
			            total: 'total' ,
			            start: 'start',
			            limit: 'limit'
	 			}
	 		},
	 		autoLoad : true
		});
		
		//ֻ��leafΪtrue�Ľڵ��data��������븸�ڵ�չ���¼�������ͻ
	    var store = Ext.create("Ext.data.TreeStore", {
	    	id:"treeStore",
	        root:{
	            expanded:true,
	            children:[  {
                    text:"�����ļ�",
                    id:"select",
                    iconCls:'search_icon',
                    //iconCls:'item_icon',
                    data:"<%=basePath%>FunctionPage/SearchFiles.jsp?flag=0&item_id=''&item_rev=''&type_name=''",
                    leaf:true
                },{
                    text:"�û�����",
                    data:"<%=basePath%>FunctionPage/UserManagement.jsp",
                    iconCls:'user_icon',
                    leaf:true
                },{
                    text:"��֯��������",
                    data:"<%=basePath%>FunctionPage/OrganizationManagement.jsp",
                    iconCls:'site_icon',
                    leaf:true
                }, {
                    text:"Ȩ�޹���",
                    data:"<%=basePath%>FunctionPage/ACL.jsp",
                    iconCls:'acl_icon',
                    leaf:false,
                    children:[
                              {text:"����Ȩ��",
                               data:"<%=basePath%>FunctionPage/DepartACL.jsp",
                               iconCls:'acl_icon',
                               leaf:true                          	  
                              },
                              {text:"�û�Ȩ��",
                               data:"<%=basePath%>FunctionPage/UserACL.jsp",
                               iconCls:'acl_icon',
                               leaf:true                         	  
                              }
                             
                    ]
                }, {
                	id : 'lookOver',
                    text:"Ȩ�޲鿴",
                    iconCls:'acl_icon',
                    data:"<%=basePath%>FunctionPage/ACLQuery.jsp",
                    leaf:true
                }, {
                    text:"Ȩ�޵���", 
                    iconCls:'acl_icon',
                    leaf:true
                }, {
                    text:"Ȩ�޵���",   
                    iconCls:'acl_icon',
                    leaf:true
                },{
                	text:"��־����",
                    data:"<%=basePath%>FunctionPage/LogManagement.jsp",
                    iconCls:'log_icon',
                    leaf:true
                },{
                	text:"ӡ�¹���",
                    data:"<%=basePath%>FunctionPage/StampManagement.jsp",
                    iconCls:'log_icon',
                    leaf:true
                },{
                	text:"������������",  
                	iconCls:'special_download',
                    leaf:true
                },{
                    text:"���������洦��",                    
                    data:"<%=basePath%>FunctionPage/CacheManagement.jsp",
                    iconCls:'folder_clear_icon',
                    leaf:true
                },
                 {
                	id:'recordData',
                    text:"��ʷ��¼",   
                    iconCls:'history_icon',
                    leaf:false                          
                }
                ]
	        }
	    });
		
	    loadRecord=function loadRecordData() { 
            var recordNode=store.getNodeById('recordData');  
	        				Ext.Ajax.request({
									timeout: 600000,
									url:'GetRecord',
									method:'POST',
									success:function (response){ 
										//Ext.Msg.alert("info",response.responseText);
									//Ext.Msg.alert("info",Ext.decode(response.responseText).dateList[0]);
									//Ext.log(Ext.decode(response.responseText));
                                   recordNode.removeAll();  
                                
								    Ext.each(Ext.decode(response.responseText).recordList,
									 
									function(model){
										 var recordStr = model.item_id+"/"+model.item_rev;
										 var type_name = model.type_name;
									     var record_day=model.record_date.substring(0,10);
									    // Ext.Msg.alert("info",record_day);  
									 var dateNode=store.getNodeById(record_day);
									 
									 if(dateNode == null) {
										 recordNode.appendChild([{
											id:record_day,  
											text:record_day,
											iconCls:'clock_icon',
											leaf:false
										}]);
									 }
									 dateNode=store.getNodeById(record_day);
									
									dateNode.appendChild([{
											id:recordStr,  
											text:recordStr,
											data:type_name+"/"+recordStr,
											iconCls:'item_rev_icon',
											leaf:true
										}]);
										
																
											  
											});  
										
										
									},
									failure:function (response){
										 Ext.Msg.alert("��ʾ","��ȡ��ʷ��¼ʧ��!");
									}
	        					});
	        			recordNode.expand();
        }
	    
	    var treePanel = Ext.create("Ext.tree.Panel", {
	        store:store,
	        rootVisible:false,
	        lines : false,
	        
	        listeners:{
	        	 itemclick: {
	        		fn: function(view, record, item, index, e,obj){
	        			
	        			
	        			
	        		if(!record.data.leaf){
	        			 e.stopEvent;
	        			return;
	        		}
	        	
	        		if(record.data.text=="���������洦��"){
	        			
	        			//alert(record.data.text);
	        			Ext.Msg.confirm("��ʾ", "�Ƿ�Ҫ���һ��֮ǰ�Ļ�������?", function (btn) {
	        				if(btn=='yes'){
	        					//Ext.Msg.alert("������İ�ť�ǣ� " + btn);
	        					
	        					Ext.Ajax.request({
									timeout: 600000,
									url:'ClearCache',
									method:'POST',
									success:function (response){
										 Ext.Msg.alert("��ʾ","����ɹ�!");
										 //var filepath = Ext.decode(response.responseText).filename;
									},
									failure:function (response){
										 Ext.Msg.alert("��ʾ","���ʧ��!");
									}
	        					});
	        				}
                			
            			});
	        			
	        		}else if(record.data.text=="Ȩ�޵���"){
	        			Ext.Msg.wait('��ʾ','���ڴ������ݣ����Ժ�');
	        			//alert(record.data.text);
	        			Ext.Ajax.request({
							timeout: 600000,
							url:'ExportMyACL',
							method:'POST',
							success:function (response){
								Ext.Msg.hide();
								var filepath = Ext.decode(response.responseText).filename;
       							//Ext.Msg.alert('Success', 'Success');
       							window.location.href = "downloadXlsx?filename="+encodeURI(encodeURI(filepath))+"&inputPath="+encodeURI(encodeURI(filepath))+"&dsname="+encodeURI(encodeURI(filepath));   
								
							},
							failure:function (response){
								Ext.Msg.hide();
							}
    					});
	        			
	        		}else if(record.data.text=="Ȩ�޵���") {
	        			var fileselector = Ext.create('Ext.form.Panel', {
						    
						    width: 400,
						    bodyPadding: 10,
						    enctype: 'multipart/form-data',						    
						    items: [{
						        xtype: 'filefield',
						        id:'uploadFile', 
            					name: 'upload', 
						        fieldLabel: 'ѡ��ģ��',
						        labelWidth: 60,
						        msgTarget: 'side',
						        allowBlank: false,
						        anchor: '100%',
						        buttonText: 'ѡ��',
						        listeners: {  
	  	        					'change': function (field, value, options) {
	  	        						
	  	        						checkFile(field);
	  	        						//field.setRawValue('');//���ڴﵽĿ��
	  	        						//alert(value);
	  	        					}
						        }
						    }],
						
						    buttons: [{
						        text: 'ȷ��',
						        handler: function() {
						            var form = this.up('form').getForm();
						            if(form.isValid()){
						                form.submit({
						                	timeout: 600000,
						                    url: 'UpLoadACL',
						                    waitMsg: '�����ϴ�ģ��...',
						                    success: function(fp, action) {
						                    	Ext.Msg.alert('��ʾ','Ȩ�޵���ɹ�');
												win.close();
						                    }
										});
						            }
						        }
						    }]
						     
						});
	        			
	        		var win =	Ext.create('Ext.window.Window', {
						    title: 'Ȩ�޵���',
						    height: 200,
						    width: 400,
						    layout: 'fit',
						    items: fileselector
						});
	        		
	        		win.show();
	        			
	        			
	        		}else if(record.data.text=="������������"){
	        			
	        			var fileselector = Ext.create('Ext.form.Panel', {
						    
						    width: 400,
						    bodyPadding: 10,
						    enctype: 'multipart/form-data',						    
						    items: [{
						        xtype: 'filefield',
						        id:'uploadFile', 
            					name: 'upload', 
						        fieldLabel: 'ѡ��ģ��',
						        labelWidth: 60,
						        msgTarget: 'side',
						        allowBlank: false,
						        anchor: '100%',
						        buttonText: 'ѡ��',
						        listeners: {  
	  	        					'change': function (field, value, options) {
	  	        						
	  	        						checkFile(field);
	  	        						//field.setRawValue('');//���ڴﵽĿ��
	  	        						//alert(value);
	  	        					}
						        }
						    }],
						
						    buttons: [{
						        text: 'ȷ��',
						        handler: function() {
						            var form = this.up('form').getForm();
						            if(form.isValid()){
						                form.submit({
						                	timeout: 600000,
						                    url: 'SpecialBatchDownload',
						                    waitMsg: '�����ϴ�ģ��...',
						                    success: function(fp, action) {
						                        //Ext.Msg.alert('��ʾ',  action.result.msg );
						                    	//window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   
												
						                    	var form123 = Ext.create('Ext.form.Panel', {  
													bodyPadding: 10,
													defaultType: 'combobox',													    
												    items: [{
												    	
											            fieldLabel: 'ˮӡλ��',
											            name: 'watermark_pos' ,
											            labelAlign: 'right',
										                labelWidth: 60,
										                store: wpstore,
										                queryMode: 'remote',
										        	    displayField: 'name',
										        	    valueField: 'name',												        	    
										        	    forceSelection: true,				            
											            emptyText:'Please choose watermark position...',
											            value:'����',
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
												    title: '��������',
												    height: 400,
												    width: 360,				   
												    buttonAlign: 'center',		    
												    modal: true,
												    layout:'fit',
												    closable: true,													    
												    items: form123,
												    
												    buttons: [{
												    	xtype: 'button',
												    	name:'ok',
												    	text:'����',
												    	handler:function(){
												    		if(form123.getForm().isValid()){
												    			//win.hide();
												    			//Ext.Msg.wait('��ʾ','���ڴ������ݣ����Ժ�'); 
												    			
												    			//var myMask = new Ext.LoadMask(Ext.getBody(),{msg:"���Ե�,���ڵ���..."}); 
																
												    			//myMask.show();	
												    			var wp = form123.getForm().findField("watermark_pos").getValue();
												    		    var sm=Ext.getCmp("images-view").getSelection(); // ���grid��SelectionModel
												    		
												    			var tmpStr = "";
																var tmpArray = [];

																for(var i =0; i< sm.length;i++) {
																	tmpStr += sm[i].get("caption")+",";
																	tmpArray.push(sm[i].get("caption"));
																}
																		
			                        	 					if(sm!=null&&sm.length>0){
			                        	 						//alert(sm[0].get("caption"));
			                        	 						 
			                        	 						form123.getForm().submit({
              														waitMsg:'���ڱ������ݣ����Ժ�',
              														waitTitle:'��ʾ',
             														method:'POST',
             														timeout: 600000,
              														url:'SBDownloadAndWaterMark',
              														params:{                
												                		//nodePath:encodeURIComponent(nodePath),
												                		//filePath:encodeURIComponent(filePath),
												                		//dataset_type:encodeURIComponent(dataset_type),
												                		file_path:encodeURIComponent(action.result.path),
												                		waterm_name:encodeURIComponent(tmpArray),
								                						//item_id:encodeURIComponent(item_id),
								                						//item_name:encodeURIComponent(item_name),
								                						//item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						
												           					
												 		   					//window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   
																			window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   

												            				win.close();
												      					}              	
        	
																});		
			                        	 						
			       					                        	 						
			                        	 					}else{
			                        	 						 
			                        	 						if(imagesStore.getCount()>0){
			                        	 							//alert(imagesStore.getAt(0).data['caption']);
			                        	 							form123.getForm().doAction('submit',{
              														waitMsg:'���ڱ������ݣ����Ժ�',
              														waitTitle:'��ʾ',
             														method:'POST',
             														timeout: 600000,
              														url:'SBDownloadAndWaterMark',
              														params:{                
												                		//nodePath:encodeURIComponent(nodePath),
												                		//filePath:encodeURIComponent(filePath),
												                		//dataset_type:encodeURIComponent(dataset_type),
												                		file_path:encodeURIComponent(action.result.path),
												                		waterm_name:encodeURIComponent(imagesStore.getAt(0).data['caption']),
								                						//item_id:encodeURIComponent(item_id),
								                						//item_name:encodeURIComponent(item_name),
								                						//item_rev:encodeURIComponent(item_rev),
								                						wp:encodeURIComponent(wp)
												                	},
												      					success:function(form,action){
												      						
												 		   					//window.location.href = "download?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(dsName+"_"+dataset_type));   
																			window.location.href = "downloadZip?filename="+encodeURI(encodeURI(action.result.path))+"&inputPath="+encodeURI(encodeURI(action.result.path))+"&dsname="+encodeURI(encodeURI(action.result.dsname));   

												            				win.close();
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
												    	text:'ȡ��',
												    	handler:function(){
												    		win.close();
												    	}
												    }]
												});
												win.show();	
						                    	
						                    }
						                });
						            }
						        }
						    }]
						     
						});
	        			
	        			Ext.create('Ext.window.Window', {
						    title: '������������',
						    height: 200,
						    width: 400,
						    layout: 'fit',
						    items: fileselector
						}).show();
	        			
	        		}else{
	        			 //alert(record.data.id+':'+record.data.text);
	        		//Ext.Msg.wait('��ʾ','���ڴ������ݣ����Ժ�');  
	        		
	        		
	        		 var pnCenter = Ext.getCmp("mainTabPanel");
	        		 
	        		// alert(record.data.id+':'+record.data.text);
	        		 var tabId = "tab-" + record.data.text;  
	        		 var newTab = pnCenter.getComponent(tabId); 
	        		 var text = record.data.text;
	        		 var data = record.data.data;
	        		 //Ext.log("m.data.path:"+m.data.path); 
	        		 if(text.indexOf("/")>0) {
	        			//Ext.Msg.alert("info",record.data.text);
	        			var resText=text;
	        			tabId= "tab-����:"+resText;
	        			
	        			//text="����:"+resText;
	        			text = resText;
	        			newTab=pnCenter.getComponent(tabId); 
	        			 var data = record.data.data;
	        			var type_name = data.substring(0,data.indexOf("/"));
	        			var item_id = data.substring(data.indexOf("/")+1,data.lastIndexOf("/"));
	        			var item_rev = data.substring(data.lastIndexOf("/")+1);
	        			//alert(type_name+item_id+item_rev);
	        			//data="<%=basePath%>FunctionPage/SearchFiles.jsp?flag=1&item_id="+item_id+"&item_rev="+item_rev+"&type_name="+type_name;
	        			
	        			data='<%=basePath%>FunctionPage/SearchFiles.jsp?'+
	        				 'flag=1&item_id='+encodeURIComponent(item_id)+
	        				 '&item_rev='+encodeURIComponent(item_rev)+
	        				 '&type_name='+encodeURIComponent(type_name);
	        		}
	        		 if (!newTab) {  
	        		    //alert(data);
	        		     newTab = pnCenter.add({  	        		        
	        		         id: tabId,  
	        		         title: text,  
	        		         closable: true,
	        		         html : '<iframe id="'+tabId+'" src="'+data+'" width="100%" height="100%" frameborder="no" border="0" ></iframe>'
	        		     });      	        		           		     
	        		     
	        		     pnCenter.setActiveTab(newTab);  
	        		 }else { //���tab�д��ڣ���ô��ֱ�ӽ��ڵ�ָ�����ҳ��  
	        		     pnCenter.setActiveTab(newTab);  
	        		 }  
	        		 
	        		// alert(record.data.id+':'+record.data.text);
	        		//Ext.Msg.hide();
	        		  e.stopEvent;
	        		  //Ext.Msg.hide();
	        		  
	        		}
	        		
	        		 
	        		 },
	        		 scope: this
	        	 },
	        	 afterrender: function(){
	                 var record = this.getStore().getNodeById('select');
	                   this.getSelectionModel().select(record)
	                  loadRecord();
	        			
	        		
	             }
	        	 
	        },
	        renderTo:Ext.getBody()
	    });
		
	    
	    var btnlogoffclick = function(){
			//if (form.getForm().isValid()) {
                //ͨ�����͵��������˻�ȡ����ֵ�ٽ��д����������Ժ�Ľ̳����ٽ������������Ľ������⡣          
                
                logoffpanel.getForm().doAction('submit',{  
              		 url:'Logoff',                		
              		 method:'post',  
               	success:function(form,action){  
               		location.href="../index.jsp"; 
                   	//Ext.Msg.alert("��¼�ɹ���",action.result.message);  
               	},  
               	failure:function(form,action){  
                   	//Ext.Msg.alert("ע��ʧ�ܣ�",action.result.message);  
               		location.href="../index.jsp"; 
               	}
               });
                
            //}
		};
	    
		//var username = '<s:property value="current_user.name"/>';
		var  username ='<%=((FWUser)session.getAttribute("current_user")).getUser_name()%>';
		
	    var logoffpanel =  Ext.create('Ext.form.Panel', {  
            
            border: true,  
            bodyPadding: 10,  
            renderTo: Ext.getBody(),  
          //  height : 80,
            flex:1,
            layout: { type: 'hbox', pack: 'end', align: 'center' }, 
            items: [{
            	xtype: 'label',           
          		itemId: 'label1',    
          		text: "��ã�"+username,
          		margin : '10 10 0 0'
          		},{
          			xtype: 'button',           
          			name:'logoff',
    		    	text:'ע ��',
    		    	handler: btnlogoffclick
          		}]  
        });
	    
	    
		var mainviewport = Ext.create('Ext.container.Viewport', {
			layout : 'border',			
			items : [{
						region : 'north',						
						border : false,
						margin : '0 0 0 0',
						items :logoffpanel,
						flex:1
					}, {
						defaultType:'container',
						layout:'fit',
						region : 'west',
						collapsible : true,
						split : true,
						title : '����',
						width : 150 ,
						minHeight : 100,
						//flex:1,
						
						// could use a TreePanel or AccordionLayout for navigational
						items : treePanel
				}	, {
						region : 'south',						
						collapsible : false,						
						html : '<iframe src="<%=basePath%>OtherPage/CompanyInfo.jsp" width="100%" height="100%" frameborder="no" border="0" ></iframe>',
						split : true,						
						/* height : 100,
						minHeight : 100,
						maxHeight : 100 */
						flex:0.8
					}, {
						region : 'center',						
						xtype : 'tabpanel', // TabPanel itself has no title
						activeTab : 0, // First tab active by default
						id:"mainTabPanel",
						items : {
							title : '�����ļ�',
							id: "tab-�����ļ�",  
							closable: true,							
							html : '<iframe id="tab-�����ļ�" src="<%=basePath%>FunctionPage/SearchFiles.jsp?flag=0" width="100%" height="100%" frameborder="no" border="0" ></iframe>'
						},
						flex:12,
						listeners : {
							'tabchange' : function(tab, newc, oldc) {
								
								
							}
						}
					}]
		});
		
		

	});
		
	
</script>	

</head>
<body>
	
</body>
</html>