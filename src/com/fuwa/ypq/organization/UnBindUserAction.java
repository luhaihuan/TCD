// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DelUserAction.java

package com.fuwa.ypq.organization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
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
public class UnBindUserAction extends ActionSupport
{

	boolean success;
	String deleteUsers;
	Department depart;
	String departId;
	
	

	

	


	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public String getDeleteUsers() {
		return deleteUsers;
	}

	public void setDeleteUsers(String deleteUsers) {
		this.deleteUsers = deleteUsers;
	}

	private static final long serialVersionUID = 1L;

	public UnBindUserAction()
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
	
	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		
		JSONArray userArray = JSONArray.fromObject(deleteUsers);
		
		//List<FWUser> users = new ArrayList<FWUser>();
		//Department srcDepart = organizationDataManager.getDepartById(departId);
		//System.out.println("UnBindUserAction departId-->"+departId+"-->object-->"+srcDepart);
		System.out.println("UnBindUserAction departId-->"+departId);
		Set<FWUser> delUsers = new HashSet<FWUser>();
		for (int i = 0; i < userArray.size(); i++)
		{
			
			FWUser fwUser = (FWUser)JSONObject.toBean(userArray.getJSONObject(i),FWUser.class);
			//FWUser currentUser =  organizationDataManager.findUserById(fwUser.getUuid());
			//fwUser.getGroups().remove(srcDepart);
			delUsers.add(fwUser);
		    // srcDepart.getUsers().remove(fwUser);
		    // currentUser.getGroups().remove(srcDepart);
			//organizationDataManager.modifyUser(fwUser);
			
			//organizationDataManager.modifyUser(currentUser);
			System.out.println("UnBindUserAction-->delete-->"+fwUser.getUser_name());
		}
		organizationDataManager.modifyDepart(departId,delUsers);

		success = true;
		return "success";
	}
}
