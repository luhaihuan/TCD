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

import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.ypq.model.Department;
import com.opensymphony.xwork2.ActionSupport;

public class GetDepartBySiteIdAction extends ActionSupport
{

	private boolean flag;
	private List departs;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	private String siteId;
	@Resource
	SiteManager siteManager;

	public GetDepartBySiteIdAction()
	{
	}
	



	
	public String getSiteId() {
		return siteId;
	}





	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}





	public List getDeparts() {
		return departs;
	}

	public void setDeparts(List departs) {
		this.departs = departs;
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
	
		System.out.println("lhh action"+toString());
		
		total = siteManager.getDepartCountBySiteId(siteId);
		if (total<limit)
			limit = total;
		
		departs = siteManager.getDepartBySiteId(siteId);
		
		
		flag = true;
		return "JSON";
	}





	@Override
	public String toString() {
		return "GetDepartBySiteIdAction [flag=" + flag + ", departs=" + departs
				+ ", total=" + total + ", start=" + start + ", limit=" + limit
				+ ", siteId=" + siteId + ", siteManager=" + siteManager + "]";
	}






	
}
