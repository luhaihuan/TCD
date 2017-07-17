// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportACLAction.java

package com.fuwa.lhh.action;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.ItemModel;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class CheckItemRevUpdateAction extends ActionSupport {

	String type_name;
	String item_id;
	String item_rev;
	String message;
	boolean success;
	List items;
	private static final long serialVersionUID = 1L;
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;
	
	@Resource
	TypeManager typeManager;


	public CheckItemRevUpdateAction() {
		
	}


	

	
	public List getItems() {
		return items;
	}





	public void setItems(List items) {
		this.items = items;
	}





	public String getType_name() {
		return type_name;
	}




	public void setType_name(String type_name) {
		this.type_name = type_name;
	}




	public String getItem_id() {
		return item_id;
	}




	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}




	public String getItem_rev() {
		return item_rev;
	}




	public void setItem_rev(String item_rev) {
		this.item_rev = item_rev;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
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

	public String execute()
		throws Exception
	{
		
		System.out.println(toString());
		success = false;
		/*
		type_name = URLDecoder.decode(type_name, "utf-8");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		ActionContext context = ActionContext.getContext();
		FWUser fwUser = (FWUser)context.getSession().get("current_user");
		SearchType typeByName = typeManager.getTypeByName(type_name);
	
		if(typeByName.isHave_old_code())
		items = fileManager.getSearchFiles(URLDecoder.decode(item_id, "utf-8"),"","", "", "",type_name , fwUser != null ? fwUser.getUser_name() : "");
		else
		items = fileManager.getSearchFiles("",URLDecoder.decode(item_id, "utf-8"),"", "", "",type_name , fwUser != null ? fwUser.getUser_name() : "");
		if(items != null ) {
			for(Iterator<ItemModel> iterator = items.iterator();iterator.hasNext();) {
				ItemModel next = iterator.next();
				System.out.println(item_rev+"-->当前版本非最新,最新版本为:--->"+next.getItem_revision());
				
				if(checkUpdate(item_rev, next.getItem_revision())) {
					success = true;
					message = "当前版本非最新,最新版本为:"+next.getItem_revision();
					break;
				}
				
			}
		}*/
		 String lastItemRev = fileManager.getLastItemRev(item_id);
		 
		 if(checkUpdate(item_rev,lastItemRev)) {
			 success = true;
			 message = "当前版本非最新,最新版本为:"+lastItemRev;
		 }
		
		
		return "success";
	}
	
	 boolean checkUpdate(String item_rev_old,String item_rev_new) {
		if(item_rev_old == null || item_rev_old.equals(""))
			return false;
		if(item_rev_new == null || item_rev_new.equals(""))
			return false;
		char A = item_rev_old.toUpperCase().charAt(0);
		char B = item_rev_new.toUpperCase().charAt(0);
		if(!(A >= 65 && A<= 90) || !(B >= 65 && B <= 90)) {
			return false;
		}
		if(B > A)
			return true;
		else
			return false;
		
	}





	@Override
	public String toString() {
		return "CheckItemRevUpdateAction [type_name=" + type_name
				+ ", item_id=" + item_id + ", item_rev=" + item_rev
				+ ", message=" + message + ", success=" + success + ", items="
				+ items + "]";
	}
	
	
}
