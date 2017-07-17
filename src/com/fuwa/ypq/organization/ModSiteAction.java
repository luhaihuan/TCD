// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModUserAction.java

package com.fuwa.ypq.organization;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class ModSiteAction extends ActionSupport
{

	String message;
	Site site;
	public Site getSite() {
		return site;
	}


	public void setSite(Site site) {
		this.site = site;
	}

	boolean success;
	String uuid;
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private static final long serialVersionUID = 1L;

	public ModSiteAction()
	{
		message = "";
		site = null;
		uuid = "";
	}

	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
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
		Site s = organizationDataManager.findSiteById(uuid);
		
		if(s != null ){
			if(s.getSite_name().equals("dba")){
				message = "Î¥·¨ÐÞ¸ÄdbaÕ¾µã!";
				success = false;
			}else{
				s.setSite_name(site.getSite_name());
				organizationDataManager.modifySite(s);
				success = true;
			}
		}		
		
		return "success";
	}
}
