// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportLogByItemAction.java

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

public class ExportLogByItemAction extends ActionSupport
{

	Date from_date;
	Date to_date;
	String itemid;
	String filename;
	boolean success;
	private static final long serialVersionUID = 1L;
	@Resource
	LogManager logManager;
	public ExportLogByItemAction()
	{
	}

	public String getItemid()
	{
		return itemid;
	}

	public void setItemid(String itemid)
	{
		this.itemid = itemid;
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
		//LogDataManager logManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		filename = logManager.exportLogByItem(from_date, to_date, itemid, realpath);
		success = true;
		return "success";
	}

	@Override
	public String toString() {
		return "ExportLogByItemAction [from_date=" + from_date + ", to_date="
				+ to_date + ", itemid=" + itemid + ", filename=" + filename
				+ ", success=" + success + ", logManager=" + logManager + "]";
	}
	
}
