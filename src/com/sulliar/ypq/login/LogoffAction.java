// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogoffAction.java

package com.sulliar.ypq.login;

import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;

import java.util.Map;

public class LogoffAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private FWUser user;

	public LogoffAction()
	{
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

	public String execute()
		throws Exception
	{
		ActionContext context = ActionContext.getContext();
		FWUser tlg = (FWUser)context.getSession().get("current_user");
		if (tlg != null)
		{
			success = true;
			context.getSession().remove("current_user");
		} else
		{
			success = false;
		}
		return "success";
	}
}
