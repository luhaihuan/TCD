// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetAllUserAction.java

package com.fuwa.ypq.organization;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.LogDataManager;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDeparBySiteIdtAction extends ActionSupport
{

	private boolean flag;
	private List departs;
	String id;
	String nodeType;

    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public List getDeparts() {
		return departs;
	}

	public void setDeparts(List departs) {
		this.departs = departs;
	}

	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;

	public GetDeparBySiteIdtAction()
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
		
        System.out.println("organization-->"+toString());
		total = organizationDataManager.getDepartCountBySiteId(id);
		if (total<limit)
			limit = total;
		departs = organizationDataManager.getDepartsBySiteId(id, start, limit);
		
		
		flag = true;
		System.out.println(toString());
		
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetDeparBySiteIdtAction [flag=" + flag + ", total=" + total
				+ ", start=" + start + ", limit=" + limit + "]";
	}

}
