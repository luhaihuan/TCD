// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportLogByUserAction.java

package com.sulliar.ypq.logManagement;

import com.fuwa.lhh.service.LogManager;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.LogDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class ExportLogByUserAction extends ActionSupport
{

	Date from_date;
	Date to_date;
	String username;
	String filename;
	boolean success;
	private static final long serialVersionUID = 1L;

	@Resource
	LogManager logManager;
	public ExportLogByUserAction()
	{
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public Date getFrom_date()
	{
		return from_date;
	}

	public void setFrom_date(Date from_date)
	{
		this.from_date = from_date;
	}

	public Date getTo_date()
	{
		return to_date;
	}

	public void setTo_date(Date to_date)
	{
		this.to_date = to_date;
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
		System.out.println(toString());
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		// logManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		filename = logManager.exportLogByUser(from_date, to_date, username, realpath);
		success = true;
		return "success";
	}

	@Override
	public String toString() {
		return "ExportLogByUserAction [from_date=" + from_date + ", to_date="
				+ to_date + ", username=" + username + ", filename=" + filename
				+ ", success=" + success + ", logManager=" + logManager + "]";
	}
	
}
