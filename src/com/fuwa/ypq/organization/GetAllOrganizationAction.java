// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetACLbyAttrsAction.java

package com.fuwa.ypq.organization;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class GetAllOrganizationAction extends ActionSupport
{

	private boolean flag;
	private List models;	
	
	private static final long serialVersionUID = 1L;

	public GetAllOrganizationAction()
	{
		flag = false;
	}

	public List getModels()
	{
		return models;
	}

	public void setModels(List models)
	{
		this.models = models;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}
	
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

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		System.out.println(toString());
		models = organizationDataManager.getAllOrganization(id,nodeType);
		
		System.out.println(flag);		
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetAllOrganizationAction [id=" + id + ", nodeType=" + nodeType
				+ "]";
	}
	
}
