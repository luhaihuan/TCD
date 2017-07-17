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
<title>�ҳ���BOM</title>

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
			var item_id_old = getQueryString("item_id_old");
			var client_object_code = getQueryString("client_object_code");
			var item_name = getQueryString("item_name");
			var material = getQueryString("material");
			var unit = getQueryString("unit");
			var quantity = getQueryString("quantity");
			var single_weight = getQueryString("single_weight");
			var total_weight = getQueryString("total_weight");
			var part_type = getQueryString("part_type");
			var remark = getQueryString("remark");
    	    //we want to setup a model and store instead of using dataUrl  
    	    Ext.define('Item', {  
    	        extend: 'Ext.data.Model',  
    	        fields: [  
    	            {name: 'item_id',     type: 'string'},  
    	            {name: 'item_rev_id', type: 'string'},  
    	            {name: 'item_id_old', type: 'string'},  
    	            {name: 'client_object_code', type: 'string'}, 
    	            {name: 'item_name', type: 'string'}, 
    	            {name: 'material', type: 'string'}, 
    	            {name: 'unit', type: 'string'}, 
    	            {name: 'quantity', type: 'string'}, 
    	            {name: 'single_weight', type: 'string'}, 
    	            {name: 'total_weight', type: 'string'}, 
    	            {name: 'part_type', type: 'string'},
    	            {name: 'remark', type: 'string'},
    	            {name: 'leaf', type: 'boolean'},
    	            {name: 'bomline', type: 'string'}
    	        ]  
    	    });  
    	    var treeStore = Ext.create('Ext.data.TreeStore', {  
    	        model: 'Item', 
    	        root : {//������ڵ㣬�������Ǳ����
    	        	  id: 'root',
    	             // text : 'item_id',
    	              item_id:item_id,
    	              item_rev_id:item_rev_id,
    	              item_id_old:item_id_old,
    	              client_object_code:client_object_code,
    	              item_name:item_name,
    	              material:material,
    	              unit:unit,
    	              quantity:quantity,
    	              single_weight:single_weight,
    	              total_weight:total_weight,
    	              part_type:part_type,
    	              remark:remark,
    	              leaf:false,
    	              iconCls:'item_rev_icon',
    	              bomline:item_id+"/"+item_rev_id+";-"+item_name+" (��ͼ) ",
    	              expanded : true
    	          	},
    	        autoLoad : false,
    	         proxy: {  
    	            type: 'ajax',  
    	            //the store will get the content from the .json file  
    	            url: 'GetGCQBOM', 
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
                //������׽ڵ�Ĳ˵��Ὣ�ڵ��idͨ��ajax���󣬽�����̨  
                        
                        this.proxy.extraParams.item_id = node.data.item_id;  
                        this.proxy.extraParams.item_rev_id = node.data.item_rev_id; 
                       // alert(node.data.text);  
                      //node.childNodes.sort(function (a, b) { return a.data.text.localeCompare(b.data.text); });
                    },  
              	  
                } ,
    	        folderSort: false  
    	    });  
    	      
    	  //Ext.ux.tree.TreeGrid��UX��չ��Ҳ�У��������ã������Լ򵥵�ʹ��һ��tree.TreePanel  
    	    var treePanel = Ext.create('Ext.tree.Panel', {  
    	        //title: 'BOM TEST',  
    	        width: 500,  
    	        height: 500,  
    	       // renderTo: 'treegrid',  
    	        collapsible: false,  
    	      //  useArrows: true,  
    	        rootVisible: true,  
    	        store: treeStore,  
    	        multiSelect: true,  
    	        singleExpand: false,
    	        split : true,
    	      
    	       
    	          
    	      //the 'columns' property is now 'headers'  
    	        columns: [{  
    	            xtype: 'treecolumn', //this is so we know which column will show the tree  
    	            text: 'BOM��',  
    	             width: 400, 
    	            sortable: true,  
    	            dataIndex: 'bomline'  
    	        },{  
    	           // xtype: 'treecolumn', //this is so we know which column will show the tree  
    	            text: 'ͼ��',  
    	             width: 200, 
    	            sortable: true,  
    	            dataIndex: 'item_id'  
    	        }, {  
    	            text: '�汾',  
    	            //flex: 1,  
    	            dataIndex: 'item_rev_id',  
    	            width: 50,
    	            sortable: true  
    	        }, {  
    	            text: '��ͼ��',  
    	            //flex: 1,  
    	            dataIndex: 'item_id_old',  
    	            width: 150,
    	            sortable: true  
    	        }, {  
    	            text: '�ͻ����Ϻ�',  
    	            //flex: 1,  
    	            dataIndex: 'client_object_code',  
    	            width: 150,
    	            sortable: true  
    	        }, {  
    	            text: '�㲿������',  
    	            //flex: 1,  
    	            dataIndex: 'item_name',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '����',  
    	            //flex: 1,  
    	            dataIndex: 'material',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '��λ',  
    	            //flex: 1,  
    	            dataIndex: 'unit',  
    	            width: 50,
    	            sortable: true  
    	        },{  
    	            text: '����',  
    	            //flex: 1,  
    	            dataIndex: 'quantity',  
    	            width: 80,
    	            sortable: true  
    	        },{  
    	            text: '����',  
    	            //flex: 1,  
    	            dataIndex: 'single_weight',  
    	            width: 80,
    	            sortable: true  
    	        },{  
    	            text: '����',  
    	           // flex: 1,  
    	            dataIndex: 'total_weight',  
    	            width: 80,
    	            sortable: true  
    	        },{  
    	            text: '�㲿������',  
    	           // flex: 1,  
    	            dataIndex: 'part_type',  
    	            width: 150,
    	            sortable: true  
    	        },{  
    	            text: '��ע',  
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
     	                blankText: '������itemid'
     	                
     		        },{
     		        	id:'item_rev_id',
     		            fieldLabel: 'item_rev_id',
     		            name: 'item_rev_id' ,
     		            labelAlign: 'right',
     	                labelWidth: 70,
     	                allowBlank: true,	                
     	                blankText: '������item_rev_id'
     	                
     		        }
     		    ],
     		   buttonAlign:'right', 
     		   buttons: [{
			    	xtype: 'button',
			    	name:'search_bom',
			    	type : 'submit', 
			    	text:'����',
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
			    	text:'ȡ��',
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
    						//title : '��ʾ��',
    						
    						
    						
    						// could use a TreePanel or AccordionLayout for navigational
    						items : treePanel
    				}	]
    		}); 
    	});  
	  function getQueryString(key){//�����������
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return decodeURIComponent(result?decodeURIComponent(result[2]):null);
      }	
	
</script>	
	

</head>
<body>

</body>
</html>