// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogDataManagerService.java

package com.sulliar.ypq.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.FWLog;
import com.sulliar.ypq.model.Log;
import com.sulliar.ypq.model.LogModel;
import com.sulliar.ypq.model.LogSetting;

// Referenced classes of package com.sulliar.ypq.service:
//			LogDataManager, LogSettingDataManager, UserDataManager, logFormt

public class LogDataManagerService
	implements LogDataManager
{

	SessionFactory sessionFactory;
	@Resource
	DepartManager departManager;
	
	@Resource(name = "LogSettingDataManager")
	LogSettingDataManager logSettingDataManager;
	
	@Resource
	UserManager userManager;

	public LogDataManagerService()
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

	public boolean add(Log object)
	{
		sessionFactory.getCurrentSession().save(object);
		boolean flag = false;
		if (object.getAction_type().equals("下载"))
		{
			//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
			//LogSettingDataManager logSettingDataManager = (LogSettingDataManager)beanFactory.getBean("LogSettingDataManager");
			LogSetting ls = logSettingDataManager.findLogSettingByType("每日最大下载数量");
			double day = Double.parseDouble(ls.getValue());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			Date now = new Date();
			String username = object.getUser().getName();
			String bdate = (new StringBuilder(String.valueOf(sdf.format(now)))).append("  00时00分").toString();
			String edate = (new StringBuilder(String.valueOf(sdf.format(now)))).append("  23时59分").toString();
			String hql = "select count(*) from Log WHERE action_date BETWEEN ? AND ? and user.name = ? and action_type = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter(0, bdate);
			query.setParameter(1, edate);
			query.setParameter(2, username);
			query.setParameter(3, "下载");
			List ll = query.list();
			Long a = (Long)ll.get(0);
			int dd = a.intValue();
			if ((double)dd > day)
			{
				flag = true;
				SendEmail(username, sdf.format(now));
			}
		}
		return flag;
	}

	private void SendEmail(String username, String date)
	{
/*		BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		UserDataManager userDataManager = (UserDataManager)beanFactory.getBean("UserDataManager");*/
		List emails = userManager.getAdminEmail();
		Properties props = new Properties();
		
	        props.setProperty("mail.store.protocol", "smtp"); 
	        props.setProperty("mail.smtp.host", "10.178.18.14"); 
	        props.setProperty("mail.smtp.port", "25");
	        props.setProperty("mail.smtp.socketFactory.port", "25");
		props.put("mail.smtp.auth","false");
		 Session session = Session.getInstance(props, new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(null, null);
	            }});
