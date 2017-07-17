// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ACLDataManagerService.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.ACLModel;
import com.sulliar.ypq.model.User;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.*;

// Referenced classes of package com.sulliar.ypq.service:
//			ACLDataManager, UserDataManager

public class ACLDataManagerService
	implements ACLDataManager
{

	SessionFactory sessionFactory;
	UserDataManager userDataManager;

	public ACLDataManagerService()
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

	public UserDataManager getUserDataManager()
	{
		return userDataManager;
	}

	public void setUserDataManager(UserDataManager userDataManager)
	{
		this.userDataManager = userDataManager;
	}

	public void add(ACLModel object)
	{
		sessionFactory.getCurrentSession().save(object);
	}

	public void modify(ACLModel object)
	{
		sessionFactory.getCurrentSession().update(object);
	}

	public void del(ACLModel object)
	{
		sessionFactory.getCurrentSession().delete(object);
	}

	public List findACLByType(String typeStr, int start, int limit)
	{
		String hql = "from ACLModel where typeStr = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, typeStr);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		return query.list();
	}

	public void delACLbyType(String typeStr)
	{
		String hql = "delete from ACLModel where typeStr = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, typeStr);
		query.executeUpdate();
	}

	public ACLModel findACLByTypeAndUser(String typeStr, String username)
	{
		String hql = "from ACLModel where typeStr = ? and username = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, typeStr);
		query.setString(1, username);
		List ms = query.list();
		if (ms.size() > 0)
			return (ACLModel)ms.get(0);
		else
			return null;
	}

	public int getTotalCount(String typeStr)
	{
		Query q = sessionFactory.getCurrentSession().createQuery("select count(*) from  ACLModel where typeStr = ?");
		q.setString(0, typeStr);
		List ll = q.list();
		Long a = (Long)ll.get(0);
		return a.intValue();
	}

	public List findACLByAttrs(String userStr, String groupStr, String siteStr, int start, int limit)
	{
		String hql = "select U.site as siteStr,U.bgroup as groupStr,A.username,A.typeStr,A.readWord,A.printWord,A.downloadWord,A.readExcel,A.printExcel,A.downloadExcel,A.readPDF,A.printPDF,A.downloadPDF from  ACLModel A, User U where A.username = U.name";
		List parmens = new ArrayList();
		if (userStr != null && userStr.length() > 0 && !userStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and A.username = ?").toString();
			parmens.add(userStr);
		}
		if (groupStr != null && groupStr.length() > 0 && !groupStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and U.bgroup = ?").toString();
			parmens.add(groupStr);
		}
		if (siteStr != null && siteStr.length() > 0 && !siteStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and U.site = ?").toString();
			parmens.add(siteStr);
		}
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < parmens.size(); i++)
			q.setString(i, (String)parmens.get(i));

		q.setFirstResult(start);
		q.setMaxResults(limit);
		List ll = q.list();
		return ll;
	}

	public int getTotalCount(String userStr, String groupStr, String siteStr)
	{
		String hql = "select count(*) from  ACLModel A, User U where A.username = U.name";
		List parmens = new ArrayList();
		if (userStr != null && userStr.length() > 0 && !userStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and A.username = ?").toString();
			parmens.add(userStr);
		}
		if (groupStr != null && groupStr.length() > 0 && !groupStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and U.bgroup = ?").toString();
			parmens.add(groupStr);
		}
		if (siteStr != null && siteStr.length() > 0 && !siteStr.equals("null"))
		{
			hql = (new StringBuilder(String.valueOf(hql))).append(" and U.site = ?").toString();
			parmens.add(siteStr);
		}
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < parmens.size(); i++)
			q.setString(i, (String)parmens.get(i));

		List ll = q.list();
		Long a = (Long)ll.get(0);
		return a.intValue();
	}

	public String ExportACLExcel(String realpath)
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filepath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/ACLDATA").append(sdf.format(new Date())).append(".xlsx").toString();
		String hqlDepart = "from ACLModel";
		Query q = sessionFactory.getCurrentSession().createQuery(hqlDepart);
		List acls = q.list();
		try
		{
			XSSFWorkbook wb = new XSSFWorkbook();
			SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
			Sheet sheet = swb.createSheet();
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setBorderLeft((short)1);
			cellStyle.setBorderBottom((short)1);
			cellStyle.setBorderRight((short)1);
			cellStyle.setBorderTop((short)1);
			Row row = sheet.createRow(0);
			Cell c0 = row.createCell(0);
			c0.setCellStyle(cellStyle);
			c0.setCellValue("User Site");
			Cell c1 = row.createCell(1);
			c1.setCellStyle(cellStyle);
			c1.setCellValue("User Group");
			Cell c2 = row.createCell(2);
			c2.setCellStyle(cellStyle);
			c2.setCellValue("User Name");
			Cell c3 = row.createCell(3);
			c3.setCellStyle(cellStyle);
			c3.setCellValue("Type Name");
			Cell c4 = row.createCell(4);
			c4.setCellStyle(cellStyle);
			c4.setCellValue("Preview Word");
			Cell c5 = row.createCell(5);
			c5.setCellStyle(cellStyle);
			c5.setCellValue("Print Word");
			Cell c6 = row.createCell(6);
			c6.setCellStyle(cellStyle);
			c6.setCellValue("Download Word");
			Cell c7 = row.createCell(7);
			c7.setCellStyle(cellStyle);
			c7.setCellValue("Preview Excel");
			Cell c8 = row.createCell(8);
			c8.setCellStyle(cellStyle);
			c8.setCellValue("Print Excel");
			Cell c9 = row.createCell(9);
			c9.setCellStyle(cellStyle);
			c9.setCellValue("Download Excel");
			Cell c10 = row.createCell(10);
			c10.setCellStyle(cellStyle);
			c10.setCellValue("Preview PDF");
			Cell c11 = row.createCell(11);
			c11.setCellStyle(cellStyle);
			c11.setCellValue("Print PDF");
			Cell c12 = row.createCell(12);
			c12.setCellStyle(cellStyle);
			c12.setCellValue("Download PDF");
			
			
			
			Cell c13 = row.createCell(11);
			c13.setCellStyle(cellStyle);
			c13.setCellValue("Search Bom");
			Cell c14 = row.createCell(12);
			c14.setCellStyle(cellStyle);
			c14.setCellValue("Search Material");
			Cell c15 = row.createCell(12);
			c15.setCellStyle(cellStyle);
			c15.setCellValue("Search Parent");
			int rc = 1;
			for (Iterator iterator = acls.iterator(); iterator.hasNext();)
			{
				ACLModel acl = (ACLModel)iterator.next();
				Row row1 = sheet.createRow(rc);
				String un = acl.getUsername();
				User user = userDataManager.findUserByName(un);
				if (user != null && user.getActived())
				{
					Cell c00 = row1.createCell(0);
					c00.setCellStyle(cellStyle);
					c00.setCellValue(user.getSite());
					Cell c01 = row1.createCell(1);
					c01.setCellStyle(cellStyle);
					c01.setCellValue(user.getBgroup());
					Cell c02 = row1.createCell(2);
					c02.setCellStyle(cellStyle);
					c02.setCellValue(un);
					Cell c03 = row1.createCell(3);
					c03.setCellStyle(cellStyle);
					c03.setCellValue(acl.getTypeStr());
					Cell c04 = row1.createCell(4);
					c04.setCellStyle(cellStyle);
					c04.setCellValue(getStringByBool(acl.isReadWord()));
					Cell c05 = row1.createCell(5);
					c05.setCellStyle(cellStyle);
					c05.setCellValue(getStringByBool(acl.isPrintWord()));
					Cell c06 = row1.createCell(6);
					c06.setCellStyle(cellStyle);
					c06.setCellValue(getStringByBool(acl.isDownloadWord()));
					Cell c07 = row1.createCell(7);
					c07.setCellStyle(cellStyle);
					c07.setCellValue(getStringByBool(acl.isReadExcel()));
					Cell c08 = row1.createCell(8);
					c08.setCellStyle(cellStyle);
					c08.setCellValue(getStringByBool(acl.isPrintExcel()));
					Cell c09 = row1.createCell(9);
					c09.setCellStyle(cellStyle);
					c09.setCellValue(getStringByBool(acl.isDownloadExcel()));
					Cell c010 = row1.createCell(10);
					c010.setCellStyle(cellStyle);
					c010.setCellValue(getStringByBool(acl.isReadPDF()));
					Cell c011 = row1.createCell(11);
					c011.setCellStyle(cellStyle);
					c011.setCellValue(getStringByBool(acl.isPrintPDF()));
					Cell c012 = row1.createCell(12);
					c012.setCellStyle(cellStyle);
					c012.setCellValue(getStringByBool(acl.isDownloadPDF()));
					
					
					Cell c013 = row1.createCell(13);
					c013.setCellStyle(cellStyle);
					c013.setCellValue(getStringByBool(acl.isBomsearch()));
					
					Cell c014 = row1.createCell(14);
					c014.setCellStyle(cellStyle);
					c014.setCellValue(getStringByBool(acl.isMaterialSearch()));
					
					Cell c015 = row1.createCell(15);
					c015.setCellStyle(cellStyle);
					c015.setCellValue(getStringByBool(acl.isParentSearch()));
					rc++;
				}
			}

			FileOutputStream fileOut = new FileOutputStream(filepath);
			swb.write(fileOut);
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		File f = new File(filepath);
		if (f.exists())
			return f.getName();
		else
			return "";
	}

	String getStringByBool(boolean flag)
	{
		if (flag)
			return "YES";
		else
			return "NO";
	}
}
