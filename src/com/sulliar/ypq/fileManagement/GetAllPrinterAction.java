// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetAllPrinterAction.java

package com.sulliar.ypq.fileManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class GetAllPrinterAction extends ActionSupport
{

	private boolean flag;
	private List pms;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;

	public GetAllPrinterAction()
	{
	}

	public List getPms()
	{
		return pms;
	}

	public void setPms(List pms)
	{
		this.pms = pms;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		pms = fileManager.getAllPrinter();
		total = pms.size();
		limit = total;
		start = 0;
		flag = true;
		return "JSON";
	}
}
