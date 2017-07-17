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
<title>Normal User</title>
<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">
       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
<script type="text/javascript">
 var loadRecord;

	Ext.onReady(function () {
		
		//只给leaf为true的节点加data，否则会与父节点展开事件发生冲突
	    var store = Ext.create("Ext.data.TreeStore", {
	        root:{
	            expanded:true,
	            children:[  {
                    text:"搜索文件",
                    id:"select",
                    iconCls:'search_icon',
                    data:"<%=basePath%>FunctionPage/SearchFiles.jsp",
                    leaf:true
                },
                {
                	id:"recordData",
                    text:"历史记录", 
                    iconCls:'history_icon',
                    leaf:false                      
                }
                ]
	        }
	    });
		 loadRecord=function loadRecordData() { 
            var recordNode=store.getNodeById('recordData'); 
            //recordNode.data.leaf = false;
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
										 Ext.Msg.alert("提示","获取历史记录失败!");
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
	        			tabId= "tab-搜索:"+resText;
	        			
	        			//text="搜索:"+resText;
	        			text = resText;
	        			newTab=pnCenter.getComponent(tabId); 
	        			 var data = record.data.data;
	        			var type_name = data.substring(0,data.indexOf("/"));
	        			var item_id = data.substring(data.indexOf("/")+1,data.lastIndexOf("/"));
	        			var item_rev = data.substring(data.lastIndexOf("/")+1);
	        			//alert(type_name+item_id+item_rev);
	        			//data="<%=basePath%>FunctionPage/SearchFiles2.jsp?flag=1&item_id="+item_id+"&item_rev="+item_rev+"&type_name="+type_name;
	        			data='<%=basePath%>FunctionPage/SearchFiles.jsp?'+
	        				 'flag=1&item_id='+encodeURIComponent(item_id)+
	        				 '&item_rev='+encodeURIComponent(item_rev)+
	        				 '&type_name='+encodeURIComponent(type_name);
	        		} 
	        		/*  if (!newTab) {  
	        		    
	        		     newTab = pnCenter.add({  	        		        
	        		         id: tabId,  
	        		         title: record.data.text,  
	        		         closable: true,
	        		         html : '<iframe src="'+record.data.data+'" width="100%" height="100%" frameborder="no" border="0" ></iframe>'
	        		     });      	        		           		     
	        		     
	        		     pnCenter.setActiveTab(newTab);  
	        		 }else { //如果tab中存在，那么就直接将节点指向这个页面  
	        		     pnCenter.setActiveTab(newTab);  
	        		 }   */
	        		 
	        		  if (!newTab) {  
	        		    //alert(data);
	        		     newTab = pnCenter.add({  	        		        
	        		         id: tabId,  
	        		         title: text,  
	        		         closable: true,
	        		         html : '<iframe id="'+tabId+'" src="'+data+'" width="100%" height="100%" frameborder="no" border="0" ></iframe>'
	        		     });      	        		           		     
	        		     
	        		     pnCenter.setActiveTab(newTab);  
	        		 }else { //如果tab中存在，那么就直接将节点指向这个页面  
	        		     pnCenter.setActiveTab(newTab);  
	        		 }  
	        		 
	        		// alert(record.data.id+':'+record.data.text);
	        		  e.stopEvent;
	        		 },
	        		 scope: this
	        	 },
	        	 afterrender: function(){
	                 var record = this.getStore().getNodeById('select');
	                   this.getSelectionModel().select(record);
	                     loadRecord();
	             }
	        	 
	        },
	        renderTo:Ext.getBody()
	    });
		
	    var  username ='<%=((FWUser)session.getAttribute("current_user")).getUser_name()%>';
	    
	    var btnlogoffclick = function(){
			//if (form.getForm().isValid()) {
                //通常发送到服务器端获取返回值再进行处理，我们在以后的教程中再讲解表单与服务器的交互问题。          
               logoffpanel.getForm().doAction('submit',{  
              		 url:'Logoff',                		
              		 method:'post',  
               	success:function(form,action){  
               		location.href="../index.jsp"; 
                   	//Ext.Msg.alert("登录成功！",action.result.message);  
               	},  
               	failure:function(form,action){  
                   	//Ext.Msg.alert("注销失败！",action.result.message);  
               		location.href="../index.jsp"; 
               	}
               });
            
                
            //}
		};
	    
	    var logoffpanel =  Ext.create('Ext.form.Panel', {  
            
            border: true,  
            bodyPadding: 10,  
            renderTo: Ext.getBody(),  
           // height : 80,
            flex:1,
            layout: { type: 'hbox', pack: 'end', align: 'center' }, 
            items: [{
            	xtype: 'label',           
          		itemId: 'label1',    
          		text: "你好，"+username,
          		margin : '10 10 0 0'
          		},{
          			xtype: 'button',           
          			name:'logoff',
    		    	text:'注 销',
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
						region : 'west',
						collapsible : true,
						split : true,
						title : '导航',
						width : 150 ,
						//minHeight : 1000,
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
						flex:12,
						items : {
							title : '搜索文件',
							id: "tab-搜索文件",  
							closable: true,
							html : '<iframe src="<%=basePath%>FunctionPage/SearchFiles.jsp" width="100%" height="100%" frameborder="no" border="0" ></iframe>'
						}
					}]
		});
		
		  

	});
</script>	

</head>
<body>
	
</body>
</html>