// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetSearchItemAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.model.DepartACLModel;
import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.model.UserACLModel;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.BaseDao;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.RecordManagerService;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
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

public class GetUserACLbyTypeAction extends ActionSupport
{

	private boolean flag;
	String ft;
	private List models;
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

	public GetUserACLbyTypeAction()
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
		System.out.println("GetUserACLbyTypeAction typeId:"+typeId);
		if(typeId!=null&&!typeId.equals("")) {
			//QueryType type = typeManager.getTypeByName(typeId);
			List userACL = authorityManager.getUsersACLByTypeId(typeId);
			models = new ArrayList();
			for(Iterator<Authority> iterator=userACL.iterator();iterator.hasNext();) {
				Authority authority=iterator.next();
				FWUser fwUser = userManager.getUserById(authority.getUser_id());
			    if(fwUser != null) { //存在用户已删除,权限记录还存在的情况
			    	UserACLModel userACLModel=new UserACLModel();
					userACLModel.setUserId(fwUser.getUuid());
					userACLModel.setUserName(fwUser.getUser_name());
					userACLModel.setDownloadExcel(authority.isCan_download_excel());
					userACLModel.setPrintExcel(authority.isCan_print_excel());
					userACLModel.setDownloadPDF(authority.isCan_download_pdf());
					userACLModel.setPrintPDF(authority.isCan_print_pdf());
					userACLModel.setDownloadWord(authority.isCan_download_word());
					userACLModel.setPrintWord(authority.isCan_print_word());
					userACLModel.setBomSearch(authority.isCan_bom_search());
					userACLModel.setMaterialSearch(authority.isCan_material_search());
					userACLModel.setParentSearch(authority.isCan_parent_search());
					models.add(userACLModel);
			    } else {
			    	authorityManager.deleteUsersACLByUserId(authority.getUser_id());//清除无用记录
			    	
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
}
