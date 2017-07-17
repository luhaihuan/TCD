// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AddUserAction.java

package com.fuwa.ypq.organization;

import java.util.Set;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.Site;
import com.fuwa.ypq.model.TreeModel;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class AddSiteAction extends ActionSupport
{

	String message;
	Site site;
	TreeModel tm;
	
	public TreeModel getTm() {
		return tm;
	}

	public void setTm(TreeModel tm) {
		this.tm = tm;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}


	boolean success;
	private static final long serialVersionUID = 1L;

	public AddSiteAction()
	{
		message = "";
		site = null;
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		String siteName = site.getSite_name();
		Site s = organizationDataManager.findSiteByName(siteName);

		if (s != null)
		{
			message = "该站点已经存在,请重新填写站点名称!";
			success = false;
		} else
		{
			organizationDataManager.addSite(site);
			
			
			System.out.println("site.getSite_name()-->"+site.getSite_name());
			Site newSite = organizationDataManager.findSiteByName(siteName);
			tm = new TreeModel();
			tm.setId(newSite.getUuid());
			tm.setText(newSite.getSite_name());
			tm.setIconCls("depart_icon");
			tm.setNodeType("site");
			tm.setLeaf(true);
			
			message = "添加站点成功!";
			success = true;
		}
		return "success";
	}
}
