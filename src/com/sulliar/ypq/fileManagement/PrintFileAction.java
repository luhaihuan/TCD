// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PrintFileAction.java

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
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class PrintFileAction extends ActionSupport
{

	String nodePath;
	String filePath;
	String printer_name;
	boolean flag;
	String dataset_type;
	String waterm_name;
	String item_id;
	String item_name;
	String item_rev;
	String ps;
	String po;
	String wp;
	private static final long serialVersionUID = 1L;
	@Resource
	DepartManager departManager;
	
	String searchType;
	@Resource
	RecordManager recordManager;
	
	@Resource(name = "UserDataManager")
	UserDataManager userDataManager ;
	
	@Resource(name = "logManagerService")
	LogManager logManager;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;
	
	
	@Resource
	StampManager stampManager;

	public PrintFileAction()
	{
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getPrinter_name()
	{
		return printer_name;
	}

	public void setPrinter_name(String printer_name)
	{
		this.printer_name = printer_name;
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

	public String getDataset_type()
	{
		return dataset_type;
	}

	public void setDataset_type(String dataset_type)
	{
		this.dataset_type = dataset_type;
	}

	public String getWaterm_name()
	{
		return waterm_name;
	}

	public void setWaterm_name(String waterm_name)
	{
		this.waterm_name = waterm_name;
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

	public String getWp()
	{
		return wp;
	}

	public void setWp(String wp)
	{
		this.wp = wp;
	}

	public String getPs()
	{
		return ps;
	}

	public void setPs(String ps)
	{
		this.ps = ps;
	}

	public String getPo()
	{
		return po;
	}

	public void setPo(String po)
	{
		this.po = po;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		//UserDataManager userDataManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		//LogDataManager logDataManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		File sourceFile = new File(URLDecoder.decode(filePath, "utf-8"));
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		String path = ServletActionContext.getServletContext().getRealPath("/");
		System.out.println((new StringBuilder("realpath:")).append(path).toString());
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
			waterm_name = URLDecoder.decode(waterm_name, "utf-8");
			Stamp stamp = stampManager.getStampByDisyName(waterm_name);
			fileManager.PrintFile(sourceFile, URLDecoder.decode(dataset_type, "utf-8"), path, URLDecoder.decode(printer_name, "utf-8"), stamp==null?null:stamp.getStamp_true_name(), user.getUser_name(), URLDecoder.decode(ps, "utf-8"), URLDecoder.decode(po, "utf-8"), URLDecoder.decode(wp, "utf-8"), department.getDepartment_name());
			flag = true;
			//User tlg = userDataManager.findUserByName(user.getName());
			FWLog log = new FWLog();
			log.setUser(user);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
			log.setAction_date(sdf.format(new Date()));
			log.setAction_type("打印");
			log.setItem_id(URLDecoder.decode(item_id, "utf-8"));
			log.setItem_name(URLDecoder.decode(item_name, "utf-8"));
			log.setItem_rev(URLDecoder.decode(item_rev, "utf-8"));
			logManager.saveFWLog(log);
		} else
		{
			flag = false;
		}
		return "success";
	}

	@Override
	public String toString() {
		return "PrintFileAction [nodePath=" + nodePath + ", filePath="
				+ filePath + ", printer_name=" + printer_name + ", flag="
				+ flag + ", dataset_type=" + dataset_type + ", waterm_name="
				+ waterm_name + ", item_id=" + item_id + ", item_name="
				+ item_name + ", item_rev=" + item_rev + ", ps=" + ps + ", po="
				+ po + ", wp=" + wp + ", departManager=" + departManager
				+ ", searchType=" + searchType + ", recordManager="
				+ recordManager + ", userDataManager=" + userDataManager
				+ ", logManager=" + logManager + ", fileManager="
				+ fileManager + "]";
	}
	
}
