// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetACLbyAttrsAction.java

package com.sulliar.ypq.aclManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.List;
import org.springframework.beans.factory.BeanFactory;

public class GetACLbyAttrsAction extends ActionSupport
{

	private boolean flag;
	private List models;
	private int total;
	private int start;
	private int limit;
	String user;
	String group;
	String site;
	private static final long serialVersionUID = 1L;

	public GetACLbyAttrsAction()
	{
		flag = false;
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

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public String execute()
		throws Exception
	{
		BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		ACLDataManager aclManager = (ACLDataManager)beanFactory.getBean("ACLDataManager");
		String userStr = URLDecoder.decode(user, "utf-8");
		String siteStr = URLDecoder.decode(site, "utf-8");
		String groupStr = URLDecoder.decode(group, "utf-8");
		System.out.println(flag);
		if (flag)
		{
			start = 0;
			limit = 10;
		}
		total = aclManager.getTotalCount(userStr, groupStr, siteStr);
		models = aclManager.findACLByAttrs(userStr, groupStr, siteStr, start, limit);
		System.out.println("GetACLbyTypeAction executed");
		flag = true;
		return "JSON";
	}
}
