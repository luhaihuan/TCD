// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LDAPLoginAction.java

package com.sulliar.ypq.login;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class LDAPLoginAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private User user;
	@Resource(name = "UserDataManager")
	UserDataManager userManager;

	public LDAPLoginAction()
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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//UserDataManager userManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		Properties prop = System.getProperties();
		System.out.println(prop.getProperty("user.name"));
		User tlg = userManager.findUserByName(prop.getProperty("user.name"));
		if (tlg != null && tlg.getIsLDAP())
		{
			success = true;
			ActionContext context = ActionContext.getContext();
			context.getSession().put("current_user", tlg);
			user = tlg;
		} else
		{
			success = false;
			message = "对不起，未授权的用户不能登录改系统";
		}
		return "success";
	}
}
