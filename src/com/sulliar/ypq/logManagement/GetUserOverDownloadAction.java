// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SaveACLbyTypeAction.java

package com.sulliar.ypq.logManagement;

import com.fuwa.lhh.model.DepartACLModel;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.LogManager;
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
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;

public class GetUserOverDownloadAction extends ActionSupport
{

	private boolean success;

	private static final long serialVersionUID = 1L;
	private String message;
	@Resource
	LogManager logManager;

	private List<String> dataList;


	
	public GetUserOverDownloadAction()
	{
	}

	

	
	public List<String> getDataList() {
		return dataList;
	}




	public void setDataList(List<String> dataList) {
		this.dataList = dataList;
	}




	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String execute()
			throws Exception
	{

		
        dataList = logManager.getUserOverDownloadLogs();
        StringBuffer buffer =new StringBuffer();
        for(String s : dataList) {
        	buffer.append("'")
        	      .append(s)
        	      .append("'")
        	      .append("下载量超过公司要求,请检查<br/>");
        }
        message =buffer.toString();
        if(dataList.size()>0)
		success = true;
        else
        success = false;	
        
		return "success";
	}





	
	

}
