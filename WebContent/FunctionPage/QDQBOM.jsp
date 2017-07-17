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
<title>驱动桥BOM</title>

<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">
<link rel="stylesheet"type="text/css" href="<%=basePath%>css/icon.css">
       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext/ext-locale-zh_CN.js"></script>

<script type="text/javascript">

	 Ext.onReady(function(){  
    	   //Ext.Msg.alert('info','hello');
    	    Ext.QuickTips.init();  
    	    
    	    var item_id = getQueryString("item_id");
			var item_rev_id = getQueryString("item_rev_id");
			var axle_type = getQueryString("axle_type");
			var specif = getQueryString("specif");
			var item_name = getQueryString("item_name");
			var material = getQueryString("material");
			var unit = getQueryString("unit");
			var quantity = getQueryString("quantity");
			var weight = getQueryString("weight");
			var class_type = getQueryString("class_type");
			var phase = getQueryString("phase");
			var client_name = getQueryString("client_name");
			var client_no = getQueryString("client_no");
			var remark = getQueryString("remark");
    	    //client_name = decodeURIComponent(client_name);
			//alert(client_name);
    	    //we want to setup a model and store instead of using dataUrl  
    	    Ext.define('Item', {  
    	        extend: 'Ext.data.Model',  
    	        fields: [  
    	            {name: 'item_id',     type: 'string'},  
    	            {name: 'item_rev_id', type: 'string'},  
    	            {name: 'axle_type', type: 'string'},  
    	            {name: 'specif', type: 'string'}, 
    	            {name: 'item_name', type: 'string'}, 
    	            {name: 'material', type: 'string'}, 
    	            {name: 'unit', type: 'string'}, 
    	            {name: 'quantity', type: 'string'}, 
    	            {name: 'weight', type: 'string'}, 
    	            {name: 'class_type', type: 'string'}, 
    	            {name: 'phase', type: 'string'},
    	            {name: 'client_name', type: 'string'},
    	            {name: 'client_no', type: 'string'},
    	            {name: 'remark', type: 'string'},
    	            {name: 'leaf', type: 'boolean'},
    	            {name: 'iconCls', type: 'string'}
    	           
    	        ]  
    	    });  
    	    var store = Ext.create('Ext.data.TreeStore', {  
    	        model: 'Item', 
    	        root : {//定义根节点，此配置是必须的
    	        	  //task: 'root',
    	             // text : '组织架构',
    	             item_id:item_id,
    	             item_rev_id:item_rev_id,
    	             axle_type:axle_type,
    	             specif:specif,
    	             item_name:item_name,
    	             material:material,
    	             unit:unit,
    	             quantity:quantity,
    	             weight:weight,
    	             class_type:class_type,
    	             phase:phase,
    	             client_name:client_name,
    	             client_no:client_no,
    	             remark:remark,
    	             leaf:false,
    	             iconCls:'item_rev_icon',
    	             expanded : true
    	          	},
    	        autoLoad : false,
    	         proxy: {  
    	            type: 'ajax',  
    	            //the store will get the content from the .json file  
    	            url: 'GetQDQBOM', 	
    	            reader : {
	 					type : 'json',
	 					root:'attrs'  
	 			    },
		            extraParams : {  
		            	item_id : '',
		            	item_rev_id : ''
		                } 
    	        },   
    	        listeners : {  
                    'nodebeforeexpand' : function(node,eOpts){  
                //点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
                        
                        this.proxy.extraParams.item_id = node.data.item_id;  
                        this.proxy.extraParams.item_rev_id = node.data.item_rev_id; 
                        //alert(node.data.text);  
                      //node.childNodes.sort(function (a, b) { return a.data.text.localeCompare(b.data.text); });
                    },  
              	  
                } ,
    	        folderSort: false  
    	    });  
    	      
    	  //Ext.ux.tree.TreeGrid在UX扩展中也有，但不常用，您可以简单地使用一个tree.TreePanel  
    	    var treePanel = Ext.create('Ext.tree.Panel', {  
    	        //title: 'BOM TEST',  
    	        width: 500,  
    	        height: 500,  
    	       // renderTo: 'treegrid',  
    	        collapsible: false,  
    	      //  useArrows: true,  
    	        rootVisible: true,  
    	        store: store,  
    	        multiSelect: true,  
    	        singleExpand: false, 
    	      
    	       
    	          
    	      //the 'columns' property is now 'headers'  
    	        columns: [{  
    	            xtype: 'treecolumn', //this is so we know which column will show the tree  
    	            text: '图号',  
    	            //flex: 1,
    	           // iconCls:'item_icon',
    	            width: 200,
    	            sortable: true,  
    	            dataIndex: 'item_id'  
    	        },/*  {  
    	            text: '版本',  
    	            //flex: 1,  
    	            dataIndex: 'item_rev_id',  
    	            width: 50,
    	            sortable: true  
    	        }, */ {  
    	            text: '零部件名称',  
    	           // flex: 1,  
    	            dataIndex: 'item_name',  
    	            width: 150,
    	            sortable: true  
    	        }, {  
    	            text: '数量',  
    	            //flex: 1,  
    	            dataIndex: 'quantity',  
    	            width: 50,
    	            sortable: true  
    	        }, {  
    	            text: '桥型',  
    	            //flex: 1,  
    	            dataIndex: 'axle_type',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '材料',  
    	            //flex: 1,  
    	            dataIndex: 'material',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '规格',  
    	            //flex: 1,  
    	            dataIndex: 'specif',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '重量',  
    	           // flex: 1,  
    	            dataIndex: 'weight',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '单位',  
    	            //flex: 1,  
    	            dataIndex: 'unit',  
    	            width: 50,
    	            sortable: true  
    	        },{  
    	            text: '分类',  
    	            //flex: 1,  
    	            dataIndex: 'class_type',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '阶段',  
    	            //flex: 1,  
    	            dataIndex: 'phase',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '客户名称',  
    	            //flex: 1,  
    	            dataIndex: 'client_name',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '客户编号',  
    	            //flex: 1,  
    	            dataIndex: 'client_no',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '备注',  
    	            //flex: 1,  
    	            dataIndex: 'remark',  
    	            width: 150,
    	            sortable: true  
    	        }
    	        ]  
    	    });  
    	  
    	    var form = Ext.create('Ext.form.Panel', {					                     		    		    
     		    height: 120,
     		    width: 100,
     		    bodyPadding: 10,
     		    defaultType: 'textfield',
     		    items: [
     		        {
     		        	id:'item_id',
     		            fieldLabel: 'item_id(*)',
     		            name: 'item_id' ,
     		            labelAlign: 'right',
     	                labelWidth: 70,
     	                allowBlank: false,	                
     	                blankText: '请输入itemid'
     	                
     		        },{
     		        	id:'item_rev_id',
     		            fieldLabel: 'item_rev_id',
     		            name: 'item_rev_id' ,
     		            labelAlign: 'right',
     	                labelWidth: 70,
     	                allowBlank: true,	                
     	                blankText: '请输入item_rev_id'
     	                
     		        }
     		    ],
     		   buttonAlign:'right', 
     		   buttons: [{
			    	xtype: 'button',
			    	name:'search_bom',
			    	type : 'submit', 
			    	text:'搜索',
			    	handler: function() {
			    		if(form.getForm().isValid()) {
			    			form.getForm().doAction('submit',{  
			    				 url: 'GetData',               		
        	              		 method:'get',         	              		
        	               	success:function(form,action){ 
        	               	
        	               		Ext.Msg.alert("success",action.result.responText);
        	               		
        	               	
        	               		
        	               	},  
        	               	failure:function(form,action){  
        	                   //	Ext.Msg.alert("failure",action.result); 
        	                   	var treeRoot = store.getRoot();
        	                   	treeRoot.data.item_id = action.result.item_id;
        	                   	treeRoot.data.item_rev_id = action.result.item_rev_id;
        	                	treeRoot.data.count = action.result.count;
        	                	treeRoot.data.iconCls = action.result.iconCls;
        	                	treeRoot.data.leaf = action.result.leaf;
        	                	treeRoot.data.expanded = action.result.expanded;
        	                	store.reload();
        	               	}  
        	           		});
			    		}
			    	}
			    },{
			    	xtype: 'button',
			    	name:'search_cancel',
			    	text:'取消',
			    	handler: function() {
			    		
			    	}
			    }] 
			
     		});
    	     var mainviewport = Ext.create('Ext.container.Viewport', {
    			layout : 'border',			
    			items : [/*  {
					defaultType:'container',
					layout:'fit',
					region : 'west',
					collapsible : false,
					split : true,
					title : 'item',
					width : 300,
					
					
					// could use a TreePanel or AccordionLayout for navigational
					items : form
			        }, */{
    						defaultType:'container',
    						layout:'fit',
    						region : 'center',
    						collapsible : false,
    						split : true,
    						//title : '树示例',
    						
    						
    						
    						// could use a TreePanel or AccordionLayout for navigational
    						items : treePanel
    				}	]
    		}); 
    	});  
	  function getQueryString(key){//解决中文乱码
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return decodeURIComponent(result?decodeURIComponent(result[2]):null);
      }	
	
</script>	
	

</head>
<body>

</body>
</html>