/*		MailSender sender = (MailSender)beanFactory.getBean("mailSender");*/
		for (Iterator iterator = emails.iterator(); iterator.hasNext();)
		{
			
			String email = (String)iterator.next();
			 String TO_EMAIL_ADDRESS=email;
			MimeMessage mailMessage = new MimeMessage(session);
			/*SimpleMailMessage mailMessage = (SimpleMailMessage)beanFactory.getBean("mailMessage");*/
			try {
				mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_EMAIL_ADDRESS));
				mailMessage.setSubject("系统提示");
				mailMessage.setText((new StringBuilder(String.valueOf(username))).append("用户").append(date).append("，单日下载量已经达到设定数量！").toString());
				Transport.send(mailMessage);
				System.out.println("feichuqu");
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				System.out.println((new StringBuilder("Email address '")).append(email).append("' is invalid!").toString());
			}
			
			
			

			
		}

	}
	public static void mailto(List<String> recipients, String subject,
	        String body) throws IOException, URISyntaxException {
	    String uriStr = String.format("mailto:%s?subject=%s&body=%s",
	            join(",", recipients), // use semicolon ";" for Outlook!
	            urlEncode(subject),
	            urlEncode(body));
	    Desktop.getDesktop().browse(new URI(uriStr));
	}

	private static final String urlEncode(String str) {
	    try {
	        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
	    } catch (UnsupportedEncodingException e) {
	        throw new RuntimeException(e);
	    }
	}

	public static final String join(String sep, Iterable<?> objs) {
	    StringBuilder sb = new StringBuilder();
	    for(Object obj : objs) {
	        if (sb.length() > 0) sb.append(sep);
	        sb.append(obj);
	    }
	    return sb.toString();
	}

	public List getAllLogs(int start, int limit)
	{
		List logs = new ArrayList();
		String hql = "from FWLog ORDER BY action_date DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List ls = query.list();
		LogModel lm;
		for (Iterator iterator = ls.iterator(); iterator.hasNext(); logs.add(lm))
		{
			Log log = (Log)iterator.next();
			lm = new LogModel();
			lm.setAction_date(log.getAction_date());
			lm.setAction_type(log.getAction_type());
			lm.setItem_id(log.getItem_id());
			lm.setItem_name(log.getItem_name());
			lm.setItem_rev(log.getItem_rev());
			lm.setUser_name(log.getUser().getName());
			
			//Department department = departManager.getPersonDepartByUserId(log.getFwUser().getUuid());
			lm.setUser_group(log.getUser().getBgroup());
		}

		return logs;
	}

	public int getTotalCount(String tablename)
	{
		Query q = sessionFactory.getCurrentSession().createQuery((new StringBuilder("select count(*) from  ")).append(tablename).toString());
		List ll = q.list();
		Long a = (Long)ll.get(0);
		return a.intValue();
	}

	public String exportLogByGroup(Date from_date, Date to_date, String group, String realpath)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String fdstr = "";
		String tdstr = "";
		if (from_date == null)
			fdstr = "1970年01月01日  00时00分";
		else
			fdstr = sdf.format(from_date);
		if (to_date == null)
			tdstr = "2099年12月31日  00时00分";
		else
			tdstr = sdf.format(to_date);
		String hql1 = "from Log WHERE action_date BETWEEN ? AND ? and user.bgroup like ? order by item_id,action_date";
		String hql2 = "from Log WHERE action_date BETWEEN ? AND ? and user.bgroup like ? order by user.name,action_date";
		if (group == null || group.trim().length() == 0)
			group = "%%";
		Query q1 = sessionFactory.getCurrentSession().createQuery(hql1);
		q1.setParameter(0, fdstr);
		q1.setParameter(1, tdstr);
		q1.setParameter(2, group);
		List logs1 = q1.list();
		Query q2 = sessionFactory.getCurrentSession().createQuery(hql2);
		q2.setParameter(0, fdstr);
		q2.setParameter(1, tdstr);
		q2.setParameter(2, group);
		List logs2 = q2.list();
		String filepath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/按部门查询").append(sdf1.format(new Date())).append(".xlsx").toString();
		try
		{
			FileInputStream fileIn = new FileInputStream((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/查询输出报表模板(按部门).xlsx").toString());
			XSSFWorkbook wb = new XSSFWorkbook(fileIn);
			SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
			Sheet sheet1 = swb.getSheetAt(0);
			Sheet sheet2 = swb.getSheetAt(1);
			exportLogFormatByItemid(logs1, sheet2);
			exportLogFormatByUser(logs2, sheet1);
			FileOutputStream fileOut = new FileOutputStream(filepath);
			swb.write(fileOut);
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		File file = new File(filepath);
		if (file.exists())
		{
			System.out.println("exportLogByGroup ok");
			return file.getName();
		} else
		{
			System.out.println("exportLogByGroup filepath null");
			return null;
		}
	}

	private void exportLogFormatByUser(List logs1, Sheet sheet)
	{
		List lfs = new ArrayList();
		for (Iterator iterator = logs1.iterator(); iterator.hasNext();)
		{
			Log log = (Log)iterator.next();
			String username = log.getUser().getName();
			String itemid = log.getItem_id();
			logFormt lf = findInLogFormat(lfs, itemid, username);
			if (lf != null)
			{
				if (log.getAction_type().equals("预览"))
				{
					lf.setLastPreviewTime(log.getAction_date());
					lf.setPreviewTimes(lf.getPreviewTimes() + 1);
				} else
				if (log.getAction_type().equals("打印"))
				{
					lf.setLastPrintTime(log.getAction_date());
					lf.setPrintTimes(lf.getPrintTimes() + 1);
				} else
				if (log.getAction_type().equals("下载"))
				{
					lf.setLastDownloadTime(log.getAction_date());
					lf.setDownloadTimes(lf.getDownloadTimes() + 1);
				}
			} else
			if (log.getAction_type().equals("预览"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastPreviewTime(log.getAction_date());
				lf1.setPreviewTimes(1);
				lf1.setItemname(log.getItem_name());
				lfs.add(lf1);
			} else
			if (log.getAction_type().equals("打印"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastPrintTime(log.getAction_date());
				lf1.setPrintTimes(1);
				lf1.setItemname(log.getItem_name());
				lfs.add(lf1);
			} else
			if (log.getAction_type().equals("下载"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastDownloadTime(log.getAction_date());
				lf1.setDownloadTimes(1);
				lf1.setItemname(log.getItem_name());
				lfs.add(lf1);
			}
		}

		Workbook wb = sheet.getWorkbook();
		CellStyle cs5 = wb.createCellStyle();
		
		cs5.setBorderLeft((short)1);
		cs5.setBorderBottom((short)1);
		cs5.setBorderRight((short)1);
		cs5.setBorderTop((short)1);
		int r = 2;
		for (Iterator iterator1 = lfs.iterator(); iterator1.hasNext();)
		{
			logFormt lf = (logFormt)iterator1.next();
			Row row = sheet.createRow(r);
			for (int i = 0; i < 10; i++)
			{
				Cell c00 = row.createCell(i);
				c00.setCellStyle(cs5);
			}

			sheet.addMergedRegion(new CellRangeAddress(r, r, 0, 1));
			Cell c0 = row.getCell(0);
			c0.setCellValue(lf.getUsername());
			Cell c1 = row.getCell(2);
			c1.setCellValue(lf.getItemid());
			Cell c11 = row.getCell(3);
			c11.setCellValue(lf.getItemname());
			Cell c2 = row.getCell(4);
			c2.setCellValue(lf.getLastPreviewTime());
			Cell c3 = row.getCell(5);
			c3.setCellValue(lf.getPreviewTimes());
			Cell c4 = row.getCell(6);
			c4.setCellValue(lf.getLastDownloadTime());
			Cell c5 = row.getCell(7);
			c5.setCellValue(lf.getDownloadTimes());
			Cell c6 = row.getCell(8);
			c6.setCellValue(lf.getLastPrintTime());
			Cell c7 = row.getCell(9);
			c7.setCellValue(lf.getPrintTimes());
			r++;
		}

	}

	private void exportLogFormatByItemid(List logs1, Sheet sheet)
	{
		List lfs = new ArrayList();
		for (Iterator iterator = logs1.iterator(); iterator.hasNext();)
		{
			Log log = (Log)iterator.next();
			String username = log.getUser().getName();
			String itemid = log.getItem_id();
			logFormt lf = findInLogFormat(lfs, itemid, username);
			if (lf != null)
			{
				if (log.getAction_type().equals("预览"))
				{
					lf.setLastPreviewTime(log.getAction_date());
					lf.setPreviewTimes(lf.getPreviewTimes() + 1);
				} else
				if (log.getAction_type().equals("打印"))
				{
					lf.setLastPrintTime(log.getAction_date());
					lf.setPrintTimes(lf.getPrintTimes() + 1);
				} else
				if (log.getAction_type().equals("下载"))
				{
					lf.setLastDownloadTime(log.getAction_date());
					lf.setDownloadTimes(lf.getDownloadTimes() + 1);
				}
			} else
			if (log.getAction_type().equals("预览"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastPreviewTime(log.getAction_date());
				lf1.setPreviewTimes(1);
				lfs.add(lf1);
			} else
			if (log.getAction_type().equals("打印"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastPrintTime(log.getAction_date());
				lf1.setPrintTimes(1);
				lfs.add(lf1);
			} else
			if (log.getAction_type().equals("下载"))
			{
				logFormt lf1 = new logFormt();
				lf1.setItemid(itemid);
				lf1.setUsername(username);
				lf1.setLastDownloadTime(log.getAction_date());
				lf1.setDownloadTimes(1);
				lfs.add(lf1);
			}
		}

		Workbook wb = sheet.getWorkbook();
		CellStyle cs5 = wb.createCellStyle();
		cs5.setBorderLeft((short)1);
		cs5.setBorderBottom((short)1);
		cs5.setBorderRight((short)1);
		cs5.setBorderTop((short)1);
		int r = 2;
		for (Iterator iterator1 = lfs.iterator(); iterator1.hasNext();)
		{
			logFormt lf = (logFormt)iterator1.next();
			Row row = sheet.createRow(r);
			for (int i = 0; i < 9; i++)
			{
				Cell c00 = row.createCell(i);
				c00.setCellStyle(cs5);
			}

			sheet.addMergedRegion(new CellRangeAddress(r, r, 0, 1));
			Cell c0 = row.getCell(0);
			c0.setCellValue(lf.getItemid());
			Cell c1 = row.getCell(2);
			c1.setCellValue(lf.getUsername());
			Cell c2 = row.getCell(3);
			c2.setCellValue(lf.getLastPreviewTime());
			Cell c3 = row.getCell(4);
			c3.setCellValue(lf.getPreviewTimes());
			Cell c4 = row.getCell(5);
			c4.setCellValue(lf.getLastDownloadTime());
			Cell c5 = row.getCell(6);
			c5.setCellValue(lf.getDownloadTimes());
			Cell c6 = row.getCell(7);
			c6.setCellValue(lf.getLastPrintTime());
			Cell c7 = row.getCell(8);
			c7.setCellValue(lf.getPrintTimes());
			r++;
		}

	}

	private logFormt findInLogFormat(List lfs, String itemid, String username)
	{
		for (Iterator iterator = lfs.iterator(); iterator.hasNext();)
		{
			logFormt lf = (logFormt)iterator.next();
			if (lf.getUsername().equals(username) && lf.getItemid().equals(itemid))
				return lf;
		}

		return null;
	}

	public String exportLogByItem(Date from_date, Date to_date, String itemid, String realpath)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String fdstr = "";
		String tdstr = "";
		if (from_date == null)
			fdstr = "1970年01月01日  00时00分";
		else
			fdstr = sdf.format(from_date);
		if (to_date == null)
			tdstr = "2099年12月31日  00时00分";
		else
			tdstr = sdf.format(to_date);
		String hql1 = "from Log WHERE action_date BETWEEN ? AND ? and item_id like ? order by item_id,action_date";
		if (itemid == null || itemid.trim().length() == 0)
			itemid = "%%";
		Query q1 = sessionFactory.getCurrentSession().createQuery(hql1);
		q1.setParameter(0, fdstr);
		q1.setParameter(1, tdstr);
		q1.setParameter(2, itemid);
		List logs1 = q1.list();
		String filepath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/按ITEM查询").append(sdf1.format(new Date())).append(".xlsx").toString();
		try
		{
			FileInputStream fileIn = new FileInputStream((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/查询输出报表模板(按ITEM).xlsx").toString());
			XSSFWorkbook wb = new XSSFWorkbook(fileIn);
			SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
			Sheet sheet1 = swb.getSheetAt(0);
			exportLogFormatByItemid(logs1, sheet1);
			FileOutputStream fileOut = new FileOutputStream(filepath);
			swb.write(fileOut);
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		File file = new File(filepath);
		if (file.exists())
		{
			System.out.println("exportLogByGroup ok");
			return file.getName();
		} else
		{
			System.out.println("exportLogByGroup filepath null");
			return null;
		}
	}

	public String exportLogByUser(Date from_date, Date to_date, String username, String realpath)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String fdstr = "";
		String tdstr = "";
		if (from_date == null)
			fdstr = "1970年01月01日  00时00分";
		else
			fdstr = sdf.format(from_date);
		if (to_date == null)
			tdstr = "2099年12月31日  00时00分";
		else
			tdstr = sdf.format(to_date);
		String hql2 = "from Log WHERE action_date BETWEEN ? AND ? and user.name like ? order by user.name,action_date";
		if (username == null || username.trim().length() == 0)
			username = "%%";
		Query q2 = sessionFactory.getCurrentSession().createQuery(hql2);
		q2.setParameter(0, fdstr);
		q2.setParameter(1, tdstr);
		q2.setParameter(2, username);
		List logs2 = q2.list();
		String filepath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/按用户查询").append(sdf1.format(new Date())).append(".xlsx").toString();
		try
		{
			FileInputStream fileIn = new FileInputStream((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/查询输出报表模板(按用户).xlsx").toString());
			XSSFWorkbook wb = new XSSFWorkbook(fileIn);
			SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
			Sheet sheet1 = swb.getSheetAt(0);
			exportLogFormatByUser(logs2, sheet1);
			FileOutputStream fileOut = new FileOutputStream(filepath);
			swb.write(fileOut);
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		File file = new File(filepath);
		if (file.exists())
		{
			System.out.println("exportLogByGroup ok");
			return file.getName();
		} else
		{
			System.out.println("exportLogByGroup filepath null");
			return null;
		}
	}

	@Override
	public void add(FWLog log) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(log);
	}
}
