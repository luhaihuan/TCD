// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SaveFileAction.java

package com.sulliar.ypq.fileManagement;

import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.model.Stamp;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.LogManager;
import com.fuwa.lhh.service.RecordManager;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.ypq.model.Department;
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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class SaveFiletoAction extends ActionSupport
{

	String nodePath;
	String filePath;
	boolean flag;
	boolean success;
	boolean previewExcel;
	String dataset_type;
	String path;
	String waterm_name;
	String item_id;
	String item_id_new;
	String item_name;
	String item_rev;
	String wp;
	
	boolean overDownload;
	private static final long serialVersionUID = 1L;
	@Resource
	DepartManager departManager;
	@Resource
	LogManager logManager;
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;
	
	String searchType;
	@Resource
	RecordManager recordManager;
	
	@Resource
	StampManager stampManager;

	
	
	
	public boolean isPreviewExcel() {
		return previewExcel;
	}

	public void setPreviewExcel(boolean previewExcel) {
		this.previewExcel = previewExcel;
	}

	public String getItem_id_new() {
		return item_id_new;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setItem_id_new(String item_id_new) {
		this.item_id_new = item_id_new;
	}

	public SaveFiletoAction()
	{
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

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public String getDataset_type()
	{
		return dataset_type;
	}

	public void setDataset_type(String dataset_type)
	{
		this.dataset_type = dataset_type;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getWaterm_name()
	{
		return waterm_name;
	}

	public void setWaterm_name(String waterm_name)
	{
		this.waterm_name = waterm_name;
	}

	public String getWp()
	{
		return wp;
	}

	public void setWp(String wp)
	{
		this.wp = wp;
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

	public boolean isOverDownload()
	{
		return overDownload;
	}

	public void setOverDownload(boolean overDownload)
	{
		this.overDownload = overDownload;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		//UserDataManager userDataManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		//LogDataManager logDataManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		System.out.println(toString());
		File sourceFile = new File(URLDecoder.decode(filePath, "utf-8"));
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println((new StringBuilder("realpath:")).append(realpath).toString());
		
		
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
			Department department = departManager.getPersonDepartByUserId(user.getUuid());
			System.out.println("用户组别:"+department.getDepartment_name());
			
			waterm_name = URLDecoder.decode(waterm_name, "utf-8");
			
			String[] water_array = waterm_name.split(",");
			List<Stamp> stampList = stampManager.getStampInArray(water_array);
			System.out.println("previewExcel-->"+previewExcel);

			path = fileManager.DownloadFile(sourceFile, URLDecoder.decode(dataset_type, "utf-8"), realpath,stampList , user.getUser_name(), URLDecoder.decode(wp, "utf-8"), department.getDepartment_name(),previewExcel);
			System.out.println(toString());
			System.out.println("Path--->"+path);
/*			if(path!=null  && (path.endsWith(".xls") || path.endsWith(".xlsx"))) {
				path = new String(path.getBytes(), "ISO8859-1");//解决下载中文名乱码
				System.out.println("after Path--->"+path);
			}*/
			System.out.println("保存成功");
			flag = true;
			success = true;
			//User tlg = userDataManager.findUserByName(user.getName());
			FWLog log = new FWLog();
			log.setUser(user);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
			log.setAction_date(sdf.format(new Date()));
			log.setAction_type("下载");
			
			
			if(!item_id.equals(""))
			log.setItem_id(URLDecoder.decode(item_id, "utf-8"));
			else if(!item_id_new.equals(""))
			log.setItem_id(URLDecoder.decode(item_id_new, "utf-8"));
			
			
			log.setItem_name(URLDecoder.decode(item_name, "utf-8"));
			log.setItem_rev(URLDecoder.decode(item_rev, "utf-8"));
			overDownload = logManager.saveFWLog(log);
		} else
		{
			flag = false;
			success = false;
		}
		return "success";
	}

	@Override
	public String toString() {
		return "SaveFiletoAction [nodePath=" + nodePath + ", filePath="
				+ filePath + ", flag=" + flag + ", success=" + success
				+ ", dataset_type=" + dataset_type + ", path=" + path
				+ ", waterm_name=" + waterm_name + ", item_id=" + item_id
				+ ", item_id_new=" + item_id_new + ", item_name=" + item_name
				+ ", item_rev=" + item_rev + ", wp=" + wp + ", overDownload="
				+ overDownload + ", departManager=" + departManager
				+ ", logManager=" + logManager + "]";
	}
	
}
