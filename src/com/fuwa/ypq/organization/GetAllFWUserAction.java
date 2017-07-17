// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetACLbyAttrsAction.java

package com.fuwa.ypq.organization;

import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class GetAllFWUserAction extends ActionSupport
{

	

    	
    	
	private boolean flag;
	private List users;	
	private int total;
	private int limit;
	private int start;
	
	public List getUsers() {
		return users;
	}

	public void setUsers(List users) {
		this.users = users;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	private static final long serialVersionUID = 1L;

	public GetAllFWUserAction()
	{
		flag = false;
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

	@Resource
	UserManager userManager;
	
	public String execute()
		throws Exception
	{
		System.out.println("organization-->"+toString());
		users =userManager.getAllUsers();
		System.out.println("FWUser-->size"+users.size());
/*		for(Iterator<FWUser> iterator=users.iterator();iterator.hasNext();) {
			FWUser fwUser = iterator.next();
			System.out.println(fwUser.getUser_name());
		}*/
		total=users.size();
		limit=total;
		System.out.println(flag);		
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetAllFWUserAction [flag=" + flag + ", users=" + users
				+ ", total=" + total + ", limit=" + limit + ", start=" + start
				+ ", id=" + id + ", nodeType=" + nodeType + ", userManager="
				+ userManager + "]";
	}
	
}
