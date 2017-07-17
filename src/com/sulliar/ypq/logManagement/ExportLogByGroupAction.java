// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportLogByGroupAction.java

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

public class ExportLogByGroupAction extends ActionSupport
{

	Date from_date;
	Date to_date;
	String bgroup;
	String filename;
	boolean success;
	private static final long serialVersionUID = 1L;

	@Resource
	LogManager logManager;
	public ExportLogByGroupAction()
	{
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

	public String getBgroup()
	{
		return bgroup;
	}

	public void setBgroup(String bgroup)
	{
		this.bgroup = bgroup;
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
		
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//LogDataManager logManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println(toString());
		filename = logManager.exportLogByGroup(from_date, to_date, bgroup, realpath);
		success = true;
		return "success";
	}

	@Override
	public String toString() {
		return "ExportLogByGroupAction [from_date=" + from_date + ", to_date="
				+ to_date + ", bgroup=" + bgroup + ", filename=" + filename
				+ ", success=" + success + ", logManager=" + logManager + "]";
	}
	
}
