// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ConfigSystemService.java

package com.sulliar.ypq.service;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
import com.sulliar.ypq.model.*;

// Referenced classes of package com.sulliar.ypq.service:
//			ConfigSystem, UserDataManager, LogSettingDataManager, ACLDataManager

public class ConfigSystemService
	implements ConfigSystem
{

	
	LogSettingDataManager logSettingDataManager;


	public ConfigSystemService()
	{
	}



	public LogSettingDataManager getLogSettingDataManager()
	{
		return logSettingDataManager;
	}

	public void setLogSettingDataManager(LogSettingDataManager logSettingDataManager)
	{
		this.logSettingDataManager = logSettingDataManager;
	}



	OrganizationDataManager organizationDataManager;
	
	public OrganizationDataManager getOrganizationDataManager() {
		return organizationDataManager;
	}

	public void setOrganizationDataManager(
			OrganizationDataManager organizationDataManager) {
		this.organizationDataManager = organizationDataManager;
	}

	public void initSystem()
	{
		
		FWUser user = organizationDataManager.findUserByName("infodba");
		LogSetting logsetting = logSettingDataManager.findLogSettingByType("每日最大下载数量");
		if (user == null)
		{
			FWUser infodba = new FWUser();
			infodba.setUser_name("infodba");
			infodba.setUser_pwd("hfplm");
			infodba.setDba(true);
			infodba.setOA(false);
			infodba.setActived(true);
			
			organizationDataManager.addUser(infodba);
		}
		if (logsetting == null)
		{
			LogSetting ls = new LogSetting();
			ls.setType("每日最大下载数量");
			ls.setValue("20");
			logSettingDataManager.add(ls);
		}
		
		Site site = organizationDataManager.findSiteByName("DBA");
		
		if(site == null){
			Site dba = new Site();
			dba.setSite_name("DBA");
			organizationDataManager.addSite(dba);
			
			Department dba1 = new Department();
			dba1.setDepartment_name("dba");
			dba1.setSite(dba);
			organizationDataManager.addDepartment(dba1);		
			
			FWUser infodba = organizationDataManager.findUserByName("infodba");
			Set<FWUser> users = new HashSet<FWUser>();
			users.add(infodba);
			dba1.setUsers(users);
			
			organizationDataManager.modifyDepartment(dba1);
			
			
		}
		
		
	}
}
