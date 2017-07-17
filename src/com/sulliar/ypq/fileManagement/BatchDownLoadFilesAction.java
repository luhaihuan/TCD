// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BatchDownLoadFilesAction.java

package com.sulliar.ypq.fileManagement;

import com.fuwa.lhh.model.Stamp;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.LogManager;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWLog;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.*;
import com.sulliar.ypq.service.*;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

public class BatchDownLoadFilesAction extends ActionSupport
{

	boolean success;
	String path;
	String dsname;
	String items;
	String waterm_name;
	boolean overDownload;
	String wp;
	String message;
	private static final long serialVersionUID = 1L;
	@Resource
	DepartManager departManager;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;
	
	@Resource
	LogManager logManager;
	
	
	@Resource
	StampManager stampManager;

	public BatchDownLoadFilesAction()
	{
		message = "";
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getDsname()
	{
		return dsname;
	}

	public void setDsname(String dsname)
	{
		this.dsname = dsname;
	}

	public String getItems()
	{
		return items;
	}

	public void setItems(String items)
	{
		this.items = items;
	}

	public String getWaterm_name()
	{
		return waterm_name;
	}

	public void setWaterm_name(String waterm_name)
	{
		this.waterm_name = waterm_name;
	}

	public boolean isOverDownload()
	{
		return overDownload;
	}

	public void setOverDownload(boolean overDownload)
	{
		this.overDownload = overDownload;
	}

	public String getWp()
	{
		return wp;
	}

	public void setWp(String wp)
	{
		this.wp = wp;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String execute()
		throws Exception
	{
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		//UserDataManager userDataManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		//LogDataManager logDataManager = (LogDataManager)beanFactory.getBean("LogDataManager");
		System.out.println(toString());
		ActionContext context = ActionContext.getContext();
		FWUser user = (FWUser)context.getSession().get("current_user");
		//User tlg = userDataManager.findUserByName(user.getName());
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println((new StringBuilder("realpath:")).append(realpath).toString());
		List paths = new ArrayList();
		List logs = new ArrayList();
		waterm_name = URLDecoder.decode(waterm_name, "utf-8");
		//Stamp stamp = stampManager.getStampByDisyName(waterm_name);
		String[] tmpArray = waterm_name.split(",");
		List<Stamp> stampInArray = stampManager.getStampInArray(tmpArray);
		JSONArray jsonArray = JSONArray.fromObject(items);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			ItemModel item = (ItemModel)JSONObject.toBean(jsonObject, ItemModel.class);
			if (item.getCanDownload())
			{
				if (item.getFilePath() == null)
				{
					success = false;
					message = (new StringBuilder("Can't find ITEM NO:")).append(item.getItem_id()).append(" , DATASET:").append(item.getDataset_name()).append(", Please check!").toString();
					return "success";
				}
				File sourceFile = new File(item.getFilePath());
				if (sourceFile.exists())
				{
					Department department = departManager.getPersonDepartByUserId(user.getUuid());
					String itempath = fileManager.DownloadFile(sourceFile, item.getDataset_type(), realpath,stampInArray , user.getUser_name(), URLDecoder.decode(wp, "utf-8"), department.getDepartment_name(),false);
					File f = new File((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(itempath).toString());
					if (f.exists())
					{
						String suffix = itempath.substring(itempath.lastIndexOf("."), itempath.length());						
						
						String tfp = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(item.getItem_id()).append("_").append(item.getItem_revision()).append("_").append(item.getDataset_name().replace("/", "_")).append(item.getDataset_type()).append(sdf1.format(new Date())).append(suffix).toString();
						fileManager.Copy((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(itempath).toString(), tfp);
						if (!paths.contains(tfp))
							paths.add(tfp);
						FWLog log = new FWLog();
						log.setUser(user);
						log.setAction_date(sdf.format(new Date()));
						log.setAction_type("下载");
						log.setItem_id(item.getItem_id());
						log.setItem_name(item.getItem_name());
						log.setItem_rev(item.getItem_revision());
						if (!isContainsLog(logs, log))
							logs.add(log);
					}
				}
			}
		}

		dsname = (new StringBuilder("BatchDownload")).append(sdf1.format(new Date())).toString();
		String zipPath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(dsname).append(".zip").toString();
		byte buffer[] = new byte[1024];
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
		for (int i = 0; i < paths.size(); i++)
		{
			File tf = new File((String)paths.get(i));
			FileInputStream fis = new FileInputStream(tf);
			out.putNextEntry(new ZipEntry(tf.getName()));
			int len;
			while ((len = fis.read(buffer)) > 0) 
				out.write(buffer, 0, len);
			out.closeEntry();
			fis.close();
		}

		out.close();
		System.out.println("生成zip成功");
		path = (new StringBuilder(String.valueOf(dsname))).append(".zip").toString();
		for (Iterator iterator = logs.iterator(); iterator.hasNext();)
		{
			FWLog l = (FWLog)iterator.next();
			boolean flag = logManager.saveFWLog(l);
			if (flag)
				overDownload = flag;
		}

		success = true;
		return "success";
	}

	boolean isContainsLog(List logs, FWLog log)
	{
		for (Iterator iterator = logs.iterator(); iterator.hasNext();)
		{
			FWLog l = (FWLog)iterator.next();
			if (l.getItem_id().equals(log.getItem_id()) && l.getItem_rev().equals(log.getItem_rev()))
				return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "BatchDownLoadFilesAction [success=" + success + ", path="
				+ path + ", dsname=" + dsname + ", items=" + items
				+ ", waterm_name=" + waterm_name + ", overDownload="
				+ overDownload + ", wp=" + wp + ", message=" + message + "]";
	}

    
}
