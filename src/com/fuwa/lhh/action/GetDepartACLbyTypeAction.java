// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetSearchItemAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.model.DepartACLModel;
import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.QueryType;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.dao.DeadlockLoserDataAccessException;

public class GetDepartACLbyTypeAction extends ActionSupport
{

	private boolean flag;
	String ft;
	private List models;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	@Resource
	SiteManager siteManager ;
	
	@Resource
	DepartManager departManager;
	
	@Resource
	AuthorityManager authorityManager;
	
	@Resource
	TypeManager typeManager;

	public GetDepartACLbyTypeAction()
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

	public List getModels()
	{
		return models;
	}

	public void setModels(List models)
	{
		this.models = models;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
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

	public String execute()throws Exception {
	
		
	
		String typeId = URLDecoder.decode(ft, "utf-8");
		System.out.println(toString());
		if(typeId!=null&&!typeId.equals("")) {
			//QueryType type = typeManager.getTypeByName(typeName);
			List departACL = authorityManager.getDepartACLByTypeId(typeId);
			System.out.println("departACL--------->"+departACL.size());
			models = new ArrayList();
			for(Iterator<Authority> iterator=departACL.iterator();iterator.hasNext();) {
				Authority authority=iterator.next();
				Department department = departManager.getDepartById(authority.getGroup_id());
				
				if(department != null) { //存在部门已删除权限记录残留的情况
					Site site = siteManager.getSiteById(authority.getSite_id());
					DepartACLModel departACLModel=new DepartACLModel();
					departACLModel.setSiteId(site.getUuid());
					departACLModel.setDepartId(department.getUuid());
					departACLModel.setSiteName(site.getSite_name());
					departACLModel.setDepartName(department.getDepartment_name());
					departACLModel.setDownloadExcel(authority.isCan_download_excel());
					departACLModel.setPrintExcel(authority.isCan_print_excel());
					departACLModel.setDownloadPDF(authority.isCan_download_pdf());
					departACLModel.setPrintPDF(authority.isCan_print_pdf());
					departACLModel.setDownloadWord(authority.isCan_download_word());
					departACLModel.setPrintWord(authority.isCan_print_word());
					departACLModel.setBomSearch(authority.isCan_bom_search());
					departACLModel.setMaterialSearch(authority.isCan_material_search());
					departACLModel.setParentSearch(authority.isCan_parent_search());
					models.add(departACLModel);
				} else {
					authorityManager.deleteDepartsACLByDepartId(authority.getGroup_id());
					System.out.println("del deparACL-->"+authority.getGroup_id());
				}
				
				
			}
		}
		total = models.size();
		limit = total;
		
		//models.add(new DepartACLModel("顺德", "研发部", true, true, true, true, true, true));
		//models.add(new DepartACLModel("台山", "运维部", false, false, false, false, false, false));
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetDepartACLbyTypeAction [flag=" + flag + ", ft=" + ft
				+ ", models=" + models + ", total=" + total + ", start="
				+ start + ", limit=" + limit + ", siteManager=" + siteManager
				+ ", departManager=" + departManager + ", authorityManager="
				+ authorityManager + ", typeManager=" + typeManager + "]";
	}
	
}
