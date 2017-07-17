// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetACLbyTypeAction.java

package com.sulliar.ypq.aclManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.beans.factory.BeanFactory;

public class GetACLbyTypeAction extends ActionSupport
{

	private boolean flag;
	String ft;
	private List models;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;

	public GetACLbyTypeAction()
	{
	}

	public String getFt()
	{
		return ft;
	}

	public void setFt(String ft)
	{
		this.ft = ft;
	}

	public List getModels()
	{
		return models;
	}

	public void setModels(List models)
	{
		this.models = models;
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
		BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		ACLDataManager aclManager = (ACLDataManager)beanFactory.getBean("ACLDataManager");
		String ftStr = URLDecoder.decode(ft, "utf-8");
		total = aclManager.getTotalCount(ftStr);
		models = aclManager.findACLByType(ftStr, 0, total);
		System.out.println("GetACLbyTypeAction executed");
		flag = true;
		return "JSON";
	}
}
