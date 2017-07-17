// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UserDataManagerService.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.*;
import java.util.*;
import org.hibernate.*;

// Referenced classes of package com.sulliar.ypq.service:
//			UserDataManager

public class UserDataManagerService
	implements UserDataManager
{

	SessionFactory sessionFactory;
	String siteStr;
	String groupStr;

	public UserDataManagerService()
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

	public String getSiteStr()
	{
		return siteStr;
	}

	public void setSiteStr(String siteStr)
	{
		this.siteStr = siteStr;
	}

	public String getGroupStr()
	{
		return groupStr;
	}

	public void setGroupStr(String groupStr)
	{
		this.groupStr = groupStr;
	}

	public User findUserByName(String username)
	{
		User user = null;
		String hql = "from User where name = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, username);
		List users = query.list();
		if (users.size() > 0)
			user = (User)users.get(0);
		return user;
	}

	public void add(User object)
	{
		sessionFactory.getCurrentSession().save(object);
	}

	public void modify(User object)
	{
		sessionFactory.getCurrentSession().update(object);
	}

	public void del(User object)
	{
		sessionFactory.getCurrentSession().delete(object);
	}

	public List getAllUsers(int start, int limit)
	{
		List users = new ArrayList();
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		users = query.list();
		return users;
	}

	public List getActivedUsers(int start, int limit)
	{
		List users = new ArrayList();
		String hql = "from User where actived = 1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		users = query.list();
		return users;
	}

	public void delAllUsers()
	{
		String hql = "delete from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	public List getAllSite()
	{
		List ms = new ArrayList();
		String ss[] = siteStr.split(",");
		if (ss != null && ss.length > 0)
		{
			String as[];
			int j = (as = ss).length;
			for (int i = 0; i < j; i++)
			{
				String s = as[i];
				Model m = new Model();
				m.setName(s);
				ms.add(m);
			}

		}
		return ms;
	}

	public List getAllGroup()
	{
		List ms = new ArrayList();
		String ss[] = groupStr.split(",");
		if (ss != null && ss.length > 0)
		{
			String as[];
			int j = (as = ss).length;
			for (int i = 0; i < j; i++)
			{
				String s = as[i];
				Model m = new Model();
				m.setName(s);
				ms.add(m);
			}

		}
		return ms;
	}

	public List getAdminEmail()
	{
		List emails = new ArrayList();
		String hql = "from User where isAdmin = 1 and actived = 1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List l = query.list();
		for (Iterator iterator = l.iterator(); iterator.hasNext();)
		{
			User user = (User)iterator.next();
			if (!emails.contains(user.getEmail()) && user.getEmail() != null)
				emails.add(user.getEmail());
		}

		return emails;
	}

	public int getActivedCount()
	{
		Query q = sessionFactory.getCurrentSession().createQuery("select count(*) from  User where  actived = 1");
		List ll = q.list();
		Long a = (Long)ll.get(0);
		return a.intValue();
	}

	public List getTypeByUser(String username)
	{
		String hql = "from ACLModel where username = ? order by typeStr";
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		q.setString(0, username);
		List acls = q.list();
		List ms = new ArrayList();
		for (Iterator iterator = acls.iterator(); iterator.hasNext();)
		{
			ACLModel acl = (ACLModel)iterator.next();
			if (acl.isReadExcel() || acl.isReadWord() || acl.isReadPDF() || acl.isPrintExcel() || acl.isPrintWord() || acl.isPrintPDF() || acl.isDownloadExcel() || acl.isDownloadWord() || acl.isDownloadPDF())
			{
				Model m = new Model();
				m.setName(acl.getTypeStr());
				ms.add(m);
			}
		}

		return ms;
	}
}
