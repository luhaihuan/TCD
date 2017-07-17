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
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;

public class GetUserByDepartIdAction extends ActionSupport
{

	private boolean flag;
	private List users;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	private String departId;
	@Resource
	DepartManager departManager;

	public GetUserByDepartIdAction()
	{
	}
	



	
	




	
	public String getDepartId() {
		return departId;
	}











	public void setDepartId(String departId) {
		this.departId = departId;
	}











	public List getUsers() {
		return users;
	}





	public void setUsers(List users) {
		this.users = users;
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
	
		System.out.println("lhh action-->"+toString());
		//List allDepart;
		
		// List allUsers = new ArrayList();
	
		
		total = departManager.getUserCountByDepartId(departId);
		if (total<limit)
			limit = total;
		
		users = departManager.getUsersByDepartId(departId);
		System.out.println("GetUserByDepartIdAction-->"+users.size()+"-->count-->"+total);
		
		flag = true;
		return "JSON";
	}











	@Override
	public String toString() {
		return "GetUserByDepartIdAction [flag=" + flag + ", users=" + users
				+ ", total=" + total + ", start=" + start + ", limit=" + limit
				+ ", departId=" + departId + ", departManager=" + departManager
				+ "]";
	}
}
	
