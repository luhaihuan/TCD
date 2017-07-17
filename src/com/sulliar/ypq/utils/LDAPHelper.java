// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LDAPHelper.java

package com.sulliar.ypq.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LDAPHelper
{

	//static String url = "LDAP://10.13.12.10";
	//static String url1 = "LDAP://10.6.12.10";
	//static String url2 = "LDAP://10.11.12.10";
	
	String urls ;
	

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public LDAPHelper()
	{
	}
	
	public boolean verifyUser(String uid, String pwd, String path){
		
		String[] url = urls.split(",");
		
		for(String u:url){
			String msg = getCtxMsg(uid,pwd,path,u);
			if(msg.trim().equals("ok")){
				return true;
			}
		}
		
		
		return false;
	}
	
	public String getCtxMsg(String uid, String pwd, String path,String url){
		String message = "";
		
		try{
			
			String cmd = path +"js/LDAPTest.exe "+uid+" "+pwd+" "+url;
			
			System.out.println(cmd);
			
			Process process = Runtime.getRuntime().exec(cmd);
			
			InputStream fis = process.getInputStream();
			
			process.waitFor();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			String line = null;
			if((line=br.readLine())!=null){
				message = line;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return message;
	}

	public static void main(String args[])
	{
		//LDAPHelper h =new LDAPHelper();
		//String msg = h.getCtxMsg("finkle.zhang", "Accduyne20151030,");
		//System.out.println(msg);
	}

}
