// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetSearchItemAction.java

package com.fuwa.lhh.action;





import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;


import com.fuwa.lhh.service.SiteManager;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllSiteAction extends ActionSupport
{

	private boolean flag;
	private List sites;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	SiteManager siteManager;

	public GetAllSiteAction()
	{
	}



	
	

	public List getSites() {
		return sites;
	}






	public void setSites(List sites) {
		this.sites = sites;
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

	public String execute()throws Exception {
	
		System.out.println("GetAllSiteAction execute-->");
//		List allSite = siteManager.getAll();
//		sites = new ArrayList<>();
/*		for(Iterator<Site> iterator=allSite.iterator();iterator.hasNext();) {
			Site site = iterator.next();
			sites.add(new SiteModel(site.getUuid(), site.getSite_name()));
		}*/
		
		//sites=siteManager.getAll();
/*		sites = new ArrayList();
		sites.add(new Model("Ì¨É½"));
		sites.add(new Model("Ë³µÂ"));*/
		sites = siteManager.getAllSite();
		total = sites.size();
		limit=total;
		flag = true;
		return "JSON";
	}
	
	
}
