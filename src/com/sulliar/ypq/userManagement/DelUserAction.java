// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DelUserAction.java

package com.sulliar.ypq.userManagement;

import javax.annotation.Resource;

import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class DelUserAction extends ActionSupport
{

	boolean success;
	String deleteUsers;
	private static final long serialVersionUID = 1L;
	
	@Resource
	AuthorityManager authorityManager;

	public DelUserAction()
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

	public String getDeleteUsers()
	{
		return deleteUsers;
	}

	public void setDeleteUsers(String deleteUsers)
	{
		this.deleteUsers = deleteUsers;
	}
	
	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		JSONArray jsonArray = JSONArray.fromObject(deleteUsers);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			FWUser fwUser = (FWUser)JSONObject.toBean(jsonObject,FWUser.class);
			System.out.println("DelUserAction-->"+fwUser);
			organizationDataManager.delUser(fwUser);
			authorityManager.deleteUsersACLByUserId(fwUser.getUuid());
		}

		success = true;
		return "success";
	}
}
