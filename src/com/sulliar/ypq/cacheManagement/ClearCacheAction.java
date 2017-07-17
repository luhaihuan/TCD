// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ClearCacheAction.java

package com.sulliar.ypq.cacheManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class ClearCacheAction extends ActionSupport
{

	boolean success;
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileDataManager;

	public ClearCacheAction()
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

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileDataManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		String path = (new StringBuilder(String.valueOf(ServletActionContext.getServletContext().getRealPath("/")))).append(fileDataManager.getDAPath()).toString();
		System.out.println(path);
		File file_folder = new File(path);
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.YEAR, -1);
		Date oneyear = calendar.getTime();
		if (file_folder.exists())
		{
			File files[] = file_folder.listFiles();
			if (files != null && files.length > 0)
			{
				File afile[];
				int j = (afile = files).length;
				for (int i = 0; i < j; i++)
				{
					File f = afile[i];
					if (f.isFile())
					{
						long lmt = f.lastModified();
						Date date = new Date(lmt);
						if (date.before(oneyear))
							f.delete();
					}
				}

			}
		}
		success = true;
		return "success";
	}
}
