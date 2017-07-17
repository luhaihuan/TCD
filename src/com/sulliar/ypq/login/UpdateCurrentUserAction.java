// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UpdateCurrentUserAction.java

package com.sulliar.ypq.login;

import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateCurrentUserAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private boolean flag;

	public UpdateCurrentUserAction()
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

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		if (user != null)
		{
			FWUser tlg = organizationDataManager.findUserByName(user.getUser_name());
			context.getSession().put("current_user", tlg);
		}
		return "success";
	}
}
