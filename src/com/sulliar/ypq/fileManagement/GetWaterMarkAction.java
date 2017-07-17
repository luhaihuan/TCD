// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetWaterMarkAction.java

package com.sulliar.ypq.fileManagement;

import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class GetWaterMarkAction extends ActionSupport
{

	private boolean flag;
	private List images;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	DepartManager departManager;
	@Resource
	SiteManager siteManager;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;

	public GetWaterMarkAction()
	{
	}

	public List getImages()
	{
		return images;
	}

	public void setImages(List images)
	{
		this.images = images;
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
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		//UserDataManager userManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		//User tlg = userManager.findUserByName(user.getName());
		Department department = departManager.getPersonDepartByUserId(user.getUuid());
		// Site   site =    siteManager.getDepartsSiteByDepartId(department.getUuid());
		if(department!=null) {
			images = fileManager.getWaterMark("", department.getDepartment_name());
			total = images.size();
		}
		
		else
		System.out.println("GetWaterMarkAction-->nodepart");
		
		limit = total;
		start = 0;
		flag = true;
		return "JSON";
	}
}
