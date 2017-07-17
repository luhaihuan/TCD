// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SaveACLbyTypeAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.model.DepartACLModel;
import com.fuwa.lhh.model.UserACLModel;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.ACLModel;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.net.URLDecoder;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;

public class SaveUsersACLbyTypeAction extends ActionSupport
{

	private boolean success;
	String ft;
	private String addModels;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	
	


	@Resource
	UserManager userManager;

	@Resource
	AuthorityManager authorityManager;

	@Resource
	TypeManager typeManager;

	public SaveUsersACLbyTypeAction()
	{
	}

	public String getFt()
	{
		return ft;
	}

	public void setFt(String ft)
	{
		this.ft = ft;
	}

	public String getAddModels()
	{
		return addModels;
	}

	public void setAddModels(String addModels)
	{
		this.addModels = addModels;
	}

	public boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public String execute()
		throws Exception
	{/*
		
		String ftStr = URLDecoder.decode(ft, "utf-8");

		JSONArray jsonArray = JSONArray.fromObject(addModels);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			UserACLModel m = (UserACLModel)JSONObject.toBean(jsonObject, UserACLModel.class);
			String un = m.getUserName();
			if (un != null && un.length() != 0)
			{
				System.out.println(m.toString());
				
			}
		}

		success = true;
		return "success";
	*/


		String typeId = URLDecoder.decode(ft, "utf-8");

		JSONArray jsonArray = JSONArray.fromObject(addModels);
		//QueryType type = typeManager.getTypeById(typeId);
		/*if(type==null) {
			type=new QueryType();
			type.setQuery_type_name(typeName);
			typeManager.save(type);
			//type = typeManager.getTypeByName(typeName);
		}*/
		authorityManager.deleteUsersByTypeId(typeId);//清除数据库数据,重新批量插入
		for (int i = 0; i < jsonArray.size(); i++)
		{

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			UserACLModel model = (UserACLModel)JSONObject.toBean(jsonObject, UserACLModel.class);
			String un = model.getUserName();
			if (un != null && un.length() != 0)
			{
				System.out.println(model.toString());
				String userId=model.getUserId();
	
				
			
				//FWUser fwUser = userManager.getUserByName(userName);
			
				
			/*	if(fwUser==null) {
					fwUser=new FWUser();
					fwUser.setUser_name(userName);
					userManager.save(fwUser);
				}*/

				//Authority authority = authorityManager.getAuthorityByUserId(fwUser.getUuid());
				//if(authority == null) {
				   Authority	authority=new Authority();
                    authority.setUser_id(userId);
					authority.setQuery_type_id(typeId);
					authority.setCan_download_excel(model.isDownloadExcel());
					authority.setCan_print_excel(model.isPrintExcel());
					authority.setCan_download_pdf(model.isDownloadPDF());
					authority.setCan_print_pdf(model.isPrintPDF());
					authority.setCan_download_word(model.isDownloadWord());
					authority.setCan_print_word(model.isPrintWord());	
					authority.setCan_bom_search(model.isBomSearch());
					authority.setCan_material_search(model.isMaterialSearch());
					authority.setCan_parent_search(model.isParentSearch());
					authorityManager.save(authority);

				/*} else {
					authority.setQuery_type_id(type.getUuid());
					authority.setCan_download_excel(model.isDownloadExcel());
					authority.setCan_print_excel(model.isPrintExcel());
					authority.setCan_download_pdf(model.isDownloadPDF());
					authority.setCan_print_pdf(model.isPrintPDF());
					authority.setCan_download_word(model.isDownloadWord());
					authority.setCan_print_word(model.isPrintWord());
					authorityManager.update(authority);
					
				}*/
				System.out.println("help--------->"+authority.toString());



			}
		}

		success = true;
		return "success";
	
		
		}
}
