// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DelUserAction.java

package com.fuwa.ypq.organization;

import javax.annotation.Resource;

import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.ypq.model.Department;
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
public class DelDepartAction extends ActionSupport
{

	boolean success;
	String deleteDeparts;
	
	
	
	@Resource
	AuthorityManager authorityManager;
    

	public String getDeleteDeparts() {
		return deleteDeparts;
	}

	public void setDeleteDeparts(String deleteDeparts) {
		this.deleteDeparts = deleteDeparts;
	}

	private static final long serialVersionUID = 1L;

	public DelDepartAction()
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
		
		System.out.println("DelDepartAction executed");
		JSONArray jsonArray = JSONArray.fromObject(deleteDeparts);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			System.out.println("jsonObject-->"+jsonObject);
			Department delDepart = (Department)JSONObject.toBean(jsonObject,Department.class);
			organizationDataManager.delDepart(delDepart);
			authorityManager.deleteDepartsACLByDepartId(delDepart.getUuid());
		}

		success = true;
		return "success";
	}
}
