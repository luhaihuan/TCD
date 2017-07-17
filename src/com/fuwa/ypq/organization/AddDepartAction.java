// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AddUserAction.java

package com.fuwa.ypq.organization;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.Site;
import com.fuwa.ypq.model.TreeModel;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
public class AddDepartAction extends ActionSupport
{

	String message;
	Department depart;
	TreeModel tm;
	
	
	
    





	public TreeModel getTm() {
		return tm;
	}

	public void setTm(TreeModel tm) {
		this.tm = tm;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	boolean success;
	private static final long serialVersionUID = 1L;

	public AddDepartAction()
	{
		message = "";
		depart = null;
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

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		Set<Department> departments = new HashSet<Department>();
		Site s = organizationDataManager.findSiteById(depart.getSite().getUuid(),departments);

		System.out.println("AddDepartAction-->"+depart.getDepartment_name()+" site-id-->"+depart.getSite().getUuid());
		if (s != null)
		{
			success = true;
			for(Department d:departments) {
				if(d.getDepartment_name().equals(depart.getDepartment_name())) {
					message = "该站点已经存在该部门,请重新填写部门名称!";
					success = false;
					break;
				}
			}
			
			if(success) {
				depart.setSite(s);
				organizationDataManager.addDepartment(depart);
				Department newDepart = organizationDataManager.getDepartBySiteIdAndDepartName(s.getUuid(),depart.getDepartment_name());
				tm = new TreeModel();
				tm.setId(newDepart.getUuid());
				tm.setText(newDepart.getDepartment_name());
				tm.setIconCls("depart_icon");
				tm.setNodeType("department");
				tm.setLeaf(true);
				System.out.println("newDepart-->"+newDepart.getDepartment_name()+"-->"+newDepart.getUuid());
				message = "添加部门成功!";
			}
			
			
		} /*else
		{
			organizationDataManager.addSite(site);
			message = "添加站点成功!";
			success = true;
		}*/
		return "success";
	}
}
