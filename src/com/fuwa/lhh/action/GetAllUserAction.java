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

import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllUserAction extends ActionSupport
{

	private boolean flag;
	private List users;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	UserManager userManager;

	public GetAllUserAction()
	{
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
	
		System.out.println("GetAllMyUserAction execute-->");
/*		List allUser = userManager.getAll();
		users = new ArrayList<>();
		for(Iterator<FWUser> iterator=allUser.iterator();iterator.hasNext();) {
			FWUser fwUser = iterator.next();
			users.add(new UserModel(fwUser.getUuid(), fwUser.getUser_name()));
		}
		*/
		//users=userManager.getAll();
/*		users = new ArrayList();
		users.add(new Model("张三"));
		users.add(new Model("李四"));*/
		users = userManager.getAllUsers();
		total=users.size();
		limit=total;
		flag = true;
		return "JSON";
	}
}
