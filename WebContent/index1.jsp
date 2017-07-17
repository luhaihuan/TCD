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
<title>Login</title>
<link rel="stylesheet"type="text/css" href="<%=basePath%>ext/ext-theme-neptune-red/build/resources/ext-theme-neptune-red-all.css">

       <script type="text/javascript" src="<%=basePath%>ext/ext-all.js"></script>

        <script type="text/javascript" src="<%=basePath%>ext/ext-bootstrap.js"></script>

       <script type="text/javascript" src="<%=basePath%>ext/ext-theme-neptune-red/build/ext-theme-neptune-red.js"></script>

       <script type="text/javascript" src="<%=basePath%>js/app.js"></script>
       
<script type="text/javascript">
	Ext.onReady(function () {
		
		var btnresetclick = function(){
			form.getForm().reset();
		};
		
		var btnokclick = function(){
			if (form.getForm().isValid()) {
                //ͨ�����͵��������˻�ȡ����ֵ�ٽ��д����������Ժ�Ľ̳����ٽ������������Ľ������⡣                
                //�ύ������������  
                form.getForm().doAction('submit',{  
               		 url:'Login',                		
               		 method:'post', 
               		 waitMsg:'���ڵ�¼ϵͳ...',
                     waitTitle:'��ʾ',
                	success:function(form,action){  
                		if(action.result.user.isAdmin){
                   			location.href="./MainPage/main_admin.jsp"; 
                   		}else{
                   			location.href="./MainPage/main_user.jsp"; 
                   		}
                    	//Ext.Msg.alert("��¼�ɹ���",action.result.message);  
                	},  
                	failure:function(form,action){  
                    	Ext.Msg.alert("��¼ʧ�ܣ�",action.result.message);  
                	}  
            	});
			}
		};
		
		var form = Ext.create('Ext.form.Panel', {
		    renderTo: document.body,		    
		    height: 300,
		    width: 500,
		    bodyPadding: 100,
		    defaultType: 'textfield',
		   	bodyStyle:"background-image:url('<%=basePath%>/css/login_backgroud.png');backgroud-repeat: no-repeat",
		    items: [
		        {
		            fieldLabel: '�û�',
		            labelStyle : 'color:#FFFFFF;font-size:16px;font-weight:bold',
		            name: 'user.name' ,
		            labelAlign: 'right',
	                labelWidth: 60,
	                allowBlank: true,	                
	                blankText: '�������û���'
		        },
		        {
		            fieldLabel: '����',
		            labelStyle : 'color:#FFFFFF;font-size:16px;font-weight:bold',
		            name: 'user.pwd' , 
		            inputType: 'password',
		            labelAlign: 'right',
	                labelWidth: 60,
	                allowBlank: true,	                
	                blankText: '����������',
	                listeners : {  
	                    specialkey : function(field, e) {  

	                             
	                        if (e.getKey() == Ext.EventObject.ENTER) {  
	                          
	                        	btnokclick();

	                        }  
	                    } // end of specialkey  
	                   } // end of listeners 
		        }
		    ]
		});
		
		var win = Ext.create('Ext.window.Window', {
			
			resizable : false,
			header: false,
		    height: 300,
		    width: 500,				   
		    buttonAlign: 'center',		    
		    modal: true,
		    layout: "fit",
		    closable: false,		    
		    closeAction: "hide",
		    items: form,
		    buttons: [
		              
		              {
		    	xtype: 'button',
		    	name:'ok',
		    	text:'�� ¼',
		    	handler: btnokclick
		    },{
		    	xtype: 'button',
		    	name:'reset',
		    	text:'�� ��',
		    	handler: btnresetclick
		    }]
		});
		win.show();
		
		
	});
    </script>    

</head>
<body style="background-color:#76ba53">

</body>
</html>