// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetAllUserAction.java

package com.sulliar.ypq.userManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.LogDataManager;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class GetAllUserAction extends ActionSupport
{

	private boolean flag;
	private List users;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;

	public GetAllUserAction()
	{
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public List getUsers()
	{
		return users;
	}

	public void setUsers(List users)
	{
		this.users = users;
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
	
	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{		
		total = organizationDataManager.getTotalCount("FWUser");
		if (total<limit)
			limit = total;
		users = organizationDataManager.getAllUser(start, limit);
		System.out.println("GetAllUserAction old executed");
		flag = true;
		return "JSON";
	}
}
