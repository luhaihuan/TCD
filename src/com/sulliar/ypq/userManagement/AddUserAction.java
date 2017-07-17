// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AddUserAction.java

package com.sulliar.ypq.userManagement;

import javax.annotation.Resource;

import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class AddUserAction extends ActionSupport
{

	String message;
	FWUser user;
	boolean success;
	private static final long serialVersionUID = 1L;

	public AddUserAction()
	{
		message = "";
		user = null;
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

	public FWUser getUser()
	{
		return user;
	}

	public void setUser(FWUser user)
	{
		this.user = user;
	}

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		
		FWUser tuser = organizationDataManager.findUserByName(user.getUser_name());
		if (tuser != null)
		{
			message = "该用户已经存在,请重新填写用户名称!";
			success = false;
		} else
		{
			organizationDataManager.addUser(user);
			message = "添加用户成功!";
			success = true;
		}
		return "success";
	}
}
