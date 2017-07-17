// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetAllLogsAction.java

package com.sulliar.ypq.logManagement;

import com.fuwa.lhh.service.LogManager;
import com.fuwa.lhh.service.LogManagerService;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.LogDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class GetAllLogsAction extends ActionSupport
{

	private boolean flag;
	private List logs;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	LogManager logManager;

	public GetAllLogsAction()
	{
	}

	public List getLogs()
	{
		return logs;
	}

	public void setLogs(List logs)
	{
		this.logs = logs;
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
		//LogDataManager logManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		System.out.println(toString());
		logs = logManager.getAllLogs(start, limit);
		total = logManager.getTotalCount("FWLog");
		if(limit > total)
			limit = total;
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetAllLogsAction [flag=" + flag + ", logs=" + logs + ", total="
				+ total + ", start=" + start + ", limit=" + limit
				+ ", logManager=" + logManager + "]";
	}
	
}
