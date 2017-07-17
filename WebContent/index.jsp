<%-- <%@page import="org.apache.tomcat.util.http.Cookies"%> --%>
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
                //�ύ������������  ;
              var _user = document.getElementById('textfield-1010-inputEl').value;
				var _pwd  = document.getElementById('textfield-1011-inputEl').value;
				localStorage.setItem('username', _user);
				localStorage.setItem('password', _pwd);
               
                
                form.getForm().doAction('submit',{  
               		 url:'Login',                		
               		 method:'post', 
               		 waitMsg:'���ڵ�¼ϵͳ...',
                     waitTitle:'��ʾ',
                	success:function(form,action){  
                		if(action.result.user.dba){
                   			location.href="./MainPage/main_admin.jsp"; 
                   		}else{
                   			location.href="./MainPage/main_user.jsp"; 
                   		}
                    	//Ext.Msg.alert("��¼�ɹ���",action.result.message);  
                	},  
                	failure:function(form,action){ 
                		if(win.isHidden)
                		win.show();
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
		            name: 'user.user_name' ,
		            labelAlign: 'right',
	                labelWidth: 60,
	                allowBlank: true,	                
	                blankText: '�������û���'
		        },
		        {               
		            fieldLabel: '����',
		            labelStyle : 'color:#FFFFFF;font-size:16px;font-weight:bold',
		            name: 'user.user_pwd' , 
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
		        },
		        
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
		    buttons: [   {
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
		var user = localStorage.getItem('username');
		var pwd = localStorage.getItem('password');
		if(user){
			document.getElementById('textfield-1010-inputEl').value = user;
		};
		if(pwd){
			document.getElementById('textfield-1011-inputEl').value = pwd;
		}
/* 		var tmp_user_name = getQueryString("user_name");
		var tmp_user_pwd = getQueryString("user_pwd");
		if(tmp_user_name != null &&tmp_user_pwd!=null) {
			form.getForm().findField("user.user_name").setValue(tmp_user_name);
			form.getForm().findField("user.user_pwd").setValue(tmp_user_pwd);
			win.hide();
			btnokclick();
		} */
<%-- 		<%String tmpUserName=request.getParameter("user_name");
		System.out.println("before:"+tmpUserName);  
		  if(tmpUserName!=null) {
			  tmpUserName = new String(request.getParameter("user_name").getBytes("ISO-8859-1"),"utf-8");  
			  System.out.println("after:"+tmpUserName);  
		  }
		  else
			  tmpUserName = "";
		  %> --%>
		  
		//post�������
		var tmp_user_name = '<%=request.getParameter("user_name")==null?"":request.getParameter("user_name")%>';
		var tmp_user_pwd = '<%=request.getParameter("user_pwd")==null?"":request.getParameter("user_pwd")%>';
		
		if(tmp_user_name!="" && tmp_user_pwd!="") {
			form.getForm().findField("user.user_name").setValue(tmp_user_name);
			form.getForm().findField("user.user_pwd").setValue(tmp_user_pwd);
			win.hide();
			btnokclick();
		}
		
		
	});
	   function getQueryString(key){//�����������
        var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
        var result = window.location.search.substr(1).match(reg);
        return result?decodeURIComponent(result[2]):null;
      }	
   
	
    </script>    




</head>
<body style="background-color:#c00000">

 
</body>
</html>