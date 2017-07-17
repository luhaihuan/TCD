// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AddUserAction.java

package com.fuwa.lhh.sealManagement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
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
public class AddSealAction extends ActionSupport
{

	String message;
	FWUser fwUser;
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

	public FWUser getFwUser() {
		return fwUser;
	}

	public void setFwUser(FWUser fwUser) {
		this.fwUser = fwUser;
	}

	boolean success;
	private static final long serialVersionUID = 1L;

	public AddSealAction()
	{
		message = "";
		fwUser = null;
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
	
	
		List<Department> userDeparts  =  organizationDataManager.findUserDepartsByUserId(fwUser.getUuid());	
		if(userDeparts != null &&userDeparts.size()>0) {
			String departName = userDeparts.get(0).getDepartment_name();
			String departId = userDeparts.get(0).getUuid();
			String msg = departId.equals(depart.getUuid())?"该部门":departName;
			message = msg+"已经存在该用户,请检查!";
			success = false;
			return "success";
		}		
		Set<FWUser> fwUsers = new HashSet<FWUser>();
		Department currentDepart = organizationDataManager.findDepartById(fwUsers, depart.getUuid());
		
		System.out.println("AddUserAction-->"+fwUser.getUser_name()+" departname-->"+depart.getDepartment_name());
		if (currentDepart != null)
		{
			success = true;
			if(success) {
				FWUser currentUser =  organizationDataManager.findUserById(fwUser.getUuid());
				currentDepart.getUsers().add(currentUser);
				organizationDataManager.modifyDepart(currentDepart);
				tm = new TreeModel();
				tm.setId(currentUser.getUuid());
				tm.setText(currentUser.getUser_name());
				tm.setIconCls("user_icon");
				tm.setNodeType("user");
				tm.setLeaf(true);
				
				message = "添加用户成功!";
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
