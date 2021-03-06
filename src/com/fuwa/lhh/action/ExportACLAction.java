// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportACLAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.service.AuthorityManager;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class ExportACLAction extends ActionSupport
{

	String filename;
	boolean success;
	private static final long serialVersionUID = 1L;
	@Resource
	AuthorityManager authorityManager;

	public ExportACLAction()
	{
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String execute()
		throws Exception
	{
		
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		filename = authorityManager.ExportACLExcel(realpath);
		success = true;
		return "success";
	}
}
