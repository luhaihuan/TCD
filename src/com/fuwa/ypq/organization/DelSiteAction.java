// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DelUserAction.java

package com.fuwa.ypq.organization;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class DelSiteAction extends ActionSupport
{

	boolean success;
	String deleteSites;
	public String getDeleteSites() {
		return deleteSites;
	}

	public void setDeleteSites(String deleteSites) {
		this.deleteSites = deleteSites;
	}

	private static final long serialVersionUID = 1L;

	public DelSiteAction()
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
	
	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		
		JSONArray jsonArray = JSONArray.fromObject(deleteSites);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			organizationDataManager.delSite((Site)JSONObject.toBean(jsonObject,Site.class));
		}

		success = true;
		return "success";
	}
}
