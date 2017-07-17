// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SaveACLbyTypeAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.model.DepartACLModel;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
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

public class SaveDepartsACLbyTypeAction extends ActionSupport
{

	private boolean success;
	String ft;
	private String addModels;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	SiteManager siteManager;

	@Resource
	DepartManager departManager;

	@Resource
	AuthorityManager authorityManager;

	@Resource
	TypeManager typeManager;

	public SaveDepartsACLbyTypeAction()
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
	{

		String typeId = URLDecoder.decode(ft, "utf-8");

		JSONArray jsonArray = JSONArray.fromObject(addModels);
		
	    System.out.println(toString());
		authorityManager.deleteDepartsByTypeId(typeId); //清除数据库数据,重新批量插入
		
		System.out.println("save depart ACLSize--->"+jsonArray.size());
		
		for (int i = 0; i < jsonArray.size(); i++)
		{

			JSONObject jsonObject = jsonArray.getJSONObject(i);
			DepartACLModel model = (DepartACLModel)JSONObject.toBean(jsonObject, DepartACLModel.class);
			String un = model.getDepartName();
			if (un != null && un.length() != 0)
			{
				
				
				
				System.out.println(model.toString());
				String departId=model.getDepartId();
				String siteId=model.getSiteId();
				//Site site = siteManager.getSiteByName(siteName);
				
			/*	if(site==null) {
					site=new Site();
					site.setSite_name(siteName);
					siteManager.save(site);
					//site = siteManager.getSiteByName(siteName);
				}*/
				//Department department = departManager.getDepartByName(departName);
				/*if(department == null) {
					department=new Department();
					department.setDepartment_name(departName);
					department.setSite(site);
					departManager.save(department);
					//department = departManager.getDepartByName(departName);
				}*/
				
			

				//Authority authority = authorityManager.getAuthorityBySiteIdAndDepartId(site.getUuid(), department.getUuid());
				//if(authority == null) {*/
				    Authority authority=new Authority();
					authority.setSite_id(siteId);
					authority.setGroup_id(departId);
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

			/*	} else {
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

	@Override
	public String toString() {
		return "SaveDepartsACLbyTypeAction [success=" + success + ", ft=" + ft
				+ ", addModels=" + addModels + ", total=" + total + ", start="
				+ start + ", limit=" + limit + "]";
	}
	

}
