// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SaveACLbyTypeAction.java

package com.sulliar.ypq.aclManagement;

import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.ACLModel;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import java.net.URLDecoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.BeanFactory;

public class SaveACLbyTypeAction extends ActionSupport
{

	private boolean success;
	String ft;
	private String addModels;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;

	public SaveACLbyTypeAction()
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
		BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		ACLDataManager aclManager = (ACLDataManager)beanFactory.getBean("ACLDataManager");
		String ftStr = URLDecoder.decode(ft, "utf-8");
		aclManager.delACLbyType(ftStr);
		JSONArray jsonArray = JSONArray.fromObject(addModels);
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			ACLModel m = (ACLModel)JSONObject.toBean(jsonObject, ACLModel.class);
			String un = m.getUsername();
			if (un != null && un.length() != 0)
			{
				m.setTypeStr(ftStr);
				aclManager.add(m);
			}
		}

		success = true;
		return "success";
	}
}
