// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogSettingDataManagerService.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.LogSetting;
import java.util.List;
import org.hibernate.*;

// Referenced classes of package com.sulliar.ypq.service:
//			LogSettingDataManager

public class LogSettingDataManagerService
	implements LogSettingDataManager
{

	SessionFactory sessionFactory;

	public LogSettingDataManagerService()
	{
	}

	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public void add(LogSetting object)
	{
		sessionFactory.getCurrentSession().save(object);
	}

	public void modify(LogSetting object)
	{
		sessionFactory.getCurrentSession().update(object);
	}

	public void del(LogSetting object)
	{
		sessionFactory.getCurrentSession().delete(object);
	}

	public List findAllLogSetting()
	{
		String hql = "from LogSetting";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public LogSetting findLogSettingByType(String type)
	{
		String hql = "from LogSetting where type = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, type);
		List ms = query.list();
		if (ms.size() > 0)
			return (LogSetting)ms.get(0);
		else
			return null;
	}
}
