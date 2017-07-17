// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModUserAction.java

package com.fuwa.ypq.organization;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class ModDepartAction extends ActionSupport
{

	String message;
	Department depart;


	public Department getDepart() {
		return depart;
	}


	public void setDepart(Department depart) {
		this.depart = depart;
	}

	boolean success;
	String uuid;
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private static final long serialVersionUID = 1L;

	public ModDepartAction()
	{
		message = "";
		depart = null;
		uuid = "";
	}

	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
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
		Department curentDepart = organizationDataManager.findDepartById(uuid);
		
		if(curentDepart != null ){
			if(curentDepart.getDepartment_name().equals("dba")){
				message = "违法修改dba部门!";
				success = false;
			}else{
				curentDepart.setDepartment_name(depart.getDepartment_name());
				organizationDataManager.modifyDepart(curentDepart);
				success = true;
			}
		}		
		
		return "success";
	}
}
