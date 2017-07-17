// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModUserAction.java

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
public class ModUserAction extends ActionSupport
{

	String message;
	FWUser user;
	boolean success;
	String username;
	private static final long serialVersionUID = 1L;

	public ModUserAction()
	{
		message = "";
		user = null;
		username = "";
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{		
		if (username.equals("infodba"))
		{
			if (!user.getDba())
			{
				message = "违法修改infodba用户!infodba用户必须是管理员!";
				success = false;
			} else
			{
				FWUser tuser = organizationDataManager.findUserByName(username);
				if (tuser != null)
				{
					tuser.setUser_pwd(user.getUser_pwd());
					//tuser.setEmail(user.getEmail());
					tuser.setUser_no(user.getUser_no());
					tuser.setDba(user.getDba());
					tuser.setOA(user.getOA());					
					tuser.setActived(user.isActived());
					organizationDataManager.modifyUser(tuser);
					success = true;
				} else
				{
					message = "修改用户失败!";
					success = false;
				}
			}
		} else
		{
			FWUser tuser = organizationDataManager.findUserByName(username);
			if (tuser != null)
			{
				tuser.setUser_pwd(user.getUser_pwd());
				//tuser.setEmail(user.getEmail());
				tuser.setUser_no(user.getUser_no());
				tuser.setDba(user.getDba());
				tuser.setOA(user.getOA());					
				tuser.setActived(user.isActived());
				organizationDataManager.modifyUser(tuser);
				success = true;
			} else
			{
				message = "修改用户失败!";
				success = false;
			}
		}
		return "success";
	}
}
