// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModLogSettingAction.java

package com.sulliar.ypq.logSettingManagement;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.LogSetting;
import com.sulliar.ypq.service.LogSettingDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;

public class ModLogSettingAction extends ActionSupport
{

	boolean success;
	double day;
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "LogSettingDataManager")
	LogSettingDataManager logSettingDataManager;

	public ModLogSettingAction()
	{
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public double getDay()
	{
		return day;
	}

	public void setDay(double day)
	{
		this.day = day;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//LogSettingDataManager logSettingDataManager = (LogSettingDataManager)beanFactory.getBean("LogSettingDataManager");
		LogSetting ls = logSettingDataManager.findLogSettingByType("每日最大下载数量");
		if (ls != null)
		{
			ls.setValue((new StringBuilder(String.valueOf(day))).toString());
			logSettingDataManager.modify(ls);
			success = true;
		} else
		{
			success = false;
		}
		return "success";
	}
}
