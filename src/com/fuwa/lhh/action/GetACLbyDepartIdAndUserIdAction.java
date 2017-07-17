// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetACLbyTypeAction.java

package com.fuwa.lhh.action;

import com.fuwa.lhh.model.ACLModel;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.SiteManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class GetACLbyDepartIdAndUserIdAction extends ActionSupport
{

	private boolean flag;
	String ft;
	String siteId;
	String departId;
	String userId;

	private List models;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	private Sign recvSign ;
	@Resource
	UserManager userManager;
	@Resource
	DepartManager departManager;
	
	@Resource
	SiteManager siteManager;
	
	@Resource
	TypeManager typeManager;
	
	
	
	List siteACLs = new ArrayList();
	
	
	List departACLs = new ArrayList();
	
	
	List userACLs = new ArrayList();
	//List departAndUserACLs ;
	private Site currentSite;
	private Department currentDepart;
	private FWUser currentUser;
	private List<String> userHaveTypeList;
	
	
	

	public GetACLbyDepartIdAndUserIdAction()
	{
	}

	
	
	

	public String getSiteId() {
		return siteId;
	}





	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}





	public String getDepartId() {
		return departId;
	}





	public void setDepartId(String departId) {
		this.departId = departId;
	}





	public String getUserId() {
		return userId;
	}





	public void setUserId(String userId) {
		this.userId = userId;
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

	@SuppressWarnings({ "unchecked", "unchecked" })
	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//ACLDataManager aclManager = (ACLDataManager)beanFactory.getBean("ACLDataManager");
	//	String ftStr = URLDecoder.decode(ft, "utf-8");
		
	//	total = aclManager.getTotalCount(ftStr);
		//models = aclManager.findACLByType(ftStr, 0, total);
		
		
		models = new ArrayList();
		System.out.println("GetACLbyDepartIdAndUserIdAction executed before-->site-->"+departId.equals("null")+"-->"+siteId+"-->group-->"+departId+"-->user-->"+userId);
		siteId = URLDecoder.decode(siteId, "utf-8");
		departId = URLDecoder.decode(departId, "utf-8");
		userId = URLDecoder.decode(userId, "utf-8");
		System.out.println("GetACLbyDepartIdAndUserIdAction executed after-->site-->"+siteId+"-->group-->"+departId+"-->user-->"+userId);
		if(siteId.equals("null")&&departId.equals("null")&&userId.equals("null")) {
			total = models.size();
			flag = true;
			return "JSON";
		}
		
		if(!siteId.equals("null")&&departId.equals("null")&&userId.equals("null"))
			recvSign = Sign.Onlysite;
		
		else if(!siteId.equals("null")&&!departId.equals("null")&&userId.equals("null"))
			recvSign = Sign.SiteAndDepart;
		
		else if(!siteId.equals("null")&&!departId.equals("null")&&!userId.equals("null"))
			recvSign = Sign.SiteAndDepartAndUser;
		
		System.out.println("recvSign-->"+recvSign);
		
		
		if(recvSign == Sign.Onlysite) {
			
			currentSite = siteManager.getSiteById(siteId);
			 siteACLs = siteManager.getSiteACLById(siteId);
			 System.out.println("siteACLs-->"+siteACLs.size());
			 
		} else if(recvSign == Sign.SiteAndDepart) {
			
			currentSite = siteManager.getSiteById(siteId);
			currentDepart = departManager.getDepartById(departId);
			departACLs = departManager.getDepartACLById(siteId,departId);
			 
		} else if(recvSign == Sign.SiteAndDepartAndUser) {
			
			currentSite = siteManager.getSiteById(siteId);
			currentDepart = departManager.getDepartById(departId);
			currentUser = userManager.getUserById(userId);
			userACLs = userManager.getUserACLById(userId);
			departACLs = departManager.getDepartACLById(siteId,departId);
			userHaveTypeList = new ArrayList<String>();
		}
		
		
	
		
		
	
		
		
		
		
	
		
		
		
		
		
		
		
		for(Iterator<Authority> iterator = siteACLs.iterator();iterator.hasNext();) {
			Authority next = iterator.next();
			ACLModel aclModel =new ACLModel();
			aclModel.setSiteStr(currentSite.getSite_name());
			aclModel.setGroupStr(departManager.getDepartById(next.getGroup_id()).getDepartment_name());
			aclModel.setUsername("");
			aclModel.setTypeStr(typeManager.getTypeById(next.getQuery_type_id()).getType_name());
			aclModel.setDownloadExcel(boolean2Str(next.isCan_download_excel()));
			aclModel.setPrintExcel(boolean2Str(next.isCan_print_excel()));
			aclModel.setDownloadPDF(boolean2Str(next.isCan_download_pdf()));
			aclModel.setPrintPDF(boolean2Str(next.isCan_print_pdf()));
			aclModel.setDownloadWord(boolean2Str(next.isCan_download_word()));
			aclModel.setPrintWord(boolean2Str(next.isCan_print_word()));
			aclModel.setBomSearch(boolean2Str(next.isCan_bom_search()));
			aclModel.setMaterialSearch(boolean2Str(next.isCan_material_search()));
			aclModel.setParentSearch(boolean2Str(next.isCan_parent_search()));
			models.add(aclModel);
		}
		
		for(Iterator<Authority> iterator = userACLs.iterator();iterator.hasNext();) {
			Authority next = iterator.next();
			ACLModel aclModel =new ACLModel();
			aclModel.setSiteStr("");
			aclModel.setGroupStr("");
			aclModel.setUsername(currentUser.getUser_name());
			String typeId = next.getQuery_type_id();
			userHaveTypeList.add(typeId);
			aclModel.setTypeStr(typeManager.getTypeById(typeId).getType_name());
			aclModel.setDownloadExcel(boolean2Str(next.isCan_download_excel()));
			aclModel.setPrintExcel(boolean2Str(next.isCan_print_excel()));
			aclModel.setDownloadPDF(boolean2Str(next.isCan_download_pdf()));
			aclModel.setPrintPDF(boolean2Str(next.isCan_print_pdf()));
			aclModel.setDownloadWord(boolean2Str(next.isCan_download_word()));
			aclModel.setPrintWord(boolean2Str(next.isCan_print_word()));
			aclModel.setBomSearch(boolean2Str(next.isCan_bom_search()));
			aclModel.setMaterialSearch(boolean2Str(next.isCan_material_search()));
			aclModel.setParentSearch(boolean2Str(next.isCan_parent_search()));
			models.add(aclModel);
		}
		for(Iterator<Authority> iterator = departACLs.iterator();iterator.hasNext();) {
			
			Authority next = iterator.next();
			
			String typeId = next.getQuery_type_id();
			if(recvSign == Sign.SiteAndDepartAndUser&&userHaveTypeList.contains(typeId)) {
				 continue;
			}
			ACLModel aclModel =new ACLModel();
			aclModel.setSiteStr(currentSite.getSite_name());
			aclModel.setGroupStr(currentDepart.getDepartment_name());
			aclModel.setUsername("");
			aclModel.setTypeStr(typeManager.getTypeById(typeId).getType_name());
			aclModel.setDownloadExcel(boolean2Str(next.isCan_download_excel()));
			aclModel.setPrintExcel(boolean2Str(next.isCan_print_excel()));
			aclModel.setDownloadPDF(boolean2Str(next.isCan_download_pdf()));
			aclModel.setPrintPDF(boolean2Str(next.isCan_print_pdf()));
			aclModel.setDownloadWord(boolean2Str(next.isCan_download_word()));
			aclModel.setPrintWord(boolean2Str(next.isCan_print_word()));
			
			
			aclModel.setBomSearch(boolean2Str(next.isCan_bom_search()));
			aclModel.setMaterialSearch(boolean2Str(next.isCan_material_search()));
			aclModel.setParentSearch(boolean2Str(next.isCan_parent_search()));
			models.add(aclModel);
		}
		
		
	/*	for(Iterator<Authority> iterator = departAndUserACLs.iterator();iterator.hasNext();) {
			Authority next = iterator.next();
			ACLModel aclModel =new ACLModel();
			aclModel.setSiteStr(currentSite.getSite_name());
			aclModel.setGroupStr(currentDepart.getDepartment_name());
			aclModel.setUsername("");
			aclModel.setTypeStr(TypeDataManager.getTypeNameById(next.getQuery_type_id()));
			aclModel.setDownloadExcel(boolean2Str(next.isCan_download_excel()));
			aclModel.setPrintExcel(boolean2Str(next.isCan_print_excel()));
			aclModel.setDownloadPDF(boolean2Str(next.isCan_download_pdf()));
			aclModel.setDownloadWord(boolean2Str(next.isCan_download_word()));
			aclModel.setPrintWord(boolean2Str(next.isCan_print_word()));
			models.add(aclModel);
		}*/
		
		
		total = models.size();
		flag = true;
		return "JSON";
	}
	String boolean2Str(boolean flag) {
		return String.valueOf(flag);
	}
	enum Sign {
		None,
		Onlysite,
		SiteAndDepart,
		SiteAndDepartAndUser
		
	}
}
