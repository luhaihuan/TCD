// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PreviewFileAction.java

package com.sulliar.ypq.fileManagement;

import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.service.LogManager;
import com.fuwa.lhh.service.RecordManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.FWLog;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.Log;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.*;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.File;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class PreviewFileAction extends ActionSupport
{

	String nodePath;
	String filePath;
	boolean flag;
	String resultPath;
	String dataset_type;
	String item_id;
	String item_id_new;
	String item_name;
	String item_rev;
	private static final long serialVersionUID = 1L;
	
	@Resource(name="FileDataManager")
	FileDataManager fileManager;
	
	@Resource
	LogManager logManager;
	
	String searchType;
	@Resource
	RecordManager recordManager;
	
	@Resource
	UserManager userManager;

	public PreviewFileAction()
	{
	}

	
	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getItem_id_new() {
		return item_id_new;
	}


	public void setItem_id_new(String item_id_new) {
		this.item_id_new = item_id_new;
	}


	public String getNodePath()
	{
		return nodePath;
	}

	public void setNodePath(String nodePath)
	{
		this.nodePath = nodePath;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public String getResultPath()
	{
		return resultPath;
	}

	public void setResultPath(String resultPath)
	{
		this.resultPath = resultPath;
	}

	public String getDataset_type()
	{
		return dataset_type;
	}

	public void setDataset_type(String dataset_type)
	{
		this.dataset_type = dataset_type;
	}

	public String getItem_id()
	{
		return item_id;
	}

	public void setItem_id(String item_id)
	{
		this.item_id = item_id;
	}

	public String getItem_name()
	{
		return item_name;
	}

	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	public String getItem_rev()
	{
		return item_rev;
	}

	public void setItem_rev(String item_rev)
	{
		this.item_rev = item_rev;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		//LogDataManager logDataManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		//UserDataManager userDataManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		//System.out.println("searchtype-->"+searchType);
		//System.out.println(toString());
		if(item_id.equals("")||item_id.equals("null"))
			item_id = item_id_new;
			
		File sourceFile = new File(URLDecoder.decode(filePath, "utf-8"));
		String path = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println((new StringBuilder("realpath:")).append(path).toString());
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		
		searchType = URLDecoder.decode(searchType, "utf-8");
		item_name = URLDecoder.decode(item_name, "utf-8");
		recordManager.deleteOldData();
		Record recordModel = recordManager.getRecordModel(user.getUser_name(),searchType,item_id, item_rev);
		Date date = new Date();
		if(recordModel==null) {
		
		
			Record model=new Record(user.getUser_name(), searchType, item_id, item_name, item_rev, date);
			recordManager.save(model);
		} else {
		
			
			recordModel.setRecord_date(date);

			recordManager.update(recordModel);
		}
		if (sourceFile.exists())
		{
			System.out.println(toString());
			resultPath = fileManager.FileToSWFPath(sourceFile, URLDecoder.decode(dataset_type, "utf-8"), path);
			System.out.println("resultPath----->"+resultPath);
			flag = true;
			/*ActionContext context = ActionContext.getContext();
			FWUser user = (FWUser)context.getSession().get("current_user");*/
			//FWUser fwUser = userManager.getUserById(user.getUuid());
			FWLog log = new FWLog();
			log.setUser(user);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
			log.setAction_date(sdf.format(new Date()));
			log.setAction_type("预览");
			log.setItem_id(URLDecoder.decode(item_id, "utf-8"));
			log.setItem_name(URLDecoder.decode(item_name, "utf-8"));
			log.setItem_rev(URLDecoder.decode(item_rev, "utf-8"));
			System.out.println("log userID-->"+user.getUuid());
			logManager.save(log);
		} else
		{
			resultPath = null;
			flag = true;
		}
		return "success";
	}


	@Override
	public String toString() {
		return "PreviewFileAction [nodePath=" + nodePath + ", filePath="
				+ filePath + ", flag=" + flag + ", resultPath=" + resultPath
				+ ", dataset_type=" + dataset_type + ", item_id=" + item_id
				+ ", item_id_new=" + item_id_new + ", item_name=" + item_name
				+ ", item_rev=" + item_rev + ", fileManager=" + fileManager
				+ ", logManager=" + logManager + ", searchType=" + searchType
				+ ", recordManager=" + recordManager + "]";
	}


	


	
	
}